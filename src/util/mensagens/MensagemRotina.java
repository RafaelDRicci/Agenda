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

    private void codificarObjeto(Rotina rotina) throws IOException{
        
        int codRotina = rotina.getCodRotina();
        setInt(codRotina);
        
        String nome = rotina.getNome();
        setString(nome);
        
        Date dataLimite = rotina.getDataLimite().getTime();
        setData(dataLimite);
        
        String descricao = rotina.getDescricao();
        setString(descricao);
    }
    
    private Rotina decodificarObjeto () throws IOException{
    
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
        codificarObjeto(rotina);
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
        
        return decodificarObjeto();
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
     * Requisição da lista de rotinas
     * Código da operação = 0;
     * @return
     * @throws IOException 
     */
    @Override
    public void codificarRequestList() throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
    }
    
    /**
     * Codificar uma lista de rotinas
     * @param rotinas
     * @return
     * @throws IOException 
     */
    @Override
    public void codificarList(List<Rotina> rotinas) throws IOException {
        codOperacao = 5;
        setByte(codOperacao);
        //número de rotinas
        int numeroRotinas = rotinas.size();
        setInt(numeroRotinas);
        //codificando cada rotina
        for(Rotina rotina: rotinas){
            codificarObjeto(rotina);
        }
    }
    
    /**
     * Decodificar uma mensagem(Array de Bytes) em um lista de rotina
     * @return
     * @throws IOException 
     */
    @Override
    public List<Rotina> decodificarList() throws IOException {
        
       if(codMensagem != 2) throw new IllegalArgumentException("Código inválido para Rotina");
       if(codOperacao != 5) throw new IllegalArgumentException("Código inválido para operação Listar Rotinas");
       //Faz a leitura do número de rotinas
       int numeroRotinas = getInt();
       //Cria uma Lista de rotinas
       List<Rotina> rotinas = new ArrayList<>();
       //faz um loop para leitura de cada rotina
       for(int i = 0; i < numeroRotinas; i++){
           Rotina rotina = decodificarObjeto();
           rotinas.add(rotina);
       }
       return rotinas;
    }
    
    public void codificarRotina(Rotina rotina) throws IOException{
        codOperacao = 6;
        setByte(codOperacao);
        codificarObjeto(rotina);
    }
    
    public Rotina decodificarRotina() throws IOException{
        
        if(codMensagem != 2) throw new IllegalArgumentException("Código de mensagem inválido para Mensagem Rotina");
        if(codOperacao != 6) throw new IllegalArgumentException("Código de operação inválido para Decodificar Rotina");
        
        Rotina rotina = decodificarObjeto();
        return rotina;
    }
}
