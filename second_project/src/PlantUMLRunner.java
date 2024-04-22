import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PlantUMLRunner {
    private static String jarPath; // ścieżka do pliku jar

    // Metoda do ustawienia ścieżki do pliku jar
    public static void setJarPath(String jarPath) {
        PlantUMLRunner.jarPath = jarPath;
    }

    // Metoda do generowania diagramu UML
    public static void generate(String data, String path, String fileName){
        try {
            File catalog = new File(path); // tworzenie nowego katalogu
            catalog.mkdirs(); // tworzenie katalogów, jeśli nie istnieją

            // tworzenie ścieżki do pliku
            String filePath = path + "/" + fileName + ".txt";
            FileWriter fileWriter = new FileWriter(filePath); // tworzenie nowego pliku
            fileWriter.write(data); // zapisywanie danych do pliku
            fileWriter.close(); // zamykanie pliku

            // tworzenie nowego procesu do uruchomienia pliku jar
            ProcessBuilder builder = new ProcessBuilder(
                    "java",
                    "-jar",
                    PlantUMLRunner.jarPath,
                    filePath
            );
            Process process = builder.start(); // uruchamianie procesu
            process.waitFor(); // czekanie na zakończenie procesu

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e); // rzucanie wyjątku, jeśli wystąpi błąd
        }
    }
}
