/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import java.util.ArrayList;
import java.util.List;
import model.Rotina;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaHelper {

    private VincularRotinaView view;
    
    VincularRotinaHelper(VincularRotinaView view) {
        this.view = view;
    }   

    void preencherRotinas(List<Rotina> rotinas) {
       for(Rotina rotina: rotinas){
           view.getjComboBoxRotina().addItem(rotina);
       }
    }

    void preencherUsuarios(List<Usuario> usuarios) {
        for(Usuario usuario : usuarios){
            view.getjComboBoxFuncionario().addItem(usuario);
        }
    }
}
