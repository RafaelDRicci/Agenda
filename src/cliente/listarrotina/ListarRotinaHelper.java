/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.listarrotina;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class ListarRotinaHelper {

    private ListarRotinaView view;
    
    ListarRotinaHelper(ListarRotinaView view) {
        this.view = view;
    }

        void preencherRotinasVinculadas(List<VincularRotina> rotinasVinculadas) {

            

            abstract class TabelaRotinasVinculadas extends AbstractTableModel{

                private String[] colunas = { "Rotina", "Período", "Horário"};

                /**
                 * A JTable vai chamar este método para obter a quantidade de colunas que ela possui
                 */
                @Override
                public int getColumnCount() {
                    return colunas.length;
                }

                /**
                 * A JTable vai chamar este método para obter o nome de cada coluna
                 */
                @Override
                public String getColumnName(int col) {
                    return colunas[col];
                }

                /**
                 * A JTable vai chamar este método para obter a quantidade de linhas que ela possui
                 */
                @Override
                public int getRowCount() {
                    return rotinasVinculadas.size();
                }

                /**
                 * A JTable vai chamar este método para obter o valor de cada célula
                 */
                @Override
                public Object getValueAt(int lin, int col) {
                    VincularRotina vinculada = rotinasVinculadas.get(lin);

                    switch(col){
                        case 0: return vinculada.getRotina();
                        case 1: return vinculada.getPeriodo();
                        case 2: return vinculada.getHorarios();
                        default: return null;

                }
            }
        }
    }
}
