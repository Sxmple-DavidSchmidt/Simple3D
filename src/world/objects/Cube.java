package world.objects;

import util.Vec3;

public class Cube implements Object3D {
    private Vec3 origin;
    private Vec3[] vertices;

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
    public Vec3 getOrigin() {
        return origin;
    }

    @Override
    public Vec3[] getVertexBuffer() {
        return vertices;
    }

    @Override
    public int[] getIndexBuffer() {
        // TODO
        return new int[0];
    }
}
