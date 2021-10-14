/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import model.Agendamento;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class AgendamentoDAOTest {
    
    final AgendamentoDAO agendamentoDAO;
    final RotinaDAO rotinaDAO;
    final UsuarioDAO usuarioDAO;
    final VincularRotinaDAO vincularRotinaDAO;
    
   
     public AgendamentoDAOTest() {
        agendamentoDAO = new AgendamentoDAO();
        rotinaDAO = new RotinaDAO();
        usuarioDAO = new UsuarioDAO();
        vincularRotinaDAO = new VincularRotinaDAO();
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void testRead_int() throws Exception {
        agendamentoDAO.read(3);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRead_Rotina_Usuario() throws Exception {
        final Rotina rotina = new Rotina("Rotina");
        final Usuario usuario = new Usuario();
        agendamentoDAO.read(rotina, usuario);
    }

    @Test
    public void testRotina() throws SQLException{
        
        final Rotina rotina1 = rotinaDAO.read(2);
        final Usuario usuario1 = usuarioDAO.read(126);
        final VincularRotina vinculacao1 = vincularRotinaDAO.read(rotina1, usuario1);
        
        Agendamento agendamento1 = new Agendamento(vinculacao1, new GregorianCalendar(2022, 7, 5));
        agendamento1.setEstado("A FAZER");
        agendamento1.setDiaUtil(true);
        agendamento1.setJustificativa("");
        agendamento1.setDescricaoPadrao();
        
        Agendamento agendamento2 = new Agendamento(vinculacao1, new GregorianCalendar(2023, 7, 5));
        agendamento2.setEstado("A FAZER");
        agendamento2.setDiaUtil(true);
        agendamento2.setJustificativa("");
        agendamento2.setDescricaoPadrao();
        
        agendamentoDAO.create(agendamento1);
        agendamentoDAO.create(agendamento2);
        
        Agendamento agendamento1Salvo = agendamentoDAO.listAgendamentos(vinculacao1).get(0);
        Agendamento agendamento2Salvo = agendamentoDAO.listAgendamentos(vinculacao1).get(1);
        
        System.out.println(agendamento1Salvo.getDescricao());
        System.out.println(agendamento2Salvo.getDescricao());

        agendamento1Salvo.setHoraInicio(7);
        agendamento1Salvo.setHoraFinal(9);
        agendamento1Salvo.setDescricaoPadrao();
        
        agendamento2Salvo.setHoraInicio(7);
        agendamento2Salvo.setHoraFinal(9);
        agendamento2Salvo.setDescricaoPadrao();
                
        agendamentoDAO.update(agendamento1Salvo);
        agendamentoDAO.update(agendamento2Salvo);

        Agendamento agendamento1At = agendamentoDAO.read(agendamento1Salvo.getCodAgendamento(), vinculacao1);
        Agendamento agendamento2At = agendamentoDAO.read(agendamento2Salvo.getCodAgendamento(), vinculacao1);
        
        System.out.println(agendamento1At.getDescricao());
        System.out.println(agendamento2At.getDescricao()); 
        
        agendamentoDAO.delete(agendamento1At);
        agendamentoDAO.delete(agendamento2At);
        
    }
    
}
