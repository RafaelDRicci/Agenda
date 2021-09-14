/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.salvarrotina;

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
    
}
