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
public class VincularRotinaSemanal extends VincularRotina{
    
    private ArrayList<Integer> diasSemana;
    
    public VincularRotinaSemanal(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
    }

    public ArrayList<Integer> getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(ArrayList<Integer> diasSemana) {
        this.diasSemana = diasSemana;
    }
    
}
