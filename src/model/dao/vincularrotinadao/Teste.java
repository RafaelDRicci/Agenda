/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.SQLException;
import model.vincularrotina.Anual;

/**
 *
 * @author rafaeld
 */
public class Teste {
    public static void main(String[] args) throws SQLException {
        
        AnualDAO dao = new AnualDAO();
        
        /*
        Anual anual1 = new Anual(2, 150);
        anual1.setDia(5);
        anual1.setMes(10);
        anual1.setHorarioFixo(true);
        int[] horarios1 = {7, 8, 9};
        anual1.setHorarios(horarios1);

        dao.create(anual1);
        */
        Anual anual = dao.read(2,150);
        int[] horarios = anual.getHorarios();
        
        for(int i = 0; i < horarios.length; i++){
            System.out.println(horarios[i]);
        }
        
    }
}
