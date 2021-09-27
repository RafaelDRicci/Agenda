/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.connection.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rafaeld
 */
public class teste {
    public static void main(String[] args) throws SQLException {
        
        Connection con = ConnectionFactory.getConnection();
        
        /*
        int[] array = {1,2,3,4,5};
        
        SQLIntArray intArray = new SQLIntArray(array);
        

        String sql = "Insert into AGENDA_TESTE(CHAVE, ARRAY) values (?, ?)";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, 1);
        stm.setString(2, intArray.getStringValue());
        stm.execute();
        */
        
        String sql = "Select * from AGENDA_TESTE";
        
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        rs.next();
        String stringArray = rs.getString("ARRAY");
        
        SQLIntArray integerArray = new SQLIntArray(stringArray);
        System.out.println(integerArray);
        for(int i = 0; i < integerArray.getIntArray().length; i++){
            System.out.println(integerArray.getIntArray()[i]);
        }
       
    }
}
