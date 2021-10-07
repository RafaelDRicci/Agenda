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
public class TestCreate {
    
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
        
        Anual anual = new Anual(rotina1, usuario1);
        anual.setDia(5);
        anual.setMes(8);
        vincularDAO.create(anual);
        
        DataUnica dataUnica = new DataUnica(rotina2, usuario2);
        dataUnica.setData(new GregorianCalendar(2022, 11, 25));
        vincularDAO.create(dataUnica);
        
        DatasAno datasAno = new DatasAno(rotina3, usuario3);
        datasAno.setDia(5);
        int[] meses = {5, 6, 7};
        datasAno.setMeses(meses);
        vincularDAO.create(datasAno);
        
        Diario diario = new Diario(rotina4, usuario4);
        vincularDAO.create(diario);
        
        DiasMes diasMes = new DiasMes(rotina5, usuario5);
        int[] dias = {1, 10, 20};
        diasMes.setDias(dias);
        vincularDAO.create(diasMes);
        
        DiasUteis diasUteis = new DiasUteis(rotina6, usuario6);
        diasUteis.setDiasUteis(dias);
        vincularDAO.create(diasUteis);
        
        Semanal semanal = new Semanal(rotina7, usuario7);
        int[] diasSemana = {5};
        semanal.setDiasSemana(diasSemana);
        vincularDAO.create(semanal);
    }
    
}
