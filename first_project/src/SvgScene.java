import javax.imageio.IIOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SvgScene {
    private List<Polygon> polygons = new ArrayList<>();
    public void addPolygon (Polygon polygon){
        polygons.add(polygon);
    }
    public void save(String filePath) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>\n<head>\n<title>SVG Scene</title>\n</head>\n<body>\n");
            writer.write("<svg width=\"500\" height=\"500\">\n");

            for (Polygon polygon : polygons){
                writer.write(polygon.toSvg());
                writer.write("\n");
            }
            writer.write("</svg>\n</body>\n</html>");
        } catch (IOException e){
            System.err.println("Błąd podczas zapisu pliku: " + e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Błąd podczas zamykania pliku: " + e.getMessage());
                }
            }
        }
    }

}
