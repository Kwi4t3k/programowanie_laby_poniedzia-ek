import java.util.Locale;

public class Segment {
    private Vec2 startVec2, endVec2; // Początkowy i końcowy punkt segmentu.

    // Metoda zwracająca początkowy punkt segmentu.
    public Vec2 getStartPoint() {
        return startVec2;
    }

    // Metoda zwracająca końcowy punkt segmentu.
    public Vec2 getEndPoint() {
        return endVec2;
    }

    // Konstruktor tworzący segment na podstawie dwóch punktów.
    public Segment(Vec2 startVec2, Vec2 endVec2) {
        this.startVec2 = startVec2;
        this.endVec2 = endVec2;
    }

    // Metoda obliczająca długość segmentu na podstawie dwóch punktów.
    public double length(Vec2 startVec2, Vec2 endVec2){
        return Math.sqrt(Math.pow(endVec2.x - startVec2.x, 2)+Math.pow(endVec2.y - startVec2.y, 2));
    }

    // Metoda konwertująca segment na format SVG.
    public String toSvg(){
        String line = "<svg height=\"200\" width=\"300\" xmlns=\"http://www.w3.org/2000/svg\">\n";
        line += String.format(Locale.ENGLISH, "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:red;stroke-width:2\" />\n</svg>", startVec2.x, startVec2.y, endVec2.x, endVec2.y);
        return line;
    }

    // Metoda statyczna tworząca dwa odcinki prostopadłe do danego segmentu, przechodzące przez dany punkt.
    public static Segment [] prostopadlyOdcinek(Segment segment, Vec2 vec2){
        // Kąt pomiędzy odcinkiem a dodatnią półosią X.
        double angle = Math.atan2(segment.getEndPoint().y - segment.getStartPoint().y, segment.getEndPoint().x - segment.getStartPoint().x);

        // Długość odcinka prostopadłego.
        double odcinekProstopadly = segment.length(segment.getStartPoint(), segment.getEndPoint());

        // Tablica przechowująca odcinki prostopadłe.
        Segment[] odcinkiProstopadle = new Segment[2];

        // Odcinek prostopadły 1.
        Vec2 endVec21 = new Vec2(
                vec2.x + odcinekProstopadly * Math.cos(angle + Math.PI/2),
                vec2.y + odcinekProstopadly * Math.sin(angle + Math.PI/2)
        );
        odcinkiProstopadle[0] = new Segment(vec2, endVec21);


        // Odcinek prostopadły 2.
        Vec2 endVec22 = new Vec2(
                vec2.x + odcinekProstopadly * Math.cos(angle + Math.PI/2),
                vec2.y + odcinekProstopadly * Math.sin(angle + Math.PI/2)
        );
        odcinkiProstopadle[1] = new Segment(vec2, endVec22);

        return odcinkiProstopadle; // Zwracamy tablicę odcinków prostopadłych.
    }
}
