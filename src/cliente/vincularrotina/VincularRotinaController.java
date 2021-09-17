/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import java.util.ArrayList;
import java.util.Calendar;
import util.cliente.Data;


/**
 *
 * @author rafaeld
 */
public class VincularRotinaController {

    private VincularRotinaView view;
    private VincularRotinaHelper helper;
    
    VincularRotinaController(VincularRotinaView view) {
        this.view = view;
        helper = new VincularRotinaHelper(view);
    }

    void cancelar() {
        view.dispose();
    }

    void preencherDias() {
        String mes;
        if(view.getjComboBoxMes().isEnabled()){
            mes = (String) view.getjComboBoxMes().getSelectedItem(); 
        } else mes = "Janeiro";
            
        int ano = Calendar.getInstance().get(Calendar.YEAR);
        ArrayList<Integer> nDias = Data.nDiasMes(mes, ano);
        helper.addJComboBoxDia(nDias);
    }
    
    void preencerDiasUteis(){
        
    }
}
