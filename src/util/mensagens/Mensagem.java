/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.communication.CodificaMensagem;

/**
 *
 * @author rafaeld
 */
public abstract class Mensagem<T> {
    
    private ByteArrayOutputStream baos;
    private DataOutputStream dos;
    
    private ByteArrayInputStream bais;
    private DataInputStream dis;

    public Mensagem(){
        baos = new ByteArrayOutputStream ();
        dos = new DataOutputStream(baos);
    }
    
    public Mensagem(byte[] bytes){
        bais = new ByteArrayInputStream(bytes);
        dis = new DataInputStream(bais);
    }
    
    /**
     * 
     * @param codigo
     * @throws IOException 
     * Código da mensagem, sempre deve ser o primeiro
     */
    public void setByte(byte codigo) throws IOException{ 
        dos.writeByte(codigo);
    }
    
    
    /**
     * 
     * @param i
     * @throws IOException
     * Insere um inteiro no vetor
     */
    public void setInt(int i) throws IOException{
        dos.writeInt(i);
    }
    
    /**
     * 
     * @param b
     * @throws IOException 
     * Insere um boolean no vetor
     */
    public void setBoolean(boolean b) throws IOException{
        dos.writeBoolean(b);
    }
    
    /**
     * 
     * @param string
     * @throws IOException 
     * Insere uma string no vetor. Antes de da string é feita a inserção do seu tamanho,
     * pois é necessário saber para decodificar
     */
    public void setString(String string) throws IOException{
        
        if(string == null){
            dos.writeInt(0);
            dos.flush();
        } else {
            dos.writeInt(string.length());
            dos.flush();
            dos.writeChars(string);
            dos.flush();
        }

    }
    
    public void setData(Date data) throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String string = sdf.format(data);
        setString(string);
    }
    
    /**
     * 
     * @return 
     * Vetor de bytes
     */
    public byte[] getMensagem(){
        return baos.toByteArray();
    }

    
    /**
     * Finaliza os componentes abertos
     */
    public void close(){
        try {
            baos.close();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(CodificaMensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
    
    public Date getData() throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return sdf.parse(getString());
        } catch (ParseException ex) {
            Logger.getLogger(Mensagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public abstract byte[] codificar() throws IOException;
    
    public abstract byte[] codificar(T objeto) throws IOException;
    
    public abstract byte[] codificar(T ...objetos) throws IOException;
    
    public abstract byte[] codificar(List<T> objetos) throws IOException;
    
    public abstract T decodifica() throws IOException;
    
    public abstract List<T> decodificar()throws IOException;

}
