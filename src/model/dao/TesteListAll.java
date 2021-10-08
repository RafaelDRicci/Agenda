/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import model.VincularRotina;
import model.vincularrotina.Anual;

/**
 *
 * @author rafaeld
 */
public class TesteListAll {
    
    public static void main(String[] args) throws SQLException {
        
        VincularRotinaDAO vincularDAO = new VincularRotinaDAO();
        
        List<VincularRotina> rotinasVinculadas = vincularDAO.listAll();
        
       rotinasVinculadas.forEach(rotinaVinculada -> {
           System.out.println(rotinaVinculada);
       });

    }
    
}
