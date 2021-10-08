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
public class DiasUteis extends VincularRotina{
    
    private int[] diasUteis;

    public DiasUteis(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }
    
    public int[] getDiasUteis() {
        return diasUteis;
    }

    public void setDiasUteis(int[] diasUteis) {
        this.diasUteis = diasUteis;
    }
    
    @Override
    public String toString(){
        String string = super.toString()+":";
        
        for(int i = 0; i < diasUteis.length; i++){
            string += " "+diasUteis[i]+"°";
        }
        
        return string;
    }
    
    
}
