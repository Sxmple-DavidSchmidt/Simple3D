package util;

import world.objects.Object3D;

public class Transformer {
    public static Vec3[] transformLocalSpace(Vec3[] vertexBuffer, Object3D object) {
        Vec3 orientation = object.getOrientation();
        Vec3 origin = object.getOrigin();

        Vec3[] rotatedWorldSpaceVertexBuffer = rotate(vertexBuffer, orientation);
        Vec3[] localSpaceVertexBuffer = new Vec3[vertexBuffer.length];
        for (int i = 0; i < rotatedWorldSpaceVertexBuffer.length; i++) {
            Vec3 vertex = rotatedWorldSpaceVertexBuffer[i];
            vertex = vertex.add(origin);
            localSpaceVertexBuffer[i] = vertex;
        }

        return localSpaceVertexBuffer;
    }

    public static Vec3[] rotate(Vec3[] vertexBuffer, Vec3 orientation) {
        double cos_a = Math.cos(orientation.z * Math.PI);
        double cos_b = Math.cos(orientation.y * Math.PI);
        double cos_g = Math.cos(orientation.x * Math.PI);
        double sin_a = Math.sin(orientation.z * Math.PI);
        double sin_b = Math.sin(orientation.y * Math.PI);
        double sin_g = Math.sin(orientation.x * Math.PI);

        Vec3[] localSpaceVertexBuffer = new Vec3[vertexBuffer.length];
        for (int i = 0; i < vertexBuffer.length; i++) {
            Vec3 vertex = vertexBuffer[i];
            double dx = vertex.x * (cos_a * cos_b)
                    + vertex.y * (cos_a * sin_b * sin_g - sin_a * cos_g)
                    + vertex.z * (cos_a * sin_b * cos_g + sin_a * sin_g);
            double dy = vertex.x * (sin_a * cos_b)
                    + vertex.y * (sin_a * sin_b * sin_g + cos_a * cos_g)
                    + vertex.z * (sin_a * sin_b * cos_g - cos_a * sin_g);
            double dz = vertex.x * (-sin_b)
                    + vertex.y * (cos_b * sin_g)
                    + vertex.z * (cos_b * cos_g);

            localSpaceVertexBuffer[i] = new Vec3(dx, dy, dz);
        }

        return localSpaceVertexBuffer;
    }

    public static Vec3 rotateOnX(Vec3 vec, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        return new Vec3(
                vec.x,
                vec.y * cos - vec.z * sin,
                vec.y * sin + vec.z * cos
        );
    }

    public static Vec3 rotateOnY(Vec3 vec, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        return new Vec3(
                vec.x * cos + vec.z * sin,
                vec.y,
                vec.x * -sin + vec.z * cos
        );
    }

    public static Vec3 rotateOnZ(Vec3 vec, double theta) {
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);

        return new Vec3(
                vec.x * cos - vec.y * sin,
                vec.x * sin + vec.y * cos,
                vec.z
        );
    }
}
