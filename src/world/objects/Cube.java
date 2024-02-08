package world.objects;

import util.Vec3;
import world.Triangle;

public class Cube extends Object3DAdapter {
    public Cube(Vec3 origin, Vec3 orientation, double size) {
        super(origin, orientation, size);
    }

    public Cube() {
        super();
    }

    @Override
    protected void buildTriangles() {
        Vec3 v0 = new Vec3(-1, -1, -1);  // (-1 -1 -1)
        Vec3 v1 = new Vec3(1, -1, -1);   // (+1 -1 -1)
        Vec3 v2 = new Vec3(-1, 1, -1);   // (-1 +1 -1)
        Vec3 v3 = new Vec3(1, 1, -1);    // (+1 +1 -1)
        Vec3 v4 = new Vec3(-1, -1, 1);   // (-1 -1 +1)
        Vec3 v5 = new Vec3(1, -1, 1);    // (+1 -1 +1)
        Vec3 v6 = new Vec3(-1, 1, 1);    // (-1 +1 +1)
        Vec3 v7 = new Vec3(1, 1, 1);     // (+1 +1 +1)

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
}
