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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Rotina;
import util.connection.database.ConnectionFactory;

/**
 *
 * @author rafaeld
 */
public class RotinaDAO {
    
    private Connection con;
    
    public RotinaDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public void create(Rotina rotina){
        
        String sql = "INSERT INTO AGENDA_ROTINA (NOME, DATALIMITE, DESCRICAO) VALUES(?, ?, ?)";
        try {
            
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, rotina.getNome());
            
            if( !(rotina.getDataLimite() == null) ) {
                stm.setDate(2, new java.sql.Date(rotina.getDataLimite().getTimeInMillis()));
            } else stm.setDate(2, null);
                
             
            stm.setString(3, rotina.getDescricao());
            stm.execute();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Rotina read(int cod){
        
        Rotina consulta = null;
        
        try {
            
            String sql = "Select * from AGENDA_ROTINA WHERE CODROTINA = ?";
            PreparedStatement stm  = con.prepareStatement(sql);
            stm.setInt(1, cod);
            ResultSet rs = stm.executeQuery();
            rs.next();
            int codRotina = rs.getInt("CODROTINA");
            String nome = rs.getString("NOME");
            GregorianCalendar data = new GregorianCalendar();
            data.setTimeInMillis(rs.getDate("DATALIMITE").getTime());
            String desc = rs.getString("DESCRICAO");
            
            consulta = new Rotina(nome);
            consulta.setCodRotin(codRotina);
            consulta.setDataLimite(data);
            consulta.setDescricao(desc);
            
        } catch (SQLException ex) {
            Logger.getLogger(RotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return consulta;
    }
    
    public void update(Rotina rotina){

        try {
            
            String sql = "Update AGENDA_ROTINA set NOME = ?, DATALIMITE = ?, DESCRICAO = ? Where CODROTINA = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, rotina.getNome());
            stm.setDate(2, ( new java.sql.Date(rotina.getDataLimite().getTimeInMillis()) ));
            stm.setString(3, rotina.getDescricao());
            stm.setInt(4, rotina.getCodRotina());
            stm.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(RotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void delete(Rotina rotina){
        
        try {
            
            String sql = "Delete from AGENDA_ROTINA WHERE CODROTINA = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, rotina.getCodRotina());
            stm.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(RotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ArrayList<Rotina> listaAll(){
        ArrayList<Rotina> rotinas = new ArrayList<>();
        
        
        try {
            
            String sql = "Select * from AGENDA_ROTINA";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            
            while(rs.next()){
                
                int cod = rs.getInt("CODROTINA");
                String nome = rs.getString("NOME");
                Rotina rotina = new Rotina (cod, nome);
                java.sql.Date data = rs.getDate("DATALIMITE");
                
                
                if( !(data == null) ){
                    GregorianCalendar gc = new GregorianCalendar();
                    gc.setTimeInMillis(rs.getDate("DATALIMITE").getTime());
                    rotina.setDataLimite(gc);
                } 
                
                String descricao = rs.getString("DESCRICAO");
                rotina.setDescricao(descricao);
                
                rotinas.add(rotina);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RotinaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rotinas;
    }
    
    public static void main(String args[]){
        
        RotinaDAO dao = new RotinaDAO();
        Rotina nova = new Rotina("Nova Rotina");
        dao.create(nova);
        
        ArrayList<Rotina> rotinas = dao.listaAll();
       
        
        rotinas.forEach(rotina ->{
            System.out.println(rotina + " " + rotina.getDataLimite().getTime());
        });
        
    }
}