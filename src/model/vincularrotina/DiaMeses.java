/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vincularrotina;

import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class DiaMeses extends VincularRotina{
    
    private int[] dias;
    
    public DiaMeses(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
    }

    public int[] getDias() {
        return dias;
    }

    public void setDias(int[] dias) {
        this.dias = dias;
    }
    
    
    
}
