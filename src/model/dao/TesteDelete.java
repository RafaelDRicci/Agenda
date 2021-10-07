/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import model.Rotina;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class TesteDelete {
    
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
        
        
        vincularDAO.delete(vincular);
    }
    
}
