import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AdminFile {

    // Método para crear carpetas (ya usa rutas completas)
    public static boolean createFolders(String path) {
        File folder = new File(path); // Ej: "db/personas/alumno/"
        return folder.mkdirs();
    }

    // Método para crear archivos (usa rutas completas)
    public static boolean createFile(String fullPath) {
        File file = new File(fullPath); // Ej: "db/personas/docente/matematicas.txt"
        try {
            return file.createNewFile();
        } catch (IOException e) {
            System.err.println("Error al crear archivo: " + e.getMessage());
            return false;
        }
    }

    // Método para escribir en archivos (usa rutas completas)
    public static boolean writeFileBuffer(String fullPath, String texto) {
        try (BufferedWriter buffer = new BufferedWriter(new FileWriter(fullPath, true))) {
            buffer.write(texto);
            buffer.newLine();
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en " + fullPath + ": " + e.getMessage());
            return false;
        }
    }

    // Método para leer archivos (usa rutas completas)
    public static void readFileBuffer(String fullPath) {
        try (BufferedReader br = new BufferedReader(new FileReader(fullPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error al leer " + fullPath + ": " + e.getMessage());
        }
    }

    // Método para eliminar carpetas (usa rutas completas)
    public static boolean deleteFolder(String folderPath) {
        File folder = new File(folderPath);
        
        // Verifica si la carpeta existe y es un directorio
        if (!folder.exists() || !folder.isDirectory()) {
            return false;
        }

        boolean success = true;
        File[] files = folder.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) {
                        System.err.println("Error al eliminar: " + file.getName());
                        success = false;
                    }
                }
            }
        }
        return success;
    }

    // Método para validar los nombres de los archivos
    public static boolean isValidFileName(String name) {
        return !name.matches(".*[/\\\\?*:|\"<>].*") && !name.trim().isEmpty();
    }
}