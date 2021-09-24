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
public class VincularRotinaAnual extends VincularRotina{
    
    private int dia;
    private int ano;
    
    public VincularRotinaAnual(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
    
}
