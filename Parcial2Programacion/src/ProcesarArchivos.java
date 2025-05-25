
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcesarArchivos {
    public static void procesarArchivosConWriteBuffer(String rutaDatos, String rutaNotas, String rutaProgramacion) {
        try (
                BufferedReader datosReader = new BufferedReader(new FileReader(rutaDatos));
                BufferedReader notasReader = new BufferedReader(new FileReader(rutaNotas));) {
            String nombre;
            String notasLine;

            while ((nombre = datosReader.readLine()) != null) {
                // Lee el código del estudiante
                String codigo = datosReader.readLine();

                // Lee las notas
                notasLine = notasReader.readLine();

                if (notasLine == null || notasLine.trim().isEmpty()) {
                    System.out.println("Advertencia: No se encontraron notas para: " + nombre);
                    continue;
                }

                try {
                    String[] notasStr = notasLine.split(",");
                    if (notasStr.length != 3) {
                        System.out.println("Error: " + nombre + " no tiene 3 notas válidas.");
                        continue;
                    }

                    // Calcula el promedio
                    double suma = 0;
                    for (String notaStr : notasStr) {
                        suma += Double.parseDouble(notaStr.trim());
                    }
                    double promedio = suma / 3;

                    // Guarda en formato: "Código: ,Nombre: ,Promedio: "
                    String resultado = String.format("Código: %s, Nombre: %s, Promedio: %.1f",
                            codigo, nombre, promedio);
                    AdminFile.writeFileBuffer(rutaProgramacion, resultado);

                } catch (NumberFormatException e) {
                    System.out.println("Error en notas de " + nombre + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivos: " + e.getMessage());
        }
    }
}