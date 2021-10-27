/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import cliente.login.LoginController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import util.mensagens.MensagemRotina;
import util.mensagens.MensagemUsuario;
import util.mensagens.MensagemVincularRotina;


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

    public void setUsuario(Usuario usuario){
        this.usuario = usuario;
    }
    
     public Usuario getUsuario(){
        return this.usuario;
    }
    
    public void requisicaoPreencherRotinas() {
         
            MensagemRotina mensagemRotina = new MensagemRotina();
           
            try {
                mensagemRotina.requestListAll();
                usuario.enviarMensagem(mensagemRotina.getMensagem());
                
            } catch (IOException ex) {
                
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
                
            } finally {               
                mensagemRotina.close();
            }
        } 
    
    public void preencherRotinas(List<Rotina> rotinas){
        helper.preencherRotinas(rotinas);
    }
    
    
    public void requisicaoPreencherUsuarios() {
        MensagemUsuario mensagemUsuario = new MensagemUsuario();

            try {
                mensagemUsuario.requestListAll();
                usuario.enviarMensagem(mensagemUsuario.getMensagem());

            } catch (IOException ex) {

                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);

            } finally {               
                mensagemUsuario.close();
            }
    }

    public void preencherUsuarios(List<Usuario> usuarios) {
        helper.preencherUsuarios(usuarios);
    }

    public void informacoesUsuario() {
        Usuario usuarioSelecionado = (Usuario) view.getjComboBoxFuncionario().getSelectedItem();
        JOptionPane.showMessageDialog(view, "Nome: "+usuarioSelecionado.getNome() + "\nCargo: "+usuarioSelecionado.getCargo() 
                +"\nUnidade: "+usuarioSelecionado.getUnidade(), "Dados do Usuário "+usuarioSelecionado, 1);
    }

    void informacoesRotina() {
        Rotina rotinaSelecionada = (Rotina) view.getjComboBoxRotina().getSelectedItem();
        JOptionPane.showMessageDialog(view, rotinaSelecionada.getDescricao(), "Descrição de "+rotinaSelecionada, 1);
    }

    void registrarVinculacao() {
        try{
            
            VincularRotina vinculacao = helper.obterVinculacao();
            MensagemVincularRotina mensagem = new MensagemVincularRotina();
            mensagem.codificarCreate(vinculacao);
            usuario.enviarMensagem(mensagem.getMensagem());
            
        } catch(Exception ex){
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro ao Salvar!",  0 );
        }
        
        
    }
}
