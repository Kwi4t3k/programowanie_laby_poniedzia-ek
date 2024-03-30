import java.util.Locale;

public class Polygon implements Shape {
    private Vec2[] vec2s;
    private Style style;
    public Polygon(Vec2[] vec2s, Style style) {
//        this.style = style;
        this.style = style;
        this.vec2s = vec2s;
    }

    public Polygon() {
    }

    public Style getStyle() {
        return style;
    }

    public String toSvg(String string) {
        String pol = "";
        for (int i = 0; i < vec2s.length; i++){
            pol += String.format(Locale.ENGLISH, "%f,%f ", vec2s[i].x, vec2s[i].y);
        }
        return String.format(Locale.ENGLISH, "<polygon points=\"%s\" %s/>", pol, string);
    }
    public Polygon(Polygon other){
        this.style = new Style(other.getStyle());

        // Tworzymy nową tablicę o takim samym rozmiarze jak oryginalna
        this.vec2s = new Vec2[other.vec2s.length];

        // Kopiujemy każdy punkt z oryginalnej tablicy
        for (int i = 0; i < other.vec2s.length; i++) {
            // Głęboka kopia punktu
            this.vec2s[i] = new Vec2(other.vec2s[i].x, other.vec2s[i].y);
        }
    }
    public static Polygon square(Segment line, Style style){
        Vec2 vec2s[] = new Vec2[4];
        vec2s[0] = line.getStartPoint();
        vec2s[1] = new Vec2(line.getEndPoint().x, line.getStartPoint().y);
        vec2s[2] = line.getEndPoint();
        vec2s[3] = new Vec2(line.getStartPoint().x, line.getEndPoint().y);
        Polygon polygon = new Polygon(vec2s, style);
        return polygon;
    }
}
