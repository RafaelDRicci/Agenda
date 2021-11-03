/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Rotina;
import model.Usuario;

/**
 *
 * @author rafaeld
 */
class RotinasTableModel extends AbstractTableModel{

    private List<Rotina> rotinas;
    private String[] columns = { "Codigo", "Nome", "Data Limite"};

    public RotinasTableModel(List<Rotina> rotinas){
        this.rotinas = rotinas;
    }
    
    public RotinasTableModel(){
        this.rotinas = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return rotinas.size();
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
    
    public void setValueAt(Rotina aValue, int rowIndex){
        
        Rotina rotina = rotinas.get(rowIndex);
        
        rotina.setCodRotina(aValue.getCodRotina());
        rotina.setNome(aValue.getNome());
        rotina.setDataLimite(aValue.getDataLimite());
        rotina.setDescricao(aValue.getDescricao());
        
        fireTableCellUpdated(rowIndex, 0);
        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);

    }
    
    public Rotina getValueAt(int rowIndex){     
        return rotinas.get(rowIndex);
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Rotina rotina = rotinas.get(rowIndex);

        switch (columnIndex) {
             case 0:
                 rotina.setCodRotina( ((Rotina)aValue).getCodRotina() );
             case 1:
                 rotina.setNome( ((Rotina)aValue).getNome() );
             case 2:
                 rotina.setDataLimite(((Rotina)aValue).getDataLimite());
             case 3:
                 rotina.setDescricao(((Rotina)aValue).getDescricao());

             default:
                 System.err.println("Índice da coluna inválido");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rotina rotinaSelecionada = rotinas.get(rowIndex);
        Object valueObject = null;
        switch(columnIndex){
            case 0: valueObject = rotinaSelecionada.getCodRotina(); break;
            case 1: valueObject = rotinaSelecionada.getNome(); break;
            case 2 : valueObject = rotinaSelecionada.getDataLimite(); break;
            case 3: valueObject = rotinaSelecionada.getDescricao(); break;
            
            default: System.err.println("Índice inválido para propriedade do VincularRotina.class");
        }

        return valueObject;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(rowIndex < rotinas.size() && columnIndex < columns.length) return true;
        return false;
    }

    public Rotina getUsuario(int indiceLinha) {
        return rotinas.get(indiceLinha);
    }

    public void addUsuario(Rotina rotina) {
        rotinas.add(rotina);


        int ultimoIndice = getRowCount() - 1;

        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }


    public void removeVinculada(int indiceLinha) {
        rotinas.remove(indiceLinha);

        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }


    public void addListaDeUsuarios(List<Rotina> rotinas) {

        int tamanhoAntigo = getRowCount();
        this.rotinas.addAll(rotinas);
        fireTableRowsInserted(tamanhoAntigo, getRowCount() - 1);
    }

    public void limpar() {
        rotinas.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return rotinas.isEmpty();
    } 
} 
