package world;

import gui.Controller;
import util.Vec3;

public class Camera implements KeyboardInputListener, Updatetable {
    private boolean[] movementInputs;

    private Vec3 position;
    private Vec3 orientation;

    public Camera(Vec3 position, Vec3 orientation) {
        this.position = position;
        this.orientation = orientation;
        this.movementInputs = new boolean[] {
                false,  // FORWARD
                false,  // BACKWARD
                false,  // LEFT
                false}; // RIGHT
    }

    public Vec3 getPosition() {
        return new Vec3(
                Controller.CAMERA_POSITION_X,
                Controller.CAMERA_POSITION_Y,
                Controller.CAMERA_POSITION_Z
        );
        //return position;
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

    @Override
    public void handleKeyPress(int key) {
        System.out.println("METHOD CALLED");
        /*
        37 -- Left
        38 -- Up
        39 -- Right
        40 -- Down
         */

        if (key == 87 || key == 38) {
            this.movementInputs[0] = true;
        } else if (key == 83 || key == 40) {
            this.movementInputs[1] = true;
        } else if (key == 65 || key == 37) {
            this.movementInputs[2] = true;
        } else if (key == 68 || key == 39) {
            this.movementInputs[3] = true;
        }
    }

    @Override
    public void handleKeyRelease(int key) {
        if (key == 87 || key == 38) {
            this.movementInputs[0] = false;
        } else if (key == 83 || key == 40) {
            this.movementInputs[1] = false;
        } else if (key == 65 || key == 37) {
            this.movementInputs[2] = false;
        } else if (key == 68 || key == 39) {
            this.movementInputs[3] = false;
        }
    }


    @Override
    public void update(double delta) {
        double dx = movementInputs[2] ? 1 : 0;
        dx -= movementInputs[3] ? 1 : 0;

        double dz = movementInputs[0] ? 1 : 0;
        dz -= movementInputs[1] ? 1 : 0;

        Vec3 movement = new Vec3(
                dx,
                position.y,
                dz
        );

        this.position = this.position.add(movement.scale(delta));
    }
}
