package world;

import util.Vec3;
import world.objects.Camera;
import world.objects.Object3D;

import java.util.ArrayList;

public class World {
    private final ArrayList<Object3D> objects;
    private final Camera camera;

    public World() {
        objects = new ArrayList<>();
        camera = new Camera(new Vec3(0, 0, 20), new Vec3(0, 0, 0));
    }

    public void addObject(Object3D object) {
        objects.add(object);
    }

    public Object3D[] getObjects() {
        return objects.toArray(new Object3D[0]);
    }

    public Camera getCamera() {
        return camera;
    }
}
