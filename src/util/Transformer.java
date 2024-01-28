package util;

import world.objects.Object3D;
import world.objects.Triangle;

public class Transformer {
    public static Triangle[] transformLocalSpace(Triangle[] triangles, Object3D object) {
        Vec3 orientation = object.getOrientation();
        Vec3 origin = object.getOrigin();

        Triangle[] rotatedWorldSpaceTriangles = rotate(triangles, orientation);
        Triangle[] localSpaceTriangles = new Triangle[triangles.length];
        for (int i = 0; i < rotatedWorldSpaceTriangles.length; i++) {
            Vec3[] vertices = rotatedWorldSpaceTriangles[i].getVertices();
            localSpaceTriangles[i] = new Triangle(
                    vertices[0].add(origin),
                    vertices[1].add(origin),
                    vertices[2].add(origin)
            );
        }

        return localSpaceTriangles;
    }

    public static Triangle[] rotate(Triangle[] triangles, Vec3 orientation) {
        double[] cos = new double[] {
                Math.cos(orientation.z * Math.PI),
                Math.cos(orientation.y * Math.PI),
                Math.cos(orientation.x * Math.PI)};
        double[] sin = new double[] {
                Math.sin(orientation.z * Math.PI),
                Math.sin(orientation.y * Math.PI),
                Math.sin(orientation.x * Math.PI)};

        Triangle[] localSpaceTriangles = new Triangle[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            Vec3[] vertices = triangles[i].getVertices();
            Vec3 v0 = rotateVertex(vertices[0], cos, sin);
            Vec3 v1 = rotateVertex(vertices[1], cos, sin);
            Vec3 v2 = rotateVertex(vertices[2], cos, sin);
            localSpaceTriangles[i] = new Triangle(v0, v1, v2);
        }

        return localSpaceTriangles;
    }

    private static Vec3 rotateVertex(Vec3 vertex, double[] cos, double[] sin) {
        double dx = vertex.x * (cos[0] * cos[1])
                + vertex.y * (cos[0] * sin[1] * sin[2] - sin[0] * cos[2])
                + vertex.z * (cos[0] * sin[1] * cos[2] + sin[0] * sin[2]);
        double dy = vertex.x * (sin[0] * cos[1])
                + vertex.y * (sin[0] * sin[1] * sin[2] + cos[0] * cos[2])
                + vertex.z * (sin[0] * sin[1] * cos[2] - cos[0] * sin[2]);
        double dz = vertex.x * (-sin[1])
                + vertex.y * (cos[1] * sin[2])
                + vertex.z * (cos[1] * cos[2]);
        return new Vec3(dx, dy, dz);
    }
}
