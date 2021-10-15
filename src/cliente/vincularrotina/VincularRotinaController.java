/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import cliente.login.LoginController;
import java.io.IOException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import util.mensagens.MensagemRotina;
import util.mensagens.MensagemSair;


/**
 *
 * @author rafaeld
 */
public class VincularRotinaController {

    private VincularRotinaView view;
    private VincularRotinaHelper helper;
    private Usuario usuario;
    
    public VincularRotinaController(VincularRotinaView view) {
        this.view = view;
        helper = new VincularRotinaHelper(view);
    }

    public void cancelar() {
        view.dispose();
    }

    public void preencherRotinas() {
         
            MensagemRotina mensagemRotina = new MensagemRotina();
           
            try {
                mensagemRotina.codificar();
                usuario.enviarMensagem(mensagemRotina.getMensagem());
                
            } catch (IOException ex) {
                
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
                
            } finally {               
                mensagemRotina.close();
            }
        } 
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
    public Usuario getUsuario(){
        return this.usuario;
    }
}
