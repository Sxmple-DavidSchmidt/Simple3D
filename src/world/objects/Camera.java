package world.objects;

import util.Vec3;

public class Camera {

    private Vec3 position;
    private Vec3 orientation;

    public Camera(Vec3 position, Vec3 orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Vec3 getPosition() {
        return position;
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public Vec3 getOrientation() {
        return orientation;
    }

    public void setOrientation(Vec3 orientation) {
        this.orientation = orientation;
    }
}
