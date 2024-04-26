public class Main {
    public static void main(String[] args) {
        // Tworzymy scenę SVG
        // Używamy metody getInstance() klasy SvgScene, aby utworzyć instancję sceny SVG.
        SvgScene scene = SvgScene.getInstance();

        // Tworzymy przykładowe punkty
        // Definiujemy punkty, które będą używane do stworzenia heksagonu.
        Vec2[] pointsHexagon = {
                new Vec2(130, 50),
                new Vec2(210, 90),
                new Vec2(210, 170),
                new Vec2(130, 210),
                new Vec2(50, 170),
                new Vec2(50, 90)
        };

        // Definiujemy punkty, które będą używane do stworzenia trójkąta.
        Vec2[] pointsTriangle = {
                new Vec2(300, 197),
                new Vec2(12, 209),
                new Vec2(48, 322)
        };

        //KWADRAT
        // Definiujemy styl dla kwadratu.
        Style style = new Style("blue", "green", 5);
        // Definiujemy początkowy i końcowy punkt dla linii, która będzie używana do stworzenia kwadratu.
        Vec2 startVec2 = new Vec2(10, 10);
        Vec2 endVec2 = new Vec2(60, 60);
        Segment line = new Segment(startVec2, endVec2);

        // Wywołujemy metodę square, aby stworzyć kwadrat na podstawie linii i stylu.
        Polygon squarePolygon = Polygon.square(line, style);

        // Dodajemy kwadrat do sceny.
        scene.addShape(squarePolygon);


        //ELIPSA
        // Tworzymy elipsę z określonymi parametrami.
        Shape ellipse = new Ellipse(new Vec2(400, 200), 50.5, 75.7);

        // Dodajemy cień do elipsy.
        ellipse = new DropShadowDecorator(ellipse);

        // Obracamy elipsę o 90 stopni wokół punktu (400, 200).
        ellipse = new TransformationDecorator.Builder()
                .rotate(new Vec2(400, 200), 90)
                .build(ellipse);

        // Wypełniamy elipsę kolorem "pink" i dodajemy zielony kontur o grubości 4.
        ellipse = new SolidFillShapeDecorator(ellipse, "pink");
        ellipse = new StrokeShapeDecorator(ellipse, "green", 4);

        // Dodajemy elipsę do sceny.
        scene.addShape(ellipse);


        // TRÓJKĄT I HEKSAGON
        // Tworzymy przykładowe wielokąty
        // Tworzymy heksagon i trójkąt z określonymi punktami i stylami.
        Shape hexagon = new Polygon(pointsHexagon, new Style("yellow", "red", 5));
        Shape triangle = new Polygon(pointsTriangle, new Style("purple", "blue", 5));


        //gradient
        // Dodajemy gradient do trójkąta.
        triangle = new GradientFillShapeDecorator.Builder()
                .setShape(triangle)
                .addStop(0, "red")
                .addStop(0.3, "white")
                .addStop(1, "cyan")
                .build();


        // Dodajemy wielokąty do sceny
        // Dodajemy heksagon i trójkąt do sceny.
        scene.addShape(hexagon);
        scene.addShape(triangle);


        // POLYGON
        // Definiujemy punkty, które będą używane do stworzenia innego wielokąta.
        Vec2[] pointsPolygon = {
                new Vec2(100, 20),
                new Vec2(180, 60),
                new Vec2(180, 140),
                new Vec2(100, 180),
                new Vec2(20, 140),
                new Vec2(20, 60)
        };

        // Tworzymy wielokąt z określonymi punktami i stylem, a następnie dodajemy do niego kolor wypełnienia i kontur.
        Shape sfp = new SolidFilledPolygon(pointsPolygon, "red", new Style("pink", "black", 7));
        sfp = new SolidFillShapeDecorator(sfp, "red");
        sfp = new StrokeShapeDecorator(sfp,"cyan", 5);
        // Dodajemy wielokąt do sceny.
        scene.addShape(sfp);

        // Zapisujemy scenę do pliku HTML
        // Zapisujemy całą scenę do pliku o nazwie "output.html".
        scene.save("output.html");
    }
}
