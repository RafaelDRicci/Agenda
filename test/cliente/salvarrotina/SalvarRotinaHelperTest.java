/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.salvarrotina;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.Rotina;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rafaeld
 */
public class SalvarRotinaHelperTest {
    private SalvarRotinaView view;
            
    public SalvarRotinaHelperTest() {
        view = new SalvarRotinaView();
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    @Test
    public void testNovaRotinaApenasNome() throws Exception {
        
        SalvarRotinaHelper helper = new SalvarRotinaHelper(view);
        view.getjTextFieldNomeRotina().setText("Rotina 1");
        
        Rotina novaRotina = helper.novaRotina();
        
        String esperado = "Rotina 1";
        String obtido = novaRotina.getNome();
        
        assertEquals(esperado, obtido);
        
    }
    
    @Test
    public void testNovaRotinaNomeData() throws ParseException, Exception{
        
        SalvarRotinaHelper helper = new SalvarRotinaHelper(view);
        view.getjTextFieldNomeRotina().setText("Rotina 1");
        view.getjTextFieldDataLimite().setText("03/05/2025");
        
        Rotina novaRotina = helper.novaRotina();
        
        String esperado = "03/05/2025";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String obtido = sdf.format(novaRotina.getDataLimite().getTime());
        assertEquals(esperado, obtido);
    }
    
    @Test
    public void testNovaRotinaSemData() throws ParseException, Exception{
        SalvarRotinaHelper helper = new SalvarRotinaHelper(view);
        view.getjTextFieldNomeRotina().setText("Rotina 1");
        
        Rotina novaRotina = helper.novaRotina();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        GregorianCalendar dataEsperada = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR)+10, 0, 1);
        String esperado = sdf.format(dataEsperada.getTime());
        Calendar dataObtida = novaRotina.getDataLimite();
        String obtido = sdf.format(dataObtida.getTime());
        
        assertEquals(esperado, obtido);
    }
    
}
