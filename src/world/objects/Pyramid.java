package world.objects;

import util.Transformer;
import util.Vec3;

public class Pyramid implements Object3D {
    private final Vec3[] vertices;
    private Vec3 origin;
    private Vec3 orientation;

    public Pyramid(Vec3 origin, Vec3 orientation, double size) {
        this.origin = origin;
        this.orientation = orientation;

        double radius = size / 2; // bad name
        this.vertices = new Vec3[] {
                new Vec3(-radius, -radius, -radius),
                new Vec3(radius, -radius, -radius),
                new Vec3(-radius, -radius, radius),
                new Vec3(radius, -radius, radius),
                new Vec3(0, radius, 0)
        };
    }

    public Pyramid(Vec3 origin, double size) {
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
        orientation.y = (System.currentTimeMillis() % 10000) * (Math.PI / 5000);

        return new Vec3[] {
                Transformer.transformLocalSpace(this, vertices[0]),
                Transformer.transformLocalSpace(this, vertices[1]),
                Transformer.transformLocalSpace(this, vertices[2]),
                Transformer.transformLocalSpace(this, vertices[3]),
                Transformer.transformLocalSpace(this, vertices[4]),
        };
    }

    @Override
    public int[] getIndexBuffer() {
        return new int[] {
                0, 1, 2,
                0, 1, 4,
                0, 2, 4,
                1, 2, 3,
                1, 3, 4,
                2, 3, 4,
        };
    }
}