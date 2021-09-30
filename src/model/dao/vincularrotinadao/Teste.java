/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import model.vincularrotina.Anual;
import model.vincularrotina.DataUnica;

/**
 *
 * @author rafaeld
 */
public class Teste {
    public static void main(String[] args) throws SQLException {
        
       List<Anual> vinculacoes = new ArrayList<>();
       
       AnualDAO anualDao = new AnualDAO();
       vinculacoes = anualDao.listAllAnual();
       
        System.out.println("ANUAL");
        System.out.println("*******************************************");
        vinculacoes.forEach(vinculacao -> {
           
            System.out.println(vinculacao.getCodUsuario() +" " + vinculacao.getCodRotina());
        });
        System.out.println("*******************************************");
        
       
       DataUnicaDAO dataDAO = new DataUnicaDAO();
       DataUnica dataUnica = dataDAO.read(5, 120);
       dataUnica.setDia(5);
       dataDAO.upadate(dataUnica);
        System.out.println(dataUnica.getCodUsuario() + " " + dataUnica.getCodRotina());
    }
}
