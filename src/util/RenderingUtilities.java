package util;

import world.objects.Object3D;

public class RenderingUtilities {
    private double aspectRatio;
    private double zNear;
    private double cache_revTangent;
    private double cache_revZClipSize;

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
        // TODO: check vertex data

        // projection
        return new Vec4(
                vertex.x * aspectRatio * cache_revTangent,
                vertex.y * cache_revTangent,
                vertex.z * cache_revZClipSize - zNear * cache_revZClipSize, // (vertex.z - zNear) * revZClipSize?
                vertex.z
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
        this.aspectRatio = (double) width / (double) height;
    }

    public void updateFieldOfView(double theta_deg) {
        this.cache_revTangent = 1 / Math.tan(Math.toRadians(Math.toRadians(theta_deg) / 2.0));
    }

    public void updateZClipping(double zNear, double zFar) {
        this.zNear = zNear;
        this.cache_revZClipSize = zFar / (zFar - zNear);
    }
}