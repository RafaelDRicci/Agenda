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
        /*
        Connection con = ConnectionFactory.getConnection();
        
        
        int[] array = {1,2,3,4,5};
        int[] array2 = {1};
        int[] array3 = {};
        int[] array4 = null;
        String sql = "Insert into AGENDA_TESTE(CHAVE, ARRAY) values (?, ?)";
        
        SQLIntArray intArray = new SQLIntArray(array);
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, 1);
        stm.setString(2, intArray.getStringValue());
        stm.execute();
        
        String sql2 = "Insert into AGENDA_TESTE(CHAVE, ARRAY) values (?, ?)";
        SQLIntArray intArray2 = new SQLIntArray(array2);
        stm = con.prepareStatement(sql2);
        stm.setInt(1, 2);
        stm.setString(2, intArray2.getStringValue());
        stm.execute();
        
        String sql3 = "Insert into AGENDA_TESTE(CHAVE, ARRAY) values (?, ?)";
        SQLIntArray intArray3 = new SQLIntArray(array3);
        stm = con.prepareStatement(sql3);
        stm.setInt(1, 3);
        stm.setString(2, intArray3.getStringValue());
        stm.execute();
        
        String sql4 = "Insert into AGENDA_TESTE(CHAVE, ARRAY) values (?, ?)";
        SQLIntArray intArray4 = new SQLIntArray(array4);
        intArray = new SQLIntArray(array4);
        stm = con.prepareStatement(sql4);
        stm.setInt(1, 4);
        stm.setString(2, intArray4.getStringValue());
        stm.execute();
        
        
        
        sql = "Select * from AGENDA_TESTE";
        
        stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            String stringArray = rs.getString("ARRAY");
        
            SQLIntArray integerArray = new SQLIntArray(stringArray);
            System.out.print("String: "+ integerArray+" ");
            if(!integerArray.toString().equals("NULL")){
                    for(int i = 0; i < integerArray.getIntArray().length; i++){
                    System.out.print(integerArray.getIntArray()[i]);
                    System.out.print(" ");
                }
            }
           
            
            System.out.println("");
        }
        
        ConnectionFactory.closeConnection(con, stm);
    */
        
        String stringArray = "{1,2,3}";
        int[] array = SQLIntArray.SQLIntArrayStringToIntArray(stringArray);
        for(int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }
}
