/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import util.communication.Comunicacao;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import util.communication.CodificaMensagem;
import util.communication.DecodificaMensagem;

/**
 *
 * @author rafaeld
 */
public class Servidor{
    
    private final int PORTA = 4000;
    
    private ServerSocket server;
    private List<Usuario> clients;
    private LinkedBlockingQueue<MensagemCliente> filaMensagens;
    private boolean online;
    
    public Servidor(){
        try {
            
            server = new ServerSocket(PORTA);
            clients = new ArrayList<>();
            filaMensagens = new LinkedBlockingQueue<>();
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao iniciar o servidor " + ex.getMessage());
        }
        
    }
    
    public void start(){
        
        this.online = true;
        Thread gerenciarServidor = new Thread(() -> loopServerManager());
        gerenciarServidor.start();
        
        Thread consumirRequisicao = new Thread(() -> loopConsomeRequisicao());
        consumirRequisicao.start();
        
        //Thread numeroAleatorio = new Thread(() -> geraNumeroAleatorio());
        //numeroAleatorio.start();
        while(online){
            try {
                
                Usuario novoUsuario = new Usuario();
                novoUsuario.setComunicacao(new Comunicacao(server.accept()));
                novoUsuario.setConectado(true);
                System.out.println("Cliente " +novoUsuario.getRemoteSocketAddress()+" conectado");
                clients.add(novoUsuario);
                
                Thread gerenciaUsuario = new Thread(() -> loopInsereRequisicao(novoUsuario));
                gerenciaUsuario.start();
                
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro de conexão com client " + ex.getMessage());
            }
        }     
    }
    
    public void loopServerManager(){
        System.out.println("SERVIDOR INICIADO");
        
        
    }
    
    private void loopConsomeRequisicao(){
         
        while(online){
            try {
                RequisicaoServer rs = new RequisicaoServer(clients);
                MensagemCliente mensagem = filaMensagens.take();
                rs.TrataMensagem(mensagem);
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        
        System.out.println("Finalizando loop de Requisicao");
    }
    
    public void loopInsereRequisicao(Usuario usuario){
        
        try {
            boolean continua = true;
            while(continua){
                
                byte[] dados = usuario.novaMensagem();
                DecodificaMensagem dm = new DecodificaMensagem(dados);
                
                continua = !(dm.getByte() == 0);
  
                MensagemCliente novaMensagem = new MensagemCliente(usuario, dados);
                filaMensagens.add(novaMensagem);
                dm.close();
                
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("Conexão com Cliente Perdida");
        }
        
    }
    
    
    

    public static void main(String [] args){
        Servidor Servidor = new Servidor();
        Servidor.start();
    }
    
    private void geraNumeroAleatorio(){
        
        Random random = new Random();
        while(true){
            try {
                
                Thread.sleep(5000);
                if(!clients.isEmpty()){
                    clients.forEach(cliente -> {
                       
                        if(cliente.isAutenticado()){
                            
                            CodificaMensagem cm = new CodificaMensagem();
                            byte i = 2;
                            try {
                                cm.setByte(i);
                                cm.setInt(random.nextInt());
                                cliente.enviarMensagem(cm.getMensagem());
                            } catch (IOException ex) {
                                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                } 
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
