import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SvgScene {
    private List<String> defs = new ArrayList<>();
    private static int index = 0;
    public int addDefs(String def){
        defs.add(String.format(def, ++index));
        return index;
    }
    private static SvgScene instance = null;
    public static SvgScene getInstance() {
        if (instance == null){
            instance = new SvgScene();
        }
        return instance;
    }

    private List<Shape> shapes = new ArrayList<>();
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


            writer.write("<defs>");
            for (String def : defs)
                writer.write(def + "\n");

            writer.write("</defs>");


            for (Shape shape : shapes)
                writer.write("\t" + shape.toSvg("") + "\n");

            writer.write("</svg>\n</body>\n</html>");
            writer.close();

        } catch (IOException e){
            System.err.println("Błąd podczas zapisu pliku: " + filePath);
        }
    }

}
