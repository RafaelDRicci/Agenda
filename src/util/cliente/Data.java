/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.cliente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author rafaeld
 */
public class Data {
    
    private GregorianCalendar data;
    private boolean diaUtil;
    
    
    public Data(int dia, int mes, int ano){
        data = new GregorianCalendar(ano, mes--, dia);
        diaUtil = isDiaUtil(data);
    }
    
    
    public static ArrayList<Integer> nDiasMes(String mes, int ano){
        
        ArrayList<Integer> dias = new ArrayList<>();
   
        dias.add(1); dias.add(2); dias.add(3); dias.add(4); 
        dias.add(5); dias.add(6); dias.add(7); dias.add(8);
        dias.add(9); dias.add(10);dias.add(11); dias.add(12);
        dias.add(13); dias.add(14); dias.add(15); dias.add(16); 
        dias.add(17); dias.add(18);dias.add(19); dias.add(20);
        dias.add(21); dias.add(22);dias.add(23); dias.add(24);
        dias.add(25); dias.add(26); dias.add(27); dias.add(28); 
        
        if(mes.equals("Janeiro") || mes.equals("Março") || mes.equals("Maio") || mes.equals("Julho") 
                || mes.equals("Agosto") || mes.equals("Outubro") || mes.equals("Dezembro")){
            dias.add(29); dias.add(30); dias.add(31);
        }
        
        if(mes.equals("Abril") || mes.equals("Junho") || mes.equals("Setembro") || mes.equals("Novembro")) {
            dias.add(29); dias.add(30);
        }
        
        if(mes.equals("Fevereiro") && anoBissexto(ano)){
            dias.add(29);
        }
        return dias;
    }
    
    public static boolean anoBissexto(int ano){
        return ano%400 == 0 || (ano%100 != 0 && ano%4 == 0);       
    }
    
    public static boolean isDiaUtil(Calendar data){
        int diaDaSemana = data.get(Calendar.DAY_OF_WEEK);
        return !(diaDaSemana == 1 || diaDaSemana == 7);
        
    }
    
    public static ArrayList<String> diasUteis(int mes, int ano){
        
        GregorianCalendar calendar = new GregorianCalendar(ano, mes, 1);
        
        int diaUtil = 0;
        ArrayList<String> diasUteis = new ArrayList<>();
        while(calendar.get(Calendar.MONTH) == mes){
            if(isDiaUtil(calendar)){
                diaUtil++;
                diasUteis.add(diaUtil+"°");
            }
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        
        return diasUteis;
    }
    
    public static void main(String[] args) {
        
        ArrayList<String> diasUteis = diasUteis(1, 2021);
       diasUteis.forEach(diaUtil -> {
           System.out.println(diaUtil+" Dia Útil");
       });
    }

    public GregorianCalendar getData() {
        return data;
    }

    public boolean isDiaUtil() {
        return diaUtil;
    }
    
    
}   
