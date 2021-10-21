/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import model.Rotina;
import model.Usuario;
import model.dao.RotinaDAO;
import model.dao.UsuarioDAO;
import util.communication.CodificaMensagem;
import util.communication.DecodificaMensagem;
import util.mensagens.MensagemRotina;
import util.mensagens.MensagemSair;
import util.mensagens.MensagemUsuario;

/**
 *
 * @author rafaeld
 */
public class RequisicaoServer {

    private List<Usuario> usuarios;
       
    public RequisicaoServer(List<Usuario> usuarios){
        this.usuarios = usuarios;
    }
    /**
     * Método do servidor que recebe a mensagem do cliente e age de acordo com o código da mensagem.<br>
     * Decodifica a mensagem do cliente caso necessário, realiza uma ou um conjunto de operações,  
     * codifica e envia uma resposta para o cliente.<br>
     * Os códigos e como o servidor reagem são as seguinte:<br>
     * 
     * <br><b>Código = 0, Mensagem SAIR -> O cliente está se desconectando do servidor; Não é necessário decodificar a mensagem, pois
     * ela possui apenas o código da mensagem. O cliente vai ser removido da lista de clientes e desconectado.</b><br>
     * 
     * <br><b>Código = 1, Mensagem AUTENTICAR CLIENTE -> O cliente está logando no sistema e precisa ser autenticado por meio do seu
     * login e senha; Mensagem possui código da mensagem, login e senha do cliente. Foi criado um método auxiliar para realizar a autenticação.
     * Após a autenticação e enviada uma mensagem resposta para o cliente, que pode ser uma resposta que não foi possível autenticar ou
     * uma resposta positiva com o cliente autenticado e em seguida todos os dados do cliente salvo no banco de dados. As respostas possue um
     * segundo código de identificação para as possíveis respostas, que são:<br>
     * 0 - Cliente não existe (Login não foi encontrado no banco)<br>
     * 1 - Senha expirada<br>
     * 2 - Usuário bloqueado<br>
     * 3 - Senha incorreta<br>
     * 4 - Usuário autenticado, além do código vai ser enviado todos os dados do cliente</b><br> 
     * 
     * <br><b> Código = 2, Mensagem LISTAR ROTINAS -> Requisição de todas as rotinas salvas no banco. É feita a leitura das rotinas e posteriormente 
     * a lista é codificada e enviada para o cliente. Para auxiliar esta operação foi instânciada um objeto da classe MensagemRotina.
     * </b><br>
     * 
     * <br><b>Código = 3, Mensagem LISTAR USUÁRIOS -> Requisição de todos os usuário. É feita a leitura de todos os usuarios que são codificados 
     * e enviados para o cliente. Foi também instânciado um objeto de uma classe(MensagemUsuario) para auxiliar a operação.</b><br>
     * 
     * @param mensagem byte[] - mensagem códificada na forma de um array de bytes
     * @throws IOException
     * @throws InterruptedException
     * @throws SQLException 
     */
    public void TrataMensagem(MensagemCliente mensagem) throws IOException, InterruptedException, SQLException{
             
            byte[] dados = mensagem.getMensagem();
            DecodificaMensagem dm = new DecodificaMensagem(dados);
            byte codigo = dm.getByte();
            
            switch(codigo){
                //Sair
                case 0: 
                    System.out.println("SAIR");
                    MensagemSair sair = new MensagemSair();
                    mensagem.getUsuario().enviarMensagem(sair.getMensagem());
                    System.out.println("Finalizando Conexão com Cliente");
                    usuarios.remove(mensagem.getUsuario());
                    mensagem.getUsuario().desconectar();
                    break;
                //Autenticar usuário
                case 1:
                    System.out.println("AUTENTICA USUARIO");
                    String login = dm.getString();
                    String senha = dm.getString();  
                    
                    autenticaUsuario(mensagem.getUsuario(), login, senha);
                    break;
               //Todas as rotinas
                case 2:
                    System.out.println("Rotinas");
                    trataRotinas(mensagem);
                    break;
                //Todos os usuários
                case 3:
                    System.out.println("Usuários");
                    UsuarioDAO usuarioDAO = new UsuarioDAO();
                    List<Usuario> todosUsuarios = usuarioDAO.listAll();
                    MensagemUsuario mensagemUsuario = new MensagemUsuario();
                    mensagemUsuario.codificarList(todosUsuarios);
                    mensagem.getUsuario().enviarMensagem(mensagemUsuario.getMensagem());
                    break;
            }
            dm.close();
            
    }
    
        private void autenticaUsuario(Usuario usuario, String login, String senha) throws IOException, SQLException{

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

    private void trataRotinas(MensagemCliente mensagem) throws SQLException, IOException {
       
        MensagemRotina mensagemRecebida = new MensagemRotina(mensagem.getMensagem());
        RotinaDAO rotinaDAO = new RotinaDAO();
        
        switch(mensagemRecebida.getCodOperacao()){
            
            case 1:
                System.out.println("CREATE");
                Rotina rotina = mensagemRecebida.decodificarCreate();
                rotinaDAO.create(rotina);
                break;
            case 5:
                System.out.println("LIST ALL");
                List<Rotina> rotinas = rotinaDAO.listAll();
                MensagemRotina mensagemResposta = new MensagemRotina();
                mensagemResposta.codificarList(rotinas);
                mensagem.getUsuario().enviarMensagem(mensagemResposta.getMensagem());
                break;
        }
        
        rotinaDAO.closeConnection();
    }
 
        
}
