/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vincularrotina;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class Anual extends VincularRotina{
    
    private int dia;
    private int mes;

    public Anual(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    @Override
    public String toString(){
        
        String string = super.toString()+":";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM");
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.set(calendar.get(Calendar.YEAR), mes-1, dia);
        string += " "+sdf.format(calendar.getTime());
       
        return string;
    }
    
}
