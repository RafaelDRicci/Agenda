/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private void codificarObjeto(Usuario usuario) throws IOException {
        
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

    private Usuario decodificarObjeto() throws IOException {
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
    public Usuario decodificarRead() throws IOException {
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

    @Override
    public void codificarRequestList() throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
    }

    @Override
    public void codificarList(List<Usuario> usuarios) throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
        //numero de usuarios
        setInt(usuarios.size());
        //percorre a lista de usuários, codificando cada um
        for(Usuario usuario : usuarios){
            codificarObjeto(usuario);
        }
    }
    
    @Override
    public List<Usuario> decodificarList() throws IOException {
        
        if(codMensagem != 3) throw new IllegalArgumentException("Código da mensagem inválido para Mensagem Usuário");
        //Verifica se o código (5), código de listar usuário
        if(!(codOperacao == 5)) throw new IllegalArgumentException("Código inválido para Listar Usuários");
        //numero de usuarios
        int numeroUsuarios = getInt();
        //lista de usuários 
        List<Usuario> usuarios = new ArrayList<>();
        for(int i = 0; i < numeroUsuarios; i++ ){
           Usuario usuario = decodificarObjeto();
           usuarios.add(usuario);
        }
        return usuarios;
    }
    
    public void codificaUsuario(Usuario usuario) throws IOException{
        codOperacao = 6;
        setByte(codOperacao);
        codificarObjeto(usuario);
    }
    
    public Usuario decodificarUsuario() throws IOException{
        
        if(codMensagem != 3) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Usuário");
        if(codOperacao != 6) throw new IllegalArgumentException("Código de operação inválido para Decodificar Usuário");
        
        Usuario usuario = decodificarObjeto();
        return usuario;
    }
}
