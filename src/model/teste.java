/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.dao.RotinaDAO;
import model.dao.UsuarioDAO;
import model.dao.VincularRotinaDAO;
import model.dao.vincularrotinadao.DataUnicaDAO;
import model.vincularrotina.DataUnica;
        

/**
 *
 * @author rafaeld
 */
public class teste {
    public static void main(String[] args) throws SQLException {
        
        DataUnicaDAO vcDAO = new DataUnicaDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RotinaDAO rotinaDAO = new RotinaDAO();
        
        Usuario usuario = usuarioDAO.read(135);
        Rotina rotina = rotinaDAO.read(2);
        
        /*
        DataUnica vincular = new DataUnica(rotina, usuario);
        vincular.setReagendavel(true);
       
        Calendar calendar = new GregorianCalendar();
        vincular.setData(calendar);
        
        int[] horarios = {7, 8, 9};
        vincular.setHorarios(horarios);
        vcDAO.create(vincular);
        
       */
        
       DataUnica dataUnica = vcDAO.read(rotina, usuario);
       System.out.println(dataUnica.getData().getTime());
       
       

    }
}
