/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.mensagens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import model.Rotina;

/**
 *
 * @author rafaeld
 */
public class MensagemRotina extends Mensagem<Rotina>{

    public MensagemRotina() {
        super();
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
    public byte[] codificar(Rotina objeto) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Rotina decodifica() throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        byte i = 2;
        //código da mensagem
        setByte(i);
        //número de rotinas
        setInt(rotinas.size());
        //codificando cada rotina
        for(Rotina rotina: rotinas){
            //código da rotina
            setInt(rotina.getCodRotina());
            //nome 
            setString(rotina.getNome());
            //Data
            setData(rotina.getDataLimite().getTime());
            //Descricao
            setString(rotina.getDescricao());
        }
        return getMensagem();
    }
    
    
    /**
     * Decodificar uma mensagem(Array de Bytes) em um lista de rotina
     * @return
     * @throws IOException 
     */
    @Override
    public List<Rotina> decodificar() throws IOException {
       
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
