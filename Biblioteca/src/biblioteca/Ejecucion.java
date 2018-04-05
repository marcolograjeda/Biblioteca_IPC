/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.obj.Libro;
import biblioteca.obj.Revista;
import biblioteca.obj.Tesis;
import biblioteca.obj.Usuario;
import biblioteca.view.Admin;
import biblioteca.view.Docs;
import biblioteca.view.Editar;
import biblioteca.view.Login;
import biblioteca.view.Tabla;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Junior
 */
public class Ejecucion {
    static Usuario[] usuarios = new Usuario[11];
    static Libro[] libros = new Libro[50];
    static Revista[] revistas = new Revista[50];
    static Tesis[] tesis = new Tesis[50];
    static String[] usuarioPrestamo;
    static String[][] documentoPrestadoUsuario = new String[0][0];
    static String[][] conteoPrestamo;
    static String[][] conteoTotal = new String[0][0];
    static String[][] conteoRevistas = new String[0][0];
    static String[][] conteoLibros = new String[0][0];
    static String[][] conteoAutores = new String[0][0];
    int _numeroProblema = 0;
    static int _numeroUsuario;
    static int _cantidadLibros;
    static int _idLibros;
    static int _cantidadRevistas;
    static int _idRevistas;
    static int _cantidadTesis;
    static int _idTesis;
    static int _opcionBusqueda;
    static Usuario usuarioLogin;
    static String txtBuscar = "";
    static boolean busco = false;
    static int opcionBuscada = 0;
    String[][] problemasUsuario = new String[50][2];
    public void ejecutarPrograma(){
        Usuario admin = new Usuario("", "", "admin", "admin", true);
        /*Usuario usuario1 = new Usuario("Prueba", "Prueba", "a", "a", false);
        Usuario usuario2 = new Usuario("Prueba1", "Prueba1", "b", "b", false);
        Usuario usuario3 = new Usuario("Prueba2", "Prueba2", "c", "c", false);
        Usuario usuario4 = new Usuario("Prueba3", "Prueba3", "d", "d", false);
        Usuario usuario5 = new Usuario("Prueba4", "Prueba4", "e", "e", false);
        Usuario usuario6 = new Usuario("Prueba5", "Prueba5", "f", "f", false);
        
        usuarios[1]=usuario1;
        usuarios[2]=usuario2;
        usuarios[3]=usuario3;
        usuarios[4]=usuario4;
        usuarios[5]=usuario5;
        usuarios[6]=usuario6;*/
        usuarios[0] = admin;
        _numeroUsuario = 1;
        _opcionBusqueda = 0;
        //Login log = new Login();
        //log.crearLogin();
        //Admin admon = new Admin();
        //admon.crearInicioAdmin();
        Docs doc = new Docs();
        doc.ventanaDocumento();
    }
    
    public Usuario login(String nick, String contraseña){
        Usuario _usuarioIniciado = new Usuario();
        _usuarioIniciado = obtenerUsuario(nick, contraseña);
        if(_usuarioIniciado.getNombre()!=null){
            showMessageDialog(null, "Iniciaste sesión correctamente");
            usuarioLogin = _usuarioIniciado;
        }else{
            showMessageDialog(null, "Usuario y/o contraseña no validos");
        }
        return _usuarioIniciado;
    }
    
    public int validarUsuario(String nick, int opcion){
        int _problema = 0;
        boolean _nombreUsuario = true;
        if(_numeroUsuario<12||opcion==2){
            for(int contar = 0; contar<usuarios.length;contar++){
                if(usuarios[contar]!=null){
                    if(usuarios[contar].getNickName().equals(nick)){
                        _problema = 1;
                    }
                }
            }
        }else{
            _problema = 2;
        }
        return _problema;
    }
    
    public void registrarUsuario(String nombre, String apellido, String nick, String contraseña){
        Usuario usuario = new Usuario(nombre, apellido, nick, contraseña, false);
        usuarios[_numeroUsuario]= usuario;
        _numeroUsuario= _numeroUsuario + 1;
    }
    
    public Usuario obtenerUsuario(String nick, String contraseña){
        Usuario _usuarioObtenido = new Usuario();
        for(int contar = 0; contar<usuarios.length;contar++){
            if(usuarios[contar]!=null){
                if(usuarios[contar].getNickName().equals(nick)){
                    if(usuarios[contar].getPassword().equals(contraseña)){
                        _usuarioObtenido = usuarios[contar];
                    }
                }
            }
        }
        return _usuarioObtenido;
    }
    
    public String[] separador(String cadena){
        StringTokenizer token = new StringTokenizer(cadena, "\n");
        int contadorCoordenadas = token.countTokens();
        String[] cadenas = new String[contadorCoordenadas];
        for(int contar = 0;contar<contadorCoordenadas;contar++){
            cadenas[contar] = token.nextToken();
        }
        return cadenas;
    }
    
    public int[] separarAgregar(String[] documento){
        int[] problema = new int[10];
        problemasUsuario = new String[50][2];
        for(int cadena=0;cadena<documento.length;cadena++){
            StringTokenizer token = new StringTokenizer(documento[cadena], "|");
            int contador = token.countTokens();
            int tipo = 5;
            String opcion = token.nextToken();
            if(!opcion.matches("[0-9]*")){
                System.out.println("Ingrese un numero entero positivo "+ opcion);
                problema[9]=1;
            }else{
                tipo = Integer.parseInt(opcion);
            }
            switch(tipo){
                case 0:
                    if(contador!=4){
                        if(_cantidadLibros<50){
                            _cantidadLibros +=1;
                            _idLibros +=1;
                            String titulo =token.nextToken();
                            String autor = token.nextToken();
                            Libro _libro = new Libro("LIB-"+_idLibros, titulo, autor,
                                    token.nextToken(), Integer.parseInt(token.nextToken()), false);
                            libros[_cantidadLibros-1]= _libro;
                            agregarConteoAutor(autor);
                        }else{
                            problema[0] = 1;
                            _numeroProblema ++;
                        }
                    }else{
                        problema[5] = 1; 
                        _numeroProblema ++;
                    }
                break;
                case 1:
                    if(contador!=4){
                        if(_cantidadRevistas<50){
                            _cantidadRevistas +=1;
                            _idRevistas +=1;
                            String titulo =token.nextToken();
                            String autor = token.nextToken();
                            Revista _revista = new Revista("REV-"+_idRevistas, 
                                    titulo, autor,token.nextToken(), token.nextToken(), false);
                            revistas[_cantidadRevistas-1]=_revista;
                            agregarConteoAutor(autor);
                        }else{
                            problema[1] = 1;
                            _numeroProblema ++;
                        }
                    }else{
                        problema[6] = 1;
                        _numeroProblema ++;
                    }
                break;
                case 2:
                    if(contador!=5){
                        if(_cantidadTesis<50){
                            _cantidadTesis += 1;
                            _idTesis += 1;
                            String titulo =token.nextToken();
                            String autor = token.nextToken();
                            Tesis _tesis = new Tesis("TES-"+_idTesis, titulo, autor,
                                    token.nextToken(), token.nextToken(), Integer.parseInt(token.nextToken()), false);
                            tesis[_cantidadTesis-1]=_tesis;
                            agregarConteoAutor(autor);
                        }else{
                            problema[2] = 1;
                            _numeroProblema ++;
                        }
                    }else{
                        problema[7] = 1;
                        _numeroProblema ++;
                    }
                break;
                case 3:
                    if(contador!=4){
                        String nombre = token.nextToken();
                        String apellido = token.nextToken();
                        String nick = token.nextToken();
                        String contraseña = token.nextToken();
                        problema[3] = validarUsuario(nick, 1);
                        
                        if (problema[3]==1){
                            problemasUsuario[_numeroProblema][0] = "Ya existe este nombre de usuario";
                            problemasUsuario[_numeroProblema][1] = nick;
                            _numeroProblema ++;
                        }else if(_numeroUsuario==11){
                            problemasUsuario[_numeroProblema][0] = "La cantidad de usuarios llego a su limite,"
                                    + " el ultimo usuario es ";
                            problemasUsuario[_numeroProblema][1] = usuarios[10].getNickName();
                            _numeroProblema ++;
                        }else if(problema[3]==0){
                            registrarUsuario(nombre, apellido, nick, contraseña);
                        }
                    }else{
                        problema[8] = 1;
                        _numeroProblema ++;
                    }
                break;
                default:
                    problema[4] = 1;
            }
        }
        listarProblema(problema);
        return problema;
    }
    
    public void listarProblema(int[] problema){
        String _losProblemas = "";
        if(problema[0]==1){
            _losProblemas = "La cantidad de libros ha llegado a su limite. El ultimo libro agregado es "+ libros[49].getTitulo() +".\n";
        }
        if(problema[1]==1){
            _losProblemas = _losProblemas + "La cantidad de revistas ha llegado a su limite. La ultima revista agregada es "+ revistas[49].getTitulo() +".\n";
        }
        if(problema[2]==1){
            _losProblemas = _losProblemas + "La cantidad de tesis ha llegado a su limite. La ultima tesis agregada es "+ tesis[49].getTitulo() +".\n";
        }
        if(problema[3]==2){
            _losProblemas = _losProblemas + "La cantidad de usuarios ha llegado a su limite. El utlimo usuario agregado es "+ usuarios[10].getNombre() +".\n";
        }
        if(problema[4]==1){
            _losProblemas = _losProblemas + "Hay uno o más elementos que no poseen categoria.\n";
        }
        if(problema[5]==1){
            _losProblemas = _losProblemas + "Faltan uno o más atributos del libro.\n";
        }
        if(problema[6]==1){
            _losProblemas = _losProblemas + "Faltan uno o más atributos de la revista.\n";
        }
        if(problema[7]==1){
            _losProblemas = _losProblemas + "Faltan uno o más atributos de la tesis.\n";
        }
        if(problema[8]==1){
            _losProblemas = _losProblemas + "Faltan uno o más atributos del usuario.\n";
        }
        if(problema[9]==1){
            _losProblemas = _losProblemas + "Ingresa un número entero positivo.\n";
        }
        if(problemasUsuario!=null){
            for(int i=0;i<problemasUsuario.length;i++){
                if(problemasUsuario[i][0]!=null){
                    _losProblemas = _losProblemas + problemasUsuario[i][0] + " "+ problemasUsuario[i][1] +".\n";
                }
            }
        }
        if(_numeroProblema==0){
            _losProblemas = "Se realizó la carga sin ningun problema";
        }
        showMessageDialog(null, _losProblemas);
        problemasUsuario= null;
        _numeroProblema = 0;
    }   
    
