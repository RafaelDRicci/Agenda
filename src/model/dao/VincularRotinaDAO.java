/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import model.vincularrotina.Anual;
import model.vincularrotina.DataUnica;
import model.vincularrotina.DatasAno;
import model.vincularrotina.DiasMes;
import model.vincularrotina.DiasUteis;
import model.vincularrotina.Semanal;
import util.connection.database.ConnectionFactory;
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaDAO{

    protected Connection con;
    
    public VincularRotinaDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public void closeConnection(){
        ConnectionFactory.closeConnection(con);
    }

    public void create(VincularRotina vincular) throws SQLException {
        
        String periodo = vincular.getClass().getSimpleName();
        
        String sql;
        PreparedStatement stm;
        
        switch (periodo){
            
            case "Anual":

                sql = "Insert into AGENDA_VINCULARROTINA (CODROTINA, CODUSUARIO,"
                + " PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, MESES,"
                + " HORARIOS) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                stm.setInt(1, vincular.getRotina().getCodRotina());
                stm.setInt(2, vincular.getUsuario().getCodUsuario());
                stm.setString(3, vincular.isPrioritario() ? "V" : "F");
                stm.setString(4, vincular.isReagendavel() ? "V" : "F");
                stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(6, vincular.getClass().getSimpleName());
                int[] array = new int[1];
                array[0] = ((Anual)vincular).getDia();
                stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(array));
                array[0] = ( (Anual) vincular).getMes();
                stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(array));
                stm.setString(9, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.execute();
                break; 
                
            case "DataUnica" :
                
                sql = "Insert into AGENDA_VINCULARROTINA "
                + "(CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, MESES, ANOS, HORARIOS)"
                + "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
        
                ((DataUnica)vincular).getData().get(Calendar.MONTH);

                stm = con.prepareStatement(sql);
                stm.setInt(1, vincular.getRotina().getCodRotina());
                stm.setInt(2, vincular.getUsuario().getCodUsuario());
                stm.setString(3, vincular.isPrioritario() ? "V" : "F");
                stm.setString(4, vincular.isReagendavel() ? "V" : "F");
                stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(6, vincular.getClass().getSimpleName());
                stm.setString(7, "{"+((DataUnica)vincular).getData().get(Calendar.DAY_OF_MONTH)+"}");
                stm.setString(8, "{"+(((DataUnica)vincular).getData().get(Calendar.MONTH)+1)+"}");
                stm.setString(9, "{"+((DataUnica)vincular).getData().get(Calendar.YEAR)+"}");
                stm.setString(10, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.execute();
                break;
                
            case "DatasAno" :
                
                sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, MESES, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
                stm = con.prepareStatement(sql);
                stm.setInt(1, vincular.getRotina().getCodRotina());
                stm.setInt(2, vincular.getUsuario().getCodUsuario());
                stm.setString(3, vincular.isPrioritario() ? "V" : "F");
                stm.setString(4, vincular.isReagendavel() ? "V" : "F");
                stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(6, vincular.getClass().getSimpleName());
                int[] intArray = new int[1];
                intArray[0] = ((DatasAno)vincular).getDia();
                stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(intArray));
                stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(((DatasAno)vincular).getMeses()));
                stm.setString(9, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.execute();
                break;
                
            case "DiasMes" :
                
                sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?)";
        
                stm = con.prepareStatement(sql);
                stm.setInt(1, vincular.getRotina().getCodRotina());
                stm.setInt(2, vincular.getUsuario().getCodUsuario());
                stm.setString(3, vincular.isPrioritario() ? "V" : "F");
                stm.setString(4, vincular.isReagendavel() ? "V" : "F");
                stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(6, vincular.getClass().getSimpleName());
                stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(((DiasMes)vincular).getDias()));
                stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.execute();
                break;
                
            case "DiasUteis" :
                
                sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIASUTEIS, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?)";
        
                stm = con.prepareStatement(sql);
                stm.setInt(1, vincular.getRotina().getCodRotina());
                stm.setInt(2, vincular.getUsuario().getCodUsuario());
                stm.setString(3, vincular.isPrioritario() ? "V" : "F");
                stm.setString(4, vincular.isReagendavel() ? "V" : "F");
                stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(6, vincular.getClass().getSimpleName());
                stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(((DiasUteis)vincular).getDiasUteis()));
                stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.execute();
                break;
                
            case "Semanal" :
                
                sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIASDASEMANA, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?)";
        
                stm = con.prepareStatement(sql);
                stm.setInt(1, vincular.getRotina().getCodRotina());
                stm.setInt(2, vincular.getUsuario().getCodUsuario());
                stm.setString(3, vincular.isPrioritario() ? "V" : "F");
                stm.setString(4, vincular.isReagendavel() ? "V" : "F");
                stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(6, vincular.getClass().getSimpleName());
                stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(((Semanal)vincular).getDiasSemana()));
                stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.execute();
                break;
                
            default:
                 
                sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?)";
        
                stm = con.prepareStatement(sql);
                stm.setInt(1, vincular.getRotina().getCodRotina());
                stm.setInt(2, vincular.getUsuario().getCodUsuario());
                stm.setString(3, vincular.isPrioritario() ? "V" : "F");
                stm.setString(4, vincular.isReagendavel() ? "V" : "F");
                stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(6, vincular.getClass().getSimpleName());
                stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.execute();
  
                break;
            
        }
        
        stm.close();
        
    }
    
    
    public VincularRotina read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException{
     
        String sql = "Select * from AGENDA_VINCULARROTINA where CODROTINA = ? and CONDUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        
        if(rs.next()){
            
            String periodo = rs.getString("PERIODO");
            VincularRotina vincular = null;
            switch(periodo) {
                case "Anual":
                    
                    Anual anual = new Anual(rotina, usuario);
                    anual.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    anual.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    anual.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    anual.setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
                    anual.setMes(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0]);
                    anual.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                default :
                    
                    break;
            }
            
            rs.close();
            stm.close();
            return vincular;
            
        } else {
            rs.close();
            stm.close();
            throw new NoSuchElementException("ROTINA " + rotina.getCodRotina() + " NÃO VINCULADA COM USUARIO " + usuario.getCodUsuario());
        }
    }
    
    /*
    public abstract void update(T vincular)throws SQLException;
    */
    
    
    public void delete(VincularRotina vincular)  throws SQLException {
        
        String sql = "Delete from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getRotina().getCodRotina());
        stm.setInt(2, vincular.getUsuario().getCodUsuario());
        stm.execute();
        stm.close();
    }
 
    public List<VincularRotina> listAll() throws SQLException, NoSuchElementException {
        List<VincularRotina> lista = new ArrayList<>();
        
        String sql = "Select * from AGENDA_VINCULARROTINA";
        
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            
            RotinaDAO rotinaDAO = new RotinaDAO();
            Rotina rotina = rotinaDAO.read(rs.getInt("CODROTINA")); 
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.read(rs.getInt("CODUSUARIO"));
            
            rotinaDAO.closeConnection();
            usuarioDAO.closeConnection();
            
            VincularRotina vincular = new VincularRotina(rotina, usuario);
            vincular.setPrioritario( rs.getString("PRIORITARIO").equals("V"));
            vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
            vincular.setPeriodo(rs.getString("PERIODO"));
            
            lista.add(vincular);
        }
        
        rs.close();
        stm.close();
        
        return lista;
    }
    
    
}