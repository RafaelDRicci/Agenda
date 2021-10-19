/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Rotina;

/**
 * 
 * @author rafaeld
 */
public class MensagemRotina extends Mensagem<Rotina>{

    public MensagemRotina() {
        super();
        try {
            codificar();
        } catch (IOException ex) {
            Logger.getLogger(MensagemRotina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MensagemRotina(byte[] bytes) {
        super(bytes);
    }

    /**
     * Requisição da lista de rotinas
     * @return
     * @throws IOException 
     */
    @Override
    public byte[] codificar() throws IOException {
        byte cod = 2;
        this.setByte(cod);
        return getMensagem();
    }

    @Override
    public byte[] codificar(Rotina rotina) throws IOException {
        
        resetBAOS();
        byte cod = 4;
        setByte(cod);
        
        String nome = rotina.getNome();
        setString(nome);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dataLimite = sdf.format(rotina.getDataLimite().getTime());
        setString(dataLimite);
        
        String descricao = rotina.getDescricao();
        setString(descricao);
        
        return getMensagem();
    }

    @Override
    public Rotina decodificar() throws IOException {
        
        byte cod = getByte();
        if(!(cod == 4)) throw new IllegalArgumentException("Código inválido para Registrar Rotina");
        
        String nome = getString();
        Rotina rotina = new Rotina(nome);
        
        String dataString = getString();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            
            Date dataLimite = sdf.parse(dataString);
            rotina.setDataLimite(dataLimite);
            
        } catch (ParseException ex) {
            Logger.getLogger(MensagemRotina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String descricao = getString();
        rotina.setDescricao(descricao);
        return rotina;
    }
    
    @Override
    public byte[] codificar(Rotina... objetos) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Codificar uma lista de rotinas
     * @param rotinas
     * @return
     * @throws IOException 
     */
    
    @Override
    public byte[] codificar(List<Rotina> rotinas) throws IOException {
        //número de rotinas
        int numeroRotinas = rotinas.size();
        setInt(numeroRotinas);
        //codificando cada rotina
        for(Rotina rotina: rotinas){
            //código da rotina
            int codRotina = rotina.getCodRotina();
            setInt(codRotina);
            //nome 
            String nome = rotina.getNome();
            setString(nome);
            //Data
            Date dataLimite = rotina.getDataLimite().getTime();
            setData(dataLimite);
            //Descricao
            String descricao = rotina.getDescricao();
            setString(descricao);
        }
        return getMensagem();
    }
    
    
    /**
     * Decodificar uma mensagem(Array de Bytes) em um lista de rotina
     * @return
     * @throws IOException 
     */
    @Override
    public List<Rotina> decodificarList() throws IOException {
       
       //Código da Mensagem
       byte cod = getByte();
       //Faz um verificação se o código condiz com (2), código de listar rotinas
       if(!(cod == 2)) throw new IllegalArgumentException("Código inválido para Listar Rotinas");
       //Faz a leitura do número de rotinas
       int numeroRotinas = getInt();
       //Cria uma Lista de rotinas
       List<Rotina> rotinas = new ArrayList<>();
       //faz um loop para leitura de cada rotina
       for(int i = 0; i < numeroRotinas; i++){
           //código da rotina
           int codRotina = getInt();
           //nome
           String nome = getString();
            //Data limite
           GregorianCalendar data = new GregorianCalendar();
           data.setTime(getData());
           //Criação da rotina
           Rotina rotina = new Rotina(codRotina, nome);
           //seta a data e a descricao;
           rotina.setDataLimite(data);
           rotina.setDescricao(getString());
           //Adiciona a rotina na lista
           rotinas.add(rotina);
       }

       return rotinas;
    }
    
}
