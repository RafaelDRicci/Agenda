/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import model.Agendamento;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class AgendamentoDAO extends GenericDAO<Agendamento>{

    @Override
    public Agendamento read(int cod) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Agendamento read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Agendamento read(int cod, VincularRotina vincular) throws SQLException, NoSuchElementException {
        
        String sql = "Select * from AGENDA_AGENDAMENTO where CODAGENDAMENTO = ? and CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, cod);
        stm.setInt(2, vincular.getRotina().getCodRotina());
        stm.setInt(3, vincular.getUsuario().getCodUsuario());
        
        ResultSet rs = stm.executeQuery();
        if(rs.next()){
            
            Agendamento agendamento = new Agendamento(cod, vincular, rs.getDate("DATA").getTime());
            
            agendamento.setHoraInicio(rs.getInt("HORAINICIAL"));
            agendamento.setHoraFinal(rs.getInt("HORAFINAL"));
            agendamento.setEstado(rs.getString("ESTADO"));
            agendamento.setJustificativa(rs.getString("JUSTIFICATIVA"));
            agendamento.setDescricao(rs.getString("DESCRICAO"));
            
            rs.close();
            stm.close();
            
            return agendamento;
            
        } else {
            rs.close();
            stm.close();
            throw new NoSuchElementException("AGENDAMENTO DE CODIGO "+cod+
                    " ENTRE ROTINA "+vincular.getRotina().getCodRotina()+ " E USUARIO "
                    +vincular.getUsuario().getCodUsuario()+ " NÃO ENCONTRADO NO BANCO DE DADOS");
        }
        
    }

    @Override
    public void create(Agendamento agendamento) throws SQLException {
        
        String sql = "Insert into AGENDA_AGENDAMENTO (CODROTINA, CODUSUARIO, "
                + "CODAGENDAMENTO, DATA, HORAINICIAL, HORAFINAL, ESTADO, JUSTIFICATIVA)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, agendamento.getVincularRotina().getRotina().getCodRotina());
        stm.setInt(2, agendamento.getVincularRotina().getUsuario().getCodUsuario());
        stm.setInt(3, agendamento.getCodAgendamento());
        stm.setDate(4, new java.sql.Date(agendamento.getData().getTimeInMillis()));
        stm.setInt(5, agendamento.getHoraInicio());
        stm.setInt(6, agendamento.getHoraFinal());
        stm.setString(7, agendamento.getEstado());
        stm.setString(8, agendamento.getJustificativa());
        stm.execute();
        
        stm.close();
    }

    @Override
    public void update(Agendamento object) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Agendamento object) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Agendamento> listAll() throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
