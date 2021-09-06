/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.login;

import cliente.principal.PrincipalView;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import model.Usuario;
import util.comunicacao.CodificaMensagem;

/**
 *
 * @author rafaeld
 */
public class LoginController {
    
    private final LoginView view;
    private final LoginHelper helper;
    private Usuario usuario;
    private PrincipalView principal;
 
//private Comunicacao comunicacao;
    //private boolean conectado;
    
    public LoginController(LoginView view){
        this.view = view;
        helper = new LoginHelper(view);   
    }
    
    /**
     * Método para conectar-se ao banco
     * @return returna verdadeira caso consiga se conectar, falso caso não
     */
    public boolean conectar(){
        
        try {
            if(usuario.conectar(new Socket("localhost", 4000))){
                JOptionPane.showMessageDialog(view, "Conexão Estabelecida com Sucesso!", "Conexão", 1);
            }
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, "Não foi Possível Conectar-se com o Servidor", "Conexão", 0);
        }
        return usuario.isConectado();

    }
    
    public void desconectar(){
        usuario.desconectar();
    }
    
    public void logar(){
        
        if(usuario.isConectado()){
            helper.pegaLoginSenha(usuario);
            CodificaMensagem cm = new CodificaMensagem();
            try {
                byte i = 1;
                cm.setByte(i);
                cm.setString(usuario.getLogin());
                cm.setString(usuario.getSenha());
                usuario.enviarMensagem(cm.getMensagem());
                usuario.setAutenticado(false);
                /*
                byte[] resposta = usuario.novaMensagem();
                DecodificaMensagem dm = new DecodificaMensagem(resposta);
                byte autenticacao = dm.getByte();
                
                switch(autenticacao){
                    case 0:
                        //Usuário não existe
                        JOptionPane.showMessageDialog(view, "Usuário Não Existe!", "Login", 1);
                        break;
                    case 1:
                        //Usuário bloqueado
                        JOptionPane.showMessageDialog(view, "Usuário Bloqueado!", "Login", 1);
                        break;
                    case 2:
                        //Senha Expirada
                        JOptionPane.showMessageDialog(view, "Senha Expirada!", "Login", 1);
                        break;
                    case 3:
                        //Senha Incorreta
                        JOptionPane.showMessageDialog(view, "Senha Incorreta!", "Login", 1);
                        break;
                    case 4:
                        //Usuário autenticado
                        usuario.setnControle(dm.getInt());
                        usuario.setCodUsuario(dm.getInt());
                        usuario.setLogin(dm.getString());
                        usuario.setSenha(dm.getString());
                        usuario.setDataCadastro(dm.getData());
                        usuario.setDataValidacao(dm.getData());
                        
                        usuario.setNome(dm.getString());
                        usuario.setNomeAprovacao(dm.getString());
                        usuario.setCargo(dm.getString());
                        usuario.setUnidade(dm.getString());
                        usuario.setAutenticado(true);
                        
                        principal.preencheUsuario();
                        
                        view.dispose();
                        break;
                }
            */
            } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } /*catch (ParseException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        } else conectar();
        
    } 
    
    public void sair(){
       principal.sair();
    }
    
    public void setTextFieldToUpper(JTextField jTextFieldUsuario) {
        jTextFieldUsuario.setDocument(new PlainDocument(){
            @Override
            public void replace(int offset, int lenght, String text, AttributeSet attrs) throws BadLocationException{
                
                if(text != null){
                    text = text.toUpperCase();
                }
                super.replace(offset, lenght, text, attrs);
            }
        });
    }

    public void verificaCaps() {
        

        if ( Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK ) ) {
            view.getjLabelCaps().setVisible(true);
        } else view.getjLabelCaps().setVisible(false);
        
        view.getjTextFieldUsuario().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke) {}

            @Override
            public void keyReleased(KeyEvent ke) {
            boolean capsLigado = Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK );
		if(capsLigado){
                    view.getjLabelCaps().setVisible(true);
                } else view.getjLabelCaps().setVisible(false);
            }
            
        });
        
        view.getjPasswordFieldSenha().addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent ke) {}

            @Override
            public void keyPressed(KeyEvent ke) {}

            @Override
            public void keyReleased(KeyEvent ke) {
            boolean capsLigado = Toolkit.getDefaultToolkit().getLockingKeyState(
                KeyEvent.VK_CAPS_LOCK );
                if(capsLigado){
                    view.getjLabelCaps().setVisible(true);
                } else view.getjLabelCaps().setVisible(false);
            }         
        });
    }
  
    public void keyPressed(KeyEvent evt) { 
        int key = evt.getKeyChar();   
        switch(key){
            case KeyEvent.VK_ENTER:
                logar();
                break;
        }  
    }
    
    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setPrincipal(PrincipalView principal){
        this.principal = principal;
    }
    
    
}
