/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author rafaeld
 */
public class Data implements Comparable<Data>{
    
    private String nome;
    private boolean diaUtil;
    private GregorianCalendar data;

    public Data(String nome, boolean diaUtil, long timeInMillis) {
        this.nome = nome;
        this.diaUtil = diaUtil;
        data = new GregorianCalendar();
        data.setTimeInMillis(timeInMillis);
    }
    
    public Data(String nome, boolean diaUtil, GregorianCalendar data) {
        this.nome = nome;
        this.diaUtil = diaUtil;
        this.data = data;
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isDiaUtil() {
        return diaUtil;
    }

    public void setDiaUtil(boolean diaUtil) {
        this.diaUtil = diaUtil;
    }
    
    public GregorianCalendar getData() {
        return data;
    }

    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    
    @Override
    public int compareTo(Data data) {
        int dia = this.data.get(Calendar.DAY_OF_MONTH);
        int mes = this.data.get(Calendar.MONTH);
        int ano = this.data.get(Calendar.YEAR);
        
        int compDia = data.getData().get(Calendar.DAY_OF_MONTH);
        int compMes = data.getData().get(Calendar.MONTH);
        int compAno = data.getData().get(Calendar.YEAR);
        
        if(ano != compAno){
            return ano - compAno;
        } else 
            if(mes != compMes){
                return mes - compMes;
            } else return dia - compDia;
        
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - EEEE");
        return sdf.format(data.getTime()) + " - " +nome;
    }
    

    
}
