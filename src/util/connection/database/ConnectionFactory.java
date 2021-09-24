/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.connection.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rafaeld
 */
public class ConnectionFactory {
    
    private static final String DRIVER = "org.firebirdsql.jdbc.FBDriver";
    private static final String URL = "jdbc:firebirdsql:localhost/3050:C:/Users/rafaeld/Documents/Banco/Apoio/APOIO.FDB?lc_ctype=WIN1252";
    private static final String USER = "SYSDBA";
    private static final String PASSWORD = "masterkey";
    
    public static Connection getConnection(){
        
        try {
            
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro de conexão", ex);
           
        }
    }
    
    public static void closeConnection(Connection con){
        
        if(con != null){
            try {   
                con.close();
                
            } catch (SQLException ex) {
                System.err.println("Error: "+ex);    
            }
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stm){
        
        closeConnection(con);
        
        if(stm != null){
            try {
                stm.close();
                
            } catch (SQLException ex) {
                System.err.println("Error: "+ex);
            }
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stm, ResultSet rs){ 
        
        closeConnection(con, stm);  
        if(rs != null){
            try {
                rs.close();
                
            } catch (SQLException ex) {
                System.err.println("Error: "+ex);
            }
        }
        
    }
}
