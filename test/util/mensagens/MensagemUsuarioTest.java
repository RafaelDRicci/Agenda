/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class MensagemUsuarioTest {
    
    @Test
    public void testCodificarDecodificarListaUsuarios() throws IOException{
        
        List<Usuario> usuariosEnviado = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Usuario usuario = new Usuario(i);
            usuario.setNomeAprovacao("Usuário "+i);
            usuario.setCargo("Cargo "+i);
            usuario.setUnidade("Unidade "+i);
            usuario.setNomeAprovacao("Usuário "+i);
            usuariosEnviado.add(usuario);
        }
        
        MensagemUsuario mensagemEnviada = new MensagemUsuario();
        mensagemEnviada.codificar(usuariosEnviado);
        
        MensagemUsuario mensagemRecebida = new MensagemUsuario(mensagemEnviada.getMensagem());
        List<Usuario> usuariosRecebido = mensagemRecebida.decodificarList();
        
        boolean igual = true;
        
        for(int i = 0; i < usuariosEnviado.size(); i++){
            if(usuariosEnviado.get(i).igual(usuariosRecebido.get(i))){
                igual = false;
                break;
            }
        }
        assertTrue(igual);
    }
    
}
