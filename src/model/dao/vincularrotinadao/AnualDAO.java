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
import java.util.NoSuchElementException;
import model.Rotina;
import model.Usuario;
import model.dao.VincularRotinaGenericDAO;
import model.dao.RotinaDAO;
import model.dao.UsuarioDAO;
import model.vincularrotina.Anual;
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class AnualDAO extends VincularRotinaGenericDAO<Anual>{
    
    
    @Override
    public void create(Anual anual) throws SQLException{
        
        String sql = "Insert into AGENDA_VINCULARROTINA (CODROTINA, CODUSUARIO,"
            + " PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, MESES,"
            + " HORARIOS) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, anual.getRotina().getCodRotina());
        stm.setInt(2, anual.getUsuario().getCodUsuario());
        stm.setString(3, anual.isPrioritario() ? "V" : "F");
        stm.setString(4, anual.isReagendavel() ? "V" : "F");
        stm.setString(5, anual.isHorarioFixo() ? "V" : "F");
        stm.setString(6, anual.getClass().getSimpleName());
        int[] array = new int[1];
        array[0] = anual.getDia();
        stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(array));
        array[0] = anual.getMes();
        stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(array));
        stm.setString(9, SQLIntArray.intArrayToSQLIntArrayString(anual.getHorarios()));
        stm.execute();
        
        stm.close();
  
    }

    @Override
    public Anual read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException {
       
        String sql = "Select * from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ? and PERIODO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        stm.setString(3, "Anual");
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            boolean prioritario = (rs.getString("PRIORITARIO").equals("V"));
            boolean reagendavel = (rs.getString("REAGENDAVEL").equals("V"));
            boolean horarioFixo = (rs.getString("HORARIOFIXO").equals("V"));
            int[] dias = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"));
            int[] meses = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"));
            int[] horarios = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS"));
            
            Anual anual = new Anual(rotina, usuario);
            anual.setPrioritario(prioritario);
            anual.setReagendavel(reagendavel);
            anual.setHorarioFixo(horarioFixo);
            anual.setDia(dias[0]);
            anual.setMes(meses[0]);
            anual.setHorarios(horarios);
            
            rs.close();
            stm.close();
            return anual;
        }
        
        rs.close();
        stm.close();
        return null;
    }

    @Override
    public void update(Anual anual) throws SQLException, NoSuchElementException {
             
        String sql= "Update AGENDA_VINCULARROTINA "
                +   "SET PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, HORARIOS = ?"
                +   "where CODROTINA = ? and CODUSUARIO = ? and Periodo = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, anual.isPrioritario() ? "V" : "F");
        stm.setString(2, anual.isReagendavel() ? "V" : "F");
        stm.setString(3, anual.isHorarioFixo() ? "V" : "F");
        stm.setString(4, "{"+anual.getDia()+"}");
        stm.setString(5, "{"+anual.getMes()+"}");
        stm.setString(6, SQLIntArray.intArrayToSQLIntArrayString(anual.getHorarios()) ) ;
        stm.setInt(7, anual.getRotina().getCodRotina());
        stm.setInt(8, anual.getUsuario().getCodUsuario());
        stm.setString(9, anual.getClass().getSimpleName());
        stm.execute();
        
        stm.close();
    }

    
    @Override
    public List<Anual> listAllPeriodo() throws SQLException {
        List<Anual> lista = new ArrayList<>();
        
        String sql = "Select * from AGENDA_VINCULARROTINA where PERIODO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, "Anual");
        ResultSet rs = stm.executeQuery();
        while(rs.next()){
            
            int codRotina  = rs.getInt("CODROTINA");
            int codUsuario = rs.getInt("CODUSUARIO");
            String prioritario = rs.getString("PRIORITARIO");
            String reagendavel = rs.getString("REAGENDAVEL");
            String horarioFixo = rs.getString("HORARIOFIXO");
            String dias = rs.getString(("DIAS"));
            String meses = rs.getString("MESES");
            String horarios = rs.getString("HORARIOS");
            
            RotinaDAO rotinaDao = new RotinaDAO();
            Rotina rotina = rotinaDao.read(codRotina);
            rotinaDao.closeConnection();
 
            UsuarioDAO usuarioDao = new UsuarioDAO();
            Usuario usuario = usuarioDao.read(codUsuario);
            usuarioDao.closeConnection();
            
            Anual vincular = new Anual(rotina, usuario);
            vincular.setPrioritario(prioritario.equals("V"));
            vincular.setReagendavel(reagendavel.equals("V"));
            vincular.setHorarioFixo(horarioFixo.equals("V"));
            vincular.setDia((SQLIntArray.SQLIntArrayStringToIntArray(dias))[0]);
            vincular.setMes(SQLIntArray.SQLIntArrayStringToIntArray(meses)[0]);
            vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(horarios));
            
            lista.add(vincular);          
        }

        
        rs.close();
        stm.close();
        return lista;
    }
    
}
