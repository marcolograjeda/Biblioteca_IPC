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
public class Tesis extends Documento{
    private String _autor;
    private String _grado;
    private int _año;

    public String getAutor() {
        return _autor;
    }

    public String getGrado() {
        return _grado;
    }

    public int getAño() {
        return _año;
    }

    public void setAutor(String _autor) {
        this._autor = _autor;
    }

    public void setGrado(String _grado) {
        this._grado = _grado;
    }

    public void setAño(int _año) {
        this._año = _año;
    }

    public Tesis() {
        super();
    }

    public Tesis(String _id, String _titulo, String _autor, String _grado, String _tema, int _año, boolean _estado) {
        super(_id, _titulo, _tema, _estado);
        this._autor = _autor;
        this._grado = _grado;
        this._año = _año;
    }
}
