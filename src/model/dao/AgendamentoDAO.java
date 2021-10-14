/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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

    @Deprecated
    @Override
    public Agendamento read(int cod) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Deprecated
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
                + "DATA, HORAINICIAL, HORAFINAL, ESTADO, JUSTIFICATIVA, DESCRICAO)"
                + "values (?, ?, ?, ?, ?, ?, ?, ?)";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, agendamento.getVincularRotina().getRotina().getCodRotina());
        stm.setInt(2, agendamento.getVincularRotina().getUsuario().getCodUsuario());
        stm.setDate(3, new java.sql.Date(agendamento.getData().getTimeInMillis()));
        stm.setInt(4, agendamento.getHoraInicio());
        stm.setInt(5, agendamento.getHoraFinal());
        stm.setString(6, agendamento.getEstado());
        stm.setString(7, agendamento.getJustificativa());
        stm.setString(8, agendamento.getDescricao());
        
        stm.execute();
        
        stm.close();
    }

    @Override
    public void update(Agendamento agendamento) throws SQLException{
        
        String sql = "Update AGENDA_AGENDAMENTO set DATA = ?, HORAINICIAL = ?, HORAFINAL = ?, ESTADO = ?, JUSTIFICATIVA = ?, DESCRICAO = ?"
                + " where CODROTINA = ? and CODUSUARIO = ? and CODAGENDAMENTO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        
        stm.setDate(1, new java.sql.Date( agendamento.getData().getTimeInMillis() ));
        stm.setInt(2, agendamento.getHoraInicio());
        stm.setInt(3, agendamento.getHoraFinal());
        stm.setString(4, agendamento.getEstado());
        stm.setString(5, agendamento.getJustificativa());
        stm.setString(6, agendamento.getDescricao());
        stm.setInt(7, agendamento.getVincularRotina().getRotina().getCodRotina());
        stm.setInt(8, agendamento.getVincularRotina().getUsuario().getCodUsuario());
        stm.setInt(9, agendamento.getCodAgendamento());
        
        stm.execute();
        stm.close();
    }

    @Override
    public void delete(Agendamento agendamento) throws SQLException{
        String sql = "Delete from AGENDA_AGENDAMENTO where CODAGENDAMENTO = ? and CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, agendamento.getCodAgendamento());
        stm.setInt(2, agendamento.getVincularRotina().getRotina().getCodRotina());
        stm.setInt(3, agendamento.getVincularRotina().getUsuario().getCodUsuario());
        
        stm.execute();
        stm.close();
        
    }

    public List<Agendamento> listAgendamentos(VincularRotina vincular) throws SQLException{
        
        String sql = "Select * from AGENDA_AGENDAMENTO where CODROTINA = ? and CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, vincular.getRotina().getCodRotina());
        stm.setInt(2, vincular.getUsuario().getCodUsuario());
        
        ResultSet rs = stm.executeQuery();
        
        List<Agendamento> agendamentos = new ArrayList<>();
        
        while(rs.next()){
            
            GregorianCalendar data = new GregorianCalendar();
            data.setTime(rs.getDate("DATA"));
            Agendamento agendamento = new Agendamento (vincular, data);
            agendamento.setCodAgendamento(rs.getInt("CODAGENDAMENTO"));
            agendamento.setHoraInicio(rs.getInt("HORAINICIAL"));
            agendamento.setHoraFinal(rs.getInt("HORAFINAL"));
            agendamento.setEstado(rs.getString("ESTADO"));
            agendamento.setJustificativa(rs.getString("JUSTIFICATIVA"));
            agendamento.setDescricao(rs.getString("DESCRICAO"));
            
            agendamentos.add(agendamento);
        }
        return agendamentos;
    }
    
    @Override
    public List<Agendamento> listAll() throws SQLException {
        throw new SQLException();
        /*
        String sql = "Select a.*, v.PERIODO from AGENDA_AGENDAMENTO a, AGENDA_VINCULARROTINA v "
                + " where a.CODROTINA = v.CODROTINA and a.CODUSUARIO = v.CODUSUARIO";
        PreparedStatement stm = con.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        
        List<Agendamento> agendamentos = new ArrayList<>();
        
        while(rs.next()){
            
            RotinaDAO rotinaDAO = new RotinaDAO();
            Rotina rotina = rotinaDAO.read(rs.getInt("CODROTINA"));
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.read(rs.getInt("CODUSUARIO"));    
            
            String periodo = rs.getString("PERIODO");
            VincularRotina vincular;
           
            switch(periodo) {
                
                case "DataUnica":
                    
                    //DataUnicaDAO duDAO = new DataUnicaDAO();
                    //vincular = duDAO.read(rotina, usuario);

                    break;
                    
                    
                case "Diario":
                    
                    
                    
                default :
                    
                   // VincularRotinaGenericDAO vrgDAO = new VincularRotinaGenericDAO();
                    //vincular = vrgDAO.read(rotina, usuario);
   
                    break;
                
            }
            
            Agendamento agendamento = new Agendamento(rs.getInt("CODAGENDAMENTO"), vincular, rs.getDate("DATA").getTime());
            agendamento.setHoraInicio(rs.getInt("HORAINICIAL"));
            agendamento.setHoraFinal(rs.getInt("HORAFINAL"));
            agendamento.setEstado(rs.getString("ESTADO"));
            agendamento.setJustificativa(rs.getString("JUSTIFICATIVA"));
            agendamento.setDescricao(rs.getString("DESCRICAO"));
            
            agendamentos.add(agendamento);
        }
        
        rs.close();
        stm.close();
        
        return agendamentos;*/
        
    }
}
