/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class MensagemTest {
    
    
    @Test
    public void testCodificaDecodificaByteArray() throws IOException{
        System.out.println("");
        byte[] arrayEnviado = {(byte)1, (byte)2, (byte)3, (byte)4, (byte)5};
        
        MensagemImpl mensagemCodificada = new MensagemImpl((byte)(0));
        mensagemCodificada.setByte((byte)0);
        mensagemCodificada.setByteArray(arrayEnviado);
        
        for(int i = 0; i < arrayEnviado.length; i++){
            System.out.print(arrayEnviado[i]);
        }
        System.out.println("");
        MensagemImpl mensagemDecodificada = new MensagemImpl(mensagemCodificada.getMensagem());      
        byte [] arrayRecebido = mensagemDecodificada.getByteArray();
        boolean igual = true;
        
        for(int i = 0; i < arrayRecebido.length; i++){
            System.out.print(arrayRecebido[i]);
            if(arrayRecebido[i] != arrayEnviado[i]) igual = false;
        }
        assertTrue(igual);
    }
    
    @Test
    public void testCodificarDecodificarIntArray() throws IOException{
        System.out.println("");
        int[] arrayEnviado = {1, 2, 3, 4, 5};
        MensagemImpl mensagemCodificada = new MensagemImpl((byte)(0));
        mensagemCodificada.setByte((byte)0);
        mensagemCodificada.setIntArray(arrayEnviado);
        
        for(int i = 0; i < arrayEnviado.length; i++){
            System.out.print(arrayEnviado[i]);
        }
        System.out.println("");
        MensagemImpl mensagemDecodificada = new MensagemImpl(mensagemCodificada.getMensagem());      
        int [] arrayRecebido = mensagemDecodificada.getIntArray();
        boolean igual = true;
        
        for(int i = 0; i < arrayRecebido.length; i++){
            System.out.print(arrayRecebido[i]);
            if(arrayRecebido[i] != arrayEnviado[i]) igual = false;
        }
        assertTrue(igual);
    }
    
    @Test
    public void testCodificaDecodificaData() throws IOException{
        
        GregorianCalendar dataEnviada = new GregorianCalendar(2022, 5, 11);
        MensagemImpl mensagemEnviada = new MensagemImpl((byte)0);
        mensagemEnviada.setByte((byte)0);
        mensagemEnviada.setData(dataEnviada.getTime());
        
        MensagemImpl mensagemRecebida = new MensagemImpl(mensagemEnviada.getMensagem());

        GregorianCalendar dataRecebida = new GregorianCalendar();
        dataRecebida.setTime(mensagemRecebida.getData());
        
        assertEquals(dataEnviada, dataRecebida);
    }
    
    
    public class MensagemImpl<Integer> extends Mensagem {

        public MensagemImpl(byte codMensagem) {
            super(codMensagem);
        }
        
        public MensagemImpl(byte[] bytes){
            super(bytes);
        }
        
        public int[] decodificarArray() throws IOException{
            return getIntArray();
        }
        
        @Override
        public void codificarCreate(Object objeto) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object decodificarCreate() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void codificarRead(int codRotina) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }


        @Override
        public void codificarUpdate(Object objeto) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object decodificarUpdate() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void codificarDelete(Object objeto) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object decodificarDelete() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void codificarRequestList() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void codificarList(List list) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List decodificarList() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public int[] decodificarRead() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void setObjeto(Object objeto) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getObjeto() throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

  
    }
    
}
