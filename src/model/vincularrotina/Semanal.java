/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.vincularrotina;

import java.text.SimpleDateFormat;
import model.VincularRotina;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.Rotina;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class Semanal extends VincularRotina{
    
    private int[] diasSemana;

    public Semanal(Rotina rotina, Usuario usuario) {
        super(rotina, usuario);
    }
    
    public int[] getDiasSemana() {
        return diasSemana;
    }

    public void setDiasSemana(int[] diasSemana) {
        this.diasSemana = diasSemana;
    }
    
    @Override
    public String toString(){
        
        String string = super.toString()+": ";
        String diaSemana = "";
        for(int i = 0; i < diasSemana.length; i++){
            switch (diasSemana[i]){
                case 1:
                    diaSemana = "Domingo";
                    break;
                case 2:
                    diaSemana = "Segunda-Feira";
                    break;
                case 3:
                    diaSemana = "Terça-Feira";
                    break;
                case 4:
                    diaSemana = "Quarta-Feira";
                    break;
                case 5:
                    diaSemana = "Quinta-Feira";
                    break;
                case 6:
                    diaSemana = "Sexta-Feira";
                    break;
                case 7:
                    diaSemana = "Sábado";
                    break;
            }
            string += " "+diaSemana;
        }
        
        return string;
        
    }
    
}
