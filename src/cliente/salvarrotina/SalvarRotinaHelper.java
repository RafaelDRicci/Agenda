/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.salvarrotina;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Rotina;

/**
 *
 * @author rafaeld
 */
public class SalvarRotinaHelper {

    private SalvarRotinaView view;
    
    public SalvarRotinaHelper(SalvarRotinaView view) {
        this.view = view;
    }

     public Rotina novaRotina() throws ParseException, Exception {
        
        String nome = view.getjTextFieldNomeRotina().getText();
        if(nome.equals("")) throw new Exception("Campo Nome é obritatório");
        String descricao = view.getjTextAreaDescricao().getText();
        Rotina rotina = new Rotina(nome);
        rotina.setDescricao(descricao);
        String campoData = view.getjTextFieldDataLimite().getText();
        
        if(!campoData.equals("")){
            try {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date dataLimite = sdf.parse(campoData);      
            rotina.setDataLimite(dataLimite);

            } catch (ParseException ex) {
                throw new ParseException("Formato de data incorreta; Utilize o formato: (DD/MM/AAAA)", 0);
            }
        }
        
        return rotina;
    }
}
