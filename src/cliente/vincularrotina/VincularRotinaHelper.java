/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vincularrotina;

import java.util.ArrayList;
import java.util.List;
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

    public VincularRotina obterVinculacao() {
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
            System.out.println(horarios[i]);
        }
        
        VincularRotina vincular;
        
        switch(periodo){
            case("DataUnica"):
                vincular = new DataUnica(rotina, usuario);
                break;
            default:
                vincular = new VincularRotina(rotina, usuario);
        }
        
        vincular.setPrioritario(prioritario);
        vincular.setReagendavel(reagendavel);
        vincular.setHorarioFixo(horarioFixo);
        return vincular;
    }
}
