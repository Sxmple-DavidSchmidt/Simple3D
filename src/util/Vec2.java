package util;

public class Vec2 implements Vec {
    public double x;
    public double y;

    public Vec2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int size() {
        return 2;
    }

    @Override
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    @Override
    public Vec normalize() {
        return divide(length());
    }

    @Override
    public Vec divide(double divisor) {
        return new Vec2(x / divisor, y / divisor);
    }

    @Override
    public Vec scale(double factor) {
        return new Vec2(x * factor, y * factor);
    }

    @Override
    public Vec add(Vec vec) {
        if (vec.size() != size()) {
            throw new RuntimeException("Tried adding vectors with different sizes.");
        }

        Vec2 cast = (Vec2) vec;
        return new Vec2(x + cast.x, y + cast.y);
    }

    @Override
    public Vec subtract(Vec vec) {
        if (vec.size() != size()) {
            throw new RuntimeException("Tried subtracting vectors with different sizes.");
        }

        Vec2 cast = (Vec2) vec;
        return new Vec2(x - cast.x, y - cast.y);
    }

    @Override
    public String toString() {
        return "Vec2: (" + x + ", " + y + ")";
    }
}
