import java.io.File;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static final String RUTA_MATERIAS = Constantes.RUTA_DOCENTE;

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
                            verificarInformacion(scanner);
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
        File carpetaDocente = new File(RUTA_MATERIAS);
        String[] materias = carpetaDocente.list();
        return materias != null && materias.length > 0;
    }

    // Método para verificar la información de los estudiantes.
    private static void verificarInformacion(Scanner scanner) {
        File carpetaDocente = new File(RUTA_MATERIAS);
        String[] materias = carpetaDocente.list();

        if (materias == null || materias.length == 0) {
            System.out.println("No hay materias registradas.");
            return;
        }

        System.out.println("\nMaterias disponibles:");
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

            String rutaMateria = RUTA_MATERIAS + materias[opcion - 1];
            System.out.println("\n--- Información de la materia ---");
            AdminFile.readFileBuffer(rutaMateria);
        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            scanner.nextLine();
        }
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