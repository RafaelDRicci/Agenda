/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.communication;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.GregorianCalendar;
import model.Rotina;

/**
 *Classe suporte com métodos estáticos para codificar e decodificar objetos e listas
 * @author rafaeld
 */
public class ByteArraySuport {
    
    /**
     * Para uma rotina, são codificados os seguintes dados em sequência: 
     * Código da rotina 
     * Nome 
     * Data Limite 
     * Descricao  
     * @param usuario
     * @throws SQLException
     * @throws IOException 
     **/
    
    public static byte[] codificaRotina(Rotina rotina) throws IOException{
        
        CodificaMensagem codificar = new CodificaMensagem();

        codificar.setInt(rotina.getCodRotina());
        codificar.setString(rotina.getNome());
        codificar.setData(rotina.getDataLimite().getTime());
        codificar.setString(rotina.getDescricao());
            
        return codificar.getMensagem();
    }
    
    
    /**
     * * Para uma rotina, são decodificados os seguintes dados em sequência: 
     * Código da rotina 
     * Nome 
     * Data Limite 
     * Descricao  
     * @param bytes
     * @return
     * @throws IOException 
     */
   public static Rotina decodificaRotina(byte[] bytes) throws IOException, ParseException{
       
       DecodificaMensagem decodificar = new DecodificaMensagem(bytes);
       
       int codRotina = decodificar.getInt();
       String nome = decodificar.getString();
       GregorianCalendar data = new GregorianCalendar();
       data.setTime(decodificar.getData());
       Rotina rotina = new Rotina(codRotina, nome);
       rotina.setDataLimite(data);
       rotina.setDescricao(decodificar.getString());
       
       return rotina;
   }
    
}
