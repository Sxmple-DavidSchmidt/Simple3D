package world.objects;

import util.Transformer;
import util.Vec3;

public class Cube implements Object3D {
    private final Vec3[] vertices;
    private Vec3 origin;
    private Vec3 orientation;

    public Cube(Vec3 origin, Vec3 orientation, double size) {
        this.origin = origin;
        this.orientation = orientation;

        double radius = size / 2; // bad name
        this.vertices = new Vec3[] {
                new Vec3(-radius, -radius, -radius),    // (-1 -1 -1)
                new Vec3(radius, -radius, -radius),     // (+1 -1 -1)
                new Vec3(-radius, radius, -radius),     // (-1 +1 -1)
                new Vec3(radius, radius, -radius),      // (+1 +1 -1)
                new Vec3(-radius, -radius, radius),     // (-1 -1 +1)
                new Vec3(radius, -radius, radius),      // (+1 -1 +1)
                new Vec3(-radius, radius, radius),      // (-1 +1 +1)
                new Vec3(radius, radius, radius),       // (+1 +1 +1)
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
    public Vec3[] getVertexBuffer() {
        this.orientation.x = (System.currentTimeMillis() % 5000.0) / 5000.0;
        return Transformer.transformLocalSpace(vertices, this);
    }

    @Override
    public int[] getIndexBuffer() {
        return new int[] {
                0, 1, 2,
                1, 2, 3,
                1, 3, 7,
                1, 5, 7,
                4, 5, 7,
                4, 6, 7,
                0, 2, 4,
                2, 4, 6,
                0, 4, 5,
                0, 1, 5,
                2, 3, 6,
                3, 6, 7
        };
    }
}
