/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.principal;

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
        Object text2 = jTable.getValueAt(3, column);
        
        Color c = Color.WHITE;
        
        if(text != null && "Rotina A".equals(text.toString())){
            c = Color.RED;
        }
        
        
        
        setBackground(c);
        
        
        return this;
    }
    
}
