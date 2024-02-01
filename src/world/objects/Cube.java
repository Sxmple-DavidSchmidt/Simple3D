package world.objects;

import util.Transformer;
import util.Vec3;

public class Cube implements Object3D {
    private final Triangle[] triangles;
    private Vec3 origin;
    private Vec3 orientation;

    public Cube(Vec3 origin, Vec3 orientation, double size) {
        this.origin = origin;
        this.orientation = orientation;

        double radius = size / 2; // bad name
        Vec3 v0 = new Vec3(-radius, -radius, -radius);  // (-1 -1 -1)
        Vec3 v1 = new Vec3(radius, -radius, -radius);   // (+1 -1 -1)
        Vec3 v2 = new Vec3(-radius, radius, -radius);   // (-1 +1 -1)
        Vec3 v3 = new Vec3(radius, radius, -radius);    // (+1 +1 -1)
        Vec3 v4 = new Vec3(-radius, -radius, radius);   // (-1 -1 +1)
        Vec3 v5 = new Vec3(radius, -radius, radius);    // (+1 -1 +1)
        Vec3 v6 = new Vec3(-radius, radius, radius);    // (-1 +1 +1)
        Vec3 v7 = new Vec3(radius, radius, radius);     // (+1 +1 +1)

        triangles = new Triangle[] {
                new Triangle(v0, v1, v2),
                new Triangle(v1, v2, v3),
                new Triangle(v1, v3, v7),
                new Triangle(v1, v5, v7),
                new Triangle(v4, v5, v7),
                new Triangle(v4, v6, v7),
                new Triangle(v0, v2, v4),
                new Triangle(v2, v4, v6),
                new Triangle(v0, v4, v5),
                new Triangle(v0, v1, v5),
                new Triangle(v3, v6, v7)
        };
    }

    public Cube(Vec3 origin, double size) {
        this(origin, new Vec3(0, 0, 0), size);
    }

    @Override
    public void setOrigin(Vec3 origin) {
        this.origin = origin;
    }

    @Override
    public Vec3 getOrigin() {
        return origin;
    }

    @Override
    public void setOrientation(Vec3 orientation) {
        this.orientation = orientation;
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
