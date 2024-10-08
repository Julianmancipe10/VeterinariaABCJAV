package vista.gui;

import Controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {

    private Controlador controlador;
    private JButton btnGestionarPersonas;
    private JButton btnGestionarMascotas;
    private Image backgroundImage; // Declarar la variable de imagen

    public VentanaPrincipal(Controlador controlador) {
        // Cargar la imagen de fondo
        backgroundImage = new ImageIcon("fondo.jpg").getImage(); // Cambia "fondo.jpg" por la ruta de tu imagen
        this.controlador = controlador;
        initComponents();
    }

    private void initComponents() {
        setTitle("Clínica Veterinaria ABC - Ventana Principal");
        setLayout(new BorderLayout()); // Cambiar a BorderLayout para el panel de fondo
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agregar el panel de fondo
        BackgroundPanel backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null); // Permitir el uso de setBounds en los componentes
        add(backgroundPanel);

        JLabel lblTitulo = new JLabel("Gestión de Personas y Mascotas");
        lblTitulo.setBounds(100, 20, 250, 25);
        backgroundPanel.add(lblTitulo); // Añadir al panel de fondo

        btnGestionarPersonas = new JButton("Gestionar Personas");
        btnGestionarPersonas.setBounds(100, 80, 200, 30);
        backgroundPanel.add(btnGestionarPersonas); // Añadir al panel de fondo

        btnGestionarMascotas = new JButton("Gestionar Mascotas");
        btnGestionarMascotas.setBounds(100, 130, 200, 30);
        backgroundPanel.add(btnGestionarMascotas); // Añadir al panel de fondo

        // Acciones de los botones
        btnGestionarPersonas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaGestionarPersonas();
            }
        });

        btnGestionarMascotas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirVentanaGestionarMascotas();
            }
        });
    }

    private void abrirVentanaGestionarPersonas() {
        VentanaGestionarPersonas ventanaPersonas = new VentanaGestionarPersonas(controlador);
        ventanaPersonas.setVisible(true);
    }

    private void abrirVentanaGestionarMascotas() {
        VentanaGestionarMascotas ventanaMascotas = new VentanaGestionarMascotas(controlador);
        ventanaMascotas.setVisible(true);
    }

    // Clase interna para el panel de fondo
    private class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar la imagen de fondo
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(controlador);
        ventanaPrincipal.setVisible(true);
    }
}



