/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vincularrotina;

import model.VincularRotina;
import java.util.ArrayList;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaDiaMes extends VincularRotina{
    
    private ArrayList<Integer> dias;
    
    public VincularRotinaDiaMes(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
    }

    public ArrayList<Integer> getDias() {
        return dias;
    }

    public void setDias(ArrayList<Integer> dias) {
        this.dias = dias;
    }
    
    
    
}
