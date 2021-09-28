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
import model.dao.VincularRotinaDAO;
import model.vincularrotina.Anual;
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class AnualDAO extends VincularRotinaDAO<Anual>{
    
    
    @Override
    public void create(Anual anual) throws SQLException{
        
        String sql = "Insert into AGENDA_VINCULARROTINA (CODROTINA, CODUSUARIO,"
            + " PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, MESES,"
            + " HORARIOS) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, anual.getCodRotina());
        stm.setInt(2, anual.getCodUsuario());
        stm.setString(3, anual.isPrioritario() ? "V" : "F");
        stm.setString(4, anual.isReagendavel() ? "V" : "F");
        stm.setString(5, anual.isHorarioFixo() ? "V" : "F");
        stm.setString(6, anual.getClass().getSimpleName());
        stm.setString(7, "{"+anual.getDia()+"}");
        stm.setString(8, "{"+anual.getMes()+"}");
        stm.setString(9, SQLIntArray.intArrayToSQLIntArrayString(anual.getHorarios()));
        stm.execute();
  
    }

    @Override
    public Anual read(int codRotina, int codUsuario) throws SQLException {
       
        String sql = "Select * from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, codRotina);
        stm.setInt(2, codUsuario);
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            boolean prioritario = (rs.getString("PRIORITARIO").equals("T"));
            boolean reagendavel = (rs.getString("REAGENDAVEL").equals("T"));
            boolean horarioFixo = (rs.getString("HORARIOFIXO").equals("T"));
            int[] dias = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"));
            int[] meses = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"));
            int[] horarios = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS"));
            
            Anual anual = new Anual(codRotina, codUsuario);
            anual.setPrioritario(prioritario);
            anual.setReagendavel(reagendavel);
            anual.setHorarioFixo(horarioFixo);
            anual.setDia(dias[0]);
            anual.setMes(meses[0]);
            anual.setHorarios(horarios);
            
            return anual;
        }
        
        return null;
    }

    @Override
    public void upadate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Anual vincular) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Anual> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
