/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.justificarrotina;

import cliente.agendarrotina.AgendarRotinaView;

/**
 *
 * @author rafaeld
 */
public class JustificarRotinaController {

    private JustificarRotinaView view;
    private JustificarRotinaHelper helper;
    
    public JustificarRotinaController(JustificarRotinaView view){
        this.view = view;
        helper = new JustificarRotinaHelper(view);
    }
    
    
    void reagendarRotina() {
        AgendarRotinaView reagendar = new AgendarRotinaView();
        reagendar.setLocationRelativeTo(view);
        reagendar.setVisible(true);
    }

    void cancelar() {
        view.dispose();
    }
    
}
