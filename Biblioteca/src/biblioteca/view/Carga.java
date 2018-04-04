/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.view;

import biblioteca.Ejecucion;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Junior
 */
public class Carga {
    public void crearCarga(){
        JFrame ventanaCarga = new JFrame();
        ventanaCarga.setSize(500,540);
        ventanaCarga.setLayout(null);
        ventanaCarga.setResizable(false);
        ventanaCarga.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                Admin admin = new Admin();
                ventanaCarga.dispose();
                admin.activarVentana();
            }
        });
        
        JLabel _lblTitulo = new JLabel();
        _lblTitulo.setText("Carga Masiva");
        _lblTitulo.setFont(new Font("Segoe UI", 0, 25));
        _lblTitulo.setBounds(160, 20, 180, 40);
        JTextArea _txtCarga = new JTextArea();
        JScrollPane scroll = new JScrollPane(_txtCarga);
        scroll.setBounds(new Rectangle(25, 100, 445, 300));   
        JButton _btnCarga = new JButton("Cargar");
        JButton _btnRegresar = new JButton("Regresar");
        JButton _btnSalir = new JButton("Salir");
        
        _btnRegresar.setBounds(300, 450, 90, 30);
        _btnCarga.setBounds(400, 450, 80, 30);
        _txtCarga.setBounds(25, 100, 445, 300);
        _btnSalir.setBounds(360, 20, 70, 25);
        
        _btnCarga.addActionListener(
            new ActionListener(){
               Ejecucion ejec = new Ejecucion();
               @Override
               public void actionPerformed (ActionEvent e){
                   String[] documentos = ejec.separador(_txtCarga.getText());
                   ejec.separarAgregar(documentos);
                   _txtCarga.setText("");
               }
            }
        );
        
        _btnRegresar.addActionListener(
            new ActionListener(){
               Admin admin = new Admin();
               @Override
               public void actionPerformed (ActionEvent e){
                   ventanaCarga.dispose();
                   admin.crearInicioAdmin();
               }
            }
        );
        
        _btnSalir.addActionListener(
            new ActionListener(){
                Ejecucion ejec = new Ejecucion();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaCarga.dispose();
                    ejec.cerrarSesion();
                }
            }
        );
        
        ventanaCarga.add(scroll);
        ventanaCarga.add(_btnSalir);
        ventanaCarga.add(_btnCarga);
        ventanaCarga.add(_btnRegresar);
        ventanaCarga.add(_lblTitulo);
        ventanaCarga.setVisible(true);
    }
}
