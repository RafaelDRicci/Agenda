/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.communication;

import java.io.IOException;
import java.text.ParseException;
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
public class ByteArraySuportTest {
    
    @Test
    public void testCodificaDecodificaRotina() throws IOException, ParseException{
        
        Rotina rotina = new Rotina(1, "Rotina 1");
        rotina.setDataLimite(Calendar.getInstance());
        rotina.setDescricao("");
        
        byte[] bytes = ByteArraySuport.codificaRotina(rotina);
        Rotina rotinaDecodificada = ByteArraySuport.decodificaRotina(bytes);
        
        boolean igual = rotina.igual(rotinaDecodificada);
        
        assertTrue(igual);
        
    }
    
    
    
}
