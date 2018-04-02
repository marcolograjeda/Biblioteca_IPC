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
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Junior
 */
public class Registrar {
    public void crearRegistrar(){
        JFrame ventanaRegistrar = new JFrame();
        ventanaRegistrar.setSize(500,540);
        ventanaRegistrar.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaRegistrar.setLayout(null);
        ventanaRegistrar.setResizable(false);
        
        JTextField _txtNombre = new JTextField();
        JTextField _txtApellido = new JTextField();
        JTextField _txtNick = new JTextField();
        JPasswordField _pswContraseña = new JPasswordField();
        JLabel _lblTitulo = new JLabel("Registro");
        JLabel _lblNick = new JLabel("Usuario:");
        JLabel _lblNombre = new JLabel("Nombre:");
        JLabel _lblApellido = new JLabel("Apellido:");
        JLabel _lblContraseña = new JLabel("Contraseña:");
        JButton _btnRegistrarse = new JButton("Registrarse");
        JButton _btnLogin = new JButton("Login");
        
        _lblTitulo.setBounds(200, 45, 180, 40);
        _lblTitulo.setFont(new Font("Segoe UI", 0, 25));
        _lblNick.setFont(new Font("Segoe UI", 0, 15));
        _lblContraseña.setFont(new Font("Segoe UI", 0, 15));
        _lblNombre.setFont(new Font("Segoe UI", 0, 15));
        _lblApellido.setFont(new Font("Segoe UI", 0, 15));
        
        _lblNombre.setBounds(162, 100, 90, 20);
        _txtNombre.setBounds(162, 135, 175, 30);
        _lblApellido.setBounds(162, 165, 90, 20);
        _txtApellido.setBounds(162, 195, 175, 30);
        _lblNick.setBounds(162, 225, 90, 20);
        _txtNick.setBounds(162, 255, 175, 30);
        _lblContraseña.setBounds(162, 285, 90, 20);
        _pswContraseña.setBounds(162, 315, 175, 30);
        _btnRegistrarse.setBounds(140, 380, 105, 30);
        _btnLogin.setBounds(260, 380, 105, 30);
        
        _btnRegistrarse.addActionListener(
             new ActionListener(){
                 @Override
                    public void actionPerformed (ActionEvent e){
                        if(_txtNombre.getText().equals("")||_txtApellido.getText().equals("")||
                                _txtNick.getText().equals("")||_pswContraseña.getText().equals("")){
                            showMessageDialog(null, "Por favor ingrese todos los campos");
                        }else{
                            Ejecucion ejec = new Ejecucion();
                            int _problema = ejec.validarUsuario(_txtNick.getText(), 1);
                            switch(_problema){
                                case 0:
                                    ejec.registrarUsuario(_txtNombre.getText(), _txtApellido.getText(), 
                                    _txtNick.getText(), _pswContraseña.getText());
                                    showMessageDialog(null, "El usuario se añadio correctamente");
                                break;
                                case 1:
                                    showMessageDialog(null, "Ese nombre de usuario ya existe");
                                break;
                                case 2:
                                    showMessageDialog(null, "La cantidad de usuarios llego a su limite");
                            }
                            System.out.println(_txtNombre.getText());
                            System.out.println(_txtApellido.getText());
                            System.out.println(_txtNick.getText());
                            System.out.println(_pswContraseña.getText());
                        }
                    }
             }
        );
        
        _btnLogin.addActionListener(
             new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                    Login log = new Login();
                    ventanaRegistrar.setVisible(false);
                    log.crearLogin();
                }
             }
        );
        
        ventanaRegistrar.add(_txtNick);
        ventanaRegistrar.add(_txtNombre);
        ventanaRegistrar.add(_txtApellido);
        ventanaRegistrar.add(_pswContraseña);
        ventanaRegistrar.add(_lblNombre);
        ventanaRegistrar.add(_lblApellido);
        ventanaRegistrar.add(_lblTitulo);
        ventanaRegistrar.add(_lblNick);
        ventanaRegistrar.add(_lblContraseña);
        ventanaRegistrar.add(_btnRegistrarse);
        ventanaRegistrar.add(_btnLogin);
        ventanaRegistrar.setVisible(true);
    }
}
