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
    private String _año;

    public String getAutor() {
        return _autor;
    }

    public String getGrado() {
        return _grado;
    }

    public String getAño() {
        return _año;
    }

    public void setAutor(String _autor) {
        this._autor = _autor;
    }

    public void setGrado(String _grado) {
        this._grado = _grado;
    }

    public void setAño(String _año) {
        this._año = _año;
    }

    public Tesis() {
        super();
    }

    public Tesis(String _autor, String _grado, String _año, String _id, String _titulo, String _tema) {
        super(_id, _titulo, _tema);
        this._autor = _autor;
        this._grado = _grado;
        this._año = _año;
    }
}
