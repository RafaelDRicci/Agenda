/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.listarrotina;

import cliente.salvarrotina.SalvarRotinaView;

/**
 *
 * @author rafaeld
 */
public class ListarRotinaController {

    private ListarRotinaView view;
    private ListarRotinaHelper helper;
    
    ListarRotinaController(ListarRotinaView view) {
        this.view = view;
        helper = new ListarRotinaHelper(view);
    }

    void voltar() {
        view.dispose();
    }

    void editarRotina() {
        SalvarRotinaView salvarRotina = new SalvarRotinaView();
        salvarRotina.setLocationRelativeTo(view);
        salvarRotina.setVisible(true);
    }
    
}
