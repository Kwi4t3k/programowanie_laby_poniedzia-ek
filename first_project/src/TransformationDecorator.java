import java.util.Locale;

public class TransformationDecorator extends ShapeDecorator{
    private boolean translate, rotate, scale;
    private Vec2 translateVector, rotateCenter, scaleVector;
    private double rotateAngle;

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

    public static class Builder{
        private boolean translate = false, rotate = false, scale = false;
        private Vec2 translateVector, rotateCenter, scaleVector;
        private double rotateAngle;
        Shape shape;
        String transform;
        public Builder(){
            this.transform = "";
        }
        public Builder translate(Vec2 translateVector){
            translate = true;
            this.translateVector = translateVector;
            this.transform += String.format(Locale.ENGLISH, "translate(%f %f) ", translateVector.x, translateVector.y);
            return this;
        }
        public Builder rotate(Vec2 rotateCenter, double rotateAngle){
            rotate = true;
            this.rotateCenter = rotateCenter;
            this.rotateAngle = rotateAngle;
            this.transform += String.format(Locale.ENGLISH, "rotate(%f %f %f) ", rotateAngle, rotateCenter.x, rotateCenter.y);
            return this;
        }
        public Builder scale(Vec2 scaleVector){
            scale = true;
            this.scaleVector = scaleVector;
            this.transform += String.format(Locale.ENGLISH, "scale(%f %f) ", scaleVector.x, scaleVector.y);
            return this;
        }
        public TransformationDecorator build(Shape shape){
            return new TransformationDecorator(shape, translate, rotate, scale, translateVector, rotateCenter, scaleVector, rotateAngle);
        }
    }
    String transform;

    public TransformationDecorator(Shape shape, String transform) {
        super(shape);
        this.transform = transform;
    }
    @Override
    public String toSvg(String string) {
        return super.toSvg(String.format("%s transform=\"%s\"", string, this.transform));
    }
}
