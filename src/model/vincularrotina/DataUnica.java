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
public class DataUnica extends VincularRotina{
    
    private Calendar data;

    public DataUnica(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public void setData(long millis){
        data = new GregorianCalendar();
        data.setTimeInMillis(millis);
    }

    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy - EEEE");
        return super.toString() + "\n"
                +sdf.format(data.getTime());
    }
    
}
