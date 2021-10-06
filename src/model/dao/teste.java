/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import model.Agendamento;
import model.Rotina;
import model.Usuario;
import model.dao.vincularrotinadao.DataUnicaDAO;
import model.vincularrotina.DataUnica;

/**
 *
 * @author rafaeld
 */
public class teste {
    public static void main(String[] args) throws SQLException {
        
        RotinaDAO rDAO = new RotinaDAO();
        Rotina rotina = rDAO.read(2);
        
        UsuarioDAO uDAO = new UsuarioDAO();
        Usuario usuario = uDAO.read(135);
        
        DataUnicaDAO dtDAO = new DataUnicaDAO();
        DataUnica dataUnica = dtDAO.read(rotina, usuario);
        
        AgendamentoDAO agendamentoDAO = new AgendamentoDAO();
        
        
        
        Agendamento agendamento = new Agendamento(1, dataUnica, dataUnica.getData());
        agendamento.setEstado("A FAZER");
        agendamento.setJustificativa("A FAZER");
        agendamento.setDescricaoPadrao();
        
        System.out.println(agendamento.getDescricao());
        
        List<Agendamento> lista = agendamentoDAO.listAll();
        lista.forEach(l -> {
            System.out.println(l);
        });
    }
}
