/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public abstract class VincularRotinaDAO<T> extends GenericDAO{
    

    public void delete(VincularRotina vincular) throws SQLException {
        
        String sql = "Delete from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getRotina().getCodRotina());
        stm.setInt(2, vincular.getUsuario().getCodUsuario());
        stm.execute();
        stm.close();
    }
    
    @Override
    public List<VincularRotina> listAll() throws SQLException {
        List<VincularRotina> lista = new ArrayList<>();
        
        String sql = "Select * from AGENDA_VINCULARROTINA";
        
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            
            RotinaDAO rotinaDAO = new RotinaDAO();
            Rotina rotina = rotinaDAO.read(rs.getInt("CODROTINA")); 
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.read(rs.getInt("CODUSUARIO"));
            
            rotinaDAO.closeConnection();
            usuarioDAO.closeConnection();
            
            VincularRotina vincular = new VincularRotina(rotina, usuario);
            vincular.setPrioritario( rs.getString("PRIORITARIO").equals("V"));
            vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
            vincular.setPeriodo(rs.getString("PERIODO"));
            
            lista.add(vincular);
        }
        
        rs.close();
        stm.close();
        
        return lista;
    }
    
   
 
}