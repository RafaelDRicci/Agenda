/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.login;

import model.Usuario;
import util.cliente.Criptografia;

/**
 *
 * @author rafaeld
 */
public class LoginHelper {
    
    private final LoginView view;
    
    public LoginHelper(LoginView view){
        this.view = view;
    }
    
    public void pegaLoginSenha(Usuario usuario){
        String login = view.getjTextFieldUsuario().getText();
        String senha = Criptografia.cifra( view.getjPasswordFieldSenha().getText() );
        usuario.setLogin(login);
        usuario.setSenha(senha);
    }
    
}
