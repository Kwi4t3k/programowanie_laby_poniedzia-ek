import java.util.Locale;

public class Segment {
    private Point startPoint, endPoint;

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public Segment(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public double length(Point startPoint, Point endPoint){
        return Math.sqrt(Math.pow(endPoint.x - startPoint.x, 2)+Math.pow(endPoint.y - startPoint.y, 2));
    }

    public String toSvg(){
        String line = "<svg height=\"200\" width=\"300\" xmlns=\"http://www.w3.org/2000/svg\">\n";
        line += String.format(Locale.ENGLISH, "<line x1=\"%f\" y1=\"%f\" x2=\"%f\" y2=\"%f\" style=\"stroke:red;stroke-width:2\" />\n</svg>", startPoint.x, startPoint.y, endPoint.x, endPoint.y);
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
    public static Segment [] prostopadlyOdcinek(Segment segment, Point point){
        // Kąt pomiędzy odcinkiem a dodatnią półosią X
        double angle = Math.atan2(segment.getEndPoint().y - segment.getStartPoint().y, segment.getEndPoint().x - segment.getStartPoint().x);

        // Długość odcinka prostopadłego
        double odcinekProstopadly = segment.length(segment.getStartPoint(), segment.getEndPoint());

        // Tablica przechowująca odcinki prostopadłe
        Segment[] odcinkiProstopadle = new Segment[2];

        // Odcinek prostopadły 1
        Point endPoint1 = new Point(
                point.x + odcinekProstopadly * Math.cos(angle + Math.PI/2),
                point.y + odcinekProstopadly * Math.sin(angle + Math.PI/2)
        );
        odcinkiProstopadle[0] = new Segment(point, endPoint1);


        // Odcinek prostopadły 2
        Point endPoint2 = new Point(
                point.x + odcinekProstopadly * Math.cos(angle + Math.PI/2),
                point.y + odcinekProstopadly * Math.sin(angle + Math.PI/2)
        );
        odcinkiProstopadle[1] = new Segment(point, endPoint2);

        return odcinkiProstopadle;
    }
}
