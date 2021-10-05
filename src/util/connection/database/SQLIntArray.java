/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.connection.database;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rafaeld
 */
public class SQLIntArray implements Array{

    private int[] intArray;
    private String stringValue;
    
    public SQLIntArray (int[] intArray){
        this.intArray = intArray;
        stringValue = intArrayToSQLIntArrayString(intArray);
    }
    
    public SQLIntArray(String stringValue){
        this.stringValue = stringValue;
        intArray = SQLIntArrayStringToIntArray(stringValue);
    }
    
    public static String intArrayToSQLIntArrayString(int[] intArray) {
        
        if(intArray == null){
            return "{}";
        }
        
        int arraySize = intArray.length;
        
        if(arraySize == 0){
            return "{}";
        }
        
        String array = "{";
        
        for(int i = 0; i < arraySize; i++){
            if(i > 0) array += ",";
            array += intArray[i];
        }
        array += "}";
        return array;
    }
  
    public static int[] SQLIntArrayStringToIntArray(String stringValue){

        if(stringValue.equals("NULL")){
            return null;
        }
       
        if(stringValue.equals("{}")){
            int[] result = {};
            return result;
        }
        
        String[] array = stringValue.split(",");
        List<Integer> lista = new ArrayList<>();
        for(int i = 0; i < array.length; i++){
            
            if(array[i].contains("{") || array[i].contains("}")){
                char[] charArray = array[i].toCharArray();
                String pos = "";
                
                for(int j = 0; j < charArray.length; j++)
                    if(!( (charArray[j] == ('}') ) || (charArray[j] == ('{') )) ) 
                        pos += charArray[j]; 
                
                array[i] = pos; 
            }
            lista.add(Integer.valueOf(array[i]));
        }
        
        int[] resultado = new int[lista.size()]; 
        for(int i = 0; i < lista.size(); i++){
            resultado[i] = lista.get(i);
        }
        
        return resultado;
    }
    
    public void setIntArray(int[] intArray){
        this.intArray = intArray;
        this.stringValue = intArrayToSQLIntArrayString(intArray);
    }
    
    public void setStringValue(String value){
        this.stringValue = value;
        this.intArray = SQLIntArrayStringToIntArray(value);
    }
    
    @Override
    public String toString(){
        return stringValue;
    }
    
    @Override
    public String getBaseTypeName() throws SQLException {
        return "int";
    }

    @Override
    public int getBaseType() throws SQLException {
        return java.sql.Types.INTEGER;
    }

    @Override
    public Object getArray() throws SQLException {
        return intArray == null ? null : Arrays.copyOf(intArray, intArray.length);
    }
    
    @Override
    public Object getArray(Map<String, Class<?>> map) throws SQLException {
        return getArray();
    }

    @Override
    public Object getArray(long l, int i) throws SQLException {
        return intArray == null ? null : Arrays.copyOfRange(intArray, (int)l, (int)l+i );
    }

    @Override
    public Object getArray(long l, int i, Map<String, Class<?>> map) throws SQLException {
        return getArray(l, i);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getResultSet(long l, int i) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResultSet getResultSet(long l, int i, Map<String, Class<?>> map) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void free() throws SQLException {
    }

    public int[] getIntArray() {
        return intArray;
    }

    public String getStringValue() {
        return stringValue;
    }

    
}
