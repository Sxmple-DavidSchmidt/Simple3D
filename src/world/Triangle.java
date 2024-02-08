package world;

import util.Vec3;

public class Triangle {
    Vec3[] vertices;

    public Triangle(Vec3 v1, Vec3 v2, Vec3 v3) {
        this.vertices = new Vec3[] {v1, v2, v3};
    }

    public Vec3[] getVertices() {
        return vertices;
    }
}

