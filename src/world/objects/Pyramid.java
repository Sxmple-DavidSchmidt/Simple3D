package world.objects;

import util.Vec3;
import world.Triangle;

public class Pyramid extends Object3DAdapter {
    public Pyramid(Vec3 origin, Vec3 orientation, double size) {
        super(origin, orientation, size);
    }

    public Pyramid() {
        super();
    }

    @Override
    protected void buildTriangles() {
        Vec3 v0 = new Vec3(-1, -1, -1);
        Vec3 v1 = new Vec3(1, -1, -1);
        Vec3 v2 = new Vec3(-1, -1, 1);
        Vec3 v3 = new Vec3(1, -1, 1);
        Vec3 v4 = new Vec3(0, 1, 0);

        triangles = new Triangle[] {
                new Triangle(v0, v1, v2),
                new Triangle(v0, v1, v4),
                new Triangle(v0, v2, v4),
                new Triangle(v1, v2, v3),
                new Triangle(v1, v3, v4),
                new Triangle(v2, v3, v4)
        };
    }
}