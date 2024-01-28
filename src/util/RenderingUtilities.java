package util;

import world.Camera;
import world.objects.Triangle;

import java.util.ArrayList;

public class RenderingUtilities {
    // private double[] precomputed_values; // TODO: optimize
    private double aspectRatio;
    private double zNear;
    private double zFar;
    private double theta;

    public RenderingUtilities(int width, int height, double theta_deg, double zNear, double zFar) {
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

        System.out.println(triangles.toArray(new Triangle[0]).length);
        return triangles.toArray(new Triangle[0]);
    }

    public Vec4[] applyProjection(Vec3[] vertexBuffer) {
        /*
        [2n/(2r), 0, 0, 0]                  [x] = [x * e_1_1 + z * e_1_3]
        [0, 2n/(2t)), 0, 0]                 [y] = [y * e_2_2 + z * e_2_3)]
        [0, 0, -(f+n)/(f-n), -2fn/(f-n)]    [z] = [z * e_3_3 + w * e_3_4]
        [0, 0, -1, 0]                       [1] = [z * e_4_3]

        TODO: optimize
        precomputed_values = [
            0: Math.tan(theta / 2.0) * zNear
            1: (2 * zNear) / (2 * rt) * aspectRatio
            2: (2 * zNear) / (2 * rt)
            3: -(zFar + zNear) / (zFar - zNear) + -2 * ((zFar * zNear) / (zFar - zNear))
            4: -1
        ]*/

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

    public Vec4[][] applyClipping(Vec4[] vertexBuffer) {
        ArrayList<Vec4[]> triangles = new ArrayList<>(1);

        Vec4[] clippedVertices = new Vec4[vertexBuffer.length];
        for (int i = 0; i < vertexBuffer.length; i++) {
            boolean doClip = false;
            Vec4 vertex = vertexBuffer[i];
            if (vertex.w < zNear) doClip = true;
            if (Math.abs(vertex.x) > vertex.w) doClip = true;
            if (Math.abs(vertex.y) > vertex.w) doClip = true;

            if(doClip)
                clippedVertices[i] = new Vec4(0, 0, 0, 1); // TODO: replace
            else
                clippedVertices[i] = vertex;
        }

        triangles.add(clippedVertices);
        return triangles.toArray(new Vec4[0][3]);
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
