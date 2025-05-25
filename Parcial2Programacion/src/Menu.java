import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {


    public static void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, String> opcionMenu = new HashMap<>();
        opcionMenu.put(1, "Ingresar materia");
        opcionMenu.put(2, "Ingresar estudiantes");
        opcionMenu.put(3, "Verificar información");
        opcionMenu.put(4, "Salir");

        try {
            int opcion;
            do {
                System.out.println("\n <3 --- Menú Principal --- <3 ");
                for (Map.Entry<Integer, String> entry : opcionMenu.entrySet()) {
                    System.out.println(entry.getKey() + ". " + entry.getValue());
                }

                System.out.print("<3 Ingrese una opción: ");
                try {
                    opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpia el buffer

                    switch (opcion) {
                        case 1:
                            GestionMaterias.ingresarMaterias(scanner);
                            break;
                        case 2:
                            if (validarMateriasExistentes()) {
                                GestionEstudiantes.ingresarEstudiantes(scanner);
                            } else {
                                System.out.println("Primero ingrese al menos una materia.");
                            }
                            break;
                        case 3:
                            VerificarInfo.verificarInformacion(scanner);
                            break;
                        case 4:
                            salir();
                            break;
                        default:
                            System.out.println("Opción incorrecta. Intente nuevamente.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Ingrese un número válido.");
                    scanner.nextLine(); // Limpia la entrada inválida
                    opcion = 0; // Fuerza una nueva iteracción
                }
            } while (opcion != 4);
        } finally {
            scanner.close();
        }
    }

    // Método para validar si existen materias registradas.
    private static boolean validarMateriasExistentes() {
        File carpetaDocente = new File(Constantes.RUTA_MATERIAS);
        String[] materias = carpetaDocente.list();
        return materias != null && materias.length > 0;
    }


    // Método para salir del programa.
    private static void salir() {
        if (AdminFile.deleteFolder(Constantes.RUTA_ALUMNO)) {
            System.out.println("Archivos de estudiantes eliminados correctamente.");
        } else {
            System.out.println("No se encontraron archivos para eliminar.");
        }
        System.out.println("Programa finalizado.");
    }
}