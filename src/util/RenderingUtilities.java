package util;

import world.Camera;

public class RenderingUtilities {
    private double aspectRatio;
    private double zNear;
    private double zFar;
    private double theta;

    public RenderingUtilities(int width, int height, double theta_deg, double zNear, double zFar) {
        this.updateValues(width, height, theta_deg, zFar, zNear);
    }

    public Vec3[] transformToCameraSpace(Vec3[] vertices, Camera camera) {
        Vec3[] rotatedWorldSpaceVertexBuffer = Transformer.rotate(vertices, camera.getOrientation());
        Vec3[] cameraSpaceVertexBuffer = new Vec3[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            cameraSpaceVertexBuffer[i] = rotatedWorldSpaceVertexBuffer[i].subtract(camera.getPosition());
        }

        return cameraSpaceVertexBuffer;
    }

    public Vec4[] applyProjection(Vec3[] vertexBuffer) {
        /*
        [2n/(2r), 0, 0, 0]                  [x] = [x * e_1_1 + z * e_1_3]
        [0, 2n/(2t)), 0, 0]                 [y] = [y * e_2_2 + z * e_2_3)]
        [0, 0, -(f+n)/(f-n), -2fn/(f-n)]    [z] = [z * e_3_3 + w * e_3_4]
        [0, 0, -1, 0]                       [1] = [z * e_4_3]
         */

        double rt = Math.tan(theta / 2.0) * zNear;

        double xf = (2 * zNear) / (2 * rt) * aspectRatio;
        double yf = (2 * zNear) / (2 * rt);
        double zf = -(zFar + zNear) / (zFar - zNear) + -2 * ((zFar * zNear) / (zFar - zNear));
        double wf = -1;

        Vec4[] projectedVertexBuffer = new Vec4[vertexBuffer.length];
        for (int i = 0; i < vertexBuffer.length; i++) {
            Vec3 vertex = vertexBuffer[i];
            projectedVertexBuffer[i] = new Vec4(
                    vertex.x * xf,
                    vertex.y * yf,
                    vertex.z * zf,
                    vertex.z * wf
            );
        }

        return projectedVertexBuffer;
    }

    public Vec3[] applyPerspectiveDivide(Vec4[] vertexBuffer) {
        Vec3[] divideVertexBuffer = new Vec3[vertexBuffer.length];
        for (int i = 0; i < vertexBuffer.length; i++) {
            Vec4 vertex = vertexBuffer[i];
            divideVertexBuffer[i] = new Vec3(
                    vertex.x / vertex.w,
                    vertex.y / vertex.w,
                    vertex.z / vertex.w
            );
        }

        return divideVertexBuffer;
    }

    public void updateValues(int width, int height, double theta_deg, double zNear, double zFar) {
        updateAspectRatio(width, height);
        updateFieldOfView(theta_deg);
        updateZClipping(zNear, zFar);
    }

    public void updateAspectRatio(int width, int height) {
        this.aspectRatio = (double) height / (double) width;
    }

    public void updateFieldOfView(double theta_deg) {
        this.theta = Math.toRadians(theta_deg);
    }

    public void updateZClipping(double zNear, double zFar) {
        this.zNear = zNear;
        this.zFar = zFar;
    }
}
