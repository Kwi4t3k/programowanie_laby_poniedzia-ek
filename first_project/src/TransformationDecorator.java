import java.util.Locale;

// Klasa TransformationDecorator dziedziczy po ShapeDecorator i dodaje funkcje transformacji.
public class TransformationDecorator extends ShapeDecorator{
    // Zmienne przechowujące informacje o transformacjach.
    private boolean translate, rotate, scale;
    private Vec2 translateVector, rotateCenter, scaleVector;
    private double rotateAngle;

    // Konstruktor klasy TransformationDecorator.
    public TransformationDecorator(Shape decoratedShape, boolean translate, boolean rotate, boolean scale, Vec2 translateVector, Vec2 rotateCenter, Vec2 scaleVector, double rotateAngle) {
        super(decoratedShape);
        this.translate = translate;
        this.rotate = rotate;
        this.scale = scale;
        this.translateVector = translateVector;
        this.rotateCenter = rotateCenter;
        this.scaleVector = scaleVector;
        this.rotateAngle = rotateAngle;
    }

    // Klasa Builder służy do tworzenia obiektów klasy TransformationDecorator.
    public static class Builder{
        private boolean translate = false, rotate = false, scale = false;
        private Vec2 translateVector, rotateCenter, scaleVector;
        private double rotateAngle;
        Shape shape;
        String transform;

        // Konstruktor klasy Builder.
        public Builder(){
            this.transform = "";
        }

        // Metoda dodaje translację do transformacji.
        public Builder translate(Vec2 translateVector){
            translate = true;
            this.translateVector = translateVector;
            this.transform += String.format(Locale.ENGLISH, "translate(%f %f) ", translateVector.x, translateVector.y);
            return this;
        }

        // Metoda dodaje rotację do transformacji.
        public Builder rotate(Vec2 rotateCenter, double rotateAngle){
            rotate = true;
            this.rotateCenter = rotateCenter;
            this.rotateAngle = rotateAngle;
            this.transform += String.format(Locale.ENGLISH, "rotate(%f %f %f) ", rotateAngle, rotateCenter.x, rotateCenter.y);
            return this;
        }

        // Metoda dodaje skalowanie do transformacji.
        public Builder scale(Vec2 scaleVector){
            scale = true;
            this.scaleVector = scaleVector;
            this.transform += String.format(Locale.ENGLISH, "scale(%f %f) ", scaleVector.x, scaleVector.y);
            return this;
        }

        // Metoda buduje obiekt klasy TransformationDecorator.
        public TransformationDecorator build(Shape shape){
            return new TransformationDecorator(shape, transform);
        }
    }

    // Zmienna przechowująca transformację.
    String transform = null;

    // Konstruktor klasy TransformationDecorator.
    public TransformationDecorator(Shape shape, String transform) {
        super(shape);
        this.transform = transform;
    }

    // Metoda zwraca reprezentację SVG kształtu z transformacją.
    @Override
    public String toSvg(String string) {
        return super.toSvg(String.format("%s transform=\"%s\"", string, this.transform));
    }
}
