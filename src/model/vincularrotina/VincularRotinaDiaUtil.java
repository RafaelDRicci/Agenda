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
public class VincularRotinaDiaUtil extends VincularRotina{
    
    private ArrayList<Integer> diasUteis;
    
    public VincularRotinaDiaUtil(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
    }

    public ArrayList<Integer> getDiasUteis() {
        return diasUteis;
    }

    public void setDiasUteis(ArrayList<Integer> diasUteis) {
        this.diasUteis = diasUteis;
    }
    
    
    
}
