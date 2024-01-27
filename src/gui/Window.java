package gui;

import util.Int2;
import util.RenderingUtilities;
import util.Vec3;
import util.Vec4;
import world.Camera;
import world.World;
import world.objects.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JComponent {
    private final World world;
    private final Color vertexColor;
    private final Color wireframeColor;
    private final Color triangleColor;
    private RenderingUtilities ru;

    public Window() {
        world = new World();
        // world.addObject(new Cube(new Vec3(0, 0, 0), 5));
        // world.addObject(new Pyramid(new Vec3(0, 0, 0), 9));
        world.addObject(new Orientation(10));

        vertexColor = Color.RED;
        wireframeColor = Color.BLACK;
        triangleColor = new Color(0, 0, 0, 0.05f);
    }

    public Vec3[] runRenderingPipeline(Vec3[] worldSpaceVertexBuffer, Camera camera) {
        Vec3[] cameraSpaceVertexBuffer = ru.transformToCameraSpace(worldSpaceVertexBuffer, camera);
        Vec4[] projectedVertexBuffer = ru.applyProjection(cameraSpaceVertexBuffer);
        return ru.applyPerspectiveDivide(projectedVertexBuffer);
    }

    public Int2[] transformToScreenCoordinate(Vec3[] vertexBuffer) {
        Int2[] screenCoordinateVertexBuffer = new Int2[vertexBuffer.length];
        double wf = getWidth() / 2.0;
        double hf = getHeight() / 2.0;
        for (int i = 0; i < vertexBuffer.length; i++) {
            screenCoordinateVertexBuffer[i] = new Int2(
                    (int) (wf + vertexBuffer[i].x * wf),
                    (int) (hf + vertexBuffer[i].y * hf)
            );
        }

        return screenCoordinateVertexBuffer;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        ru = new RenderingUtilities(getWidth(), getHeight(), 45, 1, 1200);
        for (Object3D object: world.getObjects()) {
            int[] indexBuffer = object.getIndexBuffer();
            Int2[] vertexBuffer = transformToScreenCoordinate(
                    runRenderingPipeline(object.getVertexBuffer(), world.getCamera())
            );

            for (int i = 0; i < indexBuffer.length; i+= 3) {
                Int2 v1 = vertexBuffer[indexBuffer[i]];
                Int2 v2 = vertexBuffer[indexBuffer[i + 1]];
                Int2 v3 = vertexBuffer[indexBuffer[i + 2]];

                g.setColor(triangleColor);
                g.fillPolygon(
                        new int[] {v1.x, v2.x, v3.x},
                        new int[] {v1.y, v2.y, v3.y},
                        3
                );

                g.setColor(wireframeColor);
                g.drawLine(v1.x, v1.y, v2.x, v2.y);
                g.drawLine(v2.x, v2.y, v3.x, v3.y);
                g.drawLine(v3.x, v3.y, v1.x, v1.y);
            }
        }
    }

    public void debugVertices() {
        System.out.println("Debugging vertices");
        System.out.println("##################");
        for (Object3D object: world.getObjects()) {
            Vec3[] worldSpaceVertexBuffer = object.getVertexBuffer();
            Vec3[] cameraSpaceVertexBuffer = ru.transformToCameraSpace(worldSpaceVertexBuffer, world.getCamera());
            Vec4[] projectedVertexBuffer = ru.applyProjection(cameraSpaceVertexBuffer);
            Vec3[] normalizedDeviceCoordinates = ru.applyPerspectiveDivide(projectedVertexBuffer);

            for (int i = 0; i < 2; i++) {
                System.out.println("Vertex at index " + i);
                System.out.println("world-space:" + worldSpaceVertexBuffer[i]);
                System.out.println("camera-space:" + cameraSpaceVertexBuffer[i]);
                System.out.println("projection-space:" + projectedVertexBuffer[i]);
                System.out.println("normalized-space:" + normalizedDeviceCoordinates[i]);
            }
        }
    }
}
