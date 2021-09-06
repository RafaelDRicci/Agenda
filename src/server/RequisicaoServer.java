/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import model.dao.UsuarioDAO;
import util.comunicacao.CodificaMensagem;
import util.comunicacao.DecodificaMensagem;
import util.comunicacao.MensagemSair;

/**
 *
 * @author rafaeld
 */
public class RequisicaoServer {

    private List<Usuario> usuarios;
    
    public RequisicaoServer(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }
    
    public void TrataMensagem(MensagemCliente mensagem) throws IOException, InterruptedException{
             
            byte[] dados = mensagem.getMensagem();
            DecodificaMensagem dm = new DecodificaMensagem(dados);
            byte codigo = dm.getByte();
             
            switch(codigo){
                case 0: 
                    System.out.println("SAIR");
                    MensagemSair sair = new MensagemSair();
                    sair.codificar();
                    mensagem.getUsuario().enviarMensagem(sair.getMensagem());
                    System.out.println("Finalizando Conexão com Cliente");
                    usuarios.remove(mensagem.getUsuario());
                    mensagem.getUsuario().desconectar();
                    break;
                case 1:
                    System.out.println("AUTENTICA USUARIO");
                    String login = dm.getString();
                    String senha = dm.getString();  
                    
                    autenticaUsuario(mensagem.getUsuario(), login, senha);
                    break;
            }
            dm.close();
            
    }
    
        public void autenticaUsuario(Usuario usuario, String login, String senha) throws IOException{

                UsuarioDAO dao = new UsuarioDAO();
                usuario.setLogin(login);
                usuario.setSenha(senha);
                CodificaMensagem cm = new CodificaMensagem();
                byte codigoAutenticacao = dao.autentica(usuario);
                byte codigoResposta = 1;
                cm.setByte(codigoResposta);
                
                    switch(codigoAutenticacao){
                        case 0 :
                            //Usuário não existe
                            cm.setByte(codigoAutenticacao);
                            break;
                        case 1 :
                            //Senha expirada
                            cm.setByte(codigoAutenticacao);
                            break;
                        case 2 :
                            //Usuário bloqueado
                            cm.setByte(codigoAutenticacao);
                            break;
                        case 3 :
                            //Senha incorreta
                            usuario.tentativaLogin();
                            cm.setByte(codigoAutenticacao);

                            if(usuario.getTentativaLogin() == 3){
                                dao.bloquear(login);
                                usuario.setTentativaLogin(0);
                             }
                             break;
                        case 4 :
                            //Usuário autenticado
                            cm.setByte(codigoAutenticacao);
                            cm.setInt(usuario.getnControle());
                            cm.setInt(usuario.getCodUsuario()); 
                            cm.setString(usuario.getLogin());
                            cm.setString(usuario.getSenha());
                            cm.setData(usuario.getDataCadastro());
                            cm.setData(usuario.getDataValidacao());

                            cm.setString(usuario.getNome());
                            cm.setString(usuario.getNomeAprovacao());
                            cm.setString(usuario.getCargo());
                            cm.setString(usuario.getUnidade());
                            
                            usuario.setConectado(true);
                            usuario.setAutenticado(true);

                            break;
                    }
                    usuario.enviarMensagem(cm.getMensagem());
                            
        }
 
}
