/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.view;

import biblioteca.Ejecucion;
import biblioteca.Render;
import biblioteca.obj.Usuario;
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
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author Junior
 */
public class Docs {
    JFrame ventanaDocumento = new JFrame();
    JScrollPane scroll = new JScrollPane();
    public void ventanaDocumento(){
        JFrame ventanaNueva = new JFrame();
        ventanaDocumento = ventanaNueva;
        ventanaDocumento.setSize(700,540);
        ventanaDocumento.setLayout(null);
        ventanaDocumento.setResizable(false);
        ventanaDocumento.remove(scroll);
        scroll = cargarTabla("-");
        
        Ejecucion ejec = new Ejecucion();
        ventanaDocumento.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
              //ventanaDocumento.setVisible(false);
                if(ejec.obtenerUsuario()==null||!ejec.obtenerUsuario().isRol()){
                    Login log = new Login();
                    log.crearLogin();
                }else{
                  ventanaDocumento.dispose();
                    ejec.activarVentana();
                }
            }
        });
        String[] _elementosLista = new String[0]; 
        if(ejec.obtenerUsuario()==null||ejec.obtenerUsuario().isRol()){
            String[] _elementosListado = {"-", "Libros", "Revistas", "Tesis"};
            _elementosLista = _elementosListado;
        }else if(!ejec.obtenerUsuario().isRol()){
            String[] _elementosListado = {"-", "Libros", "Revistas", "Tesis", "Mis Prestamos"};
            _elementosLista = _elementosListado;
        }
        JComboBox lista = new JComboBox(_elementosLista);
        lista.setBounds(560, 55, 100, 30);
        lista.setEditable(false);
        lista.setSelectedIndex(0);
        JTextField _txtBuscar = new JTextField();
        
        _txtBuscar.setText("");
        JButton _btnSalir = new JButton("Salir");
        JButton _btnIngresar = new JButton("Ingresar");
        JButton _btnBuscar = new JButton("Buscar");
        _txtBuscar.setBounds(20, 55, 120, 30);
        _btnBuscar.setBounds(165, 55, 85, 30);
        
        if(ejec.obtenerUsuario()==null){
            _btnIngresar.setBounds(560, 20, 90, 25);
        
            _btnIngresar.addActionListener(
                new ActionListener(){
                    Login log = new Login();
                    @Override
                    public void actionPerformed (ActionEvent e){
                        ventanaDocumento.setVisible(false);
                        log.crearLogin();
                    }
                }
            );
            ventanaDocumento.add(_btnIngresar);
        }else{
            _btnSalir.setBounds(560, 20, 70, 25);
            _btnSalir.addActionListener(
                new ActionListener(){
                    Ejecucion ejec = new Ejecucion();
                    @Override
                    public void actionPerformed (ActionEvent e){
                        ventanaDocumento.setVisible(false);
                        ejec.cerrarSesion();
                    }
                }
            );
            ventanaDocumento.add(_btnSalir);
        }
        _btnBuscar.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                    Ejecucion ejec = new Ejecucion();
                    ventanaDocumento.remove(scroll);
                    scroll = cargarTablaBusqueda(lista.getSelectedItem().toString(), _txtBuscar.getText());
                    ventanaDocumento.add(scroll, BorderLayout.CENTER);
                }
             }
        );
        
        lista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!lista.getSelectedItem().equals("Mis Prestamos")){
                    System.out.println(lista.getSelectedItem());
                    ventanaDocumento.remove(scroll);
                    scroll = cargarTabla(lista.getSelectedItem().toString());
                    ventanaDocumento.add(scroll, BorderLayout.CENTER);
                }else{
                    misPrestamos();
                }
            }
        });
        
        ventanaDocumento.add(scroll, BorderLayout.CENTER);
        ventanaDocumento.add(lista);
        ventanaDocumento.add(_txtBuscar);
        ventanaDocumento.add(_btnBuscar);
        ventanaDocumento.setVisible(true);
    }
     
    public JScrollPane cargarTabla(String seleccion){
        int opcion = 4;
        opcion = opcion(seleccion);
        Ejecucion ejec = new Ejecucion();
        JTable tabla = ejec.mostrarTabla(opcion, false, "");
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
                            if(tabla.getColumnName(columna).equals("Reservar")){
                                actualizarScroll();
                            }
                        }
                    }
                }
            }
        });
        scroll = new JScrollPane(tabla);
        scroll.setBounds(new Rectangle(25, 100, 650, 300));
        return scroll;
    }
    
    public JScrollPane cargarTablaBusqueda(String seleccion, String titulo){
        if(titulo!=null){
            int opcion = 4;
            opcion = opcion(seleccion);
            Ejecucion ejec = new Ejecucion();
            JTable tabla = ejec.buscar(titulo, opcion);
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
                                if(tabla.getColumnName(columna).equals("Reservar")){
                                    actualizarScroll();
                                }
                            }
                        }
                    }
                }
             });
            scroll = new JScrollPane(tabla);
            scroll.setBounds(new Rectangle(25, 100, 650, 300));
        }else{
            showMessageDialog(null, "Ingrese texto para la busqueda.");
        }
        return scroll;
    }
    
    public int opcion(String seleccion){
        int opcion = 0;
        if(seleccion.equals("Libros")){
            opcion = 0;
        }else if(seleccion.equals("Revistas")){
            opcion = 1;
        }else if(seleccion.equals("Tesis")){
            opcion = 2;
        }else if(seleccion.equals("-")){
            opcion = 4;
        }
        return opcion;
    }
    
    public void actualizarScroll(){
        Ejecucion ejec = new Ejecucion();
        JTable tabla = ejec.actualizarTabla();
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
                            if(tabla.getColumnName(columna).equals("Reservar")){
                                actualizarScroll();
                            }
                        }
                    }
                }
            }
        });
        ventanaDocumento.remove(scroll);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(new Rectangle(25, 100, 650, 300));
        ventanaDocumento.add(scroll);
    }
    
    public JScrollPane misPrestamos(){
        ventanaDocumento.remove(scroll);
        Ejecucion ejec = new Ejecucion();
        JTable tabla = ejec.misPrestamos();
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
                            if(tabla.getColumnName(columna).equals("Devolver")){
                                misPrestamos();
                            }
                        }
                    }
                }
            }
        });
        scroll = new JScrollPane(tabla);
        scroll.setBounds(new Rectangle(25, 100, 650, 300));
        ventanaDocumento.add(scroll);
        return scroll;
    }
}
