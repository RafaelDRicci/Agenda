/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Rotina;
import model.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class MensagemUsuarioTest {
    
    /**
     * Testando Código da Mensagem Usuário (3), codificando uma nova mensagem
     */
    @Test
    public void testCodigoMensagemUsuarioCodificando(){
        MensagemUsuario mensagem = new MensagemUsuario();
        
        int esperado = 3;
        int obtido = mensagem.getCodMensagem();
        
        assertEquals(esperado, obtido);
    }
    
    /**
     * Testando Código da Mensagem Usuário (3), decodificando uma nova mensagem
     */
    @Test
    public void testCodigoMensagemUsuarioDecodificando(){
        
        MensagemUsuario mensagemEnviada = new MensagemUsuario();
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemEnviada.getMensagem());
        
        int esperado = 3;
        int recebido = mensagemRecebida.getCodMensagem();
        
        assertEquals(esperado, recebido);
        
    }
    /**
     * Testa o código de operação enviar usuário
     * @throws IOException 
     */
    @Test
    public void testCodigoOperacaoCodificaUsuario() throws IOException{
        MensagemUsuario mensagemEnviada = new MensagemUsuario();
        Usuario usuario = new Usuario(1);
        mensagemEnviada.codificaUsuario(usuario);
        
        int esperado = 6;
        int obtido = mensagemEnviada.getCodOperacao();
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testCodigoOperacaoDecodificaUsuario() throws IOException{
        
        MensagemUsuario mensagemEnviada = new MensagemUsuario();
        Usuario usuarioEnviado = new Usuario(1);
        mensagemEnviada.codificaUsuario(usuarioEnviado);
        
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemEnviada.getMensagem());
        
        int esperado = 6;
        int obtido = mensagemRecebida.getCodOperacao();
        
        assertEquals(esperado, obtido);
        
    }
    
    
     /**
     * Cria um usuario, codifica e decodifica. Com o usuário decodificado cria um novo. 
     * No final os códigos dos usuários ( atributo codUsuario ) são comparados.
     */
    @Test
    public void testCodigoMensagemUsuarioCodificaUsuarioVerificaCodUsuario() throws IOException{
        
        MensagemUsuario mensagemEnviado = new MensagemUsuario();
        Usuario usuarioCodificado = new Usuario(5);
        mensagemEnviado.codificaUsuario(usuarioCodificado);
        
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemEnviado.getMensagem());
        Usuario usuarioDecodificado = mensagemRecebida.decodificarUsuario();
        
        int esperado = 5;
        int obtido = usuarioDecodificado.getCodUsuario();
        
        assertEquals(esperado, obtido);
        
    }
    
    /**
     * Teste que codifica e decodifica um usuário e depois compara todos os atributos 
     */
    @Test
    public void testComparandoUsuarioCodificadoDecodificadoComparaTodosOsAtributos() throws IOException{
        
        Usuario usuarioEnviado = new Usuario(1);
        usuarioEnviado.setNome("Usuario 1");
        usuarioEnviado.setNomeAprovacao("Usuario");
        usuarioEnviado.setCargo("Cargo 1");
        usuarioEnviado.setUnidade("Unidade 1");
        
        MensagemUsuario mensagemEnviada = new MensagemUsuario();
        mensagemEnviada.codificaUsuario(usuarioEnviado);
        
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemEnviada.getMensagem());
        Usuario usuarioRecebido = mensagemRecebida.decodificarUsuario();
        
        assertTrue(usuarioEnviado.igual(usuarioRecebido));
        
    }
    
    @Test
    public void testCodificaRequisicaoListaUsuarios() throws IOException{
        
        MensagemUsuario mensagemRequisicao = new MensagemUsuario();
        mensagemRequisicao.requestListAll();
               
        int esperado = 5;
        int recebido = mensagemRequisicao.getCodOperacao();
        
        assertEquals(esperado, recebido);
    }
    
    @Test
    public void testDecodificaRequisicaoListaUsuarios() throws IOException{
        
        MensagemUsuario mensagemRequisicao = new MensagemUsuario();
        mensagemRequisicao.requestListAll();
        
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemRequisicao.getMensagem());
        
        int esperado = 5;
        int recebido = mensagemRecebida.getCodOperacao();
        
        assertEquals(esperado, recebido);
    }
    
    /**
     * Teste compara o tamanho da lista, antes de codificar e após decodificar.
     * @throws IOException 
     */
    @Test
    public void testCodificaListaCodigoOperacaoTamanhoLista() throws IOException{
        List<Usuario> usuariosEnviados = new ArrayList<>();
        for(int i = 0; i < 5; i ++){
            Usuario usuario = new Usuario(i);
            usuario.setNome("Usuario "+i);
            usuario.setNomeAprovacao("Usuario");
            usuario.setCargo("Cargo "+i);
            usuario.setUnidade("Unidade "+i);
        }
        MensagemUsuario mensagemEnviada = new MensagemUsuario();
        mensagemEnviada.codificarListAll(usuariosEnviados);
        
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemEnviada.getMensagem());
        List<Usuario> usuariosRecebidos = mensagemRecebida.decodificarListAll();
        
        int esperado = usuariosEnviados.size();
        int obtido = usuariosRecebidos.size();
        
        assertEquals(esperado, obtido);
    }
    
    /**
     * Teste compara o todos os usários da lista, antes de codificar e após decodificar.
     * @throws IOException 
     */
    @Test
    public void testCodificaListaCodigoOperacaoComparaUsuarios() throws IOException{
        List<Usuario> usuariosEnviados = new ArrayList<>();
        
        for(int i = 0; i < 5; i ++){
            Usuario usuario = new Usuario(i);
            usuario.setNome("Usuario "+i);
            usuario.setNomeAprovacao("Usuario");
            usuario.setCargo("Cargo "+i);
            usuario.setUnidade("Unidade "+i);
            usuariosEnviados.add(usuario);
        }
        MensagemUsuario mensagemEnviada = new MensagemUsuario();
        mensagemEnviada.codificarListAll(usuariosEnviados);
        
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemEnviada.getMensagem());
        List<Usuario> usuariosRecebidos = mensagemRecebida.decodificarListAll();
        
        boolean igual = true;
        for(int i = 0; i < 5; i++){
            if( !(usuariosEnviados.get(i).igual(usuariosRecebidos.get(i))) ){
                igual = false;
                break;
            }
        }
        
        assertTrue(igual);
    }
}
