package util;

import world.Camera;
import world.objects.Triangle;

import java.util.ArrayList;

public class RenderingUtilities {
    // indices:
    // 0: Math.tan(theta / 2.0) * zNear
    // 1: (2 * zNear) / (2 * [0]) * aspectRatio
    // 2: (2 * zNear) / (2 * [0])
    // 3: -(zFar + zNear) / (zFar - zNear) + -2 * ((zFar * zNear) / (zFar - zNear))
    // 4: -1
    private double[] precomputed_values;
    private double aspectRatio;
    private double zNear;
    private double zFar;
    private double theta;

    public RenderingUtilities(int width, int height, double theta_deg, double zNear, double zFar) {
        precomputed_values = new double[4];
        precomputed_values[3] = -1;
        this.updateValues(width, height, theta_deg, zNear, zFar);
    }

    public Triangle[] transformToCameraSpace(Triangle[] triangles, Camera camera) {
        Triangle[] positionTransformedTriangles = new Triangle[triangles.length];
        for (int i = 0; i < triangles.length; i++) {
            Vec3[] vertices = triangles[i].getVertices();
            positionTransformedTriangles[i] = new Triangle(
                    vertices[0].subtract(camera.getPosition()),
                    vertices[1].subtract(camera.getPosition()),
                    vertices[2].subtract(camera.getPosition())
            );
        }

        return Transformer.rotate(positionTransformedTriangles, camera.getOrientation());
    }

    public Triangle[] runTransformationPipeline(Triangle[] worldSpaceTriangles, Camera camera) {
        ArrayList<Triangle> triangles = new ArrayList<>();

        Triangle[] cameraSpaceTriangles = transformToCameraSpace(worldSpaceTriangles, camera);
        for (Triangle cameraSpaceTriangle : cameraSpaceTriangles) {
            Vec3[] cameraSpaceVertices = cameraSpaceTriangle.getVertices();
            Vec4[] projectedVertices = applyProjection(cameraSpaceVertices);
            Vec4[][] clippedTriangleVertices = applyClipping(projectedVertices);

            for (Vec4[] clippedTriangleVertex : clippedTriangleVertices) {
                Vec3[] vertices = applyPerspectiveDivide(clippedTriangleVertex);
                triangles.add(new Triangle(vertices[0], vertices[1], vertices[2]));
            }
        }

        return triangles.toArray(new Triangle[0]);
    }

    public Vec4[] applyProjection(Vec3[] vertexBuffer) {
        /*
        Unoptimized:
        double rt = Math.tan(theta / 2.0) * zNear;
        double xf = (2 * zNear) / (2 * rt) * aspectRatio;
        double yf = (2 * zNear) / (2 * rt);
        double zf = -(zFar + zNear) / (zFar - zNear) + -2 * ((zFar * zNear) / (zFar - zNear));
        double wf = -1;
        */

        Vec4[] projectedVertexBuffer = new Vec4[vertexBuffer.length];
        for (int i = 0; i < vertexBuffer.length; i++) {
            Vec3 vertex = vertexBuffer[i];
            projectedVertexBuffer[i] = new Vec4(
                    vertex.x * precomputed_values[0],
                    vertex.y * precomputed_values[1],
                    vertex.z * precomputed_values[2],
                    vertex.z * precomputed_values[3]
            );
        }

        return projectedVertexBuffer;
    }

    public Vec4[][] applyClipping(Vec4[] vertexBuffer) {
        // TODO: Implement substitution triangles
        if (getClipCode(vertexBuffer[0]) + getClipCode(vertexBuffer[1]) + getClipCode(vertexBuffer[2]) != 0)
            return new Vec4[0][0];
        return new Vec4[][] {vertexBuffer};
    }

    private int getClipCode(Vec4 v) {
        int clipCode = 0;
        if (-v.x > v.w) clipCode += 1;
        if (v.x > v.w) clipCode += 2;
        if (-v.y > v.w) clipCode += 4;
        if (v.y > v.w) clipCode += 8;
        return clipCode;
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

        // Update precomputed values
        double tmp = Math.tan(theta / 2.0) * zNear;
        precomputed_values[0] = (2 * zNear) / (2 * tmp) * aspectRatio;
        precomputed_values[1] = (2 * zNear) / (2 * tmp);
    }

    public void updateZClipping(double zNear, double zFar) {
        this.zNear = zNear;
        this.zFar = zFar;

        // Update precomputed values
        double tmp = Math.tan(theta / 2.0) * zNear;
        precomputed_values[0] = (2 * zNear) / (2 * tmp) * aspectRatio;
        precomputed_values[1] = (2 * zNear) / (2 * tmp);
        precomputed_values[2] = -(zFar + zNear) / (zFar - zNear) + -2 * ((zFar * zNear) / (zFar - zNear));
    }
}
