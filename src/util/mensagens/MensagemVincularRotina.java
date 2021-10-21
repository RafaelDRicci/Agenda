/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.util.List;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class MensagemVincularRotina extends Mensagem{
    
    public MensagemVincularRotina(){
        super((byte)4);
    }
    
    public MensagemVincularRotina(byte[] mensagem){
        super(mensagem);
    }

    /**
     * protected Usuario usuario;
        protected Rotina rotina;
    protected boolean prioritario;
    protected boolean reagendavel;
    protected boolean horarioFixo;
    protected int[] horarios;
    protected String periodo;
     * @param vincular 
     */
    
    private void codificarObjeto(VincularRotina vincular) throws IOException{
        
        int codRotina = vincular.getRotina().getCodRotina();
        setInt(codRotina);
        int codUsuario = vincular.getUsuario().getCodUsuario();
        setInt(codUsuario);
        
        
    }
    
    @Override
    public void codificarCreate(Object objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object decodificarCreate() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarRead(int codRotina) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object decodificarRead() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarUpdate(Object objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object decodificarUpdate() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarDelete(Object objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object decodificarDelete() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarRequestList() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarList(List list) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List decodificarList() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
