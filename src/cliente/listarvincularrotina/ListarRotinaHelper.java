/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.listarvincularrotina;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import model.Rotina;
import model.Usuario;
import model.VincularRotina;

/**
 *
 * @author rafaeld
 */
public class ListarRotinaHelper {

    private ListarRotinaVinculadasView view;
    
    ListarRotinaHelper(ListarRotinaVinculadasView view) {
        this.view = view;
    }

    void preencherRotinasVinculadas(List<VincularRotina> rotinasVinculadas) {
        
        RotinasVinculadasTableModel tableModel = new RotinasVinculadasTableModel(rotinasVinculadas);
        view.setTableModel(tableModel);
        
    }

    void exibeRotinaVinculadaSelecionada() {
        final int rowIndex = view.getjTableRotinas().getSelectedRow();
        VincularRotina vinculada = ((RotinasVinculadasTableModel)(view.getjTableRotinas().getModel())).getValueAt(rowIndex);
        String mensagem = vinculada.toString();
        JOptionPane.showMessageDialog(view, mensagem, "Detalhes Vinculação", 1);
    }

}
class RotinasVinculadasTableModel extends AbstractTableModel{

    private List<VincularRotina> rotinasVinculadas;
    private String[] columns = { "Rotina", "Usuário", "Período", "Horário"};

    public RotinasVinculadasTableModel(List<VincularRotina> rotinasVinculadas){
        this.rotinasVinculadas = rotinasVinculadas;
    }
    
    public RotinasVinculadasTableModel(){
        this.rotinasVinculadas = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return rotinasVinculadas.size();
    }
    
    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }


    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch(columnIndex){
            case 0: return Rotina.class;
            case 1: return Usuario.class;
            case 2: return String.class;
            case 3: return String.class;
        }
        return String.class;
    }
    
    public void setValueAt(VincularRotina aValue, int rowIndex){
        
        VincularRotina vincular = rotinasVinculadas.get(rowIndex);
        
        vincular.setUsuario(aValue.getUsuario());
        vincular.setRotina(aValue.getRotina());
        vincular.setReagendavel(aValue.isReagendavel());
        vincular.setPrioritario(aValue.isPrioritario());
        vincular.setHorarioFixo(aValue.isHorarioFixo());
        vincular.setPeriodo(aValue.getPeriodo());
        vincular.setHorarios(aValue.getHorarios());
        
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
        fireTableCellUpdated(rowIndex, 3);
    }
    
    public VincularRotina getValueAt(int rowIndex){     
        return rotinasVinculadas.get(rowIndex);
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        VincularRotina vinculada = rotinasVinculadas.get(rowIndex);

        switch (columnIndex) {
             case 0:
                 vinculada.setRotina( ((VincularRotina)aValue).getRotina() );
             case 1:
                 vinculada.setUsuario( ((VincularRotina)aValue).getUsuario() );
             case 2:
                 vinculada.setPeriodo( ((VincularRotina)aValue).getPeriodo() );
             case 3:
                 vinculada.setHorarios( ((VincularRotina)aValue).getHorarios()  );

             default:
                 System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VincularRotina vinculacaoSelecionada = rotinasVinculadas.get(rowIndex);
        Object valueObject = null;
        switch(columnIndex){
            case 0: valueObject = vinculacaoSelecionada.getRotina(); break;
            case 1: valueObject = vinculacaoSelecionada.getUsuario(); break;
            case 2 : valueObject = vinculacaoSelecionada.getPeriodo(); break;
            case 3: valueObject = vinculacaoSelecionada.converteHorasEmString(); break;
            
            default: System.err.println("Índice inválido para propriedade do VincularRotina.class");
        }

        return valueObject;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(rowIndex < rotinasVinculadas.size() && columnIndex < columns.length) return true;
        return false;
    }

    public VincularRotina getUsuario(int indiceLinha) {
        return rotinasVinculadas.get(indiceLinha);
    }

    public void addUsuario(VincularRotina vincular) {
        rotinasVinculadas.add(vincular);


        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }


    public void removeVinculada(int indiceLinha) {
        rotinasVinculadas.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }


    public void addListaDeUsuarios(List<VincularRotina> novasVinculacoes) {

        int tamanhoAntigo = getRowCount();
        rotinasVinculadas.addAll(novasVinculacoes);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        rotinasVinculadas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return rotinasVinculadas.isEmpty();
    }

   
}