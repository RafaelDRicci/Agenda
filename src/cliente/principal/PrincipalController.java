/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.principal;

import cliente.RequisicaoCliente;
import cliente.agendarrotina.AgendarRotinaView;
import cliente.justificarrotina.JustificarRotinaView;
import cliente.listarrotina.ListarRotinaView;
import cliente.login.LoginController;
import cliente.login.LoginView;
import cliente.salvarrotina.SalvarRotinaView;
import cliente.vincularrotina.VincularRotinaView;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import util.communication.MensagemSair;

/**
 *
 * @author rafaeld
 */
public class PrincipalController {
    
    private final PrincipalView view;
    private final PrincipalHelper helper;
    private Thread recebeMensagem;
    private Thread consomeMensagem;
    private Usuario usuario;
    private LinkedBlockingQueue<byte[]> filaMensagens;
    private LoginView login;

    public PrincipalController(PrincipalView view) {
        this.view = view;
        helper = new PrincipalHelper(view);
        usuario = new Usuario();
        filaMensagens = new LinkedBlockingQueue<>();
        recebeMensagem = new Thread (() -> recebeMensagens());
        consomeMensagem = new Thread (() -> consomeMensagens());
        //logar();
       
    }
    
    public void logar(){
        
        login = new LoginView();
        login.setUsuario(usuario);
        login.conectar();
        
        if(usuario.isConectado()){
                recebeMensagem.start();
                consomeMensagem.start();
            }
        
        login.setLocationRelativeTo(view);
        login.setAlwaysOnTop(true);
        login.setPrincipal(view);
        login.setVisible(true);
        view.setEnabled(false);
        
           
    }
    
    public void preencheUsuario(){
        helper.setUsuario(usuario);
    }
    
    public void desconectar(){
        usuario.desconectar();
    }

    public void sair() {
        
        if(usuario.isConectado()){
            MensagemSair sair = new MensagemSair();
            try {
                
                usuario.enviarMensagem(sair.getMensagem());
                
            } catch (IOException ex) {
                
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
                
            } finally {
                
                sair.close();
               
            }
        } else System.exit(0);
    }  
    
    private void recebeMensagens(){
        boolean continua = true;
        try {
            while(continua){
                byte[] mensagem = usuario.novaMensagem();
                filaMensagens.add(mensagem);
                if(usuario.verificaSair(mensagem)){
                    continua = false;
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void consomeMensagens(){
        boolean continua = true;
        try {
            while(continua){
                byte[] mensagem = filaMensagens.take();
                if(usuario.verificaSair(mensagem)){
                    continua = false;
                }
                RequisicaoCliente.trataMensagem(mensagem, view);
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
    
     public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        preencheUsuario();
    }
     
    public LoginView getLoginView(){
        return login;
    }

    public void novaRotina() {
        SalvarRotinaView salvarRotinca = new SalvarRotinaView();
        salvarRotinca.setLocationRelativeTo(view);
        salvarRotinca.setVisible(true);
    }

    public void editarRotina() {
        SalvarRotinaView salvarRotinca = new SalvarRotinaView();
        salvarRotinca.setLocationRelativeTo(view);
        salvarRotinca.setVisible(true);
    }

    public void vincularRotina() {
        VincularRotinaView vincularRotina = new VincularRotinaView();
        vincularRotina.setLocationRelativeTo(view);
        vincularRotina.setVisible(true);
    }

    public void listarRotina() {
        ListarRotinaView listarRotina = new ListarRotinaView();
        listarRotina.setLocationRelativeTo(view);
        listarRotina.setVisible(true);
    }

    public void desvincularRotina() {
        JustificarRotinaView justificarRotina = new JustificarRotinaView();
        justificarRotina.setLocationRelativeTo(view);
        justificarRotina.setVisible(true);
    }

    void reagendar() {
        AgendarRotinaView agendar = new AgendarRotinaView();
        agendar.setLocationRelativeTo(view);
        agendar.setVisible(true);
    }
}
