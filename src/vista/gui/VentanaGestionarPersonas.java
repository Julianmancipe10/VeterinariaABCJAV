package vista.gui;

import Controlador.Controlador;
import modelo.operaciones.PersonaVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGestionarPersonas extends JFrame {

    private Controlador controlador;
    private JTextField txtDocumento;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextArea textAreaResultados;
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnConsultarLista;

    public VentanaGestionarPersonas(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Personas");
        setLayout(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblDocumento = new JLabel("Documento:");
        lblDocumento.setBounds(20, 20, 100, 25);
        add(lblDocumento);

        txtDocumento = new JTextField();
        txtDocumento.setBounds(140, 20, 200, 25);
        add(txtDocumento);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(140, 60, 200, 25);
        add(txtNombre);

        JLabel lblApellido = new JLabel("Apellido:");
        lblApellido.setBounds(20, 100, 100, 25);
        add(lblApellido);

        txtApellido = new JTextField();
        txtApellido.setBounds(140, 100, 200, 25);
        add(txtApellido);

        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setBounds(20, 140, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(140, 140, 200, 25);
        add(txtTelefono);

        textAreaResultados = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaResultados);
        scrollPane.setBounds(20, 200, 350, 100);
        add(scrollPane);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(20, 320, 100, 25);
        add(btnRegistrar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(130, 320, 100, 25);
        add(btnConsultar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(240, 320, 100, 25);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(20, 350, 100, 25);
        add(btnEliminar);

        btnConsultarLista = new JButton("Consultar Lista");
        btnConsultarLista.setBounds(130, 350, 150, 25);
        add(btnConsultarLista);

        // Acciones de los botones
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarPersona();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarPersona();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarPersona();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarPersona();
            }
        });

        btnConsultarLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarListaPersonas();
            }
        });
    }

    private void registrarPersona() {
        String documento = txtDocumento.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        PersonaVO persona = new PersonaVO(documento, nombre, apellido, telefono);
        controlador.registrarPersona(persona);
        textAreaResultados.setText("Persona registrada con éxito:\n" + persona);
    }

    private void consultarPersona() {
        String documento = txtDocumento.getText();
        PersonaVO persona = controlador.consultarPersona(documento);
        if (persona != null) {
            txtNombre.setText(persona.getNombre());
            txtApellido.setText(persona.getApellido());
            txtTelefono.setText(persona.getTelefono());
            textAreaResultados.setText("Persona consultada:\n" + persona);
        } else {
            textAreaResultados.setText("La persona no existe.");
        }
    }

    private void actualizarPersona() {
        String documento = txtDocumento.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        PersonaVO persona = new PersonaVO(documento, nombre, apellido, telefono);
        controlador.actualizarPersona(persona);
        textAreaResultados.setText("Persona actualizada con éxito:\n" + persona);
    }

    private void eliminarPersona() {
        String documento = txtDocumento.getText();
        controlador.eliminarPersona(documento);
        textAreaResultados.setText("Persona eliminada con éxito.");
    }

    private void consultarListaPersonas() {
        String lista = controlador.consultarListaPersonas();
        textAreaResultados.setText("Lista de personas:\n" + lista);
    }
}
