/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.communication.Comunicacao;
import util.communication.DecodificaMensagem;

/**
 *
 * @author rafaeld
 */

public class Usuario {

    private int tentativaLogin;
    private boolean conectado;
    private boolean autenticado;
    private Comunicacao comunicacao;
    
    private Integer nControle;
    private String login;
    private String senha;
    private Date dataCadastro;
    private Date dataValidacao;
    
    private Integer codUsuario;
    private String nomeAprovacao;
    private String cargo;
    private String nome;
    private String unidade;
    
    public Usuario(){
        tentativaLogin = 0;
        conectado = false;
        autenticado = false;
    }
 
    public void tentativaLogin(){
        tentativaLogin++;
    }

    public boolean verificaSair(byte[] mensagem) throws IOException{
        DecodificaMensagem dm = new DecodificaMensagem(mensagem);
        if(dm.getByte() == 0){
            dm.close();
            return true;
        } 
        dm.close();
        return false;
    }
    
    public byte[] novaMensagem() throws IOException{
        return comunicacao.getMessage();
    }
    
    public void enviarMensagem(byte[] mensagem) throws IOException{
        comunicacao.sendMessage(mensagem);
    }
    
    public SocketAddress getRemoteSocketAddress(){
        return comunicacao.getRemoteSocketAddress();
    }
    
    public boolean conectar(Socket socket){
        try {
            this.comunicacao = new Comunicacao(socket);
            this.conectado = true; 
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
            this.conectado = false;
        }
        
        return this.conectado;
    }
    
    public void desconectar(){
        comunicacao.close();
        autenticado = false;
        conectado = false;
    }
    
    
    
    ///////////////////////////
    ///////////////////////////
    ///////////////////////////
    
     public Integer getnControle() {
        return nControle;
    }

    public void setnControle(Integer nControle) {
        this.nControle = nControle;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataValidacao() {
        return dataValidacao;
    }

    public void setDataValidacao(Date dataValidacao) {
        this.dataValidacao = dataValidacao;
    }

    public Integer getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(Integer codUsuario) {
        this.codUsuario = codUsuario;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
    
    public String getNomeAprovacao(){
        return this.nomeAprovacao;
    }
    
    public void setNomeAprovacao(String nomeAprovacao){
        this.nomeAprovacao = nomeAprovacao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
    public void setTentativaLogin(int tentativa){
        this.tentativaLogin = tentativa;
    }
    
    public int getTentativaLogin(){
        return tentativaLogin;
    }

    public Comunicacao getComunicacao() {
        return comunicacao;
    }

    public void setComunicacao(Comunicacao comunicacao) {
        this.comunicacao = comunicacao;
    }

    public boolean isAutenticado() {
        return autenticado && conectado;
    }

    public void setAutenticado(boolean autenticado) {
        this.autenticado = autenticado;
    }
    
    @Override
    public String toString(){
        return this.nomeAprovacao;
    }   
}
