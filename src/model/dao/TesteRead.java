/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class TesteRead {
    public static void main(String[] args) throws SQLException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        RotinaDAO rotinaDAO = new RotinaDAO();
        
        Usuario usuario = usuarioDAO.read(2);
        
    }
}
