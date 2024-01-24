package world.objects;

import util.Vec3;

public class SillyPoint implements Object3D {
    private Vec3 origin;
    private Vec3 direction;
    private double magnitude;

    public SillyPoint(Vec3 origin, Vec3 direction, double magnitude) {
        this.origin = origin;
        this.direction = direction;
        this.magnitude = magnitude;
    }

    @Override
    public Vec3 getOrigin() {
        return origin;
    }

    @Override
    public Vec3[] getVertexBuffer() {
        int tick = (int) (System.currentTimeMillis() % 1000);
        double prog = (tick * Math.PI) / 1000.0;


        return new Vec3[] {
                origin,
                origin.add(direction.scale(Math.sin(prog)))
        };
    }

    @Override
    public int[] getIndexBuffer() {
        return new int[] {
                0, 1, 1
        };
    }
}
