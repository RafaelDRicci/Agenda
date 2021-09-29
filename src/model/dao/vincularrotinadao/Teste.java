/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.vincularrotina.Anual;

/**
 *
 * @author rafaeld
 */
public class Teste {
    public static void main(String[] args) throws SQLException {
        
       List<Anual> vinculacoes = new ArrayList<>();
       
       AnualDAO dao = new AnualDAO();
       vinculacoes = dao.listAllAnual();
       
       vinculacoes.forEach(vinculacao -> {
           System.out.println(vinculacao.getCodUsuario() +" " + vinculacao.getCodRotina());
       });
        
    }
}
