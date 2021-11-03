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
    public String toString() {
        String stringHorarios = "";
        for(int horario: horarios){
            if(stringHorarios.equals("")){
                stringHorarios += horario+"h";
            }else stringHorarios += " "+horario+"h";
            
        }
        return 
                "Usuario: " + usuario + "\n"
              + "Rotina: " + rotina + "\n"
              + "Prioritario: " + (prioritario ? " Sim" : " Não") + "\n"
              + "Reagendavel: " + (reagendavel ? " Sim" : " Não") + "\n"
              + "Horario Fixo: " + (horarioFixo ? " Sim" : " Não") + "\n"
              + "Horarios: " + stringHorarios + "\n"
              + "Periodo: " + periodo;
    }
    

    
    public boolean comparaValoresArraysInt(int[] array1, int[] array2){
        
        if(array1.length != array2.length) return false;
        
        boolean igual = true;
        
        for(int i = 0; i < array1.length; i++){
            if(array1[i] != array2[i]){
                igual = false;
                break;
            }
        }
        
        return igual;
        
    }
    
    public boolean comparaValoresArraysString(String[] array1, String[] array2){
        
        if(array1.length != array2.length) return false;
        boolean igual = true;
        
        for(int i = 0; i < array1.length; i++){
            if(!array1[i].equals(array2[i])){
                igual = false;
                break;
            }
        }
        
        return igual;

    }
    
    
    public String converteHorasEmString(){
        String horas = "";
        for (int i = 0; i < horarios.length; i++){
            horas += horarios[i]+"h ";
        }
        
        return horas;
    }
    
    public boolean igual(VincularRotina vincularRotina){
        
        return ((rotina.igual(vincularRotina.getRotina()) && (usuario.igual(vincularRotina.getUsuario()))  &&
                (this.horarioFixo == vincularRotina.isHorarioFixo()) && (this.prioritario == vincularRotina.isPrioritario()) &&
                (this.reagendavel == vincularRotina.isReagendavel()) && (comparaValoresArraysInt(this.horarios, vincularRotina.getHorarios())) &&  
                (this.periodo.equals(vincularRotina.getPeriodo())) ));
    }
    
}
