import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesarArchivos {
    public static void procesarArchivosConWriteBuffer(String rutaDatos, String rutaNotas, String rutaProgramacion) {
        try (
            BufferedReader datosReader = new BufferedReader(new FileReader(rutaDatos));
            BufferedReader notasReader = new BufferedReader(new FileReader(rutaNotas));
        ) {
            String nombre;
            String notasLine;

            while ((nombre = datosReader.readLine()) != null) {
                // Leer código del estudiante (si existe, aunque no se usa en el promedio)
                String codigo = datosReader.readLine(); // Se lee pero no se usa aquí

                // Leer la línea de notas del estudiante actual
                notasLine = notasReader.readLine();

                // Verificar si hay notas para el estudiante
                if (notasLine == null || notasLine.trim().isEmpty()) {
                    System.out.println("Advertencia: No se encontraron notas para el estudiante: " + nombre);
                    continue; // Saltar al siguiente estudiante
                }

                try {
                    // Calcular promedio individual
                    String[] notasStr = notasLine.split(",");
                    if (notasStr.length != 3) {
                        System.out.println("Error: El estudiante " + nombre + " no tiene 3 notas válidas.");
                        continue;
                    }

                    double suma = 0;
                    for (String notaStr : notasStr) {
                        suma += Double.parseDouble(notaStr.trim());
                    }
                    double promedio = suma / 3;

                    // Guardar resultado en el archivo de programación
                    String resultado = String.format("%s: %.1f", nombre, promedio);
                    AdminFile.writeFileBuffer(rutaProgramacion, resultado);

                } catch (NumberFormatException e) {
                    System.out.println("Error: Las notas del estudiante " + nombre + " no son números válidos.");
                }
            }

        } catch (IOException e) {
            System.out.println("Error al procesar archivos: " + e.getMessage());
        }
    }
}