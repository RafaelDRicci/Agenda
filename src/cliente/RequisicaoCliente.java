/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import cliente.login.LoginController;
import cliente.principal.PrincipalView;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Rotina;
import model.Usuario;
import util.communication.DecodificaMensagem;
import util.mensagens.MensagemRotina;
import util.mensagens.MensagemUsuario;
import util.mensagens.MensagemVincularRotina;

/**
 *
 * @author rafaeld
 */
public abstract class RequisicaoCliente {
    
    public static void trataMensagem(byte[] mensagem, PrincipalView principal) throws IOException{
               
        DecodificaMensagem dm = new DecodificaMensagem(mensagem);
        byte codMensagem = dm.getByte();
        byte codOperacao = dm.getByte();
               
        switch(codMensagem){
            case 0:
                System.out.println("SAIR");
                principal.getUsuario().desconectar();
                System.exit(0);
            case 1: 
                //login
                logar(principal, dm);
                break;
                
            case 2:
                //listar rotinas
                System.out.println("PREENCHER ROTINAS");
                MensagemRotina mensagemRotinas = new MensagemRotina(mensagem);
                List<Rotina> rotinas = mensagemRotinas.decodificarListAll();
                principal.getVincularRotina().preencherRotinas(rotinas);
                break;
            case 3:
                //listar usuários
                System.out.println("LISTAR USUÁRIO");
                MensagemUsuario mensagemUsuario = new MensagemUsuario(mensagem);
                List<Usuario> usuarios = mensagemUsuario.decodificarListAll();
                principal.getVincularRotina().preencherUsuarios(usuarios);
            case 4:
                System.out.println("Rotinas Vinculadas");
                trataVincularRotina(mensagem);
                break;
            }
        
            
            
        dm.close();
            
            
           
        
    }
    private static void logar(PrincipalView principal, DecodificaMensagem dm){
        
            Usuario usuario = principal.getUsuario();
            try {

                byte autenticacao = dm.getByte();
                
                switch(autenticacao){
                    case 0:
                        //Usuário não existe
                        JOptionPane.showMessageDialog(principal.getLoginView(), "Usuário Não Existe!", "Login", 1);
                        break;
                    case 1:
                        //Usuário bloqueado
                        JOptionPane.showMessageDialog(principal.getLoginView(), "Usuário Bloqueado!", "Login", 1);
                        break;
                    case 2:
                        //Senha Expirada
                        JOptionPane.showMessageDialog(principal.getLoginView(), "Senha Expirada!", "Login", 1);
                        break;
                    case 3:
                        //Senha Incorreta
                        JOptionPane.showMessageDialog(principal.getLoginView(), "Senha Incorreta!", "Login", 1);
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
                        
                        usuario.setConectado(true);
                        usuario.setAutenticado(true);
                        
                        principal.preencheUsuario();
                        
                        principal.getLoginView().dispose();
                        principal.setEnabled(true);
                        break;
                }
            
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 

    private static void trataVincularRotina(byte[] mensagem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
