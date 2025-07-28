/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;
import controlador.ControladorPersona;
import modelo.Persona;
import modelo.Propietario;
import modelo.Veterinario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Kevin
 */
public class VentanaFormulario extends JFrame{


    private ControladorPersona controller;
    private JTextField txtNombre, txtIdentificacion, txtExtra;
    private JTextArea areaListado;
    private JComboBox<String> cmbTipo;

    public VentanaFormulario() {
        setTitle("Gestión de Personas");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        controller = new ControladorPersona();
        initUI();
        mostrarListado();
    }

    private void initUI() {
        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 5, 5));

        txtNombre = new JTextField();
        txtIdentificacion = new JTextField();
        txtExtra = new JTextField();

        cmbTipo = new JComboBox<>(new String[]{"Propietario", "Veterinario"});
        cmbTipo.addActionListener(e -> actualizarEtiquetaExtra());

        panelSuperior.add(new JLabel("Nombre:"));
        panelSuperior.add(txtNombre);
        panelSuperior.add(new JLabel("Identificación:"));
        panelSuperior.add(txtIdentificacion);
        panelSuperior.add(new JLabel("Tipo de persona:"));
        panelSuperior.add(cmbTipo);
        panelSuperior.add(new JLabel("Teléfono / Especialidad:"));
        panelSuperior.add(txtExtra);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar por ID");

        btnAgregar.addActionListener(this::agregarPersona);
        btnEliminar.addActionListener(this::eliminarPersona);

        panelSuperior.add(btnAgregar);
        panelSuperior.add(btnEliminar);

        areaListado = new JTextArea();
        areaListado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaListado);

        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private void actualizarEtiquetaExtra() {
        String tipo = (String) cmbTipo.getSelectedItem();
        JLabel lblExtra = (JLabel)((JPanel)getContentPane().getComponent(0)).getComponent(6);
        if (tipo.equals("Propietario")) {
            lblExtra.setText("Teléfono:");
        } else {
            lblExtra.setText("Especialidad:");
        }
    }

    private void agregarPersona(ActionEvent e) {
        String nombre = txtNombre.getText().trim();
        String id = txtIdentificacion.getText().trim();
        String extra = txtExtra.getText().trim();
        String tipo = (String) cmbTipo.getSelectedItem();

        // Validación simple
        if (nombre.isEmpty() || id.isEmpty() || extra.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios");
            return;
        }

        Persona persona;
        if (tipo.equals("Propietario")) {
            persona = new Propietario(nombre, id, extra);
        } else {
            persona = new Veterinario(nombre, id, extra);
        }

        controller.agregar(persona);
        limpiarCampos();
        mostrarListado();
    }

    private void eliminarPersona(ActionEvent e) {
        String id = JOptionPane.showInputDialog(this, "Ingrese ID a eliminar:");
        
        if (id == null && id.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un ID para poder eliminar la Persona.");
            return ;
        }
         Persona confirmar = controller.buscarPersona(id);
         if(confirmar != null){
            controller.eliminarPorIdentificacion(id);
            mostrarListado();
            JOptionPane.showMessageDialog(this, "La persona fue eliminada correctamente","Warning", JOptionPane.WARNING_MESSAGE);
          }else{
             JOptionPane.showMessageDialog(this, "La pérsona no fue eliminada.", "Warning", JOptionPane.WARNING_MESSAGE);
         }
        
     }

    private void mostrarListado() {
        StringBuilder sb = new StringBuilder("Listado de personas:\n\n");
        for (Persona p : controller.listar()) {
            sb.append(p).append("\n");
        }
        areaListado.setText(sb.toString());
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtIdentificacion.setText("");
        txtExtra.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaFormulario().setVisible(true));
    }
}


