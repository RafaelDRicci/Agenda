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
    
    public VincularRotinaController(VincularRotinaView view) {
        this.view = view;
        helper = new VincularRotinaHelper(view);
    }

    public void cancelar() {
        view.dispose();
    }
}
