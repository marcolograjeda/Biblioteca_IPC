/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.obj.Usuario;
import biblioteca.view.Login;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Junior
 */
public class Ejecucion {
    static Usuario[] usuarios = new Usuario[11];
    static int numeroUsuario;
    
    public void ejecutarPrograma(){
        Usuario admin = new Usuario("", "", "admin", "admin", true);
        usuarios[0] = admin;
        Login log = new Login();
        log.crearLogin();
    }
    
    public void login(String nick, String contraseña){
        boolean _inicioExitoso = false;
        for(int contar = 0; contar<usuarios.length;contar++){
            if(usuarios[contar]!=null){
                if(usuarios[contar].getNickName().equals(nick)){
                    if(usuarios[contar].getPassword().equals(contraseña)){
                        _inicioExitoso = true;
                    }
                }else{
                    _inicioExitoso = false;
                }
            }
        }
        if(_inicioExitoso){
            showMessageDialog(null, "Iniciaste sesión correctamente");
        }else{
            showMessageDialog(null, "Usuario y/o contraseña no validos");
        }
    }
    
    public void registrarUsuario(String nombre, String apellido, String nick, String contraseña){
        boolean _nombreUsuario = true;
        numeroUsuario= numeroUsuario + 1;
        if(numeroUsuario<12){
            for(int contar = 0; contar<usuarios.length;contar++){
                if(usuarios[contar]!=null){
                    if(usuarios[contar].getNickName().equals(nick)){
                        _nombreUsuario = false;
                    }else{
                        _nombreUsuario = true;
                    }
                }
            }
            if(_nombreUsuario){
                Usuario usuario = new Usuario(nombre, apellido, nick, contraseña, false);
                usuarios[numeroUsuario]= usuario;
                System.out.println(numeroUsuario);
                
            }else{
                showMessageDialog(null, "Ya existe ese nombre de usuario");
                numeroUsuario -=1;
            }
        }else{
            showMessageDialog(null, "Ya se llego al limite de usuarios");
        }   
    }
}
