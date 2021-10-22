/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import model.Rotina;
import model.Usuario;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class MensagemVincularRotinaTest {
    
    @Test
    public void testCodificaDecodificaRotina() throws IOException{
        
        Rotina rotinaEnviada = new Rotina(1, "Rotina 1");
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        mensagemEnviada.setByte((byte)0);
        mensagemEnviada.setRotina(rotinaEnviada);
        
        byte[] array = mensagemEnviada.getMensagem();
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(array);
        Rotina rotinaRecebida = mensagemRecebida.getRotina();
        assertTrue(rotinaEnviada.igual(rotinaRecebida));
        
    }
    
    @Test
    public void testCodificaDecodificaUsuario() throws IOException{
        
        Usuario usuarioEnviada = new Usuario(1);
        usuarioEnviada.setNome("Usuario 1");
        usuarioEnviada.setNomeAprovacao("Usuario");
        
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        mensagemEnviada.setByte((byte)0);
        mensagemEnviada.setUsuario(usuarioEnviada);
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(mensagemEnviada.getMensagem());
        Usuario usuarioRecebido = mensagemRecebida.getUsuario();
        
        assertTrue(usuarioRecebido.igual(usuarioEnviada));
    }
    
}
