/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.dao.VincularRotinaDAO;
import model.vincularrotina.DataUnica;
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class DataUnicaDAO extends VincularRotinaDAO<DataUnica>{

    @Override
    public void create(DataUnica dataUnica) throws SQLException {
        
        String sql = "Insert into AGENDA_VINCULARROTINA "
                + "(CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, MESES, ANOS, HORARIOS)"
                + "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, dataUnica.getCodRotina());
        stm.setInt(2, dataUnica.getCodUsuario());
        stm.setString(3, dataUnica.isPrioritario() ? "V" : "F");
        stm.setString(4, dataUnica.isReagendavel() ? "V" : "F");
        stm.setString(5, dataUnica.isHorarioFixo() ? "V" : "F");
        stm.setString(6, dataUnica.getClass().getSimpleName());
        stm.setString(7, "{"+dataUnica.getDia()+"}");
        stm.setString(8, "{"+dataUnica.getMes()+"}");
        stm.setString(9, "{"+dataUnica.getAno()+"}");
        stm.setString(10, SQLIntArray.intArrayToSQLIntArrayString(dataUnica.getHorarios()));
        stm.execute();
        
        stm.close();
        
    }

    @Override
    public DataUnica read(int codRotina, int codUsuario) throws SQLException {
        
        String sql = "Select * from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, codRotina);
        stm.setInt(2, codUsuario);
        ResultSet rs = stm.executeQuery();
        DataUnica dataUnica = null;
        
        if(rs.next()){
            
            dataUnica = new DataUnica(codRotina, codUsuario);
            dataUnica.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            dataUnica.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            dataUnica.setHorarioFixo(rs.getString("HorarioFixo").equals("V"));
            dataUnica.setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
            dataUnica.setMes(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0]);
            dataUnica.setAno(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("ANOS"))[0]);
            dataUnica.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
        }
        
        rs.close();
        stm.close();
        return dataUnica;
    }

    @Override
    public void upadate(DataUnica vincular) throws SQLException {
        String sql = "Update AGENDA_VINCULARROTINA "
                + "set PRIORITARIO = ?, REAGENDAVEl = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, ANOS = ?, HORARIOS = ?"
                + "where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, vincular.isPrioritario() ? "T" : "F");
        stm.setString(2, vincular.isReagendavel() ? "T" : "F");
        stm.setString(3, vincular.isHorarioFixo() ? "T" : "F");
        stm.setString(4, "{"+vincular.getDia()+"}");
        stm.setString(5, "{"+vincular.getMes()+"}");
        stm.setString(6, "{"+vincular.getAno()+"}");
        stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.setInt(8, vincular.getCodRotina());
        stm.setInt(9, vincular.getCodUsuario());
        stm.execute();
    }
    
    
}
