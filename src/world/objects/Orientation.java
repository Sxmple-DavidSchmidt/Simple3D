package world.objects;

import util.Vec3;

public class Orientation implements Object3D {
    private double size;

    public Orientation(double size) {
        this.size = size;
    }

    public Orientation() {
        this(5.0);
    }

    @Override
    public void setOrigin(Vec3 origin) {}

    @Override
    public Vec3 getOrigin() {
        return new Vec3(0, 0, 0);
    }

    @Override
    public void setOrientation(Vec3 orientation) {}

    @Override
    public Vec3 getOrientation() {
        return new Vec3(0, 0, 0);
    }

    @Override
    public Vec3[] getVertexBuffer() {
        return new Vec3[] {
                new Vec3(0, 0, 0),
                new Vec3(size, 0, 0),
                new Vec3(0, size, 0),
                new Vec3(0, 0, size)
        };
    }

    @Override
    public int[] getIndexBuffer() {
        return new int[] {
                0, 0, 1,
                0, 0, 2,
                0, 0, 3
        };
    }
}
