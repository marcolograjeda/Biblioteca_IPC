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
public class Documento {
    private String _id;
    private String _titulo;
    private String _tema;

    public String getId() {
        return _id;
    }

    public String getTitulo() {
        return _titulo;
    }

    public String getTema() {
        return _tema;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    public void setTitulo(String _titulo) {
        this._titulo = _titulo;
    }

    public void setTema(String _tema) {
        this._tema = _tema;
    }

    public Documento() {
    }

    public Documento(String _id, String _titulo, String _tema) {
        this._id = _id;
        this._titulo = _titulo;
        this._tema = _tema;
    }
}
