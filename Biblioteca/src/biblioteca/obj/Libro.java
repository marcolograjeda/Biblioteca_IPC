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
public class Libro extends Documento{
    private String _autor;
    private int _paginas;

    public String getAutor() {
        return _autor;
    }

    public int getPaginas() {
        return _paginas;
    }

    public void setAutor(String _autor) {
        this._autor = _autor;
    }

    public void setPaginas(int _paginas) {
        this._paginas = _paginas;
    }

    public Libro() {
        super();
    }

    public Libro(String _id, String _titulo, String _autor, String _tema, int _paginas, boolean _estado) {
        super(_id, _titulo, _tema, _estado);
        this._autor = _autor;
        this._paginas = _paginas;
    }
    
    
}
