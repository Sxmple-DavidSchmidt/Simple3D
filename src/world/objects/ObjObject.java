package world.objects;

import util.ObjUtilities;
import util.Transformer;
import util.Vec3;

import java.nio.file.Path;

public class ObjObject implements Object3D {
    private final Triangle[] triangles;
    private final Vec3 origin;
    private final Vec3 orientation;

    public ObjObject(String objPath, Vec3 origin, Vec3 orientation) {
        this.triangles = ObjUtilities.triangles(objPath);
        this.origin = origin;
        this.orientation = orientation;
    }

    public ObjObject(String objPath) {
        this(objPath, new Vec3(0, 0, 0), new Vec3(0, 0, 0));
    }

    @Override
    public double getSize() {
        return 20;
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
