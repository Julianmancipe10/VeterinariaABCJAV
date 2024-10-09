package vista.gui;

import Controlador.Controlador;
import modelo.operaciones.MascotaVO;
import modelo.operaciones.PersonaVO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaGestionarMascotas extends JFrame {

    private Controlador controlador;
    private JTextField txtNombreMascota;
    private JTextField txtRaza;
    private JTextField txtDocumentoPropietario;
    private JTextArea textAreaResultados;
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnConsultarLista;
    private JComboBox<String> comboSexo; // JComboBox para el sexo

    public VentanaGestionarMascotas(Controlador controlador) {
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Mascotas");
        setLayout(null);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel lblNombreMascota = new JLabel("Nombre Mascota:");
        lblNombreMascota.setBounds(20, 20, 120, 25);
        add(lblNombreMascota);

        txtNombreMascota = new JTextField();
        txtNombreMascota.setBounds(150, 20, 200, 25);
        add(txtNombreMascota);

        JLabel lblRaza = new JLabel("Raza:");
        lblRaza.setBounds(20, 60, 100, 25);
        add(lblRaza);

        txtRaza = new JTextField();
        txtRaza.setBounds(150, 60, 200, 25);
        add(txtRaza);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setBounds(20, 100, 100, 25);
        add(lblSexo);

        comboSexo = new JComboBox<>(new String[]{"Macho", "Hembra"}); // Opciones de sexo
        comboSexo.setBounds(150, 100, 200, 25);
        add(comboSexo);

        JLabel lblDocumentoPropietario = new JLabel("Documento Propietario:");
        lblDocumentoPropietario.setBounds(20, 140, 140, 25);
        add(lblDocumentoPropietario);

        txtDocumentoPropietario = new JTextField();
        txtDocumentoPropietario.setBounds(150, 140, 200, 25);
        add(txtDocumentoPropietario);

        textAreaResultados = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textAreaResultados);
        scrollPane.setBounds(20, 180, 350, 100);
        add(scrollPane);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(20, 300, 100, 25);
        add(btnRegistrar);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(130, 300, 100, 25);
        add(btnConsultar);

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(240, 300, 100, 25);
        add(btnActualizar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(20, 330, 100, 25);
        add(btnEliminar);

        btnConsultarLista = new JButton("Consultar Lista");
        btnConsultarLista.setBounds(130, 330, 150, 25);
        add(btnConsultarLista);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarMascota();
            }
        });

        btnConsultar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarMascota();
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                actualizarMascota();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarMascota();
            }
        });

        btnConsultarLista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                consultarListaMascotas();
            }
        });
    }

    private void registrarMascota() {
        String nombreMascota = txtNombreMascota.getText();
        String raza = txtRaza.getText();
        String sexo = (String) comboSexo.getSelectedItem();
        String documentoPropietario = txtDocumentoPropietario.getText();
        PersonaVO propietario = controlador.consultarPersona(documentoPropietario);

        if (propietario != null) {
            MascotaVO mascota = new MascotaVO(nombreMascota, raza, sexo, propietario); // Incluye el sexo
            controlador.registrarMascota(mascota);
            textAreaResultados.setText("Mascota registrada con éxito:\n" + mascota);
        } else {
            textAreaResultados.setText("El propietario no está registrado. No se puede registrar la mascota.");
        }
    }

    private void consultarMascota() {
        String nombreMascota = txtNombreMascota.getText();
        MascotaVO mascota = controlador.consultarMascota(nombreMascota);

        if (mascota != null) {
            txtRaza.setText(mascota.getRaza());
            txtDocumentoPropietario.setText(mascota.getPropietario().getDocumento());
            comboSexo.setSelectedItem(mascota.getSexo()); // Selecciona el sexo de la mascota
            textAreaResultados.setText("Mascota consultada:\n" + mascota);
        } else {
            textAreaResultados.setText("La mascota no existe.");
        }
    }

    private void actualizarMascota() {
        String nombreMascota = txtNombreMascota.getText();
        String raza = txtRaza.getText();
        String sexo = (String) comboSexo.getSelectedItem(); // Obtiene el sexo seleccionado
        String documentoPropietario = txtDocumentoPropietario.getText();
        PersonaVO propietario = controlador.consultarPersona(documentoPropietario);

        if (propietario != null) {
            MascotaVO mascota = new MascotaVO(nombreMascota, raza, sexo, propietario); // Incluye el sexo
            controlador.actualizarMascota(mascota);
            textAreaResultados.setText("Mascota actualizada con éxito:\n" + mascota);
        } else {
            textAreaResultados.setText("El propietario no está registrado. No se puede actualizar la mascota.");
        }
    }

    private void eliminarMascota() {
        String nombreMascota = txtNombreMascota.getText();
        controlador.eliminarMascota(nombreMascota);
        textAreaResultados.setText("Mascota eliminada con éxito.");
    }

    private void consultarListaMascotas() {
        String lista = controlador.consultarListaMascotas();
        textAreaResultados.setText(lista);
    }
}



