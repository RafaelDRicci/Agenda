/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author rafaeld
 */
public class Calendario {
    
    private GregorianCalendar data;
    private boolean diaUtil;
    
    public Calendario(int dia, int mes, int ano){
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
    /**
     * 
     * @param ano
     * @return Calendar
     * Calcula a data da páscoa em um ano. Para o calculo foi utilizado o algoritmo de Meeus/Jones/Butcher
     */
    public static Calendar calculaPascoa(int ano){
       
        int a = ano%19;
        int b = ano/100;
        int c = ano%100;
        int d = b/4;
        int e = b%4;
        int f = (b + 8)/25;
        int g = (b - f + 1)/3;
        int h = (19 * a + b - d - g + 15)%30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int  m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114)/ 31;
        int dia = 1+ (h + l - 7 * m + 114)%31;
        mes--;
        return new GregorianCalendar(ano, mes, dia);
    }
    
    public static List<Data> feriados(int ano){
        
        List<Data> feriados = new ArrayList<>();
        
        feriados.add(new Data("Ano novo", false, (new GregorianCalendar( ano, 0, 1))));
        
        Calendar pascoa = calculaPascoa(ano);
        feriados.add(new Data("Páscoa", false, ( new GregorianCalendar( ano, pascoa.get(Calendar.MONTH), pascoa.get(Calendar.DAY_OF_MONTH)) )) );
        pascoa.add(Calendar.DAY_OF_MONTH, -48);
        feriados.add( new Data("Carnaval", false, ( new GregorianCalendar( ano, pascoa.get(Calendar.MONTH), pascoa.get(Calendar.DAY_OF_MONTH)) )) );
        pascoa.add(Calendar.DAY_OF_MONTH, 1);
        feriados.add(new Data("Carnaval", false, ( new GregorianCalendar( ano, pascoa.get(Calendar.MONTH), pascoa.get(Calendar.DAY_OF_MONTH)) )) );
        
        
        
        feriados.add( new Data("Tiradentes", false, ( new GregorianCalendar( ano, 3,21) ) )); 
        feriados.add( new Data("Dia do Trabalho", false, ( new GregorianCalendar( ano, 4, 1) ) ));
        
        
        
        feriados.add( new Data("Independência", false, ( new GregorianCalendar( ano, 8,  7) ) )); 
        feriados.add( new Data("Natal", false, ( new GregorianCalendar( ano, 11, 25) ) )); 
        
        
   
        return feriados;
    }
    
    public static void main(String[] args) {
        
        List<Data> feriados = feriados(2021);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy EEEE");
        
       feriados.forEach(feriado -> {
           System.out.println(feriado.getNome() +": "+sdf.format(feriado.getData().getTime()));
       });
        
       
        
    }

    public GregorianCalendar getData() {
        return data;
    }

    public boolean isDiaUtil() {
        return diaUtil;
    }
    
    
}   
