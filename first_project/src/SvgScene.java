import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Klasa SvgScene reprezentuje scenę SVG, która zawiera definicje i kształty.
public class SvgScene {
    // Lista definicji SVG.
    private List<String> defs = new ArrayList<>();
    // Statyczny indeks używany do numerowania definicji.
    private static int index = 0;

    // Metoda dodaje definicję do listy i zwraca jej indeks.
    public int addDefs(String def){
        defs.add(String.format(def, ++index));
        return index;
    }

    // Statyczna instancja klasy SvgScene dla wzorca Singleton.
    private static SvgScene instance = null;

    // Metoda zwraca instancję klasy SvgScene. Jeśli nie istnieje, tworzy nową.
    public static SvgScene getInstance() {
        if (instance == null){
            instance = new SvgScene();
        }
        return instance;
    }

    // Lista kształtów na scenie.
    private List<Shape> shapes = new ArrayList<>();

    // Metoda dodaje kształt do listy kształtów.
    public void addShape (Shape shape){
        shapes.add(shape);
    }

    // Metoda zapisuje scenę SVG do pliku o podanej ścieżce.
    public void save(String filePath) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<svg width=\"500\" height=\"500\">\n");

            // Zapisuje definicje do pliku.
            writer.write("<defs>");
            for (String def : defs)
                writer.write(def + "\n");
            writer.write("</defs>");

            // Zapisuje kształty do pliku.
            for (Shape shape : shapes)
                writer.write("\t" + shape.toSvg("") + "\n");

            writer.write("</svg>\n</body>\n</html>");
            writer.close();

        } catch (IOException e){
            // Wypisuje błąd, jeśli nie udało się zapisać pliku.
            System.err.println("Błąd podczas zapisu pliku: " + filePath);
        }
    }
}
