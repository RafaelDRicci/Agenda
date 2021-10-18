/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
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
    

    @Test 
    public void compararCodigoMensagem() throws IOException{
        MensagemRotina enviada = new MensagemRotina();
        
        List<Rotina> rotinasEnviadas = new ArrayList<>();
        for(int i = 0 ; i < 5; i++){
            Rotina rotina = new Rotina(i, "Rotina "+i);
            rotinasEnviadas.add(rotina);
        }
        
        enviada.codificar(rotinasEnviadas);
        MensagemRotina recebida = new MensagemRotina(enviada.getMensagem());
        
        byte esperada = enviada.getMensagem()[0];
        
        byte obtida = recebida.getByte();
        assertEquals(esperada, obtida);
    }
    
    @Test
    public void testTamanhoListaRotinas() throws IOException{
        MensagemRotina enviada = new MensagemRotina();
        
        List<Rotina> rotinasEnviadas = new ArrayList<>();
        for(int i = 0 ; i < 30; i++){
            Rotina rotina = new Rotina(i, "Rotina "+i);
            rotinasEnviadas.add(rotina);
        }
       
        enviada.setInt(rotinasEnviadas.size());

        MensagemRotina recebida = new MensagemRotina(enviada.getMensagem());
        
        int esperado = rotinasEnviadas.size();

        byte codMensagem = recebida.getByte();
        int obtido = recebida.getInt();
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void enviarListaDeRotinas() throws IOException{
        MensagemRotina enviada = new MensagemRotina();
        
        List<Rotina> rotinasEnviadas = new ArrayList<>();
        for(int i = 0 ; i < 5; i++){
            Rotina rotina = new Rotina(i, "Rotina "+i);
            rotinasEnviadas.add(rotina);
            
        }
        
        enviada.codificar(rotinasEnviadas);
        MensagemRotina recebida = new MensagemRotina(enviada.getMensagem());
        List<Rotina> rotinasRecebidas = recebida.decodificarList();
        
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
