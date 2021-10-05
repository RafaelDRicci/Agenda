/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.vincularrotinadao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Rotina;
import model.Usuario;
import model.dao.RotinaDAO;
import model.dao.UsuarioDAO;
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
        stm.setInt(1, dataUnica.getRotina().getCodRotina());
        stm.setInt(2, dataUnica.getUsuario().getCodUsuario());
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
    public DataUnica read(Rotina rotina, Usuario usuario) throws SQLException {
        
        String sql = "Select * from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        DataUnica dataUnica = null;
        
        if(rs.next()){
                        
            dataUnica = new DataUnica(rotina, usuario);
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
    public void update(DataUnica vincular) throws SQLException {
        String sql = "Update AGENDA_VINCULARROTINA "
                + "set PRIORITARIO = ?, REAGENDAVEl = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, ANOS = ?, HORARIOS = ?"
                + "where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, vincular.isPrioritario() ? "V" : "F");
        stm.setString(2, vincular.isReagendavel() ? "V" : "F");
        stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
        stm.setString(4, "{"+vincular.getDia()+"}");
        stm.setString(5, "{"+vincular.getMes()+"}");
        stm.setString(6, "{"+vincular.getAno()+"}");
        stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.setInt(8, vincular.getRotina().getCodRotina());
        stm.setInt(9, vincular.getUsuario().getCodUsuario());
        stm.execute();
        
        stm.close();
    }

    @Override
    public List<DataUnica> listAllPeriodo() throws SQLException {
        List<DataUnica> lista = new ArrayList<>();
        
        String sql = "Select * from AGENDA_VINCULARROTINA where PERIODO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, DataUnica.class.getSimpleName());
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            
            int codRotina  = rs.getInt("CODROTINA");
            int codUsuario = rs.getInt("CODUSUARIO");
            
            RotinaDAO rotinaDao = new RotinaDAO();
            Rotina rotina = rotinaDao.read(codRotina);
            rotinaDao.closeConnection();
 
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = usuarioDao.read(codUsuario);
            usuarioDao.closeConnection();
            
            DataUnica dataUnica = new DataUnica(rotina, usuario);
            dataUnica.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            dataUnica.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            dataUnica.setHorarioFixo(rs.getString("HorarioFixo").equals("V"));
            dataUnica.setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
            dataUnica.setMes(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0]);
            dataUnica.setAno(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("ANOS"))[0]);
            dataUnica.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
            lista.add(dataUnica);          
        }

        
        rs.close();
        stm.close();
        return lista;
    }
    
    
}
