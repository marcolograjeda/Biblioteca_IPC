/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.view;

import biblioteca.Ejecucion;
import biblioteca.Render;
import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Junior
 */
public class Tabla {
    JFrame ventanaTabla = new JFrame();
    JScrollPane scrollPane = new JScrollPane();
    public void ventanaTabla(int opcion){
        ventanaTabla.setSize(700,540);
        ventanaTabla.setLayout(null);
        ventanaTabla.setResizable(false);
        ventanaTabla.remove(scrollPane);
        Ejecucion ejec = new Ejecucion();
        ventanaTabla.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
              ventanaTabla.setVisible(false);
              ejec.activarVentana();
            }
        });
        JTable tabla;
        if(opcion!=5){
            tabla = ejec.mostrarTabla(opcion, false, "");
            tabla.setDefaultRenderer(Object.class, new Render());
            tabla.addMouseListener(new MouseAdapter(){
            @Override
                public void mousePressed(MouseEvent e){
                    int columna = tabla.getColumnModel().getColumnIndexAtX(e.getX());
                    int fila = e.getY()/tabla.getRowHeight();

                    if(fila<tabla.getRowCount() && fila >=0 && columna < tabla.getColumnCount() && columna >=0){
                        Object value = tabla.getValueAt(fila, columna);
                        if(value instanceof JButton){
                            JButton btn2 = (JButton)value;
                            if(!btn2.getName().equals("Deshabilitado")){
                                 ((JButton)value).doClick();
                            }
                        }
                    }
                }
            });
            scrollPane = new JScrollPane(tabla);
            scrollPane.setBounds(new Rectangle(25, 100, 650, 300));
        }else if(opcion==5){
            String[] _elementosLista = {"5 usuarios con mayor material", "5 autores con mayor material", 
                "5 revistas más prestadas", "10 libros más prestados", "Libro más consultado"};
            JComboBox lista = new JComboBox(_elementosLista);
            lista.setBounds(510, 55, 120, 30);
            lista.setEditable(false);
            lista.setSelectedIndex(0);
            lista.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println(lista.getSelectedItem());
                    ventanaTabla.remove(scrollPane);
                    scrollPane = reportes(lista.getSelectedItem().toString());
                    ventanaTabla.add(scrollPane, BorderLayout.CENTER);
                }
            });
            ventanaTabla.add(lista);
        }
        
        JButton _btnSalir = new JButton("Salir");
        _btnSalir.setBounds(560, 20, 70, 25);
        
        _btnSalir.addActionListener(
            new ActionListener(){
                Ejecucion ejec = new Ejecucion();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaTabla.setVisible(false);
                    ejec.cerrarSesion();
                }
            }
        );
        ventanaTabla.add(_btnSalir);
        ventanaTabla.add(scrollPane, BorderLayout.CENTER);
        ventanaTabla.setVisible(true);
    }
    
    public JScrollPane reportes(String tipo){
        Ejecucion ejec = new Ejecucion();
        JTable tabla = ejec.reportes(tipo);
        scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(new Rectangle(25, 100, 650, 300));
        return scrollPane;
    }
}
