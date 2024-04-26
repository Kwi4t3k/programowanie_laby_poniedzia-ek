import java.util.Locale;

public class Polygon implements Shape {
    private Vec2[] vec2s; // Tablica przechowująca punkty definiujące wielokąt.
    private Style style; // Styl, który będzie zastosowany do wielokąta.

    // Konstruktor tworzący wielokąt na podstawie tablicy punktów i stylu.
    public Polygon(Vec2[] vec2s, Style style) {
        this.style = style; // Przypisujemy styl do naszego wielokąta.
        this.vec2s = vec2s; // Przypisujemy punkty do naszego wielokąta.
    }

    // Domyślny konstruktor.
    public Polygon() {
    }

    // Metoda zwracająca styl wielokąta.
    public Style getStyle() {
        return style;
    }

    // Metoda konwertująca wielokąt na format SVG.
    public String toSvg(String string) {
        String pol = "";
        // Tworzymy ciąg punktów dla naszego wielokąta.
        for (int i = 0; i < vec2s.length; i++){
            pol += String.format(Locale.ENGLISH, "%f,%f ", vec2s[i].x, vec2s[i].y);
        }
        // Zwracamy ciąg w formacie SVG.
        return String.format(Locale.ENGLISH, "<polygon points=\"%s\" %s/>", pol, string);
    }

    // Konstruktor kopiujący, tworzący nowy wielokąt na podstawie innego.
    public Polygon(Polygon other){
        this.style = new Style(other.getStyle()); // Kopiujemy styl.

        // Tworzymy nową tablicę o takim samym rozmiarze jak oryginalna.
        this.vec2s = new Vec2[other.vec2s.length];

        // Kopiujemy każdy punkt z oryginalnej tablicy.
        for (int i = 0; i < other.vec2s.length; i++) {
            // Głęboka kopia punktu.
            this.vec2s[i] = new Vec2(other.vec2s[i].x, other.vec2s[i].y);
        }
    }

    // Metoda statyczna tworząca kwadrat na podstawie segmentu i stylu.
    public static Polygon square(Segment line, Style style){
        Vec2 vec2s[] = new Vec2[4]; // Tworzymy tablicę na punkty kwadratu.
        vec2s[0] = line.getStartPoint(); // Punkt startowy to początek segmentu.
        vec2s[1] = new Vec2(line.getEndPoint().x, line.getStartPoint().y); // Drugi punkt to koniec segmentu na osi x i początek na osi y.
        vec2s[2] = line.getEndPoint(); // Trzeci punkt to koniec segmentu.
        vec2s[3] = new Vec2(line.getStartPoint().x, line.getEndPoint().y); // Czwarty punkt to początek segmentu na osi x i koniec na osi y.
        Polygon polygon = new Polygon(vec2s, style); // Tworzymy nowy wielokąt na podstawie punktów i stylu.
        return polygon; // Zwracamy nowo utworzony wielokąt.
    }
}
