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
public class VincularRotinaDatasAno extends VincularRotina{
    
    private Integer dia;
    private ArrayList<Integer> meses;
    
    public VincularRotinaDatasAno(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
    }

    public Integer getDia() {
        return dia;
    }

    public void setDias(Integer dia) {
        this.dia = dia;
    }

    public ArrayList<Integer> getMeses() {
        return meses;
    }

    public void setMeses(ArrayList<Integer> meses) {
        this.meses = meses;
    }
    
    
    
}
