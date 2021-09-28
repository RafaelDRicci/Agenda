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
public class DiasUteis extends VincularRotina{
    
    private int[] diasUteis;
    
    public DiasUteis(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
        diasUteis = new int[23];
    }

    public int[] getDiasUteis() {
        return diasUteis;
    }

    public void setDiasUteis(int[] diasUteis) {
        this.diasUteis = diasUteis;
    }
    
    
    
}
