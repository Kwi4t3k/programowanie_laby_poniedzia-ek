public class Main {
    public static void main(String[] args) {
        // Tworzymy scenę SVG
        SvgScene scene = new SvgScene();

        // Tworzymy przykładowe punkty
        Vec2[] pointsHexagon = {
                new Vec2(130, 50),
                new Vec2(210, 90),
                new Vec2(210, 170),
                new Vec2(130, 210),
                new Vec2(50, 170),
                new Vec2(50, 90)
        };

        Vec2[] pointsTriangle = {
                new Vec2(300, 197),
                new Vec2(12, 209),
                new Vec2(48, 322)
        };

        //KWADRAT

        Style style = new Style("blue", "green", 5);
        Vec2 startVec2 = new Vec2(10, 10);
        Vec2 endVec2 = new Vec2(60, 60);
        Segment line = new Segment(startVec2, endVec2);

        // Wywołujemy metodę square
        Polygon squarePolygon = Polygon.square(line, style);

        scene.addShape(squarePolygon);


        //ELIPSA

        Shape ellipse = new Ellipse(new Vec2(400, 200), 50.5, 75.7);

        ellipse = new TransformationDecorator.Builder()
                .rotate(new Vec2(400, 200), 90)
                .build(ellipse);

        ellipse = new SolidFillShapeDecorator(ellipse, "pink");
        ellipse = new StrokeShapeDecorator(ellipse, "green", 10);

        scene.addShape(ellipse);


        // TRÓJKĄT I HEKSAGON

        // Tworzymy przykładowe wielokąty
        Shape hexagon = new Polygon(pointsHexagon, new Style("yellow", "red", 5));
        Shape triangle = new Polygon(pointsTriangle, new Style("purple", "blue", 5));

        // Dodajemy wielokąty do sceny

        scene.addShape(hexagon);
        scene.addShape(triangle);


        // POLYGON

        Vec2[] pointsPolygon = {
                new Vec2(100, 20),
                new Vec2(180, 60),
                new Vec2(180, 140),
                new Vec2(100, 180),
                new Vec2(20, 140),
                new Vec2(20, 60)
        };

        Shape sfp = new SolidFilledPolygon(pointsPolygon, "red", new Style("pink", "black", 7));
        sfp = new SolidFillShapeDecorator(sfp, "red");
        sfp = new StrokeShapeDecorator(sfp,"cyan", 5);
        scene.addShape(sfp);

        // Zapisujemy scenę do pliku HTML
        scene.save("output.html");
    }
}
