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

/**
 * Mesagem Rotina<br>
 * Código da Menagem = 2<br>
 * @author rafaeld
 */
public class MensagemRotina extends Mensagem<Rotina>{

    public MensagemRotina() {
        super((byte)2);
    }

    public MensagemRotina(byte[] bytes) {
        super(bytes);
    }

    @Override
    public void setObjeto(Rotina rotina) throws IOException{
        
        int codRotina = rotina.getCodRotina();
        setInt(codRotina);
        
        String nome = rotina.getNome();
        setString(nome);
        
        Date dataLimite = rotina.getDataLimite().getTime();
        setData(dataLimite);
        
        String descricao = rotina.getDescricao();
        setString(descricao);
    }
    
    @Override
    public Rotina getObjeto () throws IOException{
    
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
    
    /**
     * Enviar uma nova rotina para ser salva no banco
     * Código da operação = 1
     * @param rotina
     * @return
     * @throws IOException 
     */
    @Override
    public void codificarCreate(Rotina rotina) throws IOException {
        
        codOperacao = 1;
        setByte(codOperacao);
        setObjeto(rotina);
    }

    /**
     * Decodificar um rotina para ser inserida no banco de dados
     * @return
     * @throws IOException 
     */
    @Override
    public Rotina decodificarCreate() throws IOException {
        
        if(codMensagem != 2) throw new IllegalArgumentException("Código inválido para Rotina");
        if(codOperacao != 1) throw new IllegalArgumentException("Código inválido para operação Create Rotina");
        
        return getObjeto();
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
    public void codificarUpdate(Rotina rotina) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rotina decodificarUpdate() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void codificarDelete(Rotina rotina) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rotina decodificarDelete() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Requisição de uma lista de rotinas com todas as rotinas salvas no banco.
     * A mansagem não possui nenhuma informação além do código da operação, pois apenas faz uma requisição para o sistema
     * Código da operação = 5;
     * @return
     * @throws IOException 
     */
    
    public void requestListAll() throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
    }
    
    /**
     * Codifica uma lista de rotinas contendo todas as rotinas salvas no banco.
     * A mensagem possui o código de operação (5) para essa requisição e uma lista de rotinas com todas salvas
     * no banco. obs: Todos os métodos de listagem são bem parecidos, a única mudança é o valor de codOperação, que informa
     * ao sistema qual a lista que o usuário solicitou.
     * @param rotinas
     * @return
     * @throws IOException 
     */
    
    public void codificarListAll(List<Rotina> rotinas) throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
        setList(rotinas);
    }
    
    /**
     * Decodificar uma mensagem(Array de Bytes) em um lista de rotina
     * @return
     * @throws IOException 
     */
    
    public List<Rotina> decodificarListAll() throws IOException {
        
       if(codMensagem != 2) throw new IllegalArgumentException("Código inválido para Rotina");
       if(codOperacao != 5) throw new IllegalArgumentException("Código inválido para operação Listar Todas as Rotinas");
       
       return getList();
    }
    
    public void codificarRotina(Rotina rotina) throws IOException{
        codOperacao = 6;
        setByte(codOperacao);
        setObjeto(rotina);
    }
    
    public Rotina decodificarRotina() throws IOException{
        
        if(codMensagem != 2) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Rotina");
        if(codOperacao != 6) throw new IllegalArgumentException("Código de operação inválido para Decodificar Rotina");
        
        Rotina rotina = getObjeto();
        return rotina;
    }
}
