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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class MensagemRotinaTest {
    
    /**
     * Testando o código da Mensagem Rotina (2), codificando uma nova rotina
     * @throws IOException 
     */
    @Test
    public void testCodigoMensagemRotinaCodificando() throws IOException{
        
        Rotina rotinaEnviada = new Rotina("Rotina 1");
        MensagemRotina mensagemEnviada = new MensagemRotina();
        mensagemEnviada.codificarRotina(rotinaEnviada);
        
        int esperado = 2;
        int obtido = mensagemEnviada.getCodMensagem();
        
        assertEquals(esperado, obtido);
        
    }
    
    /**
     * Testando o código Mensagem Rotina (2), decodificando uma rotina
     * @throws IOException 
     */
    @Test
    public void testCodigoMensagemRotinaDecodificando()throws IOException{
        
        Rotina rotinaEnviada = new Rotina("Rotina 1");
        MensagemRotina mensagemEnviada = new MensagemRotina();
        mensagemEnviada.codificarRotina(rotinaEnviada);
        
        MensagemRotina mensagemRecebida = new MensagemRotina(mensagemEnviada.getMensagem());
        mensagemRecebida.decodificarRotina();
        
        byte esperado = 2;
        byte obtido = mensagemRecebida.getCodMensagem();
        
        assertEquals(esperado, obtido);
        
    }
    
    /**
     * Cria uma rotina, codifica e decodifica. Com a rotida decodificada cria uma nova rotina. 
     * No final os códigos das rotinas ( atributo codRotina) são comparados.
     */
    @Test
    public void testComparandoRotinaCodificadaDecodificadaCodRotina() throws IOException{
        
        Rotina rotinaEnviada = new Rotina(1, "Rotina 1");
        rotinaEnviada.setDataLimite(Calendar.getInstance().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        rotinaEnviada.setDescricao("1 Rotina 1 "+sdf.format(rotinaEnviada.getDataLimite().getTime()));
        
        MensagemRotina mensagemEnviada = new MensagemRotina();
        mensagemEnviada.codificarRotina(rotinaEnviada);
        
        MensagemRotina mensagemRecebida = new MensagemRotina(mensagemEnviada.getMensagem());
        Rotina rotinaRecebida = mensagemRecebida.decodificarRotina();
        
        assertEquals(rotinaEnviada.getCodRotina(), rotinaRecebida.getCodRotina());
        
    }
    /**
     * Teste que codifica e decodifica uma rotina e depois compara todos os atributos 
     */
    @Test
    public void testComparandoRotinaCodificadaDecodificadaTodosAtributos() throws IOException{
        
        Rotina rotinaEnviada = new Rotina(1, "Rotina 1");
        rotinaEnviada.setDataLimite(Calendar.getInstance().getTime());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        rotinaEnviada.setDescricao("1 Rotina 1 "+sdf.format(rotinaEnviada.getDataLimite().getTime()));
        
        MensagemRotina mensagemEnviada = new MensagemRotina();
        mensagemEnviada.codificarRotina(rotinaEnviada);
        
        MensagemRotina mensagemRecebida = new MensagemRotina(mensagemEnviada.getMensagem());
        Rotina rotinaRecebida = mensagemRecebida.decodificarRotina();
        
        assertTrue(rotinaEnviada.igual(rotinaRecebida));
        
    }
    
    
    /**
     * Testando código da operacao Codificar Create (1)
     * @throws IOException 
     */
    @Test
    public void testCodigoOperacaoCodificarCreateRotina()throws IOException{
        
        Rotina rotinaEnviada = new Rotina("Rotina 1");
        MensagemRotina mensagemEnviada = new MensagemRotina();
        mensagemEnviada.codificarCreate(rotinaEnviada);
        
        int esperado = 1;
        int obtido = mensagemEnviada.getCodOperacao();
        
        assertEquals(esperado, obtido);
        
    }
    /**
     * Testando código da operação Decodificar Create (0)
     * @throws IOException 
     */
    @Test
    public void testCodigoOperacaoDecodificarCreateRotina() throws IOException{
        
        Rotina rotinaEnviada = new Rotina("Rotina 1");
        MensagemRotina mensagemEnviada = new MensagemRotina();
        mensagemEnviada.codificarCreate(rotinaEnviada);
        
        MensagemRotina mensagemRecebida = new MensagemRotina(mensagemEnviada.getMensagem());
        mensagemRecebida.decodificarCreate();
        
        int esperado = 1;
        int obtido = mensagemRecebida.getCodOperacao();
        
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testTamanhoListaRotinas() throws IOException{
        MensagemRotina mensagemEnviada = new MensagemRotina();
        
        List<Rotina> rotinasEnviadas = new ArrayList<>();
        for(int i = 0 ; i < 30; i++){
            Rotina rotina = new Rotina(i, "Rotina "+i);
            rotinasEnviadas.add(rotina);
        }
       
        mensagemEnviada.codificarList(rotinasEnviadas);
        MensagemRotina mensagemRecebida = new MensagemRotina(mensagemEnviada.getMensagem());
        
        List<Rotina> rotinasRecebidas = mensagemRecebida.decodificarList();
        
        int esperado = rotinasEnviadas.size();
        int obtido = rotinasRecebidas.size();
        
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void enviarListaDeRotinas() throws IOException{
        MensagemRotina mensagemEnviada = new MensagemRotina();
        
        List<Rotina> rotinasEnviadas = new ArrayList<>();
        for(int i = 0 ; i < 5; i++){
            Rotina rotina = new Rotina(i, "Rotina "+i);
            rotinasEnviadas.add(rotina);
            
        }
        
        mensagemEnviada.codificarList(rotinasEnviadas);
        MensagemRotina mensagemRecebida = new MensagemRotina(mensagemEnviada.getMensagem());
        List<Rotina> rotinasRecebidas = mensagemRecebida.decodificarList();
        
        boolean igual = true;
        
        for(int i = 0; i < rotinasEnviadas.size(); i++){

            if(!rotinasEnviadas.get(i).igual(rotinasRecebidas.get(i))){
                igual = false;
                break;
            }
        }
        
        assertTrue(igual);
    }
}
