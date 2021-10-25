/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import model.vincularrotina.DataUnica;
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
    
    @Test
    public void testCodificaDecodificaObjeto() throws IOException{
        
        Rotina rotina = new Rotina(1, "Rotina 1");
        Usuario usuario = new Usuario(1);
        usuario.setNome("Usuario 1");
        usuario.setNomeAprovacao("Usuario");
        
        VincularRotina vinculacaoEnviada = new VincularRotina(rotina, usuario);
        int[] horarios = {};
        vinculacaoEnviada.setHorarios(horarios);
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        mensagemEnviada.setByte((byte)0);
        mensagemEnviada.codificarObjeto(vinculacaoEnviada);
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(mensagemEnviada.getMensagem());
        VincularRotina vinculacaoRecebida = mensagemRecebida.decodificarObjeto();
        
        assertTrue(vinculacaoEnviada.igual(vinculacaoRecebida));
        
    }
    
    
    
    @Test
    public void testCodificarDecodificarVincularRotina() throws IOException{
        
        Rotina rotina = new Rotina(1, "Rotina 1");
        Usuario usuario = new Usuario(1);
        usuario.setNome("Usuario 1");
        usuario.setNomeAprovacao("Usuario");
        
        VincularRotina vinculacaoEnviada = new VincularRotina(rotina, usuario);
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        mensagemEnviada.codificarVincularRotina(vinculacaoEnviada);
        
        byte[] arrayEnviado = mensagemEnviada.getMensagem();
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(arrayEnviado);
        VincularRotina vinculacaoRecebida = mensagemRecebida.decodificarVincularRotina();
        
        String esperado = vinculacaoEnviada.toString();
        String recebido = vinculacaoRecebida.toString();

        assertEquals(esperado, recebido);
        
    }
    
    @Test
    public void testCodificarDecodificarDataUnica() throws IOException{
        
        Rotina rotina = new Rotina(1, "Rotina 1");
        Usuario usuario = new Usuario(1);
        usuario.setNome("Usuario 1");
        usuario.setNomeAprovacao("Usuario");
        
        DataUnica vinculacaoEnviada = new DataUnica(rotina, usuario);
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        mensagemEnviada.codificarVincularRotina(vinculacaoEnviada);
        
        byte[] arrayEnviado = mensagemEnviada.getMensagem();
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(arrayEnviado);
        DataUnica vinculacaoRecebida = (DataUnica) mensagemRecebida.decodificarVincularRotina();
        
        String esperado = vinculacaoEnviada.toString();
        String recebido = vinculacaoRecebida.toString();

        assertEquals(esperado, recebido);
        
    }
    
    @Test
    public void tesCodificarDecodificarCreate() throws IOException{
        
        Rotina rotina = new Rotina(1, "Rotina 1");
        Usuario usuario = new Usuario(1);
        usuario.setNome("Usuario 1");
        usuario.setNomeAprovacao("Usuario");
        
        VincularRotina vinculacaoEnviada = new VincularRotina(rotina, usuario);
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        mensagemEnviada.codificarCreate(vinculacaoEnviada);
        
        byte[] arrayEnviado = mensagemEnviada.getMensagem();
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(arrayEnviado);
        VincularRotina vinculacaoRecebida = mensagemRecebida.decodificarCreate();
        
        String esperado = vinculacaoEnviada.toString();
        String recebido = vinculacaoRecebida.toString();

        assertEquals(esperado, recebido);
        
    }
    
    
    @Test
    public void testReadCodRotina()throws IOException{
        
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        Rotina rotina = new Rotina(1);
        Usuario usuario = new Usuario(1);
        mensagemEnviada.codificarReadVincularRotina(rotina, usuario);
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(mensagemEnviada.getMensagem());
        int[] codigos = mensagemRecebida.decodificarRead();
        
        int esperado = 1;
        int recebido = codigos[0];
        
        assertEquals(esperado, recebido);
        
    }
    
     @Test
    public void testReadCodUsuario()throws IOException{
        
        MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
        Rotina rotina = new Rotina(1);
        Usuario usuario = new Usuario(1);
        mensagemEnviada.codificarReadVincularRotina(rotina, usuario);
        
        MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(mensagemEnviada.getMensagem());
        int[] codigos = mensagemRecebida.decodificarRead();
        
        int esperado = 1;
        int recebido = codigos[1];
        
        assertEquals(esperado, recebido);
        
    }
    
    @Test
    public void testCodificaDecodificaListaVinculacao() throws IOException{
        
            List<VincularRotina> vinculacoesEnviadas = new ArrayList<>();

            for(int i = 0; i < 5; i++){
                Rotina rotina = new Rotina(i, "Rotina "+i);
                Usuario usuario = new Usuario(i);
                VincularRotina vincular = new VincularRotina(rotina, usuario);
                int[] horarios = {};
                vincular.setHorarios(horarios);
                vinculacoesEnviadas.add(vincular);
            }
            
            MensagemVincularRotina mensagemEnviada = new MensagemVincularRotina();
            mensagemEnviada.codificarList(vinculacoesEnviadas);
            
            MensagemVincularRotina mensagemRecebida = new MensagemVincularRotina(mensagemEnviada.getMensagem());
            List<VincularRotina> vinculacoesRecebidas = mensagemRecebida.decodificarList();
            
            boolean igual = true;
            
            for(int i = 0; i < 5; i++){
                if(!(vinculacoesEnviadas.get(i).igual(vinculacoesRecebidas.get(i)))){
                    igual = false;
                    break;
                }
            }
            
            assertTrue(igual);
            
    }
}
