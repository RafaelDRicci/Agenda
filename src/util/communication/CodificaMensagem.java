/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.communication;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaeld
 * Transforma uma mensagem em um vetor de bytes. A mensagem pode ser um objeto e 
 * os métodos podem ser utilizados para codificar cada campo. O primeiro byte do 
 * vetor deve ser um código de indentificação
 */
public class CodificaMensagem {
    
    ByteArrayOutputStream baos;
    DataOutputStream dos;
    
    public CodificaMensagem(){
        baos = new ByteArrayOutputStream ();
        dos = new DataOutputStream(baos);
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
}
