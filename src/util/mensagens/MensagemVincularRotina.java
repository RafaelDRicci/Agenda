/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import model.Rotina;
import model.Usuario;
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
    protected Usuario usuario;
    protected Rotina rotina;
    protected boolean prioritario;
    protected boolean reagendavel;
    protected boolean horarioFixo;
    protected int[] horarios;
    protected String periodo;
     * @param vincular 
     */
    
    public void setRotina(Rotina rotina) throws IOException{
        
        int codRotina = rotina.getCodRotina();
        setInt(codRotina);
        
        String nome = rotina.getNome();
        setString(nome);
        
        Date dataLimite = rotina.getDataLimite().getTime();
        setData(dataLimite);
        
        String descricao = rotina.getDescricao();
        setString(descricao);
        
    }
    
    public Rotina getRotina() throws IOException{
        int codRotina = getInt();
        String nome = getString();
        Rotina rotina = new Rotina(codRotina, nome);
    
        GregorianCalendar data = new GregorianCalendar();
        data.setTime(getData());
        rotina.setDataLimite(data);

        String descricao = getString();
        rotina.setDescricao(descricao);
        return rotina;
        
    }
    
    public void setUsuario(Usuario usuario) throws IOException{
       
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
    
    public Usuario getUsuario() throws IOException{
        
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
    
    
    
    protected void codificarObjeto(VincularRotina vincular) throws IOException{
        
        Rotina rotina = vincular.getRotina();
        setRotina(rotina);
        
        Usuario usuario = vincular.getUsuario();
        setUsuario(usuario);

        boolean prioritario = vincular.isPrioritario();
        setBoolean(prioritario);
        boolean reagendavel = vincular.isReagendavel();
        setBoolean(reagendavel);
        boolean horarioFixo = vincular.isHorarioFixo();
        setBoolean(horarioFixo);
        int[] horarios = vincular.getHorarios();
        setIntArray(horarios);
        String periodo = vincular.getPeriodo();
        setString(periodo);
    }
    
    protected VincularRotina decodificarObjeto()throws IOException{
        
        Rotina rotina = getRotina();
        Usuario usuario = getUsuario();
        VincularRotina vincular = new VincularRotina(rotina, usuario);
        
        boolean prioritario = getBoolean();
        vincular.setPrioritario(prioritario);
        boolean reagendavel = getBoolean();
        vincular.setReagendavel(reagendavel);
        boolean horarioFixo = getBoolean();
        vincular.setHorarioFixo(horarioFixo);
        int[] horarios = getIntArray();
        vincular.setHorarios(horarios);
        String periodo = getString();
        vincular.setPeriodo(periodo);
        
        return vincular;
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

    void setByte(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
