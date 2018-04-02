/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.view;

import biblioteca.Ejecucion;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Junior
 */
public class Admin {
    static JFrame ventanaAdmin = new JFrame();
    public void crearInicioAdmin(){
        ventanaAdmin.setSize(500,540);
        ventanaAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaAdmin.setLayout(null);
        ventanaAdmin.setResizable(false);
        
        JLabel _lblTitulo = new JLabel();
        //JButton _btnLibros = new JButton("Libros");
        //JButton _btnRevistas = new JButton("Revistas");
        //JButton _btnTesis = new JButton("Tesis");
        JButton _btnDocumentos = new JButton("Documentos");
        JButton _btnCarga = new JButton("Carga");
        JButton _btnUsuarios = new JButton("Usuarios");
        JButton _btnReportes = new JButton("Reportes");
        JButton _btnSalir = new JButton("Salir");
        
        _lblTitulo.setText("Registro");
        _lblTitulo.setFont(new Font("Segoe UI", 0, 25));
        _lblTitulo.setBounds(200, 45, 180, 40);
        _btnSalir.setBounds(360, 20, 70, 25);
        _btnDocumentos.setBounds(70, 120, 120, 120);
        //_btnRevistas.setBounds(330, 120, 90, 90);
        //_btnTesis.setBounds(70, 230, 90, 90);
        _btnCarga.setBounds(310, 120, 120, 120);
        _btnUsuarios.setBounds(70, 320, 120, 120);
        _btnReportes.setBounds(310, 320, 120, 120);
        
        ventanaAdmin.add(_lblTitulo);
        ventanaAdmin.add(_btnDocumentos);
        //ventanaAdmin.add(_btnRevistas);
        //ventanaAdmin.add(_btnTesis);
        ventanaAdmin.add(_btnSalir);
        ventanaAdmin.add(_btnCarga);
        ventanaAdmin.add(_btnUsuarios);
        ventanaAdmin.add(_btnReportes);
        ventanaAdmin.setVisible(true);
        
        _btnCarga.addActionListener(
             new ActionListener(){
                Carga carg = new Carga();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaAdmin.setVisible(false);
                    carg.crearCarga();
                }
             }
        );
        
        /*_btnLibros.addActionListener(
            new ActionListener(){
                Tabla tab = new Tabla();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaAdmin.setVisible(false);
                    tab.ventanaTabla(0);
                }
            }
        );
        
        _btnRevistas.addActionListener(
            new ActionListener(){
                Tabla tab = new Tabla();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaAdmin.setVisible(false);
                    tab.ventanaTabla(1);
                }
            }
        );
        
        _btnTesis.addActionListener(
            new ActionListener(){
                Tabla tab = new Tabla();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaAdmin.setVisible(false);
                    tab.ventanaTabla(2);
                }
            }
        );*/
        
        _btnSalir.addActionListener(
            new ActionListener(){
                Ejecucion ejec = new Ejecucion();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaAdmin.setVisible(false);
                    ejec.cerrarSesion();
                }
            }
        );
        
        _btnUsuarios.addActionListener(
             new ActionListener(){
                Tabla tab = new Tabla();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaAdmin.setVisible(false);
                    tab.ventanaTabla(3);
                }
             }
        );
        
        _btnReportes.addActionListener(
             new ActionListener(){
                Tabla tab = new Tabla();
                @Override
                public void actionPerformed (ActionEvent e){
                    tab.ventanaTabla(5);
                }
             }
        );
        
        _btnDocumentos.addActionListener(
             new ActionListener(){
                Docs doc = new Docs();
                @Override
                public void actionPerformed (ActionEvent e){
                    doc.ventanaDocumento();
                }
             }
        );
    }
    
    public void activarVentana(){
        ventanaAdmin.setVisible(true);
    }
}
