package world;

import world.objects.Object3D;

import java.util.ArrayList;

public class World {
    private ArrayList<Object3D> objects;

    public World() {
        objects = new ArrayList<>();
    }

    public void addObject(Object3D object) {
        objects.add(object);
    }

    public Object3D[] getObjects() {
        return objects.toArray(new Object3D[0]);
    }
}
