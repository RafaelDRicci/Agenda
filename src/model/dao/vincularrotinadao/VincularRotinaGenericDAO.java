/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import model.dao.VincularRotinaDAO;
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaGenericDAO extends VincularRotinaDAO<VincularRotina>{

    @Override
    public void create(VincularRotina vincular) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VincularRotina read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException {
       
        String sql = "Select * from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
        
            VincularRotina vincular = new VincularRotina(rotina, usuario);
            vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
            vincular.setPeriodo(rs.getString("PERIODO"));
            int[] horarios = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS"));
            vincular.setHorarios(horarios);
            
            rs.close();
            stm.close();
            return vincular;
        } else {
            rs.close();
            stm.close();
            throw new NoSuchElementException("ROTINA " + rotina.getCodRotina() + " NÃO VINCULADA COM USUARIO " + usuario.getCodUsuario());
        }
    }

    @Override
    public void update(VincularRotina vincular) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<VincularRotina> listAllPeriodo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



    

}

