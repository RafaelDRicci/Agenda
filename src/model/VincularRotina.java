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
public class VincularRotina {
    
    protected Usuario usuario;
    protected Rotina rotina;
    protected boolean prioritario;
    protected boolean reagendavel;
    protected boolean horarioFixo;
    protected int[] horarios;
    protected String periodo;

    public VincularRotina(Rotina rotina, Usuario usuario) {
        this.rotina = rotina;
        this.usuario = usuario;
        periodo = this.getClass().getSimpleName();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rotina getRotina() {
        return rotina;
    }

    public void setRotina(Rotina rotina) {
        this.rotina = rotina;
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    @Override
    public String toString(){
        return this.rotina + " vinculada para " + this.usuario + " com período " + periodo;
    }
    
}
