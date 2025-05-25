
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GestionEstudiantes {
    public static void ingresarEstudiantes(Scanner scanner) {

        // 1. Lista las materias existentes
        File carpetaDocente = new File(Constantes.RUTA_DOCENTE);
        String[] materias = carpetaDocente.list();

        if (materias == null || materias.length == 0) {
            System.out.println("No hay materias registradas. Primero ingrese una materia.");
            return;
        }

        System.out.println("\n<3 Materias disponibles:");
        for (int i = 0; i < materias.length; i++) {
            System.out.println((i + 1) + ". " + materias[i].replace(".txt", ""));
        }

        // 2. Selecciona una materia
        int opcionMateria;
        do {
            System.out.print("<3 Seleccione una materia (número): ");
            opcionMateria = scanner.nextInt();
            scanner.nextLine(); // Limpia el buffer
        } while (opcionMateria < 1 || opcionMateria > materias.length);

        String materiaSeleccionada = materias[opcionMateria - 1];
        String rutaMateria = Constantes.RUTA_DOCENTE + materiaSeleccionada;

        // 3. Solicita la cantidad de estudiantes
        int cantidadEstudiantes;
        do {
            System.out.print("\n<3 Ingrese la cantidad de estudiantes (mayor a 0): ");
            cantidadEstudiantes = scanner.nextInt();
            scanner.nextLine();
        } while (cantidadEstudiantes <= 0);

        // 4. Procesa a cada estudiante
        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.println("\nEstudiante #" + (i + 1) + ":");

            // Solicita nombre y código del estudiante
            System.out.print("Nombre completo: ");
            String nombre = scanner.nextLine();

            String codigo;
            do {
                System.out.print("Código (solo números): ");
                codigo = scanner.nextLine();
            } while (!codigo.matches("\\d+"));

            // Solicita 3 notas válidas
            Map<String, Double> notas = new HashMap<>();
            String[] tiposNotas = { "Nota 1", "Nota 2", "Nota 3" };

            for (String tipo : tiposNotas) {
                boolean notaValida = false;
                while (!notaValida) {
                    try {
                        System.out.printf("%s (0.0 - 5.0): ", tipo);
                        double nota = scanner.nextDouble();
                        scanner.nextLine(); // Limpia el buffer

                        if (nota < 0.0 || nota > 5.0) {
                            throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
                        }

                        notas.put(tipo, nota);
                        notaValida = true;
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                        scanner.nextLine(); // Limpia el buffer en caso de error
                    }
                }
            }

            // 5. Guarda los datos del estudiante
            String rutaAlumno = Constantes.RUTA_ALUMNO + codigo + ".txt";
            String notasStr = String.format("%.1f,%.1f,%.1f", notas.get("Nota 1"), notas.get("Nota 2"),
                    notas.get("Nota 3"));

            AdminFile.writeFileBuffer(rutaAlumno, notasStr);

            // 6. Calcula el promedio y guarda en el archivo de la materia
            double promedio = (notas.get("Nota 1") + notas.get("Nota 2") + notas.get("Nota 3")) / 3;
            String registroMateria = String.format("Código: %s, Nombre: %s, Promedio: %.1f",
                    codigo, nombre, promedio);

            AdminFile.writeFileBuffer(rutaMateria, registroMateria);
        }

        System.out.println("\n¡Estudiantes registrados exitosamente!");
    }
}