/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class TesteDelete {
    
    public static void main(String[] args) throws SQLException {
        
        VincularRotinaDAO dao = new VincularRotinaDAO();
        List<VincularRotina> rotinasVinculadas = dao.listAll();

        rotinasVinculadas.forEach(rotinaVinculada -> {
            try {
                dao.delete(rotinaVinculada);
            } catch (SQLException ex) {
                Logger.getLogger(TesteDelete.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }
    
}
