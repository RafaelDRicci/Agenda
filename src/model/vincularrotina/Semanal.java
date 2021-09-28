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
public class Semanal extends VincularRotina{
    
    private int[] diasSemana;
    
    public Semanal(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
        diasSemana = new int[7];
    }

    public int[] getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(int[] diasSemana) {
        this.diasSemana = diasSemana;
    }
    
}
