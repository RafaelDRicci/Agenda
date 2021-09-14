/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.agendarrotina;

/**
 *
 * @author rafaeld
 */
public class AgendarRotinaController {

    private AgendarRotinaView view;
    private AgendarRotinaHelper helper;
    
    public AgendarRotinaController(AgendarRotinaView view) {
        this.view = view;
        helper = new AgendarRotinaHelper(view);
    }

    void cancelar() {
        view.dispose();
    }
    
}
