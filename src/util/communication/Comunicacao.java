/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafaeld
 * Responsável por enviar e receber mensagens
 * @param input Canal de comunicação de entrada
 * @param output Canal de comunicação de saida
 * Os parâmetros são extraídos no contrututor por meio do socket que é passado
 * como 
 */
public class Comunicacao {
    
    protected Socket socket;
    protected DataInputStream input;
    protected DataOutputStream output;
   
    /**
     * 
     * @param socket 
     */
    public Comunicacao(Socket socket) throws IOException{
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
        
    }
    
    /**
     * 
     * @param dados
     * @throws IOException 
     */
    public void sendMessage(byte[] dados) throws IOException {

        output.writeInt(dados.length); //Primeiro é enviado o tamanho da mensagem
        output.flush();
        output.write(dados); //Depois os dados da mensagem
        output.flush();
    }
    
    
   /**
    * 
    * @return
    * @throws IOException 
    */
    public byte[] getMessage() throws IOException {
        
        int size = input.readInt(); //Lê o tamanho da mensagem
        byte[] msg = new byte[size]; //Cria um vetor de bytes para armazenar a mensagem
        for(int i = 0; i < size; i++){ 
            msg[i] = input.readByte(); //Faz a leitura de cada bytes e insere no vetor
        }
        return msg; 
    }
   
    
    public SocketAddress getRemoteSocketAddress(){
        return socket.getRemoteSocketAddress();
    }
    
    public void close(){
        try {
            this.input.close();
            this.output.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public DataInputStream getInput() {
        return input;
    }

    public void setInput(DataInputStream input) {
        this.input = input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public void setOutput(DataOutputStream output) {
        this.output = output;
    }
    
    
    
}
