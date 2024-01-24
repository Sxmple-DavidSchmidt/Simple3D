package world.objects;

import util.Transformer;
import util.Vec3;

public class Cube implements Object3D {
    private final Vec3[] vertices;
    private Vec3 origin;

    public Cube(Vec3 origin, double size) {
        this.origin = origin;

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

    @Override
    public void setOrigin(Vec3 origin) {
        this.origin = origin;
    }

    @Override
    public Vec3 getOrigin() {
        return origin;
    }

    @Override
    public Vec3[] getVertexBuffer() {
        double theta = (System.currentTimeMillis() % 10000) * (Math.PI / 5000);

        return new Vec3[] {
                Transformer.rotateOnX(vertices[0], theta).add(origin),
                Transformer.rotateOnX(vertices[1], theta).add(origin),
                Transformer.rotateOnX(vertices[2], theta).add(origin),
                Transformer.rotateOnX(vertices[3], theta).add(origin),
                Transformer.rotateOnX(vertices[4], theta).add(origin),
                Transformer.rotateOnX(vertices[5], theta).add(origin),
                Transformer.rotateOnX(vertices[6], theta).add(origin),
                Transformer.rotateOnX(vertices[7], theta).add(origin),
        };
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
