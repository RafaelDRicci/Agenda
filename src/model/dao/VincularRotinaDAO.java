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
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import model.vincularrotina.Anual;
import model.vincularrotina.DataUnica;
import model.vincularrotina.DatasAno;
import model.vincularrotina.Diario;
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
     
        String sql = "Select * from AGENDA_VINCULARROTINA where CODROTINA = ? and CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        
        if(rs.next()){
            
            String periodo = rs.getString("PERIODO");
            VincularRotina vincular = null;
            switch(periodo) {
                case "Anual":
                    
                    vincular = new Anual(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    ((Anual)vincular).setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
                    ((Anual)vincular).setMes(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0]);
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DataUnica":
                    
                    vincular = new DataUnica(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    int dia = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0];
                    int mes = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0] - 1;
                    int ano = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("ANOS"))[0];
                    GregorianCalendar data = new GregorianCalendar(ano, mes, dia);
                    ((DataUnica)vincular).setData(data);
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DatasAno":
                    
                    vincular = new DatasAno(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((DatasAno)vincular).setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
                    ((DatasAno)vincular).setMeses(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "Diario":
                    
                    vincular = new Diario(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DiasMes":
                    
                    vincular = new DiasMes(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((DiasMes)vincular).setDias(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DiasUteis":
                    
                    vincular = new DiasUteis(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((DiasUteis)vincular).setDiasUteis(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIASUTEIS")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "Semanal":
                    
                    vincular = new Semanal(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((Semanal)vincular).setDiasSemana(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIASDASEMANA")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                default :
                    
                    VincularRotina vincularRotina = new VincularRotina(rotina, usuario);
                    vincularRotina.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincularRotina.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincularRotina.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincularRotina.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
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
    

    public void update(VincularRotina vincular)throws SQLException{
        
        String periodo = vincular.getClass().getSimpleName();
        
        String sql;
        PreparedStatement stm;
        
        switch (periodo){
            
            case "Anual":

                sql = "Update AGENDA_VINCULARROTINA"
                + " set PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, HORARIOS = ?"
                + " where CODROTINA = ? and CODUSUARIO = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, vincular.isPrioritario() ? "V" : "F");
                stm.setString(2, vincular.isReagendavel() ? "V" : "F");
                stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
                int[] array = new int[1];
                array[0] = ((Anual)vincular).getDia();
                stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(array));
                array[0] = ( (Anual) vincular).getMes();
                stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(array));
                stm.setString(6, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.setInt(7, vincular.getRotina().getCodRotina());
                stm.setInt(8, vincular.getUsuario().getCodUsuario());
                stm.execute();
                break; 
                
            case "DataUnica" :
                
                sql = "Update AGENDA_VINCULARROTINA"
                + " set PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, ANOS = ?, HORARIOS = ?"
                + " where CODROTINA = ? and CODUSUARIO = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, vincular.isPrioritario() ? "V" : "F");
                stm.setString(2, vincular.isReagendavel() ? "V" : "F");
                stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(4, "{"+((DataUnica)vincular).getData().get(Calendar.DAY_OF_MONTH)+"}");
                stm.setString(5, "{"+(((DataUnica)vincular).getData().get(Calendar.MONTH)+1)+"}");
                stm.setString(6, "{"+((DataUnica)vincular).getData().get(Calendar.YEAR)+"}");
                stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.setInt(8, vincular.getRotina().getCodRotina());
                stm.setInt(9, vincular.getUsuario().getCodUsuario());
                stm.execute();
                break;
                
            case "DatasAno" :
                
                sql = "Update AGENDA_VINCULARROTINA"
                + " set PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, HORARIOS = ?"
                + " where CODROTINA = ? and CODUSUARIO = ?";
        
                stm = con.prepareStatement(sql);
                stm.setString(1, vincular.isPrioritario() ? "V" : "F");
                stm.setString(2, vincular.isReagendavel() ? "V" : "F");
                stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
                int[] intArray = new int[1];
                intArray[0] = ((DatasAno)vincular).getDia();
                stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(intArray));
                stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(((DatasAno)vincular).getMeses()));
                stm.setString(6, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.setInt(7, vincular.getRotina().getCodRotina());
                stm.setInt(8, vincular.getUsuario().getCodUsuario());
                stm.execute();
                break;
                
            case "DiasMes" :
                
                sql = "Update AGENDA_VINCULARROTINA"
                + " set PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, DIAS = ?, HORARIOS = ?"
                + " where CODROTINA = ? and CODUSUARIO = ?";
        
                stm = con.prepareStatement(sql);
                stm.setString(1, vincular.isPrioritario() ? "V" : "F");
                stm.setString(2, vincular.isReagendavel() ? "V" : "F");
                stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(((DiasMes)vincular).getDias()));
                stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.setInt(6, vincular.getRotina().getCodRotina());
                stm.setInt(7, vincular.getUsuario().getCodUsuario());
                stm.execute();
                break;
                
            case "DiasUteis" :
                
                sql = "Update AGENDA_VINCULARROTINA"
                + " set PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, DIASUTEIS = ?, HORARIOS = ?"
                + " where CODROTINA = ? and CODUSUARIO = ?";
        
                stm = con.prepareStatement(sql);
                stm.setString(1, vincular.isPrioritario() ? "V" : "F");
                stm.setString(2, vincular.isReagendavel() ? "V" : "F");
                stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(((DiasUteis)vincular).getDiasUteis()));
                stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.setInt(6, vincular.getRotina().getCodRotina());
                stm.setInt(7, vincular.getUsuario().getCodUsuario());
                stm.execute();
                break;
                
            case "Semanal" :
                
                sql = "Update AGENDA_VINCULARROTINA"
                + " set PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, DIASDASEMANA = ?, HORARIOS = ?"
                + " where CODROTINA = ? and CODUSUARIO = ?";
        
                stm = con.prepareStatement(sql);
                stm.setString(1, vincular.isPrioritario() ? "V" : "F");
                stm.setString(2, vincular.isReagendavel() ? "V" : "F");
                stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(((Semanal)vincular).getDiasSemana()));
                stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.setInt(6, vincular.getRotina().getCodRotina());
                stm.setInt(7, vincular.getUsuario().getCodUsuario());
                stm.execute();
                break;
                
            default:
                 
               sql = "Update AGENDA_VINCULARROTINA"
                + " set PRIORITARIO = ?, REAGENDAVEL = ?, HORARIOFIXO = ?, HORARIOS = ?"
                + " where CODROTINA = ? and CODUSUARIO = ?";
        
                stm = con.prepareStatement(sql);          
                stm.setString(1, vincular.isPrioritario() ? "V" : "F");
                stm.setString(2, vincular.isReagendavel() ? "V" : "F");
                stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
                stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
                stm.setInt(5, vincular.getRotina().getCodRotina());
                stm.setInt(6, vincular.getUsuario().getCodUsuario());
                stm.execute();
  
                break;
            
        }
        
        stm.close();
        
    }
    
    
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
            String periodo = rs.getString("PERIODO");
            VincularRotina vincular = null;
            switch(periodo) {
                case "Anual":
                    
                    vincular = new Anual(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    ((Anual)vincular).setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
                    ((Anual)vincular).setMes(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0]);
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DataUnica":
                    
                    vincular = new DataUnica(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    int dia = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0];
                    int mes = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES"))[0] - 1;
                    int ano = SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("ANOS"))[0];
                    GregorianCalendar data = new GregorianCalendar(ano, mes, dia);
                    ((DataUnica)vincular).setData(data);
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DatasAno":
                    
                    vincular = new DatasAno(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((DatasAno)vincular).setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
                    ((DatasAno)vincular).setMeses(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "Diario":
                    
                    vincular = new Diario(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DiasMes":
                    
                    vincular = new DiasMes(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((DiasMes)vincular).setDias(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "DiasUteis":
                    
                    vincular = new DiasUteis(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((DiasUteis)vincular).setDiasUteis(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIASUTEIS")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                    
                case "Semanal":
                    
                    vincular = new Semanal(rotina, usuario);
                    vincular.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincular.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincular.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincular.setPeriodo(rs.getString("PERIODO"));
                    ((Semanal)vincular).setDiasSemana(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIASDASEMANA")));
                    vincular.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
                default :
                    
                    VincularRotina vincularRotina = new VincularRotina(rotina, usuario);
                    vincularRotina.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
                    vincularRotina.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
                    vincularRotina.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
                    vincularRotina.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
                    
                    break;
            }
            lista.add(vincular);
        }
        
        rs.close();
        stm.close();
        
        return lista;
    }
    
    
}