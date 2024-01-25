package util;

public class Vec2 {
    public double x;
    public double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public int size() {
        return 2;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public Vec2 normalize() {
        return divide(length());
    }

    public Vec2 divide(double divisor) {
        return new Vec2(x / divisor, y / divisor);
    }

    public Vec2 scale(double factor) {
        return new Vec2(x * factor, y * factor);
    }

    public Vec2 add(Vec2 vec) {
        return new Vec2(x + vec.x, y + vec.y);
    }

    public Vec2 subtract(Vec2 vec) {
        return new Vec2(x - vec.x, y - vec.y);
    }

    @Override
    public String toString() {
        return "Vec2: (" + x + ", " + y + ")";
    }
}
