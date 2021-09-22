/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.date;

import java.util.Calendar;

/**
 *
 * @author rafaeld
 */
public class Data {
    
    private String nome;
    private boolean diaUtil;
    private Calendar data;

    public Data(String nome, boolean diaUtil, Calendar data) {
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

    
    
    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }
    

    
}
