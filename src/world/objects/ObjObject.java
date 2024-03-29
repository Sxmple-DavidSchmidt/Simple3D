package world.objects;

import util.ObjUtilities;
import util.Transformer;
import util.Vec3;
import world.Triangle;

import java.io.InputStream;

public class ObjObject implements Object3D {
    private final Triangle[] triangles;
    private final Vec3 origin;
    private final Vec3 orientation;
    private double size;

    public ObjObject(InputStream objInputStream, Vec3 origin, Vec3 orientation, double size) {
        this.origin = origin;
        this.orientation = orientation;
        this.size = size;
        this.triangles = ObjUtilities.triangles(objInputStream);
    }

    public ObjObject(InputStream objInputStream) {
        this(objInputStream, new Vec3(0, 0, 0), new Vec3(0,0 ,0), 1);
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
