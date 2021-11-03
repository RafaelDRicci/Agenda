/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.listarrotinas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class ListarRotinasHelper {
    
    private ListarRotinasView view;
    
    public ListarRotinasHelper(ListarRotinasView view){
        this.view = view;
    }
}
