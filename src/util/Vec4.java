package util;

public class Vec4 {
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

    public int size() {
        return 4;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Vec4 normalize() {
        return divide(length());
    }

    public Vec4 divide(double divisor) {
        return new Vec4(x / divisor, y / divisor, z / divisor, w / divisor);
    }

    public Vec4 scale(double factor) {
        return new Vec4(x * factor, y * factor, z * factor, w * factor);
    }

    public Vec4 add(Vec4 vec) {
        return new Vec4(x + vec.x, y + vec.y, z + vec.z, w + vec.w);
    }

    public Vec4 subtract(Vec4 vec) {
        return new Vec4(x - vec.x, y - vec.y, z - vec.z, w - vec.w);
    }

    @Override
    public String toString() {
        return "Vec4: (" + x + ", " + y + ", " + z + ", " + w + ")";
    }
}
