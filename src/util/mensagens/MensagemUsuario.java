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
        super();
        try {
            codificar();
        } catch (IOException ex) {
            Logger.getLogger(MensagemUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public MensagemUsuario(byte[] mensagem){
        super(mensagem);
    }
    
    @Override
    public byte[] codificar() throws IOException {
        byte cod = 3;
        this.setByte(cod);
        return this.getMensagem();
    }

    @Override
    public byte[] codificar(Usuario objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] codificar(Usuario... objetos) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] codificar(List<Usuario> usuarios) throws IOException {
        //numero de usuarios
        setInt(usuarios.size());
        //percorre a lista de usuários, codificando cada um
        for(Usuario usuario : usuarios){
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
        return this.getMensagem();
    }

    @Override
    public Usuario decodificar() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Usuario> decodificarList() throws IOException {
       //Código da Mensagem
       byte cod = getByte();
       //Faz um verificação se o código condiz com (3), código de listar usuário
       if(!(cod == 3)) throw new IllegalArgumentException("Código inválido para Listar Usuários");
        //numero de usuarios
        int numeroUsuarios = getInt();
        //lista de usuários 
        List<Usuario> usuarios = new ArrayList<>();
        for(int i = 0; i < numeroUsuarios; i++ ){
            Usuario usuario = new Usuario();
            //Código de usuário
            int codUsuario = getInt();
            usuario.setCodUsuario(codUsuario);
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
            //Adiciona o usuário na lista 
            usuarios.add(usuario);
        }
        return usuarios;
    }
    
}
