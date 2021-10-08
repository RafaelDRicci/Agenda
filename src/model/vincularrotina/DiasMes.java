/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vincularrotina;

import model.Rotina;
import model.Usuario;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class DiasMes extends VincularRotina{
    
    private int[] dias;

    public DiasMes(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }

    public int[] getDias() {
        return dias;
    }

    public void setDias(int[] dias) {
        this.dias = dias;
    }
    
    
    @Override
    public String toString(){
        String string = super.toString()+": Dia(s)";
        
        for(int i = 0; i < dias.length; i++){
            string += String.format(" %02d", dias[i]);
        }
        
        return string;
    }
    
}
