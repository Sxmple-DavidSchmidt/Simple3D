package world.objects;

import util.Vec3;
import world.Triangle;

import java.util.ArrayList;

public class Orientation extends Object3DAdapter {
    @Override
    protected void buildTriangles() {
        ArrayList<Triangle> triangleArrayList = new ArrayList<>();

        int increment = 5;
        int size = 300;
        for (int x = -size; x < size; x += increment) {
            for (int z = -size; z < size; z += increment) {
                Vec3 c1 = new Vec3(x, 0, z);
                Vec3 c2 = new Vec3(x, 0, z+increment);
                Vec3 c3 = new Vec3(x+increment, 0, z);
                Vec3 c4 = new Vec3(x+increment, 0, z+increment);

                triangleArrayList.add(new Triangle(c1, c2, c3));
                triangleArrayList.add(new Triangle(c2, c3, c4));
            }
        }

        triangles = triangleArrayList.toArray(new Triangle[0]);
    }
}
