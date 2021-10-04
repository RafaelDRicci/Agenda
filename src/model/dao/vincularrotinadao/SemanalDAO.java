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
import model.dao.RotinaDAO;
import model.dao.UsuarioDAO;
import model.dao.VincularRotinaGenericDAO;
import model.vincularrotina.DatasAno;
import model.vincularrotina.DiasUteis;
import model.vincularrotina.Semanal;
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class SemanalDAO extends VincularRotinaGenericDAO<Semanal>{

    @Override
    public void create(Semanal vincular) throws SQLException {
        String sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIASDASEMANA, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getRotina().getCodRotina());
        stm.setInt(2, vincular.getUsuario().getCodUsuario());
        stm.setString(3, vincular.isPrioritario() ? "V" : "F");
        stm.setString(4, vincular.isReagendavel() ? "V" : "F");
        stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
        stm.setString(6, vincular.getClass().getSimpleName());
        stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(vincular.getDiasSemana()));
        stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.execute();
        
        stm.close();
        }

    @Override
    public Semanal read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException {
        String sql = "Select * from AGENDA_VINCULARROTINA"
        + " where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            
            Semanal semanal = new Semanal(rotina, usuario);
            semanal.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            semanal.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            semanal.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
            semanal.setPeriodo(rs.getString("PERIODO"));
            semanal.setDiasSemana(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIASDASEMANA")));
            semanal.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            rs.close();
            stm.close();
            return semanal;
            
        }else {
            rs.close();
            stm.close();
            throw new NoSuchElementException("ROTINA " + rotina.getCodRotina() + " NÃO VINCULADA COM USUARIO " + usuario.getCodUsuario());
        }
    }

    @Override
    public void update(Semanal vincular) throws SQLException {
        String sql = "Update AGENDA_VINCULARROTINA "
                + "set PRIORITARIO = ?, REAGENDAVEl = ?, HORARIOFIXO = ?, DIASDASEMANA = ?, HORARIOS = ?"
                + "where CODROTINA = ? and CODUSUARIO = ?";
         
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, vincular.isPrioritario() ? "V" : "F");
        stm.setString(2, vincular.isReagendavel() ? "V" : "F");
        stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
        stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(vincular.getDiasSemana()));
        stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.setInt(6, vincular.getRotina().getCodRotina());
        stm.setInt(7, vincular.getUsuario().getCodUsuario());
        stm.execute();
        
        stm.close();
    }

    @Override
    public List<Semanal> listAllPeriodo() throws SQLException {
        List<Semanal> lista = new ArrayList<>();
        
        String sql = "Select * from AGENDA_VINCULARROTINA where PERIODO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, DatasAno.class.getSimpleName());
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
            
            Semanal diasUteis = new Semanal(rotina, usuario);
            diasUteis.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            diasUteis.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            diasUteis.setHorarioFixo(rs.getString("HorarioFixo").equals("V"));
            diasUteis.setDiasSemana(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIASDASEMANA")));
            diasUteis.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
            lista.add(diasUteis);          
        }

        rs.close();
        stm.close();
        return lista;
    }
    

   
    
}
