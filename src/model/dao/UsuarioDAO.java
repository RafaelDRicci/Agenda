/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Usuario;
import banco.conexao.ConnectionFactory;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author rafaeld
 */
public class UsuarioDAO{
    
    /*
    public UsuarioDAO() {
        super(Usuario.class);
    }
    
    public boolean autenticaUsuario(Usuario usuario){
        return em.contains(usuario);
    }

    */
    
    private Connection con = null;
    
    
    
    public UsuarioDAO(){
        con = ConnectionFactory.getConnection();
    }
    
    public void close() throws SQLException{
        con.close();
    }
    
    /**
     * 
     * @param login
     * @param senha
     * @return byte que representa uma resposta de autenticação
     * 0 - Usuário não existe
     * 1 - Usuário não ativado
     * 2 - Senha expirada
     * 3 - Senha inocorreta
     * 4 - Usuário autenticado
     */
    
    /**
     * 
     * @param login
     * @param senha
     * @return código de autenticação; 0 - Usuário não Existe; 1 - Usuário inativado;
     * 2 - Senha expirada; 3 - Senha incorreta; 4 - Usuário autenticado;
     */
    public byte autentica(Usuario usuario){
        
        Usuario maisRecente = maisRecente(buscaPorLogin(usuario.getLogin()));
        Calendar calendar = Calendar.getInstance();
        
        byte codigo = 0;
        //Confere se o usuário existe
        if(maisRecente != null){
            //Confere se o usuário está ativado
            if(ativado(maisRecente)){
                //Confere se a senha não expirou, por meio do campo dataValidacao
                if(maisRecente.getDataValidacao().after(calendar.getTime())){
                    //Confere se a senha é a mesma registrada em banco
                    if(usuario.getSenha().equals(maisRecente.getSenha())){
                        codigo = 4;
                        
                        usuario.setnControle(maisRecente.getnControle());
                        usuario.setCodUsuario(maisRecente.getCodUsuario());
                        usuario.setDataCadastro(maisRecente.getDataCadastro());
                        usuario.setDataValidacao(maisRecente.getDataValidacao());
                        usuario.setNome(maisRecente.getNome());
                        usuario.setNomeAprovacao(maisRecente.getNomeAprovacao());
                        usuario.setUnidade(maisRecente.getUnidade());
                        usuario.setCargo(maisRecente.getCargo());
                        
                        
                    } else codigo = 3;
                } else codigo = 2;
            } else codigo = 1;
        }
        return codigo;
    }
    
    public ArrayList<Usuario> buscaPorLogin(String login){
        String sql = "Select * from USUARIOSCONTROLES where LOGIN = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        ArrayList<Usuario> list = new ArrayList<>();
        try {
            stm = con.prepareStatement(sql);
            stm.setString(1, login);
            rs = stm.executeQuery();
            
            while(rs.next()){
               
                Usuario novo = new Usuario();
                novo.setLogin(login);
                novo.setSenha(rs.getString("SENHA"));
                novo.setnControle(rs.getInt("NCONTROLE"));
                novo.setCodUsuario(rs.getInt("CODUSUARIO"));
                novo.setDataCadastro(rs.getDate("DATACADASTRO"));
                novo.setDataValidacao(rs.getDate("DATAVALIDADE"));
                list.add(novo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  list;
    }
    
    public Usuario maisRecente(ArrayList<Usuario> usuarios){
        
        if(!usuarios.isEmpty()){
            Usuario maisRecente = usuarios.get(0);

            for(Usuario usuario : usuarios){
                if(usuario.getDataCadastro().after(maisRecente.getDataCadastro())){
                    maisRecente = usuario;
                }
            }
            
            String sql = "Select * from USUARIOS where CODUSUARIO = ?";
            PreparedStatement stm = null;
            ResultSet rs = null;
            
            try {
                
                stm = con.prepareStatement(sql);
                stm.setInt(1, maisRecente.getCodUsuario());
               
                rs = stm.executeQuery();
                if(rs.next()){
                    
                    maisRecente.setNome("NOME");
                    maisRecente.setNomeAprovacao(rs.getString("NOMEAPROVACAO"));
                    maisRecente.setUnidade(rs.getString("UNIDADE"));
                    maisRecente.setCargo(rs.getString("CARGO"));
                    

                }

                rs.close();
                stm.close();
                
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } 

            return maisRecente;
        }
        return null;
    }
    
    public boolean ativado(Usuario usuario){
        
        String sql = "Select * from USUARIOS where CODUSUARIO = ?";
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            
            stm = con.prepareStatement(sql);
            stm.setInt(1, usuario.getCodUsuario());
            rs = stm.executeQuery();
            if(rs.next()){
                if(rs.getString("ATIVADO").equals("S")) return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    public void bloquear(String login){
        Usuario maisRecente = maisRecente(buscaPorLogin(login));
        
        String sql = "Update USUARIOS set ATIVADO = ? where CODUSUARIO = ?";
        PreparedStatement stm = null;
        
        try {
            
            stm = con.prepareStatement(sql);
            stm.setString(1, "N");
            stm.setInt(2, maisRecente.getCodUsuario());
            stm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
