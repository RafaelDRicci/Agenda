/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.communication;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rafaeld
 * Responsável por criar a mensagem
 */

public class DecodificaMensagem {
    
    
    ByteArrayInputStream bais;
    DataInputStream dis;
    
    
    public DecodificaMensagem (byte[] bytes){
        bais = new ByteArrayInputStream(bytes);
        dis = new DataInputStream(bais);
    }
    
    public byte getByte() throws IOException{
        return dis.readByte();  
    }
        
    public int getInt() throws IOException{
        return dis.readInt();
    }
    
    public boolean getBoolean() throws IOException{
        return dis.readBoolean();
    }
    
    public String getString() throws IOException{       
        int size = dis.readInt();
        String string = "";
        for(int i = 0; i < size; i++){
            string += dis.readChar();
        }
            
        return string;
    }
    
    public Date getData() throws IOException, ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.parse(getString());
    }
    
        public void close() throws IOException{
        bais.close();
        dis.close();
    }
}
