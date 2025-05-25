
import java.util.Scanner;

public class GestionMaterias {
    public static void ingresarMaterias(Scanner scanner) {
        String nombreMateria;
        
        do {
            System.out.print("<3 Ingrese el nombre de la materia: ");
            nombreMateria = scanner.nextLine().trim();

            if (!AdminFile.isValidFileName(nombreMateria)) {
                System.out.println(Constantes.ERROR_NOMBRE_INVALIDO);
            }
        } while (!AdminFile.isValidFileName(nombreMateria));

        // Crea el archivo "materia.txt" en la ruta especificada
        String rutaArchivo = Constantes.RUTA_DOCENTE + nombreMateria + ".txt";
        boolean creado = AdminFile.createFile(rutaArchivo);

        if (creado) {
            System.out.println("Materia registrada correctamente.");
        }
    }
}