    public JTable mostrarTabla(int opcion, boolean _busqueda, String titulo){
        opcionBuscada = opcion;
        txtBuscar = titulo;
        int us = tipoUsuarioLogeado(usuarioLogin);
        JTable tabla = new JTable();
        DefaultTableModel tabModel= new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        int cantidadColumnas = 8;
        if(!_busqueda){
            txtBuscar ="";
        }else if(txtBuscar.equals("")){
            _busqueda = false;
        }
        switch(opcion){
            case 0:
                Object[] columnas = new Object[0];
                Object[][] datos = new Object[0][0];
                if(us==0){
                    Object[] columnasNombre = {"ID", "Titulo", "Autor", "Tema", "# de Paginas", "Estado", "Editar", "Eliminar"};
                    columnas = columnasNombre;
                    Object[][] datosL = new Object[_cantidadLibros][8];
                    datos = datosL;
                }else if(us==1){
                    Object[] columnasNombre = {"ID", "Titulo", "Autor", "Tema", "# de Paginas", "Estado", "Ver", "Reservar"};
                    columnas = columnasNombre;
                    Object[][] datosL = new Object[_cantidadLibros][8];
                    datos = datosL;
                }else if(us==2){
                    Object[] columnasNombre = {"ID", "Titulo", "Autor", "Tema", "# de Paginas", "Estado", "Ver"};
                    columnas = columnasNombre;
                    Object[][] datosL = new Object[_cantidadLibros][7];
                    datos = datosL;
                    cantidadColumnas = 7;
                }
                int libBuscado = 0;
                for(int i = 0; i<_cantidadLibros;i++){
                    Pattern pat = Pattern.compile("");
                    Matcher mat = pat.matcher(titulo);
                    if(titulo!=""){
                        txtBuscar = titulo;
                        busco = true;
                        System.out.println("La cadena queda "+".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                        +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1));
                        pat = Pattern.compile(".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                        +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1)+".*");
                        mat = pat.matcher(libros[i].getTitulo());
                    }
                    final String nombreBtn = libros[i].getId();
                    if(_busqueda==false){
                        Object[][] objeto = filaLibros(i, us);
                        for(int pos = 0; pos<cantidadColumnas;pos++){
                            datos[i][pos] = objeto[0][pos];
                        }
                        
                        if(us==0){
                            JButton _btnEliminar = (JButton)datos[i][7];
                            _btnEliminar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarLibro(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow());
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datos[i][7] = _btnEliminar;
                        }
                    }else if(mat.matches()){
                        busco = true;
                        Object[][] objeto = filaLibros(i, us);
                        for(int pos = 0; pos<cantidadColumnas;pos++){
                            datos[libBuscado][pos] = objeto[0][pos];
                        }
                        if(us==0){
                            JButton _btnEliminar = (JButton)datos[libBuscado][7];
                            _btnEliminar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarLibro(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow());
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datos[libBuscado][7] = _btnEliminar;
                        }
                        libBuscado++;
                    }
                }
                tabModel = new DefaultTableModel(datos, columnas){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
            case 1:
                Object[] titulosRevistas = new Object[0];
                Object[][] datosRevistas = new Object[0][0];
                if(us==0){
                    Object[] columnasNombre = {"ID", "Titulo", "Compañia", "Fecha", "Tema", "Estado", "Editar", "Eliminar"};
                    titulosRevistas = columnasNombre;
                    Object[][] datosR = new Object[_cantidadRevistas][8];
                    datosRevistas = datosR;
                }else if(us==1){
                    Object[] columnasNombre = {"ID", "Titulo", "Compañia", "Fecha", "Tema", "Estado", "Ver", "Reservar"};
                    titulosRevistas = columnasNombre;
                    Object[][] datosR = new Object[_cantidadRevistas][8];
                    datosRevistas = datosR;
                }else if(us==2){
                    Object[] columnasNombre = {"ID", "Titulo", "Compañia", "Fecha", "Tema", "Estado", "Ver"};
                    titulosRevistas = columnasNombre;
                    Object[][] datosR = new Object[_cantidadRevistas][7];
                    datosRevistas = datosR;
                    cantidadColumnas = 7;
                }
                int revBuscada = 0;
                for(int i = 0; i<_cantidadRevistas;i++){
                    Pattern pat = Pattern.compile("");
                    Matcher mat = pat.matcher(titulo);
                    if(titulo!=""){
                        txtBuscar = titulo;
                        busco = true;
                        System.out.println("La cadena queda "+".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                        +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1));
                        
                        pat = Pattern.compile(".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                        +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1)+".*");
                        mat = pat.matcher(revistas[i].getTitulo());
                    }
                    final String nombreBtn = revistas[i].getId();
                    if(_busqueda==false){
                        Object[][] objeto = filaRevista(i, us);
                        for(int pos = 0; pos<cantidadColumnas;pos++){
                            datosRevistas[i][pos] = objeto[0][pos];
                        }
                        if(us==0){
                            JButton _btnEliminar = (JButton)datosRevistas[i][7];
                            _btnEliminar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarRevista(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datosRevistas[i][7] = _btnEliminar;
                        }
                    }else if(mat.matches()){
                        Object[][] objeto = filaRevista(i, us);
                        for(int pos = 0; pos<cantidadColumnas;pos++){
                            datosRevistas[revBuscada][pos] = objeto[0][pos];
                        } 
                        if(us==0){
                            JButton _btnEliminar = (JButton)datosRevistas[revBuscada][7];
                            _btnEliminar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarRevista(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datosRevistas[revBuscada][7] = _btnEliminar;
                        }
                        revBuscada++;
                    }
                }
                tabModel = new DefaultTableModel(datosRevistas, titulosRevistas){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
            case 2:
                Object[] titulosTesis = new Object[0];
                Object[][] datosTesis = new Object[0][0];
                if(us==0){
                    Object[] columnasNombre = {"ID", "Titulo", "Autor", "Grado", "Tema", "Año", "Estado", "Editar", "Eliminar"};
                    titulosTesis = columnasNombre;
                    Object[][] datosT = new Object[_cantidadTesis][9];
                    datosTesis = datosT;
                    cantidadColumnas = 9;
                }else if(us==1){
                    Object[] columnasNombre = {"ID", "Titulo", "Autor", "Grado", "Tema", "Año", "Estado", "Ver", "Reservar"};
                    titulosTesis = columnasNombre;
                    cantidadColumnas = 9;
                    Object[][] datosT = new Object[_cantidadTesis][9];
                    datosTesis = datosT;
                    cantidadColumnas = 9;
                }else if(us==2){
                    Object[] columnasNombre = {"ID", "Titulo", "Autor", "Grado", "Tema", "Año", "Estado", "Ver"};
                    titulosTesis = columnasNombre;
                    Object[][] datosT = new Object[_cantidadTesis][8];
                    datosTesis = datosT;
                }
                int tesBuscada = 0;
                for(int i = 0; i<_cantidadTesis;i++){
                    Pattern pat = Pattern.compile("");
                    Matcher mat = pat.matcher(titulo);
                    if(titulo!=""){
                        txtBuscar = titulo;
                        busco = true;
                        System.out.println("La cadena queda "+".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                        +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1));
                        pat = Pattern.compile(".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                        +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1)+".*");
                        mat = pat.matcher(tesis[i].getTitulo());
                    }
                    final String nombreBtn = tesis[i].getId();
                    if(_busqueda == false){
                        Object[][] objeto = filaTesis(i, us);
                        for(int pos = 0; pos<cantidadColumnas;pos++){
                            datosTesis[i][pos] = objeto[0][pos];
                        }
                        if(us==0){
                            JButton _btnEliminar = (JButton)datosTesis[i][8];
                            _btnEliminar.addActionListener(
                                new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                        if(eliminarTesis(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datosTesis[i][8] = _btnEliminar;
                        }
                    }else if(mat.matches()){
                        Object[][] objeto = filaTesis(i, us);
                        for(int pos = 0; pos<cantidadColumnas;pos++){
                            datosTesis[tesBuscada][pos] = objeto[0][pos];
                        }
                        if(us==0){
                            JButton _btnEliminar = (JButton)datosTesis[tesBuscada][8];
                            _btnEliminar.addActionListener(
                                new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                        if(eliminarTesis(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datosTesis[tesBuscada][8] = _btnEliminar;
                        }
                        tesBuscada++;
                    }
                }
                tabModel = new DefaultTableModel(datosTesis, titulosTesis){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
            case 3:
                Object[] tituloUsuarios = {"Nombre", "Apellido", "Usuario", "Contraseña", "Editar", "Eliminar"};
                Object[][] datosUsuarios = new Object[_numeroUsuario][6];
                for(int i = 0; i<_numeroUsuario;i++){
                    if(usuarios[i]!=null){
                        datosUsuarios[i][0]= usuarios[i].getNombre();
                        datosUsuarios[i][1]= usuarios[i].getApellido();
                        datosUsuarios[i][2]= usuarios[i].getNickName();
                        datosUsuarios[i][3]= usuarios[i].getPassword();
                        JButton _btnEditar = new JButton("Editar");
                        JButton _btnEliminar = new JButton("Eliminar");
                        final String nombreBtn = usuarios[i].getNickName();
                        _btnEditar.addActionListener(
                        new ActionListener(){
                            @Override
                            public void actionPerformed (ActionEvent e){
                                    System.out.println("Ed " + nombreBtn);
                                    Object obj = new Object();
                                    int posicion = 0;
                                    for(int i = 0;i<_numeroUsuario;i++){
                                        if(usuarios[i].getNickName().equals(nombreBtn)){
                                            obj = usuarios[i];
                                            posicion = i;
                                        }
                                    }
                                    Tabla tab = new Tabla();
                                    Editar ed = new Editar();
                                    ed.crearEditar(obj, 3, posicion);
                                }
                            }
                        );
                        if(i==0){
                            _btnEditar.setName(nombreBtn);
                            _btnEliminar.disable();
                            _btnEliminar.setName("Deshabilitado");
                        }else{
                            _btnEditar.setName(nombreBtn);
                             _btnEliminar.setName(nombreBtn);
                        }
                        _btnEliminar.addActionListener(
                        new ActionListener(){
                            @Override
                            public void actionPerformed (ActionEvent e){
                                    if(eliminarUsuario(nombreBtn)){
                                        System.out.println("El " + nombreBtn);
                                        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                        dtm.removeRow(tabla.getSelectedRow()); 
                                    }
                                }
                            }
                        );
                        datosUsuarios[i][4] = _btnEditar;
                        datosUsuarios[i][5] = _btnEliminar;
                        tabModel = new DefaultTableModel(datosUsuarios, tituloUsuarios){
                            public boolean isCellEditable(int rowIndex, int colIndex){
                                return false;
                            }
                        };
                    }
                    
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
                }
            break;
            case 4:
                Object[] documentos = new Object[0];
                Object[][] datosDoc = new Object[0][0];
                if(us==0){
                    Object[] documentosVarios = {"ID", "Titulo", "Tema", "Estado", "Editar", "Eliminar"};
                    documentos = documentosVarios;
                    datosDoc = new Object[_cantidadLibros+_cantidadRevistas+_cantidadTesis][6];
                }else if(us==1){
                    Object[] documentosVarios = {"ID", "Titulo", "Tema", "Estado", "Ver", "Reservar"};
                    documentos = documentosVarios;
                    datosDoc = new Object[_cantidadLibros+_cantidadRevistas+_cantidadTesis][6];
                }else if(us==2){
                    Object[] documentosVarios = {"ID", "Titulo", "Tema", "Estado", "Ver"};
                    documentos = documentosVarios;
                    datosDoc = new Object[_cantidadLibros+_cantidadRevistas+_cantidadTesis][5];
                }
                int doc = 0;
                Pattern pat = Pattern.compile("");
                Matcher mat = pat.matcher(titulo);
                if(titulo!=""){
                    txtBuscar = titulo;
                    System.out.println("La cadena queda "+".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                    +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1));
                    pat = Pattern.compile(".*"+titulo+".*|.*"+titulo.toUpperCase()+".*|.*"+titulo.toLowerCase()
                    +".*|.*"+titulo.substring(0, 1).toUpperCase()+titulo.substring(1)+".*");
                    //mat = pat.matcher(tesis[i].getTitulo());
                }
                if(_busqueda == false){
                    for(int lib = 0; lib<_cantidadLibros;lib++){
                        datosDoc[doc][0] = libros[lib].getId();
                        datosDoc[doc][1] = libros[lib].getTitulo();
                        datosDoc[doc][2] = libros[lib].getTema();
                        if(libros[lib].isEstado()){
                            datosDoc[doc][3] = "Prestado";
                        }else{
                            datosDoc[doc][3] = "Disponible";
                        }
                        final String nombreBtn = libros[lib].getId();
                        if(us==0){
                            JButton _btnEditar = new JButton("Editar");
                            _btnEditar.setName(libros[lib].getId());
                            JButton _btnEliminar = new JButton("Eliminar");
                            _btnEliminar.setName(libros[lib].getId());
                            _btnEditar.addActionListener(
                            new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                    System.out.println("Ed " + nombreBtn);
                                        Object obj = new Object();
                                        int posicion = 0;
                                        for(int i = 0;i<_cantidadLibros;i++){
                                            if(libros[i].getId().equals(nombreBtn)){
                                                obj = libros[i];
                                                posicion = i;
                                            }
                                        }
                                        Editar ed = new Editar();
                                        ed.crearEditar(obj, 0, posicion);
                                    }
                                }
                            );
                            _btnEliminar.addActionListener(
                            new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarLibro(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnEditar;
                            datosDoc[doc][5] = _btnEliminar;
                        }else if(us==1){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(libros[lib].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        String ver =verDocumento(nombreBtn, 0);
                                        showMessageDialog(null, ver);
                                    }
                                }
                            );
                            JButton _btnReservar = new JButton("Reservar");
                            _btnReservar.setName(libros[lib].getId());
                            _btnReservar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Reservar " + nombreBtn);
                                        agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 0);
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                            datosDoc[doc][5] = _btnReservar;
                        }else if(us==2){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(libros[lib].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        showMessageDialog(null, verDocumento(nombreBtn, 0));
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                        }
                        doc++;
                    }
                    for(int rev = 0; rev<_cantidadRevistas;rev++){
                        datosDoc[doc][0]= revistas[rev].getId();
                        datosDoc[doc][1]= revistas[rev].getTitulo();
                        datosDoc[doc][2]= revistas[rev].getTema();
                        if(revistas[rev].isEstado()){
                            datosDoc[doc][3] = "Prestado";
                        }else{
                            datosDoc[doc][3] = "Disponible";
                        }
                        final String nombreBtn = revistas[rev].getId();
                        if(us==0){
                            JButton _btnEditar = new JButton("Editar");
                            _btnEditar.setName(revistas[rev].getId());
                            JButton _btnEliminar = new JButton("Eliminar");
                            _btnEliminar.setName(revistas[rev].getId());
                            _btnEliminar.addActionListener(
                            new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarRevista(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            _btnEditar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                    System.out.println("Ed " + nombreBtn);
                                        Object obj = new Object();
                                        int posicion = 0;
                                        for(int i = 0;i<_cantidadRevistas;i++){
                                            if(revistas[i].getId().equals(nombreBtn)){
                                                obj = revistas[i];
                                                posicion = i;
                                            }
                                        }
                                        Editar ed = new Editar();
                                        ed.crearEditar(obj, 1, posicion);
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnEditar;
                            datosDoc[doc][5] = _btnEliminar;
                        }else if(us == 1){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(revistas[rev].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        showMessageDialog(null, verDocumento(nombreBtn, 1));
                                    }
                                }
                            );
                            JButton _btnReservar = new JButton("Reservar");
                            _btnReservar.setName(revistas[rev].getId());
                            _btnReservar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Reservar " + nombreBtn);
                                        agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 1);
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                            datosDoc[doc][5] = _btnReservar;
                        }else if(us ==2){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(revistas[rev].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        showMessageDialog(null, verDocumento(nombreBtn, 1));
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                        }
                        doc++;
                    }
                    for(int tes = 0; tes<_cantidadTesis;tes++){
                        datosDoc[doc][0]= tesis[tes].getId();
                        datosDoc[doc][1]= tesis[tes].getTitulo();
                        datosDoc[doc][2]= tesis[tes].getTema();
                        if(tesis[tes].isEstado()){
                            datosDoc[doc][3] = "Prestado";
                        }else{
                            datosDoc[doc][3] = "Disponible";
                        }
                        final String nombreBtn = tesis[tes].getId();
                        if(us==0){
                            JButton _btnEditar = new JButton("Editar");
                            _btnEditar.setName(tesis[tes].getId());
                            JButton _btnEliminar = new JButton("Eliminar");
                            _btnEliminar.setName(tesis[tes].getId());
                            _btnEliminar.addActionListener(
                            new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarTesis(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            _btnEditar.addActionListener(
                                new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                        System.out.println("Ed " + nombreBtn);
                                        Object obj = new Object();
                                        int posicion = 0;
                                        for(int i = 0;i<_cantidadTesis;i++){
                                            if(tesis[i].getId().equals(nombreBtn)){
                                                obj = tesis[i];
                                                posicion = i;
                                            }
                                        }
                                        Tabla tab = new Tabla();
                                        Editar ed = new Editar();
                                        ed.crearEditar(obj, 2, posicion);
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnEditar;
                            datosDoc[doc][5] = _btnEliminar;
                        }else if(us==1){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(tesis[tes].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        showMessageDialog(null, verDocumento(nombreBtn, 2));
                                    }
                                }
                            );
                            JButton _btnReservar = new JButton("Reservar");
                            _btnReservar.setName(tesis[tes].getId());
                            _btnReservar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Reservar " + nombreBtn);
                                        agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 2);
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                            datosDoc[doc][5] = _btnReservar;
                        }else if(us ==2){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(tesis[tes].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        showMessageDialog(null, verDocumento(nombreBtn, 2));
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                        }
                        doc++;
                    }
                }else{
                    busco = true;
                    for(int lib = 0; lib<_cantidadLibros;lib++){
                        mat = pat.matcher(libros[lib].getTitulo());
                        if(mat.matches()){
                            datosDoc[doc][0] = libros[lib].getId();
                            datosDoc[doc][1] = libros[lib].getTitulo();
                            datosDoc[doc][2] = libros[lib].getTema();
                            if(libros[lib].isEstado()){
                                datosDoc[doc][3] = "Prestado";
                            }else{
                                datosDoc[doc][3] = "Disponible";
                            }
                            final String nombreBtn = libros[lib].getId();
                        if(us==0){
                            JButton _btnEditar = new JButton("Editar");
                            _btnEditar.setName(libros[lib].getId());
                            JButton _btnEliminar = new JButton("Eliminar");
                            _btnEliminar.setName(libros[lib].getId());
                            _btnEditar.addActionListener(
                            new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                    System.out.println("Ed " + nombreBtn);
                                        Object obj = new Object();
                                        int posicion = 0;
                                        for(int i = 0;i<_cantidadLibros;i++){
                                            if(libros[i].getId().equals(nombreBtn)){
                                                obj = libros[i];
                                                posicion = i;
                                            }
                                        }
                                        Tabla tab = new Tabla();
                                        Editar ed = new Editar();
                                        ed.crearEditar(obj, 0, posicion);
                                    }
                                }
                            );
                            _btnEliminar.addActionListener(
                            new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        if(eliminarLibro(nombreBtn)){
                                            System.out.println("El " + nombreBtn);
                                            DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
                                            dtm.removeRow(tabla.getSelectedRow()); 
                                        }else{
                                            showMessageDialog(null, "El documento esta en prestamo"); 
                                        }
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnEditar;
                            datosDoc[doc][5] = _btnEliminar;
                        }else if(us==1){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(libros[lib].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        showMessageDialog(null, verDocumento(nombreBtn, 0));
                                    }
                                }
                            );
                            JButton _btnReservar = new JButton("Reservar");
                            _btnReservar.setName(libros[lib].getId());
                            _btnReservar.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Reservar " + nombreBtn);
                                        agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 0);
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                            datosDoc[doc][5] = _btnReservar;
                        }else if(us==2){
                            JButton _btnVer = new JButton("Ver");
                            _btnVer.setName(libros[lib].getId());
                            _btnVer.addActionListener(
                                new ActionListener(){
                                @Override
                                public void actionPerformed (ActionEvent e){
                                        System.out.println("Ver " + nombreBtn);
                                        showMessageDialog(null, verDocumento(nombreBtn, 0));
                                    }
                                }
                            );
                            datosDoc[doc][4] = _btnVer;
                        }
                            doc++;
                        }
                    }
                    for(int rev = 0; rev<_cantidadRevistas;rev++){
                        mat = pat.matcher(revistas[rev].getTitulo());
                        if(mat.matches()){
                            datosDoc[doc][0]= revistas[rev].getId();
                            datosDoc[doc][1]= revistas[rev].getTitulo();
                            datosDoc[doc][2]= revistas[rev].getTema();
                            if(revistas[rev].isEstado()){
                                datosDoc[doc][3] = "Prestado";
                            }else{
                                datosDoc[doc][3] = "Disponible";
                            }
                            final String nombreBtn = revistas[rev].getId();
                            if(us==0){
                                JButton _btnEditar = new JButton("Editar");
                                _btnEditar.setName(revistas[rev].getId());
                                JButton _btnEliminar = new JButton("Eliminar");
                                _btnEliminar.setName(revistas[rev].getId());
                                _btnEditar.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                        System.out.println("Ed " + nombreBtn);
                                            Object obj = new Object();
                                            int posicion = 0;
                                            for(int i = 0;i<_cantidadRevistas;i++){
                                                if(revistas[i].getId().equals(nombreBtn)){
                                                    obj = revistas[i];
                                                    posicion = i;
                                                }
                                            }
                                            Editar ed = new Editar();
                                            ed.crearEditar(obj, 1, posicion);
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnEditar;
                                datosDoc[doc][5] = _btnEliminar;
                            }else if(us == 1){
                                JButton _btnVer = new JButton("Ver");
                                _btnVer.setName(revistas[rev].getId());
                                _btnVer.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Ver " + nombreBtn);
                                            showMessageDialog(null, verDocumento(nombreBtn, 1));
                                        }
                                    }
                                );
                                JButton _btnReservar = new JButton("Reservar");
                                _btnReservar.setName(revistas[rev].getId());
                                _btnReservar.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Reservar " + nombreBtn);
                                            agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 1);
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnVer;
                                datosDoc[doc][5] = _btnReservar;
                            }else if(us ==2){
                                JButton _btnVer = new JButton("Ver");
                                _btnVer.setName(revistas[rev].getId());
                                _btnVer.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Ver " + nombreBtn);
                                            showMessageDialog(null, verDocumento(nombreBtn, 1));
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnVer;
                            }
                            doc++;
                        }
                    }
                    for(int tes = 0; tes<_cantidadTesis;tes++){
                        mat = pat.matcher(tesis[tes].getTitulo());
                        if(mat.matches()){
                            datosDoc[doc][0]= tesis[tes].getId();
                            datosDoc[doc][1]= tesis[tes].getTitulo();
                            datosDoc[doc][2]= tesis[tes].getTema();
                            if(tesis[tes].isEstado()){
                                datosDoc[doc][3] = "Prestado";
                            }else{
                                datosDoc[doc][3] = "Disponible";
                            }
                            final String nombreBtn = tesis[tes].getId();
                            if(us==0){
                                JButton _btnEditar = new JButton("Editar");
                                _btnEditar.setName(tesis[tes].getId());
                                JButton _btnEliminar = new JButton("Eliminar");
                                _btnEliminar.setName(tesis[tes].getId());
                                _btnEditar.addActionListener(
                                    new ActionListener(){
                                        @Override
                                        public void actionPerformed (ActionEvent e){
                                            System.out.println("Ed " + nombreBtn);
                                            Object obj = new Object();
                                            int posicion = 0;
                                            for(int i = 0;i<_cantidadTesis;i++){
                                                if(tesis[i].getId().equals(nombreBtn)){
                                                    obj = tesis[i];
                                                    posicion = i;
                                                }
                                            }
                                            Tabla tab = new Tabla();
                                            Editar ed = new Editar();
                                            ed.crearEditar(obj, 2, posicion);
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnEditar;
                                datosDoc[doc][5] = _btnEliminar;
                            }else if(us==1){
                                JButton _btnVer = new JButton("Ver");
                                _btnVer.setName(tesis[tes].getId());
                                _btnVer.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Ver " + nombreBtn);
                                            showMessageDialog(null, verDocumento(nombreBtn, 2));
                                        }
                                    }
                                );
                                JButton _btnReservar = new JButton("Reservar");
                                _btnReservar.setName(tesis[tes].getId());
                                _btnReservar.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Reservar " + nombreBtn);
                                            agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 3);
                                        }
                                    }
                                );
                                datosDoc[0][7] = _btnVer;
                                datosDoc[0][8] = _btnReservar;
                            }else if(us ==2){
                                JButton _btnVer = new JButton("Ver");
                                _btnVer.setName(tesis[tes].getId());
                                _btnVer.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Ver " + nombreBtn);
                                            showMessageDialog(null, verDocumento(nombreBtn, 2));
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnVer;
                            }
                            doc++;
                        }
                    }
                }
                tabModel = new DefaultTableModel(datosDoc, documentos){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false;
                        }
                    };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
        }
        tabla.setBounds(10, 10, 400, 400);
        tabla.setRowHeight(30);
        return tabla;  
    }
    
    public boolean eliminarLibro(String _idLibro){
        boolean eliminado = false;
        for(int i = 0; i<_cantidadLibros;i++){
            if(libros[i].getId().equals(_idLibro)&&libros[i].isEstado()==false){
                String autor = libros[i].getAutor();
                libros[i] = null;
                eliminado = true;
                Libro[] librosNuevos = new Libro[50];
                int b = 0;
                for(int a = 0; a<_cantidadLibros;a++){
                    if(libros[a]!=null){
                        librosNuevos[b] = libros[a];
                        b++;
                    }
                }
                libros = librosNuevos;
                _cantidadLibros -=1;
                i = _cantidadLibros;
                eliminarLibroDelContador(_idLibro);
                eliminarAutor(autor);
            }
        }
        return eliminado;
    }
    
    public void eliminarLibroDelContador(String _idLibro){
        for(int i = 0; i<conteoLibros.length;i++){
            if(conteoLibros[i][0].equals(_idLibro)){
                conteoLibros[i] = null;
                String[][] conteoTemporal = new String[conteoLibros.length-1][3];
                int b=0;
                for(int a = 0; a<conteoLibros.length;a++){
                    if(conteoLibros[a]!=null){
                        conteoTemporal[b][0] = conteoLibros[a][0];
                        conteoTemporal[b][1] = conteoLibros[a][1];
                        conteoTemporal[b][2] = conteoLibros[a][2];
                        b++;
                    }
                }
                conteoLibros = conteoTemporal;
                i=conteoLibros.length;
            }
        }
    }
    
    public boolean eliminarRevista(String _idRevista){
        boolean eliminado = false;
        for(int i = 0; i<_cantidadRevistas;i++){
            if(revistas[i].getId().equals(_idRevista)&&revistas[i].isEstado()==false){
                String autor = revistas[i].getCompañia();
                revistas[i] = null;
                eliminado = true;
                Revista[] revistasNuevas = new Revista[50];
                int b = 0;
                for(int a = 0; a<_cantidadRevistas;a++){
                    if(revistas[a]!=null){
                        revistasNuevas[b] = revistas[a];
                        b++;
                    }
                }
                revistas = revistasNuevas;
                _cantidadRevistas -=1;
                i = _cantidadRevistas;
                eliminarRevistaDelContador(_idRevista);
                eliminarAutor(autor);
            }
        }
        return eliminado;
    }
    
    public void eliminarRevistaDelContador(String _idRevista){
        for(int i = 0; i<conteoRevistas.length;i++){
            if(conteoRevistas[i][0].equals(_idRevista)){
                conteoRevistas[i] = null;
                String[][] conteoTemporal = new String[conteoRevistas.length-1][5];
                int b=0;
                for(int a = 0; a<conteoRevistas.length;a++){
                    if(conteoRevistas[a]!=null){
                        conteoTemporal[b][0] = conteoRevistas[a][0];
                        conteoTemporal[b][1] = conteoRevistas[a][1];
                        b++;
                    }
                }
                conteoRevistas = conteoTemporal;
                i=conteoRevistas.length;
            }
        }
    }
    
    public void eliminarAutor(String nombreAutor){
        for(int i = 0; i<conteoAutores.length;i++){
            if(conteoAutores[i][0].equals(nombreAutor)){
                if(Integer.parseInt(conteoAutores[i][1])>1){
                    conteoAutores[i][1] = Integer.toString(Integer.parseInt(conteoAutores[i][1])-1);
                }else{
                    conteoAutores[i] = null;
                    String[][] conteoTemporal = new String[conteoAutores.length-1][5];
                    int b=0;
                    for(int a = 0; a<conteoAutores.length;a++){
                        if(conteoAutores[a]!=null){
                            conteoTemporal[b][0] = conteoAutores[a][0];
                            conteoTemporal[b][1] = conteoAutores[a][1];
                            b++;
                        }
                    }
                    conteoAutores = conteoTemporal;
                }
                i=conteoAutores.length;
            }
        }
    }
    
    public boolean eliminarTesis(String _idRevista){
        boolean eliminado = false;
        for(int i = 0; i<_cantidadTesis;i++){
            if(tesis[i].getId().equals(_idRevista)&&tesis[i].isEstado()==false){
                String autor = tesis[i].getAutor();
                tesis[i] = null;
                eliminado = true;
                Tesis[] tesisNuevas = new Tesis[50];
                int b = 0;
                for(int a = 0; a<_cantidadTesis;a++){
                    if(tesis[a]!=null){
                        tesisNuevas[b] = tesis[a];
                        b++;
                    }
                }
                tesis = tesisNuevas;
                _cantidadTesis -=1;
                i = _cantidadTesis;
                eliminarAutor(autor);
            }
        }
        return eliminado;
    }
    
    public boolean eliminarUsuario(String _nick){
        boolean eliminado = false;
        int posicion = 0;
        boolean coincidencia = false;
        for(int a=0;a<_numeroUsuario;a++){
            if(usuarios[a].getNickName().equals(_nick)){
                posicion = a;
            }
        }
        int posicionCoincidencia = 0;
        for(int a = 0;a<conteoTotal.length;a++){
            if(conteoTotal[a][0].equals(_nick)){
                posicionCoincidencia = a;
                coincidencia = true;
            }
        }
        if(coincidencia){
            int prestamos = Integer.parseInt(conteoTotal[posicionCoincidencia][1])+Integer.parseInt(conteoTotal[posicionCoincidencia][2])
                +Integer.parseInt(conteoTotal[posicionCoincidencia][3]);
            if(prestamos==0){
                eliminarAlUsuario(posicion);
                eliminado = true;
            }else{
               showMessageDialog(null, "El usuario no puede ser eliminado, posee uno o varios documentos"); 
            }
        }else{
            eliminarAlUsuario(posicion);
            eliminado = true;
        }
        return eliminado;
    }
    
    public void eliminarAlUsuario(int posicion){
        usuarios[posicion]=null;
        Usuario[] usuarioNuevo = new Usuario[11];
        int b = 0;
        for(int a = 0; a<_numeroUsuario;a++){
            if(usuarios[a]!=null){
                usuarioNuevo[b] = usuarios[a];
                b++;
            }
        }
        usuarios = usuarioNuevo;
        _numeroUsuario -=1;
    }
    
    public void eliminarAlUsuarioDelContador(String _nick){
        for(int i = 0; i<conteoTotal.length;i++){
            if(conteoTotal[i][0].equals(_nick)){
                conteoTotal[i] = null;
                String[][] conteoTemporal = new String[conteoTotal.length-1][5];
                int b=0;
                for(int a = 0; a<conteoTotal.length;a++){
                    if(conteoTotal[a]!=null){
                        conteoTemporal[b][0] = conteoTotal[a][0];
                        conteoTemporal[b][1] = conteoTotal[a][1];
                        conteoTemporal[b][2] = conteoTotal[a][2];
                        conteoTemporal[b][3] = conteoTotal[a][3];
                        conteoTemporal[b][4] = conteoTotal[a][4];
                        b++;
                    }
                }
                conteoTotal = conteoTemporal;
                i=conteoTotal.length;
            }
        }
    }
    
    public void activarVentana(){
        Admin admon = new Admin();
        admon.activarVentana();
    }
    
    public void agregarEditar(Object objeto, int opcion, int posicion){
        switch(opcion){
            case 0:
                libros[posicion] = (Libro)objeto;
            break;
            case 1:
                revistas[posicion] = (Revista)objeto;
            break;
            case 2:
                tesis[posicion] = (Tesis)objeto;
            break;
            case 3:
                usuarios[posicion] = (Usuario)objeto;
            break;
        }
    }
    
    public JTable buscar(String titulo, int opcion){
        JTable tabla = mostrarTabla(opcion, true, titulo);
        return tabla;
    }
    
    public Object[][] filaLibros(int i, int usuario){
        Object[][] datos = new Object[0][0];
        if(usuario==2){
            datos = new Object[1][7];
        }else{
            datos = new Object[1][8];
        }
        datos[0][0] = libros[i].getId();
        datos[0][1] = libros[i].getTitulo();
        datos[0][2] = libros[i].getAutor();
        datos[0][3] = libros[i].getTema();
        datos[0][4] = Integer.toString(libros[i].getPaginas());
        if(libros[i].isEstado()){
            datos[0][5] = "Prestado";
        }else{
            datos[0][5] = "Disponible";
        }
        final String nombreBtn = libros[i].getId();
        if(usuario==0){
            JButton _btnEditar = new JButton("Editar");
            JButton _btnEliminar = new JButton("Eliminar");
            _btnEditar.setName(libros[i].getId());
            _btnEliminar.setName(libros[i].getId());
            _btnEditar.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed (ActionEvent e){
                        System.out.println("Ed " + nombreBtn);
                            Object obj = new Object();
                            int posicion = 0;
                            for(int i = 0;i<_cantidadLibros;i++){
                                if(libros[i].getId().equals(nombreBtn)){
                                    obj = libros[i];
                                    posicion = i;
                                }
                            }
                            Tabla tab = new Tabla();
                            Editar ed = new Editar();
                            ed.crearEditar(obj, 0, posicion);
                        }
                    }
                );
            datos[0][6] = _btnEditar;
            datos[0][7] = _btnEliminar;
        }else if(usuario == 1){
            JButton _btnVer = new JButton("Ver");
            _btnVer.setName(libros[i].getId());
            
            _btnVer.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Ver " + nombreBtn);
                        showMessageDialog(null, verDocumento(nombreBtn, 0));
                    }
                }
            );
            datos[0][6] = _btnVer;
            JButton _btnReservar = new JButton("Reservar");
            _btnReservar.setName(libros[i].getId());
            _btnReservar.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Reservar " + nombreBtn);
                        agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 0);
                    }
                }
            );
            datos[0][6] = _btnVer;
            datos[0][7] = _btnReservar;
        }else if(usuario ==2){
            JButton _btnVer = new JButton("Ver");
            _btnVer.setName(libros[i].getId());
            _btnVer.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Ver " + nombreBtn);
                        showMessageDialog(null, verDocumento(nombreBtn, 0));
                    }
                }
            );
            datos[0][6] = _btnVer;
        }
        return datos;
    }
    
    public Object[][] filaRevista(int i, int usuario){//Aqui ejemplo
        Object[][] datosRevistas = new Object[0][0];
        if(usuario == 2){
            datosRevistas = new Object[1][7];
        }else{
            datosRevistas = new Object[1][8];
        }
        datosRevistas[0][0]= revistas[i].getId();
        datosRevistas[0][1]= revistas[i].getTitulo();
        datosRevistas[0][2]= revistas[i].getCompañia();
        datosRevistas[0][3]= revistas[i].getFecha();
        datosRevistas[0][4]= revistas[i].getTema();
        if(revistas[i].isEstado()){
            datosRevistas[0][5] = "Prestado";
        }else{
            datosRevistas[0][5] = "Disponible";
        }
        final String nombreBtn = revistas[i].getId();
        if(usuario==0){
            JButton _btnEditar = new JButton("Editar");
            _btnEditar.setName(revistas[i].getId());
            JButton _btnEliminar = new JButton("Eliminar");
            _btnEliminar.setName(revistas[i].getId());
            _btnEditar.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                    System.out.println("Ed " + nombreBtn);
                        Object obj = new Object();
                        int posicion = 0;
                        for(int i = 0;i<_cantidadRevistas;i++){
                            if(revistas[i].getId().equals(nombreBtn)){
                                obj = revistas[i];
                                posicion = i;
                            }
                        }
                        Editar ed = new Editar();
                        ed.crearEditar(obj, 1, posicion);
                    }
                }
            );
            datosRevistas[0][6] = _btnEditar;
            datosRevistas[0][7] = _btnEliminar;
        }else if(usuario == 1){
            JButton _btnVer = new JButton("Ver");
            _btnVer.setName(revistas[i].getId());
            _btnVer.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Ver " + nombreBtn);
                        showMessageDialog(null, verDocumento(nombreBtn, 1));
                    }
                }
            );
            JButton _btnReservar = new JButton("Reservar");
            _btnReservar.setName(revistas[i].getId());
            _btnReservar.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Reservar " + nombreBtn);
                        agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn, 1);
                    }
                }
            );
            datosRevistas[0][6] = _btnVer;
            datosRevistas[0][7] = _btnReservar;
        }else if(usuario ==2){
            JButton _btnVer = new JButton("Ver");
            _btnVer.setName(revistas[i].getId());
            _btnVer.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Ver " + nombreBtn);
                        showMessageDialog(null, verDocumento(nombreBtn, 1));
                    }
                }
            );
            datosRevistas[0][6] = _btnVer;
        }
        return datosRevistas;
    }
    
    public Object[][] filaTesis(int i, int usuario){//Aqui estas programando
        if(usuario==2){
            Object[][] datosTesis = new Object[1][8];
        }else{
            Object[][] datosTesis = new Object[1][9];
        }
        Object[][] datosTesis = new Object[1][9];
        datosTesis[0][0]= tesis[i].getId();
        datosTesis[0][1]= tesis[i].getTitulo();
        datosTesis[0][2]= tesis[i].getAutor();
        datosTesis[0][3]= tesis[i].getGrado();
        datosTesis[0][4]= tesis[i].getTema();
        datosTesis[0][5]= tesis[i].getAño();
        if(tesis[i].isEstado()){
            datosTesis[0][6] = "Prestado";
        }else{
            datosTesis[0][6] = "Disponible";
        }
        final String nombreBtn = tesis[i].getId();
        if(usuario==0){
            JButton _btnEditar = new JButton("Editar");
            _btnEditar.setName(tesis[i].getId());
            JButton _btnEliminar = new JButton("Eliminar");
            _btnEliminar.setName(tesis[i].getId());
            _btnEditar.addActionListener(
                new ActionListener(){
                    @Override
                    public void actionPerformed (ActionEvent e){
                        System.out.println("Ed " + nombreBtn);
                        Object obj = new Object();
                        int posicion = 0;
                        for(int i = 0;i<_cantidadTesis;i++){
                            if(tesis[i].getId().equals(nombreBtn)){
                                obj = tesis[i];
                                posicion = i;
                            }
                        }
                        Tabla tab = new Tabla();
                        Editar ed = new Editar();
                        ed.crearEditar(obj, 2, posicion);
                    }
                }
            );
            datosTesis[0][7] = _btnEditar;
            datosTesis[0][8] = _btnEliminar;
        }else if(usuario==1){
            JButton _btnVer = new JButton("Ver");
            _btnVer.setName(tesis[i].getId());
            _btnVer.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Ver " + nombreBtn);
                        showMessageDialog(null, verDocumento(nombreBtn, 2));
                    }
                }
            );
            JButton _btnReservar = new JButton("Reservar");
            _btnReservar.setName(tesis[i].getId());
            _btnReservar.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Reservar " + nombreBtn);
                        agregarPrestamoUsuario(usuarioLogin.getNickName(), nombreBtn,2);
                        for(int a = 0;a<documentoPrestadoUsuario.length;a++){
                            for(int c =0;c<documentoPrestadoUsuario[0].length;c++){
                                System.out.println(documentoPrestadoUsuario[a][c]);
                            }
                        }
                    }
                }
            );
            datosTesis[0][7] = _btnVer;
            datosTesis[0][8] = _btnReservar;
        }else if(usuario ==2){
            JButton _btnVer = new JButton("Ver");
            _btnVer.setName(tesis[i].getId());
            _btnVer.addActionListener(
                new ActionListener(){
                @Override
                public void actionPerformed (ActionEvent e){
                        System.out.println("Ver " + nombreBtn);
                        showMessageDialog(null, verDocumento(nombreBtn, 2));
                    }
                }
            );
            datosTesis[0][7] = _btnVer;
        }
        return datosTesis;
    }
    
    public int tipoUsuarioLogeado(Usuario usuario){
        int _tipoUsuario = 0;
        if(usuario==null){
            _tipoUsuario = 2;
        }else if(!usuario.isRol()){
            _tipoUsuario = 1;
        }
        return _tipoUsuario;
    }
    
    //agrega un usuario al vector de usuario que han prestado un documento
    public void agregarUsuarioAPrestamo(String _nuevoNick){
        int tamaño = usuarioPrestamo.length +1;
        String[] usuarioPresta = new String[tamaño];
        if(tamaño != 1){
            for(int a= 0;a<tamaño; a++){
                usuarioPresta[a] = usuarioPrestamo[a];
            }
            usuarioPresta[tamaño] = _nuevoNick;
            usuarioPrestamo = usuarioPresta;
        }else{
            usuarioPresta[tamaño] = _nuevoNick;
            usuarioPrestamo = usuarioPresta;
        }
    }
    
    public void agregarPrestamoUsuario(String _nick, String _idDoc, int opcion){
        if(!verificarEstado(_idDoc, opcion)){
            int tamaño = documentoPrestadoUsuario.length+1;
            String[][] usuarioDoc = new String[tamaño][2];
            if(tamaño != 1){
                for(int a=0;a<tamaño-1; a++){
                    usuarioDoc[a][0] = documentoPrestadoUsuario[a][0];
                    usuarioDoc[a][1] = documentoPrestadoUsuario[a][1];
                }
                usuarioDoc[tamaño-1][0] = _nick;
                usuarioDoc[tamaño-1][1] = _idDoc;
                documentoPrestadoUsuario = usuarioDoc;
            }else{
                usuarioDoc[0][0] = _nick;
                usuarioDoc[0][1] = _idDoc;
                documentoPrestadoUsuario = usuarioDoc;
            }
            agregarConteoPrestamoUsuario(_nick, opcion, _idDoc);
        }else{
            showMessageDialog(null, "El libro se encuentra en prestamo");
        }
    }
    
    public void devolverPrestamoUsuario(String _nick, String _idDoc, int opcion){
        int tamaño = documentoPrestadoUsuario.length;
        String[][] usuarioDoc = new String[tamaño-1][2];
        int posicion = 0;
        for(int a=0;a<tamaño; a++){
            boolean coincidencia = false;
            if(documentoPrestadoUsuario[a][0].equals(_nick)&&documentoPrestadoUsuario[a][1].equals(_idDoc)){
                coincidencia = true;
            }
            if(!coincidencia){
                usuarioDoc[posicion][0] = documentoPrestadoUsuario[a][0];
                usuarioDoc[posicion][1] = documentoPrestadoUsuario[a][1];
                posicion++;
            }
        }
        switch(opcion){
            case 0:
                for(int a = 0; a<_cantidadLibros; a++){
                    if(libros[a].getId().equals(_idDoc)){
                        if(libros[a].isEstado()){
                            libros[a].setEstado(false);
                        }
                    }
                }
            break;
            case 1:
                for(int a = 0; a<_cantidadRevistas; a++){
                    if(revistas[a].getId().equals(_idDoc)){
                        if(revistas[a].isEstado()){
                            revistas[a].setEstado(false);
                        }
                    }
                }
            break;
            case 2:
                for(int a = 0; a<_cantidadTesis; a++){
                    if(tesis[a].getId().equals(_idDoc)){
                        if(tesis[a].isEstado()){
                            tesis[a].setEstado(false);
                        }
                    }
                }
            break;
        }
        documentoPrestadoUsuario = usuarioDoc;
        restarConteoPrestamoUsuario(_nick, opcion);
    }
    
    public boolean verificarEstado(String _idDoc, int opcion){
        boolean prestado = false;
        switch(opcion){
            case 0:
                for(int a = 0; a<_cantidadLibros; a++){
                    if(libros[a].getId().equals(_idDoc)){
                        if(libros[a].isEstado()){
                            prestado = true;
                        }else{
                            libros[a].setEstado(true);
                        }
                    }
                }
            break;
            case 1:
                for(int a = 0; a<_cantidadRevistas; a++){
                    if(revistas[a].getId().equals(_idDoc)){
                        if(revistas[a].isEstado()){
                            prestado = true;
                        }else{
                            revistas[a].setEstado(true);
                        }
                    }
                }
            break;
            case 2:
                for(int a = 0; a<_cantidadTesis; a++){
                    if(tesis[a].getId().equals(_idDoc)){
                        if(tesis[a].isEstado()){
                            prestado = true;
                        }else{
                            tesis[a].setEstado(true);
                        }
                    }
                }
            break;
        }
        return prestado;
    }
    
    public void agregarConteoPrestamoUsuario(String _nick, int opcion, String _idDoc){
        int posicion = 0;
        boolean coincidencia = false;
        for(int a = 0;a<conteoTotal.length;a++){
            if(conteoTotal[a][0].equals(_nick)){
                posicion = a;
                coincidencia = true;
            }
        }
        if(coincidencia){
            conteoTotal[posicion][4] = Integer.toString(Integer.parseInt(conteoTotal[posicion][4])+1);
            switch(opcion){
                case 0:
                    conteoTotal[posicion][1] = Integer.toString(Integer.parseInt(conteoTotal[posicion][1])+1);
                    agregarConteoLibro(_idDoc, 0);
                break;
                case 1:
                    conteoTotal[posicion][2] = Integer.toString(Integer.parseInt(conteoTotal[posicion][2])+1);
                    agregarConteoRevista(_idDoc);
                break;
                case 2:
                    conteoTotal[posicion][3] = Integer.toString(Integer.parseInt(conteoTotal[posicion][3])+1);
                break;
            }
        }else{
            int tamaño = conteoTotal.length+1;
            String[][] usuarioDoc = new String[tamaño][5];
            for(int a = 0;a<tamaño-1;a++){
                usuarioDoc[a][0] = conteoTotal[a][0];
                usuarioDoc[a][1] = conteoTotal[a][1];
                usuarioDoc[a][2] = conteoTotal[a][2];
                usuarioDoc[a][3] = conteoTotal[a][3];
                usuarioDoc[a][4] = conteoTotal[a][4];
            }
            usuarioDoc[tamaño-1][0] = _nick;
            usuarioDoc[tamaño-1][1] = "0";
            usuarioDoc[tamaño-1][2] = "0";
            usuarioDoc[tamaño-1][3] = "0";
            usuarioDoc[tamaño-1][4] = "1";
            conteoTotal = usuarioDoc;
            switch(opcion){
                case 0:
                    conteoTotal[tamaño-1][1] = Integer.toString(Integer.parseInt(conteoTotal[posicion][1])+1);
                    agregarConteoLibro(_idDoc, 0);
                break;
                case 1:
                    conteoTotal[tamaño-1][2] = Integer.toString(Integer.parseInt(conteoTotal[posicion][2])+1);
                    agregarConteoRevista(_idDoc);
                break;
                case 2:
                    conteoTotal[tamaño-1][3] = Integer.toString(Integer.parseInt(conteoTotal[posicion][3])+1);
                break;
            }
        }
    }
    
    public void agregarConteoRevista(String _idDoc){
        int posicion = 0;
        boolean coincidencia = false;
        for(int a = 0;a<conteoRevistas.length;a++){
            if(conteoRevistas[a][0].equals(_idDoc)){
                posicion = a;
                coincidencia = true;
            }
        }
        if(coincidencia){
            conteoRevistas[posicion][1] = Integer.toString(Integer.parseInt(conteoRevistas[posicion][1])+1);
        }else{
            int tamaño = conteoRevistas.length+1;
            String[][] usuarioDoc = new String[tamaño][2];
            for(int a = 0;a<tamaño-1;a++){
                usuarioDoc[a][0] = conteoRevistas[a][0];
                usuarioDoc[a][1] = conteoRevistas[a][1];
            }
            usuarioDoc[tamaño-1][0] = _idDoc;
            usuarioDoc[tamaño-1][1] = "1";
            conteoRevistas = usuarioDoc;
        }
    }
    
    public void agregarConteoAutor(String nombreAutor){
        int posicion = 0;
        boolean coincidencia = false;
        for(int a = 0;a<conteoAutores.length;a++){
            if(conteoAutores[a][0].equals(nombreAutor)){
                posicion = a;
                coincidencia = true;
            }
        }
        if(coincidencia){
            conteoAutores[posicion][1] = Integer.toString(Integer.parseInt(conteoAutores[posicion][1])+1);
        }else{
            int tamaño = conteoAutores.length+1;
            String[][] autor = new String[tamaño][2];
            for(int a = 0;a<tamaño-1;a++){
                autor[a][0] = conteoAutores[a][0];
                autor[a][1] = conteoAutores[a][1];
            }
            autor[tamaño-1][0] = nombreAutor;
            autor[tamaño-1][1] = "1";
            conteoAutores = autor;
        }
    }
    
    public void agregarConteoLibro(String _idDoc, int opcion){
        int posicion = 0;
        boolean coincidencia = false;
        for(int a = 0;a<conteoLibros.length;a++){
            if(conteoLibros[a][0].equals(_idDoc)){
                posicion = a;
                coincidencia = true;
            }
        }
        if(coincidencia){
            conteoLibros[posicion][1] = Integer.toString(Integer.parseInt(conteoLibros[posicion][1])+1);
            conteoLibros[posicion][2] = Integer.toString(Integer.parseInt(conteoLibros[posicion][2])+1);
        }else{
            int tamaño = conteoLibros.length+1;
            String[][] usuarioDoc = new String[tamaño][3];
            for(int a = 0;a<tamaño-1;a++){
                usuarioDoc[a][0] = conteoLibros[a][0];
                usuarioDoc[a][1] = conteoLibros[a][1];
                usuarioDoc[a][2] = conteoLibros[a][2];
            }
            usuarioDoc[tamaño-1][0] = _idDoc;
            usuarioDoc[tamaño-1][1] = "0";
            usuarioDoc[tamaño-1][2] = "0";
            conteoLibros = usuarioDoc;
            switch(opcion){
                case 0:
                    conteoLibros[tamaño-1][1] = Integer.toString(Integer.parseInt(conteoLibros[tamaño-1][1])+1);
                    conteoLibros[tamaño-1][2] = Integer.toString(Integer.parseInt(conteoLibros[tamaño-1][2])+1);
                break;
                case 1:
                    conteoLibros[tamaño-1][2] = Integer.toString(Integer.parseInt(conteoLibros[tamaño-1][2])+1);
                break;
            }
        }
    }
    
    public void restarConteoPrestamoUsuario(String _nick, int opcion){
        int posicion = 0;
        for(int a = 0;a<conteoTotal.length;a++){
            if(conteoTotal[a][0].equals(_nick)){
                posicion = a;
            }
        }
        switch(opcion){
            case 0:
                conteoTotal[posicion][1] = Integer.toString(Integer.parseInt(conteoTotal[posicion][1])-1);
            break;
            case 1:
                conteoTotal[posicion][2] = Integer.toString(Integer.parseInt(conteoTotal[posicion][2])-1);
            break;
            case 2:
                conteoTotal[posicion][3] = Integer.toString(Integer.parseInt(conteoTotal[posicion][3])-1);
            break;
        }
    }
    
    public void cerrarSesion(){
        usuarioLogin = null;
        Login log = new Login();
        log.crearLogin();
    }
    
    public JTable actualizarTabla(){
        JTable tabla = mostrarTabla(opcionBuscada, busco, txtBuscar);
        return tabla;
    }
    
    public JTable misPrestamos(){
        JTable tabla = new JTable();
        DefaultTableModel tabModel= new DefaultTableModel(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        Object[] documentos = {"ID", "Titulo", "Tema", "Estado", "Ver", "Devolver"};
        int cantidadPrestamos = 0;
        int posicionUsuario = 0;
        for(int buscarUsuario=0;buscarUsuario<conteoTotal.length;buscarUsuario++){
            if(conteoTotal[buscarUsuario][0].equals(usuarioLogin.getNickName())){
                posicionUsuario = buscarUsuario;
                cantidadPrestamos = Integer.parseInt(conteoTotal[buscarUsuario][1])+Integer.parseInt(conteoTotal[buscarUsuario][2])
                        +Integer.parseInt(conteoTotal[buscarUsuario][3]);
            }
        }
        Object[][] datosDoc = new Object[cantidadPrestamos][6];
        if(cantidadPrestamos!=0){
            int doc = 0;
            for(int a = 0;a<documentoPrestadoUsuario.length;a++){
                if(documentoPrestadoUsuario[a][0].equals(usuarioLogin.getNickName())){
                    Pattern pat = Pattern.compile(".*LIB.*");
                    Matcher mat = pat.matcher(documentoPrestadoUsuario[a][1]);
                    if(mat.matches()){
                        //for(int posicion = 0;posicion<Integer.parseInt(conteoTotal[posicionUsuario][1]);posicion++){
                        for(int posicion = 0; posicion<_cantidadLibros;posicion++){
                            if(libros[posicion].getId().equals(documentoPrestadoUsuario[a][1])){
                                datosDoc[doc][0] = libros[posicion].getId();
                                datosDoc[doc][1] = libros[posicion].getTitulo();
                                datosDoc[doc][2] = libros[posicion].getTema();
                                if(libros[posicion].isEstado()){
                                    datosDoc[doc][3] = "Prestado";
                                }else{
                                    datosDoc[doc][3] = "Disponible";
                                }
                                final String nombreBtn = libros[posicion].getId();
                                JButton _btnVer = new JButton("Ver");
                                _btnVer.setName(libros[posicion].getId());
                                _btnVer.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Ver " + nombreBtn);
                                            showMessageDialog(null, verDocumento(nombreBtn, 0));
                                        }
                                    }
                                );
                                JButton _btnDevolver = new JButton("Devolver");
                                _btnDevolver.setName(libros[posicion].getId());
                                _btnDevolver.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Reservar " + nombreBtn);
                                            devolverPrestamoUsuario(usuarioLogin.getNickName(),nombreBtn,0);
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnVer;
                                datosDoc[doc][5] = _btnDevolver;
                                doc++;
                            }
                        }
                    }
                    pat = Pattern.compile(".*REV.*");
                    mat = pat.matcher(documentoPrestadoUsuario[a][1]);
                    if(mat.matches()){
                        //for(int posicion = 0;posicion<Integer.parseInt(conteoTotal[posicionUsuario][2]);posicion++){
                        for(int posicion = 0;posicion<_cantidadRevistas;posicion++){
                            if(revistas[posicion].getId().equals(documentoPrestadoUsuario[a][1])){
                                datosDoc[doc][0] = revistas[posicion].getId();
                                datosDoc[doc][1] = revistas[posicion].getTitulo();
                                datosDoc[doc][2] = revistas[posicion].getTema();
                                if(revistas[posicion].isEstado()){
                                    datosDoc[doc][3] = "Prestado";
                                }else{
                                    datosDoc[doc][3] = "Disponible";
                                }
                                final String nombreBtn = revistas[posicion].getId();
                                JButton _btnVer = new JButton("Ver");
                                _btnVer.setName(revistas[posicion].getId());
                                _btnVer.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Ver " + nombreBtn);
                                            showMessageDialog(null, verDocumento(nombreBtn, 1));
                                        }
                                    }
                                );
                                JButton _btnDevolver = new JButton("Devolver");
                                _btnDevolver.setName(revistas[posicion].getId());
                                _btnDevolver.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Devolver " + nombreBtn);
                                            devolverPrestamoUsuario(usuarioLogin.getNickName(),nombreBtn,1);
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnVer;
                                datosDoc[doc][5] = _btnDevolver;
                                doc++;
                            }
                        }
                    }
                    pat = Pattern.compile(".*TES.*");
                    mat = pat.matcher(documentoPrestadoUsuario[a][1]);
                    if(mat.matches()){
                        //for(int posicion = 0;posicion<Integer.parseInt(conteoTotal[posicionUsuario][3]);posicion++){
                        for(int posicion = 0;posicion<_cantidadTesis;posicion++){  
                            if(tesis[posicion].getId().equals(documentoPrestadoUsuario[a][1])){
                                datosDoc[doc][0] = tesis[posicion].getId();
                                datosDoc[doc][1] = tesis[posicion].getTitulo();
                                datosDoc[doc][2] = tesis[posicion].getTema();
                                if(tesis[posicion].isEstado()){
                                    datosDoc[doc][3] = "Prestado";
                                }else{
                                    datosDoc[doc][3] = "Disponible";
                                }
                                final String nombreBtn = tesis[posicion].getId();
                                JButton _btnVer = new JButton("Ver");
                                _btnVer.setName(tesis[posicion].getId());
                                _btnVer.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Ver " + nombreBtn);
                                            showMessageDialog(null, verDocumento(nombreBtn, 2));
                                        }
                                    }
                                );
                                JButton _btnDevolver = new JButton("Devolver");
                                _btnDevolver.setName(tesis[posicion].getId());
                                _btnDevolver.addActionListener(
                                    new ActionListener(){
                                    @Override
                                    public void actionPerformed (ActionEvent e){
                                            System.out.println("Devolver " + nombreBtn);
                                            devolverPrestamoUsuario(usuarioLogin.getNickName(),nombreBtn,2);
                                        }
                                    }
                                );
                                datosDoc[doc][4] = _btnVer;
                                datosDoc[doc][5] = _btnDevolver;
                                doc++;
                            }
                        }
                    }
                }
            }
            tabla.setRowHeight(30);
            tabModel = new DefaultTableModel(datosDoc, documentos){
                public boolean isCellEditable(int rowIndex, int colIndex){
                    return false;
                }
            };
        } 
        tabla.setModel(tabModel);
        tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
        return tabla;
    }
    
    public Usuario obtenerUsuario(){
        return usuarioLogin;
    }
    
    public JTable reportes(String _reporte){
        int opcion = 0;
        if(_reporte.equals("5 usuarios con mayor material")){
            opcion = 1;
        }else if(_reporte.equals("5 autores con mayor material")){
            opcion = 2;
        }else if(_reporte.equals("5 revistas más prestadas")){
            opcion = 3;
        }else if(_reporte.equals("10 libros más prestados")){
            opcion = 4;
        }
        JTable tabla = new JTable();
        String idLibro="";
        int posicion =0;
        DefaultTableModel tabModel;
        switch(opcion){
            case 0:
                Object[] documentos = {"ID", "Titulo", "Autor", "Tema", "# de Paginas", "Estado", "Consultas"};
                Object[][] datosDoc = new Object[1][7];
                if(conteoLibros.length>0){
                    int numeroMayor = Integer.parseInt(conteoLibros[0][2]);
                    idLibro = conteoLibros[0][0];
                    for(int a = 0; a<conteoLibros.length;a++){
                        if(numeroMayor<Integer.parseInt(conteoLibros[a][2])){
                            numeroMayor = Integer.parseInt(conteoLibros[a][2]);
                            idLibro = conteoLibros[a][0];
                            posicion = a;
                            System.out.println("Numero mayor es: " +numeroMayor);
                        }
                    }
                    for(int a = 0;a<_cantidadLibros;a++){
                        if(libros[a].getId().equals(idLibro)){
                            datosDoc[0][0] =libros[a].getId();
                            datosDoc[0][1] =libros[a].getTitulo();
                            datosDoc[0][2] =libros[a].getAutor();
                            datosDoc[0][3] =libros[a].getTema();
                            datosDoc[0][4] =libros[a].getPaginas();
                            if(libros[a].isEstado()){
                                datosDoc[0][5] = "Prestado";
                            }else{
                                datosDoc[0][5] = "Disponible";
                            }
                            datosDoc[0][6] =conteoLibros[posicion][2];
                        }
                    }
                }
                tabModel = new DefaultTableModel(datosDoc, documentos){
                        public boolean isCellEditable(int rowIndex, int colIndex){
                            return false;
                        }
                    };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
            case 1:
                Object[] datosUsuario = {"Nombre", "Apellido", "Nick Name", "Consultas"};
                Object[][] usuario = new Object[5][4];
                String[][] conteoTemporalUsuario = conteoTotal;
                String[][] temporalUsuario = new String[5][4];
                for(int a = 0;a<conteoTemporalUsuario.length;a++){
                    for(int b=0;b<conteoTemporalUsuario.length-a-1;b++){
                        if(Integer.parseInt(conteoTemporalUsuario[b][4])<Integer.parseInt(conteoTemporalUsuario[b+1][4])){
                            String[][] temp = new String[1][5];
                            temp[0][0] = conteoTemporalUsuario[b+1][0];
                            temp[0][1] = conteoTemporalUsuario[b+1][1];
                            temp[0][2] = conteoTemporalUsuario[b+1][2];
                            temp[0][3] = conteoTemporalUsuario[b+1][3];
                            temp[0][4] = conteoTemporalUsuario[b+1][4];
                            conteoTemporalUsuario[b+1][0] = conteoTemporalUsuario[b][0];
                            conteoTemporalUsuario[b+1][1] = conteoTemporalUsuario[b][1];
                            conteoTemporalUsuario[b+1][2] = conteoTemporalUsuario[b][2];
                            conteoTemporalUsuario[b+1][3] = conteoTemporalUsuario[b][3];
                            conteoTemporalUsuario[b+1][4] = conteoTemporalUsuario[b][4];
                            conteoTemporalUsuario[b][0] = temp[0][0];
                            conteoTemporalUsuario[b][1] = temp[0][1];
                            conteoTemporalUsuario[b][2] = temp[0][2];
                            conteoTemporalUsuario[b][3] = temp[0][3];
                            conteoTemporalUsuario[b][4] = temp[0][4];
                        }
                    }
                }
                temporalUsuario = conteoTemporalUsuario;
                int pos = 0;
                for(int a=0;a<temporalUsuario.length;a++){
                    for(int b=0;b<_numeroUsuario;b++){
                        if(usuarios[b]!=null){
                            if(temporalUsuario[a][0].equals(usuarios[b].getNickName())){
                                usuario[pos][0] = usuarios[b].getNombre();
                                usuario[pos][1] = usuarios[b].getApellido();
                                usuario[pos][2] = usuarios[b].getNickName();
                                usuario[pos][3] = temporalUsuario[a][4];
                                if(pos==4){
                                    b=_numeroUsuario;
                                }else{
                                    pos++;
                                }
                            }
                        }
                    }
                }
                tabModel = new DefaultTableModel(usuario, datosUsuario){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
            case 2:
                Object[] datosAutor = {"Nombre", "Documentos"};
                Object[][] autor = new Object[5][2];
                String[][] conteoTemporalAutores = conteoAutores;
                for(int a = 0;a<conteoTemporalAutores.length;a++){
                    for(int b=0;b<conteoTemporalAutores.length-a-1;b++){
                        if(Integer.parseInt(conteoTemporalAutores[b][1])<Integer.parseInt(conteoTemporalAutores[b+1][1])){
                            String[][] temp = new String[1][2];
                            temp[0][0] = conteoTemporalAutores[b+1][0];
                            temp[0][1] = conteoTemporalAutores[b+1][1];
                            conteoTemporalAutores[b+1][0] = conteoTemporalAutores[b][0];
                            conteoTemporalAutores[b+1][1] = conteoTemporalAutores[b][1];
                            conteoTemporalAutores[b][0] = temp[0][0];
                            conteoTemporalAutores[b][1] = temp[0][1];
                        }
                    }
                }
                autor = conteoTemporalAutores;
                tabModel = new DefaultTableModel(autor, datosAutor){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
            case 3:
                Object[] datosRevista = {"ID", "Titulo", "Compañia", "Fecha", "Tema", "Estado", "Cantidad"};
                Object[][] revista = new Object[5][7];
                String[][] conteoTemporalRevista = conteoRevistas;
                String[][] temporalRevista = new String[5][2];
                for(int a = 0;a<conteoTemporalRevista.length;a++){
                    for(int b=0;b<conteoTemporalRevista.length-a-1;b++){
                        if(Integer.parseInt(conteoTemporalRevista[b][1])<Integer.parseInt(conteoTemporalRevista[b+1][1])){
                            String[][] temp = new String[1][2];
                            temp[0][0] = conteoTemporalRevista[b+1][0];
                            temp[0][1] = conteoTemporalRevista[b+1][1];
                            conteoTemporalRevista[b+1][0] = conteoTemporalRevista[b][0];
                            conteoTemporalRevista[b+1][1] = conteoTemporalRevista[b][1];
                            conteoTemporalRevista[b][0] = temp[0][0];
                            conteoTemporalRevista[b][1] = temp[0][1];
                        }
                    }
                }
                temporalRevista = conteoTemporalRevista;
                pos = 0;
                for(int a=0;a<temporalRevista.length;a++){
                    for(int b=0;b<_numeroUsuario;b++){
                        if(revistas[b]!=null){
                            if(temporalRevista[a][0].equals(revistas[b].getId())){
                                revista[pos][0] = revistas[b].getId();
                                revista[pos][1] = revistas[b].getTitulo();
                                revista[pos][2] = revistas[b].getCompañia();
                                revista[pos][3] = revistas[b].getFecha();
                                revista[pos][4] = revistas[b].getTema();
                                if(libros[a].isEstado()){
                                    revista[pos][5] = "Prestado";
                                }else{
                                    revista[pos][5] = "Disponible";
                                }
                                revista[pos][6] = temporalRevista[a][1];
                                if(pos==4){
                                    b=_cantidadRevistas;
                                }else{
                                    pos++;
                                }
                            }
                        }
                    }
                }
                tabModel = new DefaultTableModel(revista, datosRevista){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
            case 4:
                Object[] datosLibro = {"ID", "Titulo", "Compañia", "Fecha", "Tema", "Estado", "Cantidad"};
                Object[][] libro = new Object[10][7];
                String[][] conteoTemporalLibro = conteoLibros;
                String[][] temporalLibro = new String[5][3];
                for(int a = 0;a<conteoTemporalLibro.length;a++){
                    for(int b=0;b<conteoTemporalLibro.length-a-1;b++){
                        if(Integer.parseInt(conteoTemporalLibro[b][1])<Integer.parseInt(conteoTemporalLibro[b+1][1])){
                            String[][] temp = new String[1][3];
                            temp[0][0] = conteoTemporalLibro[b+1][0];
                            temp[0][1] = conteoTemporalLibro[b+1][1];
                            temp[0][2] = conteoTemporalLibro[b+1][2];
                            conteoTemporalLibro[b+1][0] = conteoTemporalLibro[b][0];
                            conteoTemporalLibro[b+1][1] = conteoTemporalLibro[b][1];
                            conteoTemporalLibro[b+1][2] = conteoTemporalLibro[b][2];
                            conteoTemporalLibro[b][0] = temp[0][0];
                            conteoTemporalLibro[b][1] = temp[0][1];
                            conteoTemporalLibro[b][2] = temp[0][2];
                        }
                    }
                }
                temporalLibro = conteoTemporalLibro;
                pos = 0;
                for(int a=0;a<temporalLibro.length;a++){
                    for(int b=0;b<_cantidadLibros;b++){
                        if(libros[b]!=null){
                            if(temporalLibro[a][0].equals(libros[b].getId())){
                                libro[pos][0] =libros[a].getId();
                                libro[pos][1] =libros[a].getTitulo();
                                libro[pos][2] =libros[a].getAutor();
                                libro[pos][3] =libros[a].getTema();
                                libro[pos][4] =libros[a].getPaginas();
                                if(libros[a].isEstado()){
                                    libro[pos][5] = "Prestado";
                                }else{
                                    libro[pos][5] = "Disponible";
                                }
                                libro[pos][6] = temporalLibro[a][1];
                                if(pos==9){
                                    b=_cantidadLibros;
                                }else{
                                    pos++;
                                }
                            }
                        }
                    }
                }
                tabModel = new DefaultTableModel(libro, datosLibro){
                    public boolean isCellEditable(int rowIndex, int colIndex){
                        return false;
                    }
                };
                tabla.setModel(tabModel);
                tabla.setPreferredScrollableViewportSize(new Dimension(500, 80));
            break;
        }
        return tabla;
    }
    
    public String verDocumento(String _idDoc, int opcion){
        String ver = "";
        switch(opcion){
            case 0:
                for(int a=0;a<_cantidadLibros;a++){
                    if(libros[a].getId().equals(_idDoc)){
                        String estado ="";
                        if(libros[a].isEstado()){
                            estado = "Prestado";
                        }else{
                            estado = "Disponible";
                        }
                        ver= "ID: "+libros[a].getId()+"\nTitulo: "+libros[a].getTitulo()+"\nAutor: "+libros[a].getAutor()
                                +"\nTema: "+libros[a].getTema()+"\n# de Paginas: "+libros[a].getPaginas()+"\nEstado: "+estado;
                    }
                }
            break;
            case 1:
                for(int a=0;a<_cantidadRevistas;a++){
                    if(revistas[a].getId().equals(_idDoc)){
                        String estado ="";
                        if(revistas[a].isEstado()){
                            estado = "Prestado";
                        }else{
                            estado = "Disponible";
                        }
                        ver = "ID: "+revistas[a].getId()+"\nTitulo: "+revistas[a].getTitulo()+"\nCompañia: "+revistas[a].getCompañia() 
                                +"\nFecha : "+revistas[a].getFecha()+"\nTema: "+revistas[a].getTema()+"\nEstado: "+estado;
                    }
                }
            break;
            case 2:
                for(int a=0;a<_cantidadTesis;a++){
                    if(tesis[a].getId().equals(_idDoc)){
                        String estado ="";
                        if(tesis[a].isEstado()){
                            estado = "Prestado";
                        }else{
                            estado = "Disponible";
                        }
                        ver = "ID: "+tesis[a].getId()+"\nTitulo: "+tesis[a].getTitulo()+"\nCompañia: "+tesis[a].getAutor() 
                                +"\nFecha : "+tesis[a].getGrado()+"\nTema: "+tesis[a].getTema()+"\nAño: "+tesis[a].getAño()+
                                "\nEstado: "+estado;
                    }
                }
            break;
        }
        return ver;
    }
    
    public void intentarLuego(){
        usuarioLogin = null;
    }
}
