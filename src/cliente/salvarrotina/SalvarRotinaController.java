/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.salvarrotina;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Rotina;

/**
 *
 * @author rafaeld
 */
public class SalvarRotinaController {

    private SalvarRotinaView view;
    private SalvarRotinaHelper helper;
    
    public SalvarRotinaController(SalvarRotinaView view) {
        this.view = view;
        helper = new SalvarRotinaHelper(view);
    }
    
    public void cancelar(){
        view.dispose();
    }

    public void novaRotina() {
        try {
            
            Rotina novaRotina = helper.novaRotina();
            
            
        } catch (Exception ex) {
            
            Logger.getLogger(SalvarRotinaController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(view, ex.getMessage(), "ERRO AO SALVAR A ROTINA", 0);
            
        }
    }
    
}
