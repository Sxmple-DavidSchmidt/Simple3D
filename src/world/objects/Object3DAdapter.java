package world.objects;

import util.Transformer;
import util.Vec3;
import world.Triangle;

public class Object3DAdapter implements Object3D {
    protected Triangle[] triangles;
    protected Vec3 origin;
    protected Vec3 orientation;
    protected double size;

    public Object3DAdapter(Vec3 origin, Vec3 orientation, double size) {
        this.origin = origin;
        this.orientation = orientation;
        this.size = size;

        buildTriangles();
    }

    public Object3DAdapter() {
        this(new Vec3(0, 0, 0), new Vec3(0, 0, 0), 1);
    }

    protected void buildTriangles() {
        triangles = new Triangle[0];
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public Vec3 getOrigin() {
        return origin;
    }

    @Override
    public Vec3 getOrientation() {
        return orientation;
    }

    @Override
    public Triangle[] getTriangles() {
        return Transformer.transformLocalSpace(triangles, this);
    }
}
