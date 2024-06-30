package grafico;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import logica.Becas;
import logica.Estudiantes;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reportes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextArea textAreaReportes;

    public Reportes(Becas becas) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 666, 483);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Reportes");
        lblNewLabel.setBounds(238, 10, 149, 46);
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
        contentPane.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 84, 622, 260);
        contentPane.add(scrollPane);

        textAreaReportes = new JTextArea();
        scrollPane.setViewportView(textAreaReportes);

        JButton btnMostrarBecados = new JButton("Mostrar Becados");
        btnMostrarBecados.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnMostrarBecados.setBounds(33, 370, 176, 29);
        btnMostrarBecados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarBecados(becas);
            }
        });
        contentPane.add(btnMostrarBecados);

        JButton btnMostrarNoBecados = new JButton("Mostrar No Becados");
        btnMostrarNoBecados.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnMostrarNoBecados.setBounds(238, 370, 176, 29);
        btnMostrarNoBecados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarNoBecados(becas);
            }
        });
        contentPane.add(btnMostrarNoBecados);

        JButton btnVolver = new JButton("Volver al Inicio");
        btnVolver.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnVolver.setBounds(462, 370, 176, 29);
        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                volverAlInicio();
            }
        });
        contentPane.add(btnVolver);
    }

    private void mostrarBecados(Becas becas) {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiantes Becados:\n");
        for (Estudiantes estudiante : becas.obtenerEstudiantesBecados()) {
            sb.append("Nombre: ").append(estudiante.getNombre()).append(", Carrera: ").append(estudiante.getCarrera()).append(", Sexo: ").append(estudiante.getSexo()).append("\n");
        }
        textAreaReportes.setText(sb.toString());
    }

    private void mostrarNoBecados(Becas becas) {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudiantes No Becados:\n");
        for (Estudiantes estudiante : becas.obtenerEstudiantesNoBecados()) {
            sb.append("Nombre: ").append(estudiante.getNombre()).append(", Carrera: ").append(estudiante.getCarrera()).append(", Sexo: ").append(estudiante.getSexo()).append("\n");
        }
        textAreaReportes.setText(sb.toString());
    }

    private void volverAlInicio() {

        this.dispose();
        Formulario formulario = new Formulario();
        formulario.setVisible(true);
    }
}
