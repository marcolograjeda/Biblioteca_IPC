/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.view;

import biblioteca.Ejecucion;
import biblioteca.obj.Usuario;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Junior
 */
public class Login {
    JPanel cards;
    public void crearLogin(){
        JFrame ventanaLogin = new JFrame();
        ventanaLogin.setSize(500,540);
        ventanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaLogin.setLayout(null);
        ventanaLogin.setResizable(false);
        
        JTextField _txtNick = new JTextField();
        JPasswordField _pswContraseña = new JPasswordField();
        JLabel _lblTitulo = new JLabel("INICIAR SESIÓN");
        JLabel _lblNick = new JLabel("Usuario:");
        JLabel _lblContraseña = new JLabel("Contraseña:");
        JButton _btnEntrar = new JButton("Entrar");
        JButton _btnRegistrarse = new JButton("Registrarse");
        
        _lblTitulo.setBounds(160, 90, 180, 40);
        _lblTitulo.setFont(new Font("Segoe UI", 0, 25));
        _lblNick.setFont(new Font("Segoe UI", 0, 15));
        _lblContraseña.setFont(new Font("Segoe UI", 0, 15));
        _lblNick.setBounds(162, 165, 90, 20);
        _txtNick.setBounds(162, 195, 175, 30);
        _lblContraseña.setBounds(162, 245, 90, 20);
        _pswContraseña.setBounds(162, 275, 175, 30);
        _btnEntrar.setBounds(140, 380, 105, 30);
        _btnRegistrarse.setBounds(260, 380, 105, 30);
        
        _btnEntrar.addActionListener(
             new ActionListener(){
                 @Override
                public void actionPerformed (ActionEvent e){
                    Ejecucion ejec = new Ejecucion();
                    Usuario _usuarioIniciado = ejec.login(_txtNick.getText(), _pswContraseña.getText());
                    if(_usuarioIniciado.isRol()){
                        ventanaLogin.dispose();
                        Admin admin = new Admin();
                        admin.crearInicioAdmin();
                    }else{
                        System.out.println("Soy usuario");
                        ventanaLogin.dispose();
                        Docs doc = new Docs();
                        doc.ventanaDocumento();
                    }
                    System.out.println(_pswContraseña.getText());
                    System.out.println(_txtNick.getText());
                }
             }
        );
        
        _btnRegistrarse.addActionListener(
             new ActionListener(){
                Registrar reg = new Registrar();
                @Override
                public void actionPerformed (ActionEvent e){
                    ventanaLogin.setVisible(false);
                    reg.crearRegistrar();
                }
             }
        );
        
        ventanaLogin.add(_txtNick);
        ventanaLogin.add(_pswContraseña);
        ventanaLogin.add(_lblTitulo);
        ventanaLogin.add(_lblNick);
        ventanaLogin.add(_lblContraseña);
        ventanaLogin.add(_btnEntrar);
        ventanaLogin.add(_btnRegistrarse);
        ventanaLogin.setVisible(true);
    }
}
