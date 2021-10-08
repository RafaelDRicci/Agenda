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
public class DatasAno extends VincularRotina{
    
    private int dia;
    private int[] meses;

    public DatasAno(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public int[] getMeses() {
        return meses;
    }

    public void setMeses(int[] meses) {
        this.meses = meses;
    }
    
    @Override
    public String toString(){
        
        String string = super.toString()+":";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM");
        GregorianCalendar calendar = new GregorianCalendar();
        for(int i = 0; i < meses.length; i++){
            calendar.set(calendar.get(Calendar.YEAR), meses[i]-1, dia);
            string += " "+sdf.format(calendar.getTime());
        }
        
        return string;
    }
    
}
