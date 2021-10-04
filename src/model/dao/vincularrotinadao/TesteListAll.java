/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.SQLException;
import java.util.List;
import model.VincularRotina;
import model.vincularrotina.Anual;
import model.vincularrotina.DataUnica;
import model.vincularrotina.DatasAno;

/**
 *
 * @author rafaeld
 */
public class TesteListAll {
    public static void main(String[] args) throws SQLException {
        
       AnualDAO anualDao = new AnualDAO();
       List<VincularRotina> todas = anualDao.listAll();
       System.out.println("********************************************");
       System.out.println("TODAS");
       todas.forEach(rotina -> {
           System.out.println(rotina.getRotina() +" "+rotina.getUsuario() + " "+rotina.getPeriodo());
       });
       
       List<Anual> anuais = anualDao.listAllPeriodo();
       System.out.println("********************************************");
       System.out.println("ANUAL");
       anuais.forEach(rotina -> {
           System.out.println(rotina.getRotina() +" "+rotina.getUsuario() + " "+rotina.getPeriodo());
       });
       
       DataUnicaDAO dataUnicaDao = new DataUnicaDAO();
       List<DataUnica> datasUnicas = dataUnicaDao.listAllPeriodo();
       System.out.println("********************************************");
       System.out.println("DATA ÚNICA");
       datasUnicas.forEach(rotina -> {
           System.out.println(rotina.getRotina() +" "+rotina.getUsuario() + " "+rotina.getPeriodo());
       });
       
       DatasAnoDAO dataAnoDAO = new DatasAnoDAO();
       List<DatasAno> datasAnos = dataAnoDAO.listAllPeriodo();
       System.out.println("********************************************");
       System.out.println("DATAS ANO");
       datasAnos.forEach(rotina -> {
           System.out.println(rotina.getRotina() +" "+rotina.getUsuario() + " "+rotina.getPeriodo());
       });
      
    }
}
