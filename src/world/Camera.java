package world;

import gui.Controller;
import util.Vec3;

public class Camera {
    private Vec3 position;
    private Vec3 orientation;

    public Camera(Vec3 position, Vec3 orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public Vec3 getPosition() {
        return new Vec3(
                Controller.CAMERA_POSITION_X,
                Controller.CAMERA_POSITION_Y,
                Controller.CAMERA_POSITION_Z
        );
    }

    public void setPosition(Vec3 position) {
        this.position = position;
    }

    public Vec3 getOrientation() {
        return new Vec3(
                Controller.CAMERA_ORIENTATION_X,
                Controller.CAMERA_ORIENTATION_Y,
                Controller.CAMERA_ORIENTATION_Z
        );
    }

    public void setOrientation(Vec3 orientation) {
        this.orientation = orientation;
    }
}
