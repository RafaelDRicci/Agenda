/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import java.util.Calendar;


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
    
    void preencerAno() {
        Calendar dataHoje = Calendar.getInstance();
        int ano = dataHoje.get(Calendar.YEAR);
        for(int i = 0; i < 10; i++){
            view.getjComboBoxAno().addItem(ano+i);
        }
    }
}
