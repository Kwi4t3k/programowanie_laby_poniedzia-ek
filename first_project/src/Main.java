public class Main {
    public static void main(String[] args) {
        // Tworzymy scenę SVG
        SvgScene scene = new SvgScene();

        // Tworzymy przykładowe punkty
        Point[] pointsHexagon = {
                new Point(100, 20),
                new Point(180, 60),
                new Point(180, 140),
                new Point(100, 180),
                new Point(20, 140),
                new Point(20, 60)
        };

        Point[] pointsTriangle = {
                new Point(300, 197),
                new Point(12, 209),
                new Point(48, 322)
        };


        Style style = new Style("blue", "green", 5);
        Point startPoint = new Point(10, 10);
        Point endPoint = new Point(60, 60);
        Segment line = new Segment(startPoint, endPoint);

        // Wywołujemy metodę square
        Polygon squarePolygon = Polygon.square(line, style);

        scene.addPolygon(squarePolygon);


        // Tworzymy przykładowe wielokąty
        Polygon hexagon = new Polygon(pointsHexagon, new Style("yellow", "red", 5));
        Polygon triangle = new Polygon(pointsTriangle, new Style("purple", "blue", 5));

        // Dodajemy wielokąty do sceny
        scene.addPolygon(hexagon);
        scene.addPolygon(triangle);

        // Zapisujemy scenę do pliku HTML
        scene.save("output.html");
    }
}
