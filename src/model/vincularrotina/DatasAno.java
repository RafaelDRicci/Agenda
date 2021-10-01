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
public class DatasAno extends VincularRotina{
    
    private int dia;
    private int[] meses;

    public DatasAno(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }

    public Integer getDia() {
        return dia;
    }

    public void setDias(Integer dia) {
        this.dia = dia;
    }

    public int[] getMeses() {
        return meses;
    }

    public void setMeses(int[] meses) {
        this.meses = meses;
    }
    
    
    
}
