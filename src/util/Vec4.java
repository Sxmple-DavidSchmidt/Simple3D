package util;

public class Vec4 implements Vec {
    public double x;
    public double y;
    public double z;
    public double w;

    public Vec4(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    @Override
    public int size() {
        return 4;
    }

    @Override
    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    @Override
    public Vec normalize() {
        return divide(length());
    }

    @Override
    public Vec divide(double divisor) {
        return new Vec4(x / divisor, y / divisor, z / divisor, w / divisor);
    }

    @Override
    public Vec scale(double factor) {
        return new Vec4(x * factor, y * factor, z * factor, w * factor);
    }

    @Override
    public Vec add(Vec vec) {
        if (vec.size() != size()) {
            throw new RuntimeException("Tried adding vectors with different sizes.");
        }

        Vec4 cast = (Vec4) vec;
        return new Vec4(x + cast.x, y + cast.y, z + cast.z, w + cast.w);
    }

    @Override
    public Vec4 subtract(Vec vec) {
        if (vec.size() != size()) {
            throw new RuntimeException("Tried adding vectors with different sizes.");
        }

        Vec4 cast = (Vec4) vec;
        return new Vec4(x - cast.x, y - cast.y, z - cast.z, w - cast.w);
    }

    @Override
    public String toString() {
        return "Vec4: (" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
