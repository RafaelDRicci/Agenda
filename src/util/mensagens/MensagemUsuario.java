/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class MensagemUsuario extends Mensagem<Usuario> {

    public MensagemUsuario(){
        super((byte)3);
    }
    
    public MensagemUsuario(byte[] mensagem){
        super(mensagem);
    }
    
    @Override
    public void setObjeto(Usuario usuario) throws IOException {
        
        //código de usuário
        setInt(usuario.getCodUsuario());
        //Nome de Aprovação
        setString(usuario.getNomeAprovacao());
        //Cargo
        setString(usuario.getCargo());
        //Nome completo
        setString(usuario.getNome());
        //Unidade
        setString(usuario.getUnidade());
        
    }

    @Override
    public Usuario getObjeto() throws IOException {
        //Código de usuário
        int codUsuario = getInt();
        Usuario usuario = new Usuario(codUsuario);
        //Nome de aprovação
        String nomeAprovacao = getString();
        usuario.setNomeAprovacao(nomeAprovacao);
        //Cargo
        String cargo = getString();
        usuario.setCargo(cargo);
        //Nome completo
        String nome = getString();
        usuario.setNome(nome);
        //Unidade
        String unidade = getString();
        usuario.setUnidade(unidade);
        return usuario;
    }

    @Override
    public void codificarCreate(Usuario objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario decodificarCreate() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarRead(int codRotina) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public int[] decodificarRead() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void codificarUpdate(Usuario objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario decodificarUpdate() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarDelete(Usuario objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario decodificarDelete() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    public void requestListAll() throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
    }

    public void codificarListAll(List<Usuario> usuarios) throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
        setList(usuarios);
    }
    
    public List<Usuario> decodificarListAll() throws IOException {
        
        if(codMensagem != 3) throw new IllegalArgumentException("Código da mensagem inválido para Mensagem Usuário");
        //Verifica se o código (5), código de listar usuário
        if(!(codOperacao == 5)) throw new IllegalArgumentException("Código inválido para Listar Usuários");
        return getList();
    }
    
    public void codificaUsuario(Usuario usuario) throws IOException{
        codOperacao = 6;
        setByte(codOperacao);
        setObjeto(usuario);
    }
    
    public Usuario decodificarUsuario() throws IOException{
        
        if(codMensagem != 3) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Usuário");
        if(codOperacao != 6) throw new IllegalArgumentException("Código de operação inválido para Decodificar Usuário");
        
        Usuario usuario = getObjeto();
        return usuario;
    }
}
