/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.util.ArrayList;
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
        
        switch(periodo){
            case "DataUnica":
                DataUnica dataUnica = (DataUnica)vincular;
                System.out.println(dataUnica.getData());
                
                break;
            default:
                break;
        }
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
        
        switch(periodo){
            case "DataUnica":
                DataUnica dataUnica = (DataUnica)vincular;
                System.out.println(dataUnica.getData());
                
                break;
            default:
                break;
        }
        
        return vincular;
    }

    @Override
    public void codificarCreate(VincularRotina vincularRotina) throws IOException {
        
        codOperacao = 1;
        setByte(codOperacao);
        
        codificarObjeto(vincularRotina);
    }

    @Override
    public VincularRotina decodificarCreate() throws IOException {
        
        if(codMensagem != 4) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Vincular Rotina");
        if(codOperacao != 1) throw new IllegalArgumentException("Código de operação inválido para Decodificar Create");
        
        VincularRotina vincularRotina = decodificarObjeto();
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

    @Override
    public void codificarUpdate(VincularRotina objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VincularRotina decodificarUpdate() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarDelete(VincularRotina objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public VincularRotina decodificarDelete() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarRequestList() throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
    }

    @Override
    public void codificarList(List<VincularRotina> vinculacoes) throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
        int numeroVinculacoes = vinculacoes.size();
        setInt(numeroVinculacoes);
        for(VincularRotina vincular: vinculacoes){
            codificarObjeto(vincular);
        }
    }

    @Override
    public List<VincularRotina> decodificarList() throws IOException {
       
       if(codMensagem != 4) throw new IllegalArgumentException("Código inválido para Vincular Rotina");
       if(codOperacao != 5) throw new IllegalArgumentException("Código inválido para operação Listar");
       
       int numeroVinculacoes = getInt();

       List<VincularRotina> vinculacoes = new ArrayList<>();

       for(int i = 0; i < numeroVinculacoes; i++){
           VincularRotina vincular = decodificarObjeto();
           vinculacoes.add(vincular);
       }
       return vinculacoes;
    }
    
    
    public void codificarVincularRotina(VincularRotina vincularRotina) throws IOException{
        
        codOperacao = 6;
        setByte(codOperacao);
        codificarObjeto(vincularRotina);
        
    }
    
    public VincularRotina decodificarVincularRotina() throws IOException{
        
        if(codMensagem != 4) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Vincular Rotina");
        if(codOperacao != 6) throw new IllegalArgumentException("Código de operação inválido para Decodificar Vincular Rotina");
        
        VincularRotina vincularRotina = decodificarObjeto();
        return vincularRotina;
    }
}
