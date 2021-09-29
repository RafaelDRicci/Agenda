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
    
    public abstract void upadate(T vincular) throws SQLException;
    
    public void delete(VincularRotina vincular) throws SQLException {
        
        String sql = "Delete from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getCodRotina());
        stm.setInt(2, vincular.getCodUsuario());
        stm.execute();
    }
    
    public List<VincularRotina> listAll() throws SQLException {
        List<VincularRotina> lista = new ArrayList<>();
        
        String sql = "Select * from AGENDA_VINCULARROTINA";
        
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.getResultSet();
        
        while(rs.next()){
            
            int codRotina  = rs.getInt("CODROTINA");
            int codUsuario = rs.getInt("CODUSUARIO");
            String prioritario = rs.getString("PRIORITARIO");
            String reagendavel = rs.getString("REAGENDAVEL");
            String horarioFixo = rs.getString("HORARIOFIXO");
            String periodo = rs.getString("PERIODO");
            
            VincularRotina vincular = new VincularRotina(codRotina, codUsuario);
            vincular.setPrioritario(prioritario.equals("V"));
            vincular.setReagendavel(reagendavel.equals("V"));
            vincular.setHorarioFixo(horarioFixo.equals("V"));
            vincular.setPeriodo(periodo);
            
            lista.add(vincular);
        }
        
        return lista;
    }
    
   
 
}