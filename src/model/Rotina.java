/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author rafaeld
 */
public class Rotina implements Comparable<Rotina>{
    private int codRotina;
    private String nome;
    private Calendar dataLimite;
    private String descricao;

    public Rotina(int cod, String nome) {
        this.codRotina = cod;
        this.nome = nome;
        dataLimite = new GregorianCalendar();
        dataLimite.set(Calendar.getInstance().get(Calendar.YEAR)+10, 0, 1);
        descricao = nome;
    }

    public Rotina(String nome) {
        this.nome = nome;
        dataLimite = new GregorianCalendar();
        dataLimite.set(Calendar.getInstance().get(Calendar.YEAR)+10, 0, 1);
    }
    
    public Rotina(int codRotina){
        this.codRotina = codRotina;
        dataLimite = new GregorianCalendar();
        dataLimite.set(Calendar.getInstance().get(Calendar.YEAR)+10, 0, 1);
    }
    
    public void setCodRotin(int cod){
        this.codRotina = cod;
    }
    
    public int getCodRotina(){
        return this.codRotina;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(Calendar dataLimite) {
        this.dataLimite = dataLimite;
    }

    public void setDataLimite(Date data){
        dataLimite = new GregorianCalendar();
        dataLimite.setTimeInMillis(data.getTime());

    }
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    } 
    
    @Override
    public String toString(){
        return nome;
    }
   

    @Override
    public int compareTo(Rotina rotina) {
        return this.getCodRotina() - rotina.getCodRotina();
    }
    
    public boolean igual(Rotina rotina){
        return  (this.getCodRotina() == rotina.getCodRotina()) && 
                (this.getDataLimite().get(Calendar.DAY_OF_MONTH) == rotina.getDataLimite().get(Calendar.DAY_OF_MONTH)) &&
                (this.getDataLimite().get(Calendar.MONTH) == rotina.getDataLimite().get(Calendar.MONTH)) &&
                (this.getDataLimite().get(Calendar.YEAR) == rotina.getDataLimite().get(Calendar.YEAR)) &&
                (this.getDescricao().equals(rotina.getDescricao())) &&
                (this.getNome().equals(rotina.getNome()));
    }
}
