package world.objects;

import util.Vec3;

public class Triangle implements Object3D {
    Vec3[] vertices;

    public Triangle(Vec3[] vertices) {
        if (vertices.length != 3)
            throw new RuntimeException("Tried instantiating triangle with more than 3 vertices");
        this.vertices = vertices;
    }

    public Triangle(Vec3 v1, Vec3 v2, Vec3 v3) {
        this(new Vec3[] {v1, v2, v3});
    }

    public Vec3[] getVertices() {
        return vertices;
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
    public Triangle[] getTriangles() {
        return new Triangle[] {this};
    }
}

