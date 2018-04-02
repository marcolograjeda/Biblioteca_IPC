/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.view;

import biblioteca.Ejecucion;
import biblioteca.obj.Libro;
import biblioteca.obj.Revista;
import biblioteca.obj.Tesis;
import biblioteca.obj.Usuario;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTextField;

/**
 *
 * @author Junior
 */
public class Editar {
    public void crearEditar(Object objeto, int opcion, int posicion){
        JFrame ventanaEditar = new JFrame();
        ventanaEditar.setSize(500,600);
        ventanaEditar.setLayout(null);
        ventanaEditar.setResizable(false);
        
        JButton _btnEditar = new JButton("Editar");
        JButton _btnCancelar = new JButton("Cancelar");
        if(opcion <3){
            JTextField _txtId = new JTextField();
            JTextField _txtTitulo = new JTextField();
            JTextField _txtTema = new JTextField();
            JTextField _txtEstado = new JTextField();
            JLabel _lblTituloVentana = new JLabel();
            JLabel _lblId = new JLabel();
            JLabel _lblTitulo = new JLabel();
            JLabel _lblTema = new JLabel();
            JLabel _lblEstado = new JLabel();
            _lblTituloVentana.setText("Editar");
            _lblId.setText("ID:");
            _lblTitulo.setText("Titulo:");
            _lblTema.setText("Tema:");
            _lblEstado.setText("Estado:");
            _txtId.setEditable(false);
            _txtEstado.setEditable(false);
            _lblTituloVentana.setFont(new Font("Segoe UI", 0, 25));
            _lblTitulo.setFont(new Font("Segoe UI", 0, 15));
            _lblTema.setFont(new Font("Segoe UI", 0, 15));
            _lblEstado.setFont(new Font("Segoe UI", 0, 15));
            _lblId.setFont(new Font("Segoe UI", 0, 15));
            
            _lblTituloVentana.setBounds(200, 40, 180, 40);
            _lblId.setBounds(162, 80, 90, 20);
            _txtId.setBounds(162, 105, 175, 30);
            _lblTitulo.setBounds(162, 145, 90, 20);
            _txtTitulo.setBounds(162, 170, 175, 30);
            _lblTema.setBounds(162, 205, 90, 20);
            _txtTema.setBounds(162, 230, 175, 30);
            final int posicionP = posicion;
            switch(opcion){
                case 0:
                    Libro _libro = (Libro)objeto;
                    _txtId.setText(_libro.getId());
                    _txtTitulo.setText(_libro.getTitulo());
                    _txtTema.setText(_libro.getTema());
                    String estado = estado(_libro.isEstado());
                    _txtEstado.setText(estado);
                    JLabel _lblPaginas = new JLabel("Paginas:");
                    _lblPaginas.setFont(new Font("Segoe UI", 0, 15));
                    JTextField _txtPaginas = new JTextField();
                    _txtPaginas.setText(Integer.toString((_libro.getPaginas())));
                    
                    _lblPaginas.setBounds(162, 265, 90, 20);
                    _txtPaginas.setBounds(162, 290, 175, 30);
                    _lblEstado.setBounds(162, 325, 90, 20);
                    _txtEstado.setBounds(162, 350, 175, 30);
                    _btnEditar.setBounds(140, 400, 105, 30);
                    _btnCancelar.setBounds(260, 400, 105, 30);
                    ventanaEditar.add(_lblPaginas);
                    ventanaEditar.add(_txtPaginas);
                    _btnEditar.addActionListener(
                        new ActionListener(){
                            Ejecucion ejec = new Ejecucion();
                            @Override
                            public void actionPerformed (ActionEvent e){
                                _libro.setTitulo(_txtTitulo.getText());
                                _libro.setTema(_txtTema.getText());
                                _libro.setPaginas(Integer.parseInt(_txtPaginas.getText()));
                                ejec.agregarEditar(_libro, 0, posicionP);
                                Tabla tab = new Tabla();
                                tab.ventanaTabla(opcion);
                                ventanaEditar.setVisible(false);
                            }
                        }
                    );
                break;
                case 1:
                    Revista _revista = (Revista)objeto;
                    _txtId.setText(_revista.getId());
                    _txtTitulo.setText(_revista.getTitulo());
                    _txtTema.setText(_revista.getTema());
                    String estadoR = estado(_revista.isEstado());
                    _txtEstado.setText(estadoR);
                    
                    JLabel _lblCompañia = new JLabel("Compañia:");
                    _lblCompañia.setFont(new Font("Segoe UI", 0, 15));
                    JLabel _lblFecha = new JLabel("Fecha:");
                    _lblFecha.setFont(new Font("Segoe UI", 0, 15));
                    JTextField _txtCompañia = new JTextField();
                    _txtCompañia.setText(_revista.getCompañia());
                    JTextField _txtFecha = new JTextField();
                    _txtFecha.setText(_revista.getFecha());
                    
                    _lblCompañia.setBounds(162, 265, 90, 20);
                    _txtCompañia.setBounds(162, 290, 175, 30);
                    _lblFecha.setBounds(162, 325, 90, 20);
                    _txtFecha.setBounds(162, 350, 175, 30);
                    _lblEstado.setBounds(162, 385, 90, 20);
                    _txtEstado.setBounds(162, 410, 175, 30);
                    _btnEditar.setBounds(140, 450, 105, 30);
                    _btnCancelar.setBounds(260, 450, 105, 30);
                    ventanaEditar.add(_lblCompañia);
                    ventanaEditar.add(_txtCompañia);
                    ventanaEditar.add(_lblFecha);
                    ventanaEditar.add(_txtFecha);
                    
                    _btnEditar.addActionListener(
                        new ActionListener(){
                            Ejecucion ejec = new Ejecucion();
                            @Override
                            public void actionPerformed (ActionEvent e){
                                _revista.setTitulo(_txtTitulo.getText());
                                _revista.setTema(_txtTema.getText());
                                _revista.setCompañia(_txtCompañia.getText());
                                _revista.setFecha(_txtFecha.getText());
                                ejec.agregarEditar(_revista, 1, posicionP);
                                Tabla tab = new Tabla();
                                tab.ventanaTabla(opcion);
                                ventanaEditar.setVisible(false);
                            }
                        }
                    );
                break;
                case 2:
                    Tesis _tesis = (Tesis)objeto;
                    _txtId.setText(_tesis.getId());
                    _txtTitulo.setText(_tesis.getTitulo());
                    _txtTema.setText(_tesis.getTema());
                    String estadoT = estado(_tesis.isEstado());
                    _txtEstado.setText(estadoT);
                    
                    JLabel _lblAutor = new JLabel("Autor:");
                    _lblAutor.setFont(new Font("Segoe UI", 0, 15));
                    JLabel _lblGrado = new JLabel("Grado:");
                    _lblGrado.setFont(new Font("Segoe UI", 0, 15));
                    JLabel _lblAño = new JLabel("Año:");
                    _lblAño.setFont(new Font("Segoe UI", 0, 15));
                    JTextField _txtAutor = new JTextField();
                    _txtAutor.setText(_tesis.getAutor());
                    JTextField _txtGrado = new JTextField();
                    _txtGrado.setText(_tesis.getGrado());
                    JTextField _txtAño = new JTextField();
                    _txtAño.setText(Integer.toString((_tesis.getAño())));
                    _lblAutor.setBounds(162, 265, 90, 20);
                    _txtAutor.setBounds(162, 290, 175, 30);
                    _lblGrado.setBounds(162, 325, 90, 20);
                    _txtGrado.setBounds(162, 350, 175, 30);
                    _lblAño.setBounds(162, 385, 90, 20);
                    _txtAño.setBounds(162, 410, 175, 30);
                    _lblEstado.setBounds(162, 445, 90, 20);
                    _txtEstado.setBounds(162, 470, 175, 30);
                    _btnEditar.setBounds(140, 525, 105, 30);
                    _btnCancelar.setBounds(260, 525, 105, 30);
                    ventanaEditar.add(_lblAutor);
                    ventanaEditar.add(_txtAutor);
                    ventanaEditar.add(_lblGrado);
                    ventanaEditar.add(_txtGrado);
                    
                    _btnEditar.addActionListener(
                        new ActionListener(){
                            Ejecucion ejec = new Ejecucion();
                            @Override
                            public void actionPerformed (ActionEvent e){
                                _tesis.setTitulo(_txtTitulo.getText());
                                _tesis.setTema(_txtTema.getText());
                                _tesis.setAutor(_txtAutor.getText());
                                _tesis.setGrado(_txtGrado.getText());
                                _tesis.setAño(Integer.parseInt(_txtGrado.getText()));
                                ejec.agregarEditar(_tesis, 2, posicionP);
                                Tabla tab = new Tabla();
                                tab.ventanaTabla(opcion);
                                ventanaEditar.setVisible(false);
                            }
                        }
                    );
                break;
            }
            ventanaEditar.add(_lblTituloVentana);
            ventanaEditar.add(_lblId);
            ventanaEditar.add(_txtId);
            ventanaEditar.add(_lblTitulo);
            ventanaEditar.add(_txtTitulo);
            ventanaEditar.add(_lblTema);
            ventanaEditar.add(_txtTema);
            ventanaEditar.add(_lblEstado);
            ventanaEditar.add(_txtEstado);
        }else{
            JTextField _txtNombre = new JTextField();
            JTextField _txtApellido = new JTextField();
            JTextField _txtNick = new JTextField();
            JTextField _txtContraseña = new JTextField();
            JLabel _lblTitulo = new JLabel("Registro");
            JLabel _lblNick = new JLabel("Usuario:");
            JLabel _lblNombre = new JLabel("Nombre:");
            JLabel _lblApellido = new JLabel("Apellido:");
            JLabel _lblContraseña = new JLabel("Contraseña:");

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
            _txtContraseña.setBounds(162, 315, 175, 30);
            Usuario _usuarioSinEditar = (Usuario)objeto;
            
            _txtNombre.setText(_usuarioSinEditar.getNombre());
            _txtApellido.setText(_usuarioSinEditar.getApellido());
            _txtNick.setText(_usuarioSinEditar.getNickName());
            _txtContraseña.setText(_usuarioSinEditar.getPassword());
            Usuario _usuarioEditado = new Usuario();
            final int posicionP = posicion;
            _btnEditar.addActionListener(
                new ActionListener(){
                    Ejecucion ejec = new Ejecucion();
                    @Override
                    public void actionPerformed (ActionEvent e){
                        _usuarioEditado.setNombre(_txtNombre.getText());
                        _usuarioEditado.setApellido(_txtApellido.getText());
                        _usuarioEditado.setNickName(_txtNick.getText());
                        _usuarioEditado.setPassword(_txtContraseña.getText());
                        if(_usuarioSinEditar.getNickName().equals(_usuarioEditado.getNickName())){
                            ejec.agregarEditar(_usuarioEditado, 3, posicionP);
                            Tabla tab = new Tabla();
                            tab.ventanaTabla(opcion);
                            ventanaEditar.setVisible(false);
                        }else{
                            int problema = ejec.validarUsuario(_usuarioEditado.getNickName(),2);
                            switch (problema){
                                case 0:
                                    ejec.agregarEditar(_usuarioEditado, 3, posicionP);
                                    Tabla tab = new Tabla();
                                    tab.ventanaTabla(opcion);
                                    ventanaEditar.setVisible(false);
                                break;
                                case 1:
                                    showMessageDialog(null, "Ese nombre de usuario ya existe");
                                break;
                            }
                        }
                        
                    }
                }
            );
            _btnEditar.setBounds(140, 380, 105, 30);
            _btnCancelar.setBounds(260, 380, 105, 30);
            ventanaEditar.add(_txtNick);
            ventanaEditar.add(_txtNombre);
            ventanaEditar.add(_txtApellido);
            ventanaEditar.add(_txtContraseña);
            ventanaEditar.add(_lblNombre);
            ventanaEditar.add(_lblApellido);
            ventanaEditar.add(_lblTitulo);
            ventanaEditar.add(_lblNick);
            ventanaEditar.add(_lblContraseña);
        }
        ventanaEditar.add(_btnEditar);
        ventanaEditar.add(_btnCancelar);
        ventanaEditar.setVisible(true);
    }
    
    public String estado(boolean estado){
        String estadoP = "";
        if(estado){
            estadoP = "Prestado";
        }else{
            estadoP = "Disponible";
        }
        return estadoP;
    }
}
