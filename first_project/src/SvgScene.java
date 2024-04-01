import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SvgScene {
//    private List<Polygon> polygons = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();

//    public void addPolygon (Polygon polygon){
//        polygons.add(polygon);
//    }

    public void addShape (Shape shape){
        shapes.add(shape);
    }

    public void save(String filePath) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("<!DOCTYPE html>\n");
            writer.write("<html>");
            writer.write("<body>");
            writer.write("<svg width=\"500\" height=\"500\">\n");


            for (Shape shape : shapes)
                writer.write("\t" + shape.toSvg("") + "\n");

            writer.write("</svg>\n</body>\n</html>");
            writer.close();

        } catch (IOException e){
            System.err.println("Błąd podczas zapisu pliku: " + filePath);
        }
    }

}
