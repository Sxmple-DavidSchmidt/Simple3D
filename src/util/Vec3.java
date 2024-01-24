package util;

public class Vec3 implements Vec {
    public double x;
    public double y;
    public double z;

    public Vec3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int size() {
        return 3;
    }

    @Override
    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    @Override
    public Vec3 normalize() {
        return divide(length());
    }

    @Override
    public Vec3 divide(double divisor) {
        return new Vec3(x / divisor, y / divisor, z / divisor);
    }

    @Override
    public Vec3 scale(double factor) {
        return new Vec3(x * factor, y * factor, z * factor);
    }

    @Override
    public Vec3 add(Vec vec) {
        if (vec.size() != size()) {
            throw new RuntimeException("Tried adding vectors with different sizes.");
        }

        Vec3 cast = (Vec3) vec;
        return new Vec3(x + cast.x, y + cast.y, z + cast.z);
    }

    @Override
    public Vec3 subtract(Vec vec) {
        if (vec.size() != size()) {
            throw new RuntimeException("Tried adding vectors with different sizes.");
        }

        Vec3 cast = (Vec3) vec;
        return new Vec3(x - cast.x, y - cast.y, z - cast.z);
    }

    @Override
    public String toString() {
        return "Vec3: (" + x + ", " + y + ", " + z + ")";
    }
 }
