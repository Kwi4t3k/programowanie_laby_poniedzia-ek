import java.util.Locale;

public class Polygon {
    private Point[] points;
    private Style style;
    public Polygon(Point[] points, Style style) {
        this.points = points;
        this.style = style;
    }
    public String toSvg() {
        String pol = "";
        pol += "<polygon points=\"";
        for (Point p : points) {
            pol += String.format(Locale.ENGLISH, "%f,%f ", p.x, p.y);
        }
        pol += "\"";
        if(style == null){
            pol += " style=\"fill:none;stroke:black;stroke-width:1\"";
        } else {
            pol += "";
            pol += style.toSvg();
        }
        pol += "/>";
        return pol;
    }
    public Polygon(Polygon other){
        // Tworzymy nową tablicę o takim samym rozmiarze jak oryginalna
        this.points = new Point[other.points.length];

        // Kopiujemy każdy punkt z oryginalnej tablicy
        for (int i = 0; i < other.points.length; i++) {
            // Głęboka kopia punktu
            this.points[i] = new Point(other.points[i].x, other.points[i].y);
        }
    }
    public static Polygon square(Segment line, Style style){
        Point points[] = new Point[4];
        points[0] = line.getStartPoint();
        points[1] = new Point(line.getEndPoint().x, line.getStartPoint().y);
        points[2] = line.getEndPoint();
        points[3] = new Point(line.getStartPoint().x, line.getEndPoint().y);
        Polygon polygon = new Polygon(points, style);
        return polygon;
    }
}
