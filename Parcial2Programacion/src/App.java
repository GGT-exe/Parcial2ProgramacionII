public class App {
    public static void main(String[] args) {

        // Crea las estructuras de las carpetas usando Constantes
        System.out.println(
            AdminFile.createFolders(Constantes.RUTA_ALUMNO)
        );
        System.out.println(
            AdminFile.createFolders(Constantes.RUTA_DOCENTE)
        );

        Menu.mostrarMenu();
    }
}