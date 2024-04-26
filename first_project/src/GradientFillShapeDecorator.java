import java.util.Arrays;
import java.util.Locale;

// Klasa GradientFillShapeDecorator dziedziczy po ShapeDecorator i dodaje gradient do kształtu.
public class GradientFillShapeDecorator extends ShapeDecorator{
    private static int index = 1; // Statyczny indeks używany do generowania unikalnych identyfikatorów gradientów.
    private record Stop(double offset, String color){} // Rekord reprezentujący punkt zatrzymania gradientu.

    private Stop stops[]; // Tablica punktów zatrzymania gradientu.

    // Klasa Builder służy do tworzenia instancji GradientFillShapeDecorator.
    public static class Builder{
        private Stop stops[] = new Stop[0]; // Tablica punktów zatrzymania gradientu.
        private Shape shape; // Kształt do dekoracji.

        // Metoda ustawiająca kształt do dekoracji.
        public GradientFillShapeDecorator.Builder setShape(Shape shape){
            this.shape = shape;
            return this;
        }

        // Metoda dodająca punkt zatrzymania gradientu.
        public GradientFillShapeDecorator.Builder addStop(double offset, String color){
            stops = Arrays.copyOf(stops, stops.length + 1);
            stops[stops.length - 1] = new Stop(offset, color);
            return this;
        }

        // Metoda budująca instancję GradientFillShapeDecorator.
        public GradientFillShapeDecorator build(){
            GradientFillShapeDecorator result = new GradientFillShapeDecorator(shape);
            result.stops = this.stops;
            return result;
        }
    }

    // Konstruktor klasy GradientFillShapeDecorator.
    public GradientFillShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    // Metoda dodająca definicję gradientu do sceny SVG.
    private int addRequiredDefToScene(){
        SvgScene scene = SvgScene.getInstance(); // Pobranie instancji sceny SVG.
        String result = "\t<linearGradient id=\"g%d\" >\n"; // Definicja gradientu.

        // Dodanie punktów zatrzymania do definicji gradientu.
        for (var stop : stops)
            result += String.format(Locale.ENGLISH,"\t\t<stop offset=\"%f\" style=\"stop-color:%s\" />\n", stop.offset, stop.color);

        result += "\t</linearGradient>"; // Zamknięcie definicji gradientu.
        scene.addDefs(result); // Dodanie definicji do sceny.
        return index++; // Zwrócenie i inkrementacja indeksu.
    }

    // Metoda konwertująca kształt do reprezentacji SVG z gradientem.
    @Override
    public String toSvg(String string) {
        int index = addRequiredDefToScene(); // Dodanie definicji gradientu do sceny.
        // Dodanie atrybutu wypełnienia gradientem do reprezentacji SVG kształtu.
        return decoratedShape.toSvg(string + String.format(Locale.ENGLISH,"fill=\"url(#g%d)\" ", index));
    }
}
