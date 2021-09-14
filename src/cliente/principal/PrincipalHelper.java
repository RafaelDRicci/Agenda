/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.principal;

import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class PrincipalHelper {
    private PrincipalView view;
    
    public PrincipalHelper(PrincipalView view){
        this.view = view;
    }
    
    public void setUsuario(Usuario usuario){
        view.getJLabelNome().setText(usuario.toString());
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //(sdf.format(usuario.getDataCadastro()));
        //view.getjLabelDataValidacao().setText(sdf.format(usuario.getDataValidacao()));
        view.getjLabelCargo().setText(usuario.getCargo());
        view.getjLabelUnidade().setText(usuario.getUnidade());
    }
    
}
