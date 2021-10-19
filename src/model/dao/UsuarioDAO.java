/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Usuario;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import model.Rotina;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class UsuarioDAO extends GenericDAO<Usuario>{
    
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
    public byte autentica(Usuario usuario) throws SQLException{
        
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
    
    public ArrayList<Usuario> buscaPorLogin(String login) throws SQLException{
       
        String sql = "Select * from USUARIOSCONTROLES where LOGIN = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setString(1, login);
        ResultSet rs = stm.executeQuery();
        ArrayList<Usuario> list = new ArrayList<>();
        
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
        rs.close();
        stm.close();
 
        return  list;
    }
    
    public Usuario maisRecente(ArrayList<Usuario> usuarios) throws SQLException{
        
        if(!usuarios.isEmpty()){
            Usuario maisRecente = usuarios.get(0);

            for(Usuario usuario : usuarios){
                if(usuario.getDataCadastro().after(maisRecente.getDataCadastro())){
                    maisRecente = usuario;
                }
            }
            
            String sql = "Select * from USUARIOS where CODUSUARIO = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, maisRecente.getCodUsuario());
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()){

                maisRecente.setNome("NOME");
                maisRecente.setNomeAprovacao(rs.getString("NOMEAPROVACAO"));
                maisRecente.setUnidade(rs.getString("UNIDADE"));
                maisRecente.setCargo(rs.getString("CARGO"));    

            }

            rs.close();
            stm.close();             

            return maisRecente;
        }
        return null;
    }
    
    public boolean ativado(Usuario usuario) throws SQLException, NoSuchElementException{
        
        String sql = "Select * from USUARIOS where CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);

        stm.setInt(1, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();

        if(rs.next()){
            boolean retorno = rs.getString("ATIVADO").equals("S");
            rs.close();
            stm.close();
            return retorno;
        } else {
            rs.close();
            stm.close();
            throw new NoSuchElementException("USUARIO DE CÓDIGO "+ usuario.getCodUsuario() +" NÃO ENCONTRADO NO BANCO DE DADOS");
        }
    }

    public void bloquear(String login) throws SQLException{
        Usuario maisRecente = maisRecente(buscaPorLogin(login));
        
        String sql = "Update USUARIOS set ATIVADO = ? where CODUSUARIO = ?";
        PreparedStatement stm = con.prepareStatement(sql);
        
        stm.setString(1, "N");
        stm.setInt(2, maisRecente.getCodUsuario());
        stm.executeUpdate();
        
        stm.close();
        
    }

    public List<Rotina> listarRotinasVinculadas(Usuario usuario) throws SQLException{
        List<Rotina> rotinas = new ArrayList<>();
        
        String sql = "Select r.* From USUARIOS u, AGENDA_ROTINA r, AGENDA_VINCULARROTINA vr" +
            "where u.CODUSUARIO = vr.CODUSUARIO and r.CODROTINA = vr.CODROTINA" +
            "and u.CODUSUARIO = ?";
        
        PreparedStatement stm = con.prepareStatement(sql);
        stm.setInt(1, usuario.getCodUsuario());
        ResultSet rs = stm.executeQuery();
        
        while(rs.next()){
            
            int codRotina = rs.getInt("CODROTINA");
            String nome = rs.getString("NOME");
            Rotina rotina = new Rotina(codRotina, nome);
            rotina.setDataLimite(rs.getDate("DATALIMITE"));
            rotina.setDescricao(rs.getString("DESCRICAO"));
            
            rotinas.add(rotina);
            
        }
        
        rs.close();
        stm.close();
        
        return rotinas;
    }
    
    public List<Usuario> buscarPorCargo(String cargo) throws SQLException{       
        return buscarPorCondicao("CARGO",cargo, "String");
    }
    
    public List<Usuario> buscarPorUnidade(String unidade) throws SQLException{  
        return buscarPorCondicao("UNIDADE",unidade, "String");
    }
    
    public List<Usuario> buscarPorCondicao(String coluna, String valor, String tipo) throws SQLException{
        String novaCondicao = ""; 
        
        if(tipo.equals("String")){
            novaCondicao = " and u."+coluna+ " LIKE ?";
        } else if(tipo.equals("int")){
            novaCondicao = " and u."+coluna+ " = ?";
        }
        
        String sql  = "Select u.CODUSUARIO, u.NOMEUSUARIO, u.NOMEAPROVACAO, u.CARGO, u.UNIDADE"
                    + " from USUARIOS u"
                    + " Where u.ATIVADO = 'S'" + novaCondicao
                    + " ORDER BY u.NOMEAPROVACAO";
        
        PreparedStatement stm = con.prepareStatement(sql);
        
        if(tipo.equals("String")){
            stm.setString(1, "%"+valor+"%");
        } else if(tipo.equals("int")){
            stm.setInt(1, Integer.valueOf(valor));
        } 
        
        ResultSet rs = stm.executeQuery();
        
        List<Usuario> usuarios = new ArrayList<>();
        while(rs.next()){
            Usuario usuario = new Usuario(rs.getInt("CODUSUARIO"));
            usuario.setNome(rs.getString("NOMEUSUARIO"));
            usuario.setNomeAprovacao(rs.getString("NOMEAPROVACAO"));
            usuario.setCargo(rs.getString("CARGO"));
            usuario.setUnidade(rs.getString("UNIDADE"));
            usuarios.add(usuario);
        }
        
        rs.close();
        stm.close();
        return usuarios;
    }
    
    
    @Override
    public Usuario read(int codUsuario) throws SQLException, NoSuchElementException{
        List<Usuario> usuarios = buscarPorCondicao("CODUSUARIO",Integer.toString(codUsuario), "int");
        if(usuarios.isEmpty()) {
            throw new NoSuchElementException("NENHUM USUARIO COM CÓDIGO "+codUsuario);
        }
        return usuarios.get(0);
    }
    
    
    @Override
    public void create(Usuario object) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Usuario object) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Usuario object) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> listAll() throws SQLException {
       return buscarPorCondicao("", "", "");
    }

    @Override
    public Usuario read(Rotina rotina, Usuario usuario) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario read(int cod, VincularRotina vincular) throws SQLException, NoSuchElementException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}


