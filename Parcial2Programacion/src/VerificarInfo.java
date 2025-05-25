
import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VerificarInfo {

    // Método para verificar la información de los estudiantes.
    public static void verificarInformacion(Scanner scanner) {
        File carpetaDocente = new File(Constantes.RUTA_MATERIAS);
        String[] materias = carpetaDocente.list();

        if (materias == null || materias.length == 0) {
            System.out.println("No hay materias registradas.");
            return;
        }

        System.out.println("\n<3 Materias disponibles:");
        for (int i = 0; i < materias.length; i++) {
            System.out.println((i + 1) + ". " + materias[i].replace(".txt", ""));
        }

        System.out.print("Seleccione una materia (número): ");
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            if (opcion < 1 || opcion > materias.length) {
                System.out.println("Opción inválida.");
                return;
            }

            String rutaMateria = Constantes.RUTA_MATERIAS + materias[opcion - 1];
            System.out.println("\n <3 --- Información de la materia --- <3 ");
            AdminFile.readFileBuffer(rutaMateria);
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();
        }
    }
}
