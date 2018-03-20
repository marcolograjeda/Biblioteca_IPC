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
public class Revista extends Documento{
    private String _compañia;
    private String _fecha;

    public String getCompañia() {
        return _compañia;
    }

    public String getFecha() {
        return _fecha;
    }

    public void setCompañia(String _compañia) {
        this._compañia = _compañia;
    }

    public void setFecha(String _fecha) {
        this._fecha = _fecha;
    }

    public Revista() {
        super();
    }

    public Revista(String _compañia, String _fecha, String _id, String _titulo, String _tema) {
        super(_id, _titulo, _tema);
        this._compañia = _compañia;
        this._fecha = _fecha;
    }
    
}
