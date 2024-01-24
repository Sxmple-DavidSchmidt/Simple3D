package util;

import world.objects.Object3D;

public class Transformer {
    public static Vec3 transformLocalSpace(Object3D object, Vec3 vec) {
        Vec3 orientation = object.getOrientation();
        Vec3 origin = object.getOrigin();

        Vec3 dVec = vec;
        dVec = Transformer.rotateOnX(dVec, orientation.x);
        dVec = Transformer.rotateOnY(dVec, orientation.y);
        dVec = Transformer.rotateOnZ(dVec, orientation.z);
        return dVec.add(origin);
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
