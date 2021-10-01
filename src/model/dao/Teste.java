/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import model.dao.vincularrotinadao.AnualDAO;
import util.date.Data;

/**
 *
 * @author rafaeld
 */
public class Teste {
    public static void main(String[] args) throws SQLException {
        
        RotinaDAO dao = new RotinaDAO();
        UsuarioDAO uDao = new UsuarioDAO();
        Usuario u = uDao.read(120);
        
        System.out.println(u);
        
        List<Usuario> usuarios = uDao.buscarPorUnidade("ADMINISTRAÇÃO");
        
        usuarios.forEach(usuario -> {
            System.out.println("*****************************");
            System.out.println(usuario);
        });
    }
   
}
