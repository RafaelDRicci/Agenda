/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import model.Usuario;

/**
 *
 * @author rafaeld
 */
public class MensagemCliente {
    
    private Usuario usuario;
    private byte[] mensagem;

    public MensagemCliente(Usuario usuario, byte[] mensagem) {
        this.usuario = usuario;
        this.mensagem = mensagem;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public byte[] getMensagem() {
        return mensagem;
    }

    public void setMensagem(byte[] mensagem) {
        this.mensagem = mensagem;
    }
}
