package logica;


import java.util.ArrayList;

public class Becas {
    private static final int MAX_ESTUDIANTES = 100;
    private ArrayList<Estudiantes> estudiantes;

    public Becas() {
        estudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Estudiantes estudiante) {
        if (estudiantes.size() < MAX_ESTUDIANTES) {
            estudiantes.add(estudiante);
        } else {
            System.out.println("No se puede agregar más estudiantes. Límite alcanzado.");
        }
    }

    public ArrayList<Estudiantes> obtenerEstudiantesBecados() {
        ArrayList<Estudiantes> estudiantesBecados = new ArrayList<>();

        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getIndiceAcademico() >= 2.0) {
                estudiantesBecados.add(estudiante);
            }
        }

        return estudiantesBecados;
    }

    public ArrayList<Estudiantes> obtenerEstudiantesNoBecados() {
        ArrayList<Estudiantes> estudiantesNoBecados = new ArrayList<>();

        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getIndiceAcademico() < 2.0) {
                estudiantesNoBecados.add(estudiante);
            }
        }

        return estudiantesNoBecados;
    }

    public ArrayList<Estudiantes> buscarBecadosPorCarreraYSexo(String carrera, String sexo) {
        ArrayList<Estudiantes> becadosFiltrados = new ArrayList<>();

        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getCarrera().equals(carrera) && estudiante.getSexo().equals(sexo) && estudiante.getIndiceAcademico() >= 2.0) {
                becadosFiltrados.add(estudiante);
            }
        }

        return becadosFiltrados;
    }

    public Estudiantes buscarEstudiantePorCedula(String cedula) {
        for (Estudiantes estudiante : estudiantes) {
            if (estudiante.getCedula().equals(cedula)) {
                return estudiante;
            }
        }
        return null;
    }

    public ArrayList<Estudiantes> getEstudiantes() {
        return estudiantes;
    }
}
