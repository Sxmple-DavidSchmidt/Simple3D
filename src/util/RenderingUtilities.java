package util;

import world.objects.Object3D;

public class RenderingUtilities {
    private double aspectRatio;
    private double zNear;
    private double zFar;
    private double theta;

    public RenderingUtilities(int width, int height, double theta_deg, double zNear, double zFar) {
        this.updateValues(width, height, theta_deg, zFar, zNear);
    }

    /***
     * Method to transform vertices relative to 3D-object
     * to vertices that are absolute in world-space.
     * @param object - 3D-object
     * @return Array of transformed vertices
     */
    public Vec3[] transformToWorldSpace(Object3D object) {
        return null;
    }

    /***
     * Method to transform vertices in world-space such that
     * the world camera is facing forward from (0, 0, 0).
     * Camera object has yet to be implemented
     * @param vertices - Array of world-space vertices
     * @return Array of transformed vertices
     */
    public Vec3[] transformToCameraSpace(Vec3[] vertices) {
        return null;
    }

    /***
     * Method to apply perspective projection
     * @param vertex - 3D Coordinate in world-space
     * @return projected 4D coordinate
     */
    public Vec4 applyProjection(Vec3 vertex) {
        /*
        [2n/(2r), 0, 0, 0]                  [x] = [x * e_1_1 + z * e_1_3]
        [0, 2n/(2t)), 0, 0]                 [y] = [y * e_2_2 + z * e_2_3)]
        [0, 0, -(f+n)/(f-n), -2fn/(f-n)]    [z] = [z * e_3_3 + w * e_3_4]
        [0, 0, -1, 0]                       [1] = [z * e_4_3]
         */

        double rt = Math.tan(theta / 2.0) * zNear;

        // projection
        return new Vec4(
                vertex.x * ((2 * zNear) / (2 * rt)) * aspectRatio,
                vertex.y * ((2 * zNear) / (2 * rt)),
                vertex.z * -(zFar + zNear) / (zFar - zNear) + -2 * ((zFar * zNear) / (zFar - zNear)),
                vertex.z * -1
        );
    }

    /***
     * Method to apply perspective divide
     * @param vertex - 4D-coordinate coming from method 'applyProjection'
     * @return 3D-coordinate with applied perspective divide
     */
    public Vec3 applyPerspectiveDivide(Vec4 vertex) {
        return new Vec3(
                vertex.x / vertex.w,
                vertex.y / vertex.w,
                vertex.z / vertex.w
        );
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
