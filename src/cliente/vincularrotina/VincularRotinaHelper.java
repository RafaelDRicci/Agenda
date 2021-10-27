/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.NoSuchElementException;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;
import model.vincularrotina.DataUnica;

/**
 *
 * @author rafaeld
 */
public class VincularRotinaHelper {

    private VincularRotinaView view;
    
    VincularRotinaHelper(VincularRotinaView view) {
        this.view = view;
    }   

    void preencherRotinas(List<Rotina> rotinas) {
       for(Rotina rotina: rotinas){
           view.getjComboBoxRotina().addItem(rotina);
       }
    }

    void preencherUsuarios(List<Usuario> usuarios) {
        for(Usuario usuario : usuarios){
            view.getjComboBoxFuncionario().addItem(usuario);
        }
    }

    public VincularRotina obterVinculacao() throws Exception{
        
        Rotina rotina = (Rotina) view.getjComboBoxRotina().getSelectedItem();
        Usuario usuario = (Usuario) view.getjComboBoxFuncionario().getSelectedItem();
        
        boolean prioritario = view.getjCheckBoxPrioritario().isSelected();
        boolean reagendavel = view.getjCheckBoxReagendavel().isSelected();
        boolean horarioFixo = view.getjCheckBoxHorarioFixo().isSelected();
        
        String periodo = (String) view.getjComboBoxPeriodo().getSelectedItem();
        
        int tamanhoHorarios = view.getjListHorario().getModel().getSize();
        int[] horarios = new int[tamanhoHorarios];
        
        for(int i = 0; i < tamanhoHorarios; i++){
            String elemento = view.getjListHorario().getModel().getElementAt(i);
            horarios[i] = Integer.parseInt(elemento.split("h")[0]);
        }
        
        VincularRotina vincular;
        
        switch(periodo){
            case("Data Única"):
                vincular = new DataUnica(rotina, usuario);
                
                int dia = getDia(0);          
                int mes = getMes(0);
                int ano = getAno(0);
                
                GregorianCalendar data = new GregorianCalendar(ano, mes, dia);
                
                ((DataUnica)vincular).setData(data);
                
                break;
            default:
                vincular = new VincularRotina(rotina, usuario);
        }
        
        vincular.setPrioritario(prioritario);
        vincular.setReagendavel(reagendavel);
        vincular.setHorarioFixo(horarioFixo);
        vincular.setHorarios(horarios);
        return vincular;
    }
    
    public static int converteMesStringEmInt(String mes){
        
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM");
        
        for(int i = 0; i < 12; i++){
            GregorianCalendar calendar = new GregorianCalendar(2021, i, 1);
            if( mes.equals(sdf.format(calendar.getTime())) ) return i;
        }
        
        throw new NoSuchElementException("Não exite mês "+mes);
    }
    
    public int getDia(int posicao) throws Exception{
        if(view.getjListDia().getModel().getSize() == 0) throw new Exception("Campo Dia é Obrigatório.");
        return Integer.parseInt(view.getjListDia().getModel().getElementAt(posicao)); 
    }
    
    public int getMes(int poscicao) throws Exception{
        if(view.getjListMes().getModel().getSize() == 0) throw new Exception("Campo Mês é Obrigatório.");
        return converteMesStringEmInt(view.getjListMes().getModel().getElementAt(poscicao));
    }
    
    public int getAno(int posicao) throws Exception{
        if(view.getjListAno().getModel().getSize() == 0) throw new Exception("Campo Ano é Obrigatório.");
        return Integer.parseInt(view.getjListAno().getModel().getElementAt(posicao));
    }
}
