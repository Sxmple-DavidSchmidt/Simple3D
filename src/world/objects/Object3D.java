package world.objects;

import util.Vec3;

public interface Object3D {
    double getSize();
    Vec3 getOrigin();
    Vec3 getOrientation();
    Triangle[] getTriangles();
}
