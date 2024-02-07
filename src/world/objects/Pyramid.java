package world.objects;

import util.Transformer;
import util.Vec3;

public class Pyramid implements Object3D {
    private final Triangle[] triangles;
    private final Vec3 origin;
    private final Vec3 orientation;

    public Pyramid(Vec3 origin, Vec3 orientation, double size) {
        this.origin = origin;
        this.orientation = orientation;

        double radius = size / 2; // bad name
        Vec3 v0 = new Vec3(-radius, -radius, -radius);
        Vec3 v1 = new Vec3(radius, -radius, -radius);
        Vec3 v2 = new Vec3(-radius, -radius, radius);
        Vec3 v3 = new Vec3(radius, -radius, radius);
        Vec3 v4 = new Vec3(0, radius, 0);

        triangles = new Triangle[] {
                new Triangle(v0, v1, v2),
                new Triangle(v0, v1, v4),
                new Triangle(v0, v2, v4),
                new Triangle(v1, v2, v3),
                new Triangle(v1, v3, v4),
                new Triangle(v2, v3, v4)
        };
    }

    public Pyramid(Vec3 origin) {
        this(origin, new Vec3(0, 0, 0), 1);
    }

    @Override
    public double getSize() {
        return 1;
    }

    @Override
    public Vec3 getOrigin() {
        return origin;
    }

    @Override
    public Vec3 getOrientation() {
        return orientation;
    }

    @Override
    public Triangle[] getTriangles() {
        return Transformer.transformLocalSpace(triangles, this);
    }
}