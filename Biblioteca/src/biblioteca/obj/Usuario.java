/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.obj;

/**
 *
 * @author Junior
 */
public class Usuario {
    private String _nombre;
    private String _apellido;
    private String _nickName;
    private String _password;
    private boolean _rol;

    public String getNombre() {
        return _nombre;
    }

    public String getApellido() {
        return _apellido;
    }

    public String getNickName() {
        return _nickName;
    }

    public String getPassword() {
        return _password;
    }

    public boolean isRol() {
        return _rol;
    }

    public void setNombre(String _nombre) {
        this._nombre = _nombre;
    }

    public void setApellido(String _apellido) {
        this._apellido = _apellido;
    }

    public void setNickName(String _nickName) {
        this._nickName = _nickName;
    }

    public void setPassword(String _password) {
        this._password = _password;
    }

    public void setRol(boolean _rol) {
        this._rol = _rol;
    }

    public Usuario() {
    }

    public Usuario(String _nombre, String _apellido, String _nickName, String _password, boolean _rol) {
        this._nombre = _nombre;
        this._apellido = _apellido;
        this._nickName = _nickName;
        this._password = _password;
        this._rol = _rol;
    }
}
