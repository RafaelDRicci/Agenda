/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import model.VincularRotina;
import util.connection.database.ConnectionFactory;

/**
 *
 * @author rafaeld
 */
public abstract class VincularRotinaDAO<T> {
    
    protected Connection con;

    public VincularRotinaDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public abstract void create(T vincular) throws SQLException;
    
    public abstract T read(int codRotina, int codUsuario) throws SQLException;
    
    public abstract void upadate() throws SQLException;
    
    public abstract void delete(T vincular) throws SQLException;
    
    public abstract List<T> list() throws SQLException;
    
    protected List<Usuario> usuariosVinculados(VincularRotina vincular) throws SQLException{
        List<Usuario> usuarios = new ArrayList<>();
        
        String sql = "Select * From Usuario where CODUSUAIOR = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            Usuario usuario = new Usuario();
            usuario.setnControle(rs.getInt(sql));
        }
        
        return usuarios;
    }
    
 
}