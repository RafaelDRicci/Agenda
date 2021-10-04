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
import util.connection.database.SQLIntArray;

/**
 *
 * @author rafaeld
 */
public class DatasAnoDAO extends VincularRotinaGenericDAO<DatasAno> {

    @Override
    public void create(DatasAno vincular) throws SQLException {
        String sql = "Insert into AGENDA_VINCULARROTINA"
                    + " (CODROTINA, CODUSUARIO, PRIORITARIO, REAGENDAVEL, HORARIOFIXO, PERIODO, DIAS, MESES, HORARIOS)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getRotina().getCodRotina());
        stm.setInt(2, vincular.getUsuario().getCodUsuario());
        stm.setString(3, vincular.isPrioritario() ? "V" : "F");
        stm.setString(4, vincular.isReagendavel() ? "V" : "F");
        stm.setString(5, vincular.isHorarioFixo() ? "V" : "F");
        stm.setString(6, vincular.getClass().getSimpleName());
        int[] intArray = new int[1];
        intArray[0] = vincular.getDia();
        stm.setString(7, SQLIntArray.intArrayToSQLIntArrayString(intArray));
        stm.setString(8, SQLIntArray.intArrayToSQLIntArrayString(vincular.getMeses()));
        stm.setString(9, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.execute();
        
        stm.close();
        
    }

    @Override
    public DatasAno read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException {
        
        String sql = "Select * from AGENDA_VINCULARROTINA"
                + " where CODROTINA = ?, USUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, rotina.getCodRotina());
        stm.setInt(2, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            
            DatasAno datasAno = new DatasAno(rotina, usuario);
            datasAno.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            datasAno.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            datasAno.setHorarioFixo(rs.getString("HORARIOFIXO").equals("V"));
            datasAno.setPeriodo(rs.getString("PERIODO"));
            datasAno.setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
            datasAno.setMeses(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES")));
            datasAno.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
            rs.close();
            stm.close();
            return datasAno;
            
        }else {
            rs.close();
            stm.close();
            throw new NoSuchElementException("ROTINA " + rotina.getCodRotina() + " NÃO VINCULADA COM USUARIO " + usuario.getCodUsuario());
        }
        
    }

    @Override
    public void update(DatasAno vincular) throws SQLException {
        String sql = "Update AGENDA_VINCULARROTINA "
                + "set PRIORITARIO = ?, REAGENDAVEl = ?, HORARIOFIXO = ?, DIAS = ?, MESES = ?, HORARIOS = ?"
                + "where CODROTINA = ? and CODUSUARIO = ?";
         
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, vincular.isPrioritario() ? "V" : "F");
        stm.setString(2, vincular.isReagendavel() ? "V" : "F");
        stm.setString(3, vincular.isHorarioFixo() ? "V" : "F");
        stm.setString(4, "{"+vincular.getDia()+"}");
        stm.setString(5, SQLIntArray.intArrayToSQLIntArrayString(vincular.getMeses()));
        stm.setString(6, SQLIntArray.intArrayToSQLIntArrayString(vincular.getHorarios()));
        stm.setInt(7, vincular.getRotina().getCodRotina());
        stm.setInt(8, vincular.getUsuario().getCodUsuario());
        stm.execute();
        
        stm.close();
    }

    @Override
    public List<DatasAno> listAllPeriodo() throws SQLException {
        List<DatasAno> lista = new ArrayList<>();
        
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
            
            DatasAno dataAno = new DatasAno(rotina, usuario);
            dataAno.setPrioritario(rs.getString("PRIORITARIO").equals("V"));
            dataAno.setReagendavel(rs.getString("REAGENDAVEL").equals("V"));
            dataAno.setHorarioFixo(rs.getString("HorarioFixo").equals("V"));
            dataAno.setDia(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("DIAS"))[0]);
            dataAno.setMeses(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("MESES")));
            dataAno.setHorarios(SQLIntArray.SQLIntArrayStringToIntArray(rs.getString("HORARIOS")));
            
            lista.add(dataAno);          
        }

        
        rs.close();
        stm.close();
        return lista;
    }
    
}
