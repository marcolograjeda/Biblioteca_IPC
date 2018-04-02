/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;
import java.awt.Component;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Junior
 */
public class Render extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable tabla, Object value, boolean seleccion, boolean hasFocus, 
            int fila, int columna){
            if(value instanceof JButton){
                JButton _btn = (JButton)value;
                return _btn;
            }
        return super.getTableCellRendererComponent(tabla, value, seleccion, hasFocus, fila, columna);
    }
}
