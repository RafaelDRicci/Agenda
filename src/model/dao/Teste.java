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
import util.date.Data;

/**
 *
 * @author rafaeld
 */
public class Teste {
    public static void main(String[] args) throws SQLException {
        
        
        RotinaDAO rotinaDao = new RotinaDAO();
        Rotina rotina = rotinaDao.read(2);
        List<Usuario> usuarios = rotinaDao.usuarioVinculados(rotina);
        
        usuarios.forEach(usuario -> {
            System.out.println("***********************************");
            System.out.println(rotina);
            System.out.println(usuario);
            System.out.println(usuario.getCargo());
            System.out.println(usuario.getUnidade());
            
        });
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        Usuario usuario = new Usuario();
        usuario.setCodUsuario(2);
        List<Rotina> rotinas = usuarioDao.listarRotinasVinculadas(usuario);
        rotinas.forEach(rotina1 -> {
            System.out.println("*********************************");
            System.out.println(rotina1);
        });
        
        
        System.out.println("");
        GregorianCalendar calendar1 = new GregorianCalendar(2021, 8, 28);
        Date date = calendar1.getTime();
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(date.getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy EEEE");
        System.out.println(sdf.format(calendar.getTime()));
    }
   
}
