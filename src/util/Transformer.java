package util;

public class Transformer {
    public Transformer() {

    }

    public static Vec3 transform(Vec3 coordinate, double height, double width, double omega, double zFar, double zNear) {
        if (coordinate.z == 0) return new Vec3(-1, -1, -1);

        double dx, dy, dz, dw;
        double fov = Math.toRadians(omega);

        // projection
        {
            // precompute
            double revTan = (1 / Math.tan(fov / 2.0));
            double farDivDif = (zFar / (zFar - zNear));

            // projection matrix
            double entry1_1 = (height / width) * revTan;
            double entry2_2 = revTan;
            double entry3_3 = farDivDif;
            double entry3_4 = -zNear * farDivDif;
            double entry4_3 = 1;

            // matrix multiplication
            dx = coordinate.x * entry1_1;
            dy = coordinate.y * entry2_2;
            dz = coordinate.z * entry3_3 + 1 * entry3_4;
            dw = coordinate.z * entry4_3;
        }

        // perspective divide
        {
            dx /= dw;
            dy /= dw;
            dz /= dw;
        }

        return new Vec3(dx, dy, dz);
    }

    public Vec4 projection(Vec3 coordinate, double height, double width, double theta, double zFar, double zNear) {
        double InvTanFov = (1 / Math.tan(Math.toRadians(theta / 2.0)));
        return new Vec4(
                coordinate.x * (width / height) * InvTanFov,
                coordinate.y * InvTanFov,
                coordinate.z * (zFar / (zFar - zNear)) - ((zFar * zNear) / (zFar - zNear)),
                coordinate.z
        );
    }

    public Vec3 perspectiveDivide(Vec4 coordinate) {
        return new Vec3(
                coordinate.x / coordinate.w,
                coordinate.y / coordinate.w,
                coordinate.z / coordinate.w
        );
    }
}
