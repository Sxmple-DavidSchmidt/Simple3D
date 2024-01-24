package util;

public class Transformer {
    public static Vec3 rotateOnX(Vec3 vec, double theta) {
        return new Vec3(
                vec.x,
                vec.y * Math.cos(theta) - vec.z * Math.sin(theta), // y * cos + z * -sin
                vec.y * Math.sin(theta) + vec.z * Math.cos(theta)
        );
    }
}
