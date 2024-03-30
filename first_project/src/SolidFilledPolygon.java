public class SolidFilledPolygon extends Polygon{
    private String color;

    public SolidFilledPolygon(Vec2[] vec2s, String color, Style style) {
        super(vec2s, style);
        this.color = color;
    }

    @Override
    public String toSvg(String string) {
        String f = String.format("<polygon points=\"%s\" %s ", color, string);
        return super.toSvg(f);
    }
}
