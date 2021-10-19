/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.cliente;

/**
 *
 * @author rafaeld
 */

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorirTabelaRotinas extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable jTable, Object value, 
            boolean isSelected, boolean hasFocus, int row, int column){
        super.getTableCellRendererComponent(jTable, value, isSelected, hasFocus, row, column);
        
        Object text = jTable.getValueAt(row, 2);
        
        if(text != null && "Rotina A".equals(text.toString())){
            
        }
        
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        
        return this;
    }
    
}
