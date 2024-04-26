import java.util.Locale;

// Klasa DropShadowDecorator dziedziczy po ShapeDecorator
public class DropShadowDecorator extends ShapeDecorator{
    // Statyczna zmienna do przechowywania indeksu
    private static int index;

    // Konstruktor klasy DropShadowDecorator
    public DropShadowDecorator(Shape decoratedShape) {
        super(decoratedShape);

        // Dodajemy definicję filtru do SvgScene i przechowujemy indeks
        index = SvgScene.getInstance().addDefs(
                "\t<filter id=\"f%d\" x=\"-100%%\" y=\"-100%%\" width=\"300%%\" height=\"300%%\">\n" +
                        "\t\t<feOffset result=\"offOut\" in=\"SourceAlpha\" dx=\"5\" dy=\"5\" />\n" +
                        "\t\t<feGaussianBlur result=\"blurOut\" in=\"offOut\" stdDeviation=\"5\" />\n" +
                        "\t\t<feBlend in=\"SourceGraphic\" in2=\"blurOut\" mode=\"normal\" />\n" +
                        "\t</filter>"
        );
    }

    // Nadpisujemy metodę toSvg
    @Override
    public String toSvg(String string) {
        // Formatujemy stringa dodając do niego filtr
        String filterWithId = String.format(Locale.ENGLISH,"filter=\"url(#f%d)\" %s ", index, string);
        // Zwracamy wynik metody toSvg z klasy bazowej z dodanym filtrem
        return decoratedShape.toSvg(filterWithId);
    }
}
