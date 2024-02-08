package world;

import util.Vec3;

public class Camera {

    private final Vec3 origin;
    private final Vec3 orientation;

    public Camera(Vec3 origin, Vec3 orientation) {
        this.origin = origin;
        this.orientation = orientation;
    }

    public Vec3 getPosition() {
        return origin;
    }

    public Vec3 getOrientation() {
        return orientation;
    }
}
