/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vincularrotina;

import model.VincularRotina;
import java.util.ArrayList;
import model.Rotina;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class Semanal extends VincularRotina{
    
    private int[] diasSemana;

    public Semanal(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }
    
    public int[] getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(int[] diasSemana) {
        this.diasSemana = diasSemana;
    }
    
}
