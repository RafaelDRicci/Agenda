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
public class VincularRotinaDataUnica extends VincularRotina{
    
    private Integer dia;
    private Integer mes;
    private Integer ano;
    
    public VincularRotinaDataUnica(int codRotina, int codUsuario) {
        super(codRotina, codUsuario);
    }

    public Integer getDias() {
        return dia;
    }

    public void setDias(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMeses(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAnos(Integer anos) {
        this.ano = anos;
    }
    
}
