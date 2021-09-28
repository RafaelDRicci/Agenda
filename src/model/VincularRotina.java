/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rafaeld
 */
public abstract class VincularRotina {
    
    protected int codRotina;
    protected int codUsuario;
    protected boolean prioritario;
    protected boolean reagendavel;
    protected boolean horarioFixo;
    protected int[] horarios;

    public VincularRotina(int codRotina, int codUsuario) {
        this.codRotina = codRotina;
        this.codUsuario = codUsuario;
    }

    public int getCodRotina() {
        return codRotina;
    }

    public void setCodRotina(int codRotina) {
        this.codRotina = codRotina;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public boolean isReagendavel() {
        return reagendavel;
    }

    public void setReagendavel(boolean reagendavel) {
        this.reagendavel = reagendavel;
    }

    public boolean isHorarioFixo() {
        return horarioFixo;
    }

    public void setHorarioFixo(boolean horarioFixo) {
        this.horarioFixo = horarioFixo;
    }

    public int[] getHorarios() {
        return horarios;
    }

    public void setHorarios(int[] horarios) {
        this.horarios = horarios;
    }
    
    

    
}
