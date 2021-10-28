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
import model.vincularrotina.DataUnica;

/**
 *
 * @author rafaeld
 */
public class MensagemVincularRotina extends Mensagem<VincularRotina>{
    
    public MensagemVincularRotina(){
        super((byte)4);
    }
    
    public MensagemVincularRotina(byte[] mensagem){
        super(mensagem);
    }

    
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
    
    @Override
    public void setObjeto(VincularRotina vincular) throws IOException{
        
        Rotina rotina = vincular.getRotina();
        setRotina(rotina);
        
        Usuario usuario = vincular.getUsuario();
        setUsuario(usuario);

        boolean prioritario = vincular.isPrioritario();
        boolean reagendavel = vincular.isReagendavel();
        boolean horarioFixo = vincular.isHorarioFixo();
        int[] horarios = vincular.getHorarios();
        String periodo = vincular.getPeriodo();

        setBoolean(prioritario);
        setBoolean(reagendavel);
        setBoolean(horarioFixo);
        setIntArray(horarios);
        setString(periodo);
        
        switch(periodo){
            case "DataUnica":
                Date data = ((DataUnica)vincular).getData().getTime();
                setData(data);
                break;
        }  
    }
    
    @Override
    public VincularRotina getObjeto()throws IOException{
        
        Rotina rotina = getRotina();
        Usuario usuario = getUsuario();
        
        boolean prioritario = getBoolean();
        boolean reagendavel = getBoolean();
        boolean horarioFixo = getBoolean();  
        int[] horarios = getIntArray();
        String periodo = getString();

        VincularRotina vincular = null;
        switch(periodo){
            case "DataUnica":
                vincular = new DataUnica(rotina, usuario);
                GregorianCalendar data = new GregorianCalendar();
                data.setTime(getData());
                ((DataUnica)vincular).setData(data);
                break;
            default:
                vincular = new VincularRotina(rotina,usuario);
                break;
        }
        vincular.setPrioritario(prioritario);
        vincular.setReagendavel(reagendavel);
        vincular.setHorarioFixo(horarioFixo);
        vincular.setHorarios(horarios);
        vincular.setPeriodo(periodo);
         
        return vincular;
    }

    @Override
    public void codificarCreate(VincularRotina vincularRotina) throws IOException {
        
        codOperacao = 1;
        setByte(codOperacao);
        
        setObjeto(vincularRotina);
    }

    @Override
    public VincularRotina decodificarCreate() throws IOException {
        
        if(codMensagem != 4) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Vincular Rotina");
        if(codOperacao != 1) throw new IllegalArgumentException("Código de operação inválido para Decodificar Create");
        
        VincularRotina vincularRotina = getObjeto();
        return vincularRotina;
    }

    @Override
    public void codificarRead(int codRotina) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void codificarReadVincularRotina(Rotina rotina, Usuario usuario) throws IOException{
        
        codOperacao = 2;
        setByte(codOperacao);
        
        setInt(rotina.getCodRotina());
        setInt(usuario.getCodUsuario());
        
    }

    @Override
    public int[] decodificarRead() throws IOException{
        
        if(codMensagem != 4 ) throw new IllegalArgumentException("Código inválido para mensagem Vincular Rotina");
        if(codOperacao != 2 ) throw new IllegalArgumentException("Código inválido para READ");
        
        int codRotina = getInt();
        int codUsuario = getInt();
        
        int[] codigos = new int[2];
        codigos[0] = codRotina;
        codigos[1] = codUsuario;
        
        return codigos;
    }
    //3
    @Override
    public void codificarUpdate(VincularRotina objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VincularRotina decodificarUpdate() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    //4
    @Override
    public void codificarDelete(VincularRotina vinculacao) throws IOException {
        
        codOperacao = 4;
        setByte(codOperacao);
      
        setObjeto(vinculacao);
    
    }

    @Override
    public VincularRotina decodificarDelete() throws IOException {
        
        if(codMensagem != 4 ) throw new IllegalArgumentException("Código inválido para mensagem Vincular Rotina");
        if(codOperacao != 4 ) throw new IllegalArgumentException("Código inválido para DELETE");
        
        VincularRotina vinculacao = getObjeto();
        return vinculacao;
    }

    public void requestListAll() throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
    }

    public void codificarListAll(List<VincularRotina> vinculacoes) throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
        setList(vinculacoes);
    }

    public List<VincularRotina> decodificarListAll() throws IOException {
       
       if(codMensagem != 4) throw new IllegalArgumentException("Código inválido para Vincular Rotina");
       if(codOperacao != 5) throw new IllegalArgumentException("Código inválido para operação Listar");
       
       return getList();
    }
    
    
    public void codificarVincularRotina(VincularRotina vincularRotina) throws IOException{
        
        codOperacao = 6;
        setByte(codOperacao);
        setObjeto(vincularRotina);
        
    }
    
    public VincularRotina decodificarVincularRotina() throws IOException{
        
        if(codMensagem != 4) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Vincular Rotina");
        if(codOperacao != 6) throw new IllegalArgumentException("Código de operação inválido para Decodificar Vincular Rotina");
        
        VincularRotina vincularRotina = getObjeto();
        return vincularRotina;
    }
    
    public void requestListVinculadasUsuario(Usuario usuario) throws IOException{
        
        codOperacao = 7;
        setByte(codOperacao);
        setUsuario(usuario);
        
    }
}
