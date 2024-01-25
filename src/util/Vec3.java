package util;

public class Vec3 {
    public double x;
    public double y;
    public double z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int size() {
        return 3;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public Vec3 normalize() {
        return divide(length());
    }

    public Vec3 divide(double divisor) {
        return new Vec3(x / divisor, y / divisor, z / divisor);
    }

    public Vec3 scale(double factor) {
        return new Vec3(x * factor, y * factor, z * factor);
    }

    public Vec3 add(Vec3 vec) {
        return new Vec3(x + vec.x, y + vec.y, z + vec.z);
    }

    public Vec3 subtract(Vec3 vec) {
        return new Vec3(x - vec.x, y - vec.y, z - vec.z);
    }

    public String toString() {
        return "Vec3: (" + Math.round(x * 100) / 100 + ", " + Math.round(y * 100) / 100 + ", " + Math.round(z * 100) / 100 + ")";
    }
 }
