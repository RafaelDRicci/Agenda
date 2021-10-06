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
import java.util.Calendar;
import java.util.GregorianCalendar;
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
        
        dataUnica.getData().get(Calendar.MONTH);
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, dataUnica.getRotina().getCodRotina());
        stm.setInt(2, dataUnica.getUsuario().getCodUsuario());
        stm.setString(3, dataUnica.isPrioritario() ? "V" : "F");
        stm.setString(4, dataUnica.isReagendavel() ? "V" : "F");
        stm.setString(5, dataUnica.isHorarioFixo() ? "V" : "F");
        stm.setString(6, dataUnica.getClass().getSimpleName());
        stm.setString(7, "{"+dataUnica.getData().get(Calendar.DAY_OF_MONTH)+"}");
        stm.setString(8, "{"+(dataUnica.getData().get(Calendar.MONTH)+1)+"}");
        stm.setString(9, "{"+dataUnica.getData().get(Calendar.YEAR)+"}");
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
            
            int dia = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0];
            int mes = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0] - 1;
            int ano = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("ANOS"))[0];
            
            GregorianCalendar data = new GregorianCalendar(ano, mes, dia);
           
            dataUnica.setData(data);
            
            dataUnica.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
        }
        
        rs.close();
        stm.close();
        return dataUnica;
    }

    @Override
    public void update(DataUnica dataUnica) throws SQLException {
        String sql = "Update AGENDA_VINCULARROTINA "
                + "set PRIORITARIO = ?, REAGENDAVEl = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, ANOS = ?, HORARIOS = ?"
                + "where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, dataUnica.isPrioritario() ? "V" : "F");
        stm.setString(2, dataUnica.isReagendavel() ? "V" : "F");
        stm.setString(3, dataUnica.isHorarioFixo() ? "V" : "F");
        stm.setString(7, "{"+dataUnica.getData().get(Calendar.DAY_OF_MONTH)+"}");
        stm.setString(8, "{"+(dataUnica.getData().get(Calendar.MONTH)+1)+"}");
        stm.setString(9, "{"+dataUnica.getData().get(Calendar.DAY_OF_MONTH)+"}");
        stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(dataUnica.getHorarios()));
        stm.setInt(8, dataUnica.getRotina().getCodRotina());
        stm.setInt(9, dataUnica.getUsuario().getCodUsuario());
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
            
            
            int dia = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0];
            int mes = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0] - 1;
            int ano = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("ANOS"))[0];
            
            GregorianCalendar data = new GregorianCalendar(ano, mes, dia);
            dataUnica.setData(data);

            dataUnica.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
            lista.add(dataUnica);          
        }

        
        rs.close();
        stm.close();
        return lista;
    }
    
    
}
