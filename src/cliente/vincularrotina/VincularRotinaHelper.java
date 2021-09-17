/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import java.util.ArrayList;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaHelper {

    private VincularRotinaView view;
    
    VincularRotinaHelper(VincularRotinaView view) {
        this.view = view;
    }

    void addJComboBoxDia(ArrayList<Integer> nDias) {
        nDias.forEach(dia -> {
            view.getjComboBoxDia().addItem(dia);
        });
    }
    
}
