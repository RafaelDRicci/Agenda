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
import model.vincularrotina.DiasMes;
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class DiasMesDAO extends VincularRotinaGenericDAO<DiasMes>{

    @Override
    public void create(DiasMes vincular) throws SQLException {
        
        String sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getRotina().getCodRotina());
        stm.setInt(2, vincular.getUsuario().getCodUsuario());
        stm.setString(3, vincular.isPrioritario() ? "V" : "F");
        stm.setString(4, vincular.isReagendavel() ? "V" : "F");
        stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
        stm.setString(6, vincular.getClass().getSimpleName());
        stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(vincular.getDias()));
        stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.execute();
        
        stm.close();
    }

    @Override
    public DiasMes read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException {
        String sql = "Select * from AGENDA_VINCULARROTINA"
                + " where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            
            DiasMes diasMes = new DiasMes(rotina, usuario);
            diasMes.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            diasMes.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            diasMes.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
            diasMes.setPeriodo(rs.getString("PERIODO"));
            diasMes.setDias(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS")));
            diasMes.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            rs.close();
            stm.close();
            return diasMes;
            
        }else {
            rs.close();
            stm.close();
            throw new NoSuchElementException("ROTINA " + rotina.getCodRotina() + " NÃO VINCULADA COM USUARIO " + usuario.getCodUsuario());
        }
    }

    @Override
    public void update(DiasMes vincular) throws SQLException {
        String sql = "Update AGENDA_VINCULARROTINA "
                + "set PRIORITARIO = ?, REAGENDAVEl = ?, HORARIOFIXO = ?, DIAS = ?, HORARIOS = ?"
                + "where CODROTINA = ? and CODUSUARIO = ?";
         
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, vincular.isPrioritario() ? "V" : "F");
        stm.setString(2, vincular.isReagendavel() ? "V" : "F");
        stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
        stm.setString(4, SQLIntArray.intArrayToSQLIntArrayString(vincular.getDias()));
        stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.setInt(6, vincular.getRotina().getCodRotina());
        stm.setInt(7, vincular.getUsuario().getCodUsuario());
        stm.execute();
        
        stm.close();
    }

    @Override
    public List<DiasMes> listAllPeriodo() throws SQLException {
        List<DiasMes> lista = new ArrayList<>();
        
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
            
            DiasMes diasMeses = new DiasMes(rotina, usuario);
            diasMeses.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            diasMeses.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            diasMeses.setHorarioFixo(rs.getString("HorarioFixo").equals("V"));
            diasMeses.setDias(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS")));
            diasMeses.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
            lista.add(diasMeses);          
        }

        
        rs.close();
        stm.close();
        return lista;
    }

   
    
}
