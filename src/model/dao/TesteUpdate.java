/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import model.Rotina;
import model.Usuario;
import model.vincularrotina.Anual;
import model.vincularrotina.DataUnica;
import model.vincularrotina.DatasAno;
import model.vincularrotina.Diario;
import model.vincularrotina.DiasMes;
import model.vincularrotina.DiasUteis;
import model.vincularrotina.Semanal;

/**
 *
 * @author rafaeld
 */
public class TesteUpdate {
    public static void main(String[] args) throws SQLException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RotinaDAO rotinaDAO = new RotinaDAO();
        VincularRotinaDAO vincularDAO = new VincularRotinaDAO();
        
        Usuario usuario1 = usuarioDAO.read(121);
        Usuario usuario2 = usuarioDAO.read(122);
        Usuario usuario3 = usuarioDAO.read(129);
        Usuario usuario4 = usuarioDAO.read(130);
        Usuario usuario5 = usuarioDAO.read(125);
        Usuario usuario6 = usuarioDAO.read(126);
        Usuario usuario7 = usuarioDAO.read(132);
        
        Rotina rotina1 = rotinaDAO.read(2);
        Rotina rotina2 = rotinaDAO.read(3);
        Rotina rotina3 = rotinaDAO.read(4);
        Rotina rotina4 = rotinaDAO.read(5);
        Rotina rotina5 = rotinaDAO.read(6);
        Rotina rotina6 = rotinaDAO.read(7);
        Rotina rotina7 = rotinaDAO.read(8);
        
        Anual anual = (Anual) vincularDAO.read(rotina1, usuario1);
        DataUnica dataUnica = (DataUnica) vincularDAO.read(rotina2, usuario2);
        DatasAno datasAno = (DatasAno) vincularDAO.read(rotina3, usuario3);
        Diario diario = (Diario) vincularDAO.read(rotina4, usuario4);
        DiasMes diasMes = (DiasMes) vincularDAO.read(rotina5, usuario5); 
        DiasUteis diasUteis = (DiasUteis) vincularDAO.read(rotina6, usuario6);
        Semanal semanal = (Semanal) vincularDAO.read(rotina7, usuario7);
        
        anual.setDia(6);
        anual.setMes(6);
        int[] horarios = {7,8,9};
        anual.setHorarios(horarios);
        anual.setPrioritario(true);
        anual.setReagendavel(true);
        anual.setHorarioFixo(true);
        vincularDAO.update(anual);
        
        GregorianCalendar data = new GregorianCalendar(2022, 10, 25);
        dataUnica.setData(data);
        dataUnica.setPrioritario(true);
        dataUnica.setReagendavel(true);
        dataUnica.setHorarioFixo(true);
        dataUnica.setHorarios(horarios);
        vincularDAO.update(dataUnica);
        
        datasAno.setDia(8);
        int[] meses = {3, 9, 12};
        datasAno.setMeses(meses);
        datasAno.setPrioritario(true);
        datasAno.setReagendavel(true);
        datasAno.setHorarioFixo(true);
        datasAno.setHorarios(horarios);
        vincularDAO.update(datasAno);
        
        diario.setPrioritario(true);
        diario.setReagendavel(true);
        diario.setHorarioFixo(true);
        diario.setHorarios(horarios);
        vincularDAO.update(diario);
        
        int[] dias = {10, 20};
        diasMes.setDias(dias);
        diasMes.setPrioritario(true);
        diasMes.setReagendavel(true);
        diasMes.setHorarioFixo(true);
        diasMes.setHorarios(horarios);
        vincularDAO.update(diasMes);
        
        int[] uteis = {5, 10};
        diasUteis.setDiasUteis(uteis);
        diasUteis.setPrioritario(true);
        diasUteis.setReagendavel(true);
        diasUteis.setHorarioFixo(true);
        diasUteis.setHorarios(horarios);
        vincularDAO.update(diasUteis);
        
        int[] diasSemana = {2, 3, 5};
        semanal.setDiasSemana(diasSemana);
        semanal.setPrioritario(true);
        semanal.setReagendavel(true);
        semanal.setHorarioFixo(true);
        semanal.setHorarios(horarios);
        vincularDAO.update(semanal);
    }
}
