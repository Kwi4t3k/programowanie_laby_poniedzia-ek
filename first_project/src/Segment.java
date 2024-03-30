import java.util.Locale;

public class Segment {
    private Vec2 startVec2, endVec2;

    public Vec2 getStartPoint() {
        return startVec2;
    }

    public Vec2 getEndPoint() {
        return endVec2;
    }

    public Segment(Vec2 startVec2, Vec2 endVec2) {
        this.startVec2 = startVec2;
        this.endVec2 = endVec2;
    }

    public double length(Vec2 startVec2, Vec2 endVec2){
        return Math.sqrt(Math.pow(endVec2.x - startVec2.x, 2)+Math.pow(endVec2.y - startVec2.y, 2));
    }

    public String toSvg(){
        String line = "<svg height=\"200\" width=\"300\" xmlns=\"http://www.w3.org/2000/svg\">\n";
        line += String.format(Locale.ENGLISH, "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:red;stroke-width:2\" />\n</svg>", startVec2.x, startVec2.y, endVec2.x, endVec2.y);
        return line;
    }
//                ---------------- metoda v1 -------------------
//    public static Segment prostopadlyOdcinek(Segment segment, Point point){
//        //obliczanie długości odcinka
//        double segmentLenght = segment.length(segment.getStartPoint(), segment.getEndPoint());
//
//        //obliczanie kąta nachylenia odcinka
//        double angle = Math.atan2(segment.getEndPoint().y - segment.getStartPoint().y, segment.getEndPoint().x - segment.getStartPoint().x);
//
//        // Obliczamy nowe współrzędne punktu na prostopadłym odcinku
//        double newX = point.x + segmentLenght * Math.cos(angle + Math.PI/2);
//        double newY = point.y + segmentLenght * Math.sin(angle + Math.PI/2);
//
//        // Tworzymy punkt dla nowego końca prostopadłego odcinka
//        Point newEndPoint = new Point(newX, newY);
//        return new Segment(point, newEndPoint);
//    }

//                ---------------- metoda v2 -------------------
    public static Segment [] prostopadlyOdcinek(Segment segment, Vec2 vec2){
        // Kąt pomiędzy odcinkiem a dodatnią półosią X
        double angle = Math.atan2(segment.getEndPoint().y - segment.getStartPoint().y, segment.getEndPoint().x - segment.getStartPoint().x);

        // Długość odcinka prostopadłego
        double odcinekProstopadly = segment.length(segment.getStartPoint(), segment.getEndPoint());

        // Tablica przechowująca odcinki prostopadłe
        Segment[] odcinkiProstopadle = new Segment[2];

        // Odcinek prostopadły 1
        Vec2 endVec21 = new Vec2(
                vec2.x + odcinekProstopadly * Math.cos(angle + Math.PI/2),
                vec2.y + odcinekProstopadly * Math.sin(angle + Math.PI/2)
        );
        odcinkiProstopadle[0] = new Segment(vec2, endVec21);


        // Odcinek prostopadły 2
        Vec2 endVec22 = new Vec2(
                vec2.x + odcinekProstopadly * Math.cos(angle + Math.PI/2),
                vec2.y + odcinekProstopadly * Math.sin(angle + Math.PI/2)
        );
        odcinkiProstopadle[1] = new Segment(vec2, endVec22);

        return odcinkiProstopadle;
    }
}
