package grafico;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Becas;
import logica.Estudiantes;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Formulario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textNombre;
    private JTextField textIndice;
    private JTextField textCedula;
    private JComboBox<String> comboBoxCarreras;
    private JComboBox<String> comboBoxSexo;

    private Becas becas;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Formulario frame = new Formulario();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Formulario() {
        becas = new Becas();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 932, 552);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Información de estudiantes");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblNewLabel.setBounds(277, 10, 271, 62);
        contentPane.add(lblNewLabel);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNombre.setBounds(21, 97, 95, 22);
        contentPane.add(lblNombre);

        textNombre = new JTextField();
        textNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textNombre.setBounds(141, 102, 153, 19);
        contentPane.add(textNombre);
        textNombre.setColumns(10);

        JLabel lblIndice = new JLabel("Indice:");
        lblIndice.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblIndice.setBounds(21, 239, 95, 22);
        contentPane.add(lblIndice);

        textIndice = new JTextField();
        textIndice.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textIndice.setColumns(10);
        textIndice.setBounds(141, 240, 153, 19);
        contentPane.add(textIndice);

        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCedula.setBounds(21, 166, 95, 22);
        contentPane.add(lblCedula);

        textCedula = new JTextField();
        textCedula.setFont(new Font("Tahoma", Font.PLAIN, 20));
        textCedula.setColumns(10);
        textCedula.setBounds(141, 167, 153, 19);
        contentPane.add(textCedula);

        JLabel lblCarrera = new JLabel("Carrera:");
        lblCarrera.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCarrera.setBounds(21, 308, 95, 22);
        contentPane.add(lblCarrera);

        comboBoxCarreras = new JComboBox<>();
        comboBoxCarreras.setModel(new DefaultComboBoxModel<>(new String[]{"Ingeniería Civil", "Ingeniería Eléctrica", "Ingeniería Industrial", "Ingeniería en Sistemas", "Ingeniería Mecánica", "Ingeniería Marítima"}));
        comboBoxCarreras.setBounds(141, 312, 153, 21);
        comboBoxCarreras.setSelectedIndex(-1);
        contentPane.add(comboBoxCarreras);

        JLabel lblSexo = new JLabel("Sexo:");
        lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSexo.setBounds(21, 376, 95, 22);
        contentPane.add(lblSexo);

        comboBoxSexo = new JComboBox<>();
        comboBoxSexo.setModel(new DefaultComboBoxModel<>(new String[]{"Masculino", "Femenino"}));
        comboBoxSexo.setBounds(141, 380, 153, 21);
        comboBoxSexo.setSelectedIndex(-1);
        contentPane.add(comboBoxSexo);

        JButton btnGuardar = new JButton("Guardar Datos");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarDatos();
            }
        });
        btnGuardar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnGuardar.setBounds(460, 163, 184, 34);
        contentPane.add(btnGuardar);

        JButton btnReportes = new JButton("Mostrar Reportes");
        btnReportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirReportes();
            }
        });
        btnReportes.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnReportes.setBounds(700, 361, 184, 34);
        contentPane.add(btnReportes);
    }

    private void guardarDatos() {
        try {
            String nombre = textNombre.getText();
            String cedula = textCedula.getText();
            String indiceStr = textIndice.getText();
            String carrera = (String) comboBoxCarreras.getSelectedItem();
            String sexo = (String) comboBoxSexo.getSelectedItem();

            // Validaciones
            if (nombre.isEmpty()) {
                mostrarMensajeError("El campo nombre está vacío.");
                return;
            }
            if (!Pattern.matches("^[a-zA-Z\\s]+$", nombre)) {
                mostrarMensajeError("El nombre no puede contener números ni caracteres especiales.");
                return;
            }
            if (cedula.isEmpty()) {
                mostrarMensajeError("El campo cédula está vacío.");
                return;
            }
            if (indiceStr.isEmpty()) {
                mostrarMensajeError("El campo índice está vacío.");
                return;
            }
            if (carrera == null) {
                mostrarMensajeError("Debe seleccionar una carrera.");
                return;
            }
            if (sexo == null) {
                mostrarMensajeError("Debe seleccionar un sexo.");
                return;
            }

            double indice = Double.parseDouble(indiceStr);

            // Validación del índice académico (entre 0 y 3)
            if (indice < 0 || indice > 3) {
                mostrarMensajeError("El índice académico debe estar entre 0 y 3.");
                return;
            }

            Estudiantes estudiante = new Estudiantes(nombre, cedula, carrera, indice, sexo);
            becas.agregarEstudiante(estudiante);

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(this, "Información guardada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Limpiar campos
            textNombre.setText("");
            textCedula.setText("");
            textIndice.setText("");
            comboBoxCarreras.setSelectedIndex(-1);
            comboBoxSexo.setSelectedIndex(-1);

        } catch (NumberFormatException e) {
            mostrarMensajeError("Por favor, ingrese un valor numérico válido para el índice académico.");
        }
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void abrirReportes() {
        // Crear y mostrar la ventana de reportes
        Reportes reportes = new Reportes(becas);
        reportes.setVisible(true);
    }
}
