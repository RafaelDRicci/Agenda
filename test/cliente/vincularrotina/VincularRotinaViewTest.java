/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaViewTest {
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntJaneiro(){
        
        int esperado = 0;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Janeiro");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntFevereiro(){
        
        int esperado = 1;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Fevereiro");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntMarco(){
        
        int esperado = 2;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Março");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntAbril(){
        
        int esperado = 3;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Abril");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntMaio(){
        
        int esperado = 4;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Maio");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntJunho(){
        
        int esperado = 5;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Junho");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntJulho(){
        
        int esperado = 6;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Julho");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntAgosto(){
        
        int esperado = 7;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Agosto");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntSetembro(){
        
        int esperado = 8;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Setembro");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntOutubro(){
        
        int esperado = 9;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Outubro");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntNovembro(){
        
        int esperado = 10;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Novembro");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testVincularRotinaHelperConverteMesStringEmIntDezembro(){
        
        int esperado = 11;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("Dezembro");
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testVincularRotinaHelperConverteMesStringEmIntMesNaoExiste(){
        
        int esperado = 0;
        int obtido = VincularRotinaHelper.converteMesStringEmInt("BlaBla");
        
        assertEquals(esperado, obtido);
        
    }
    
    
}
