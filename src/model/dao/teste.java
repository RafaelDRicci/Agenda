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
import model.dao.vincularrotinadao.SemanalDAO;
import model.vincularrotina.DataUnica;
import model.vincularrotina.Semanal;

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
        
        DataUnicaDAO duDAO = new DataUnicaDAO();
        DataUnica dataUnica = duDAO.read(rotina, usuario);
        
        AgendamentoDAO aDAO = new AgendamentoDAO();
        Agendamento agendamento = aDAO.read(1, dataUnica);
        agendamento.setEstado("EXPIRADO");
        agendamento.setJustificativa("BANCO DE HORAS");
        agendamento.setDescricaoPadrao();
        aDAO.update(agendamento);
        
        
        Rotina rotina2 = rDAO.read(3);
        Usuario usuario2 = uDAO.read(125);
        SemanalDAO sDAO = new SemanalDAO();
        Semanal semanal = sDAO.read(rotina2, usuario2);
        int[] diasSemana = {5};
        semanal.setDiasSemana(diasSemana);
        int[] horarios = {14, 15};
        semanal.setHorarios(horarios);
        sDAO.update(semanal);
        
        Agendamento agendamento2 = aDAO.read(4, semanal);
        agendamento2.setEstado("A FAZER");
        agendamento2.setJustificativa("A FAZER");
        agendamento2.setHoraInicio(agendamento2.getVincularRotina().getHorarios()[0]);
        agendamento2.setHoraFinal(agendamento2.getVincularRotina().getHorarios()[agendamento2.getVincularRotina().getHorarios().length-1]);
        agendamento2.setDescricaoPadrao();
        aDAO.update(agendamento2);

        
        
        
        List<Agendamento> lista = aDAO.listAll();
        lista.forEach(l -> {
            System.out.println(l.getDescricao());
        });
    }
}
