/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.SQLException;
import model.Rotina;
import model.Usuario;
import model.dao.RotinaDAO;
import model.dao.UsuarioDAO;
import model.vincularrotina.Anual;
import model.vincularrotina.DatasAno;
import model.vincularrotina.DiasMes;

/**
 *
 * @author rafaeld
 */
public class TestCreate {
    public static void main(String[] args) throws SQLException {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.read(56);
        
        RotinaDAO rotinaDAO = new RotinaDAO();
        Rotina rotina = rotinaDAO.read(6);
        
        DiasMesDAO diasMesDAO = new DiasMesDAO();
        DiasMes diasMeses = diasMesDAO.read(rotina, usuario);
        int[] dias = {1, 10, 20};
        diasMeses.setDias(dias);
        diasMesDAO.update(diasMeses);
        
        rotina.setCodRotin(8);
        usuario.setCodUsuario(69);
        diasMeses = new DiasMes(rotina, usuario);
        diasMesDAO.create(diasMeses);
        diasMesDAO.delete(diasMeses);
        
    }
}
