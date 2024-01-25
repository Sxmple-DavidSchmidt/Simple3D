package gui;

import util.RenderingUtilities;
import util.Vec3;
import util.Vec4;
import world.Camera;
import world.World;
import world.objects.Cube;
import world.objects.Object3D;
import world.objects.Pyramid;

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
        world.addObject(new Cube(new Vec3(0, 0, 0), 10));
        world.addObject(new Pyramid(new Vec3(0, 0, 0), 9));

        vertexColor = Color.RED;
        wireframeColor = Color.BLACK;
        triangleColor = new Color(0, 0, 0, 0.05f);
    }

    public Vec3[] runRenderingPipeline(Vec3[] worldSpaceVertexBuffer, Camera camera) {
        Vec3[] cameraSpaceVertexBuffer = ru.transformToCameraSpace(worldSpaceVertexBuffer, camera);
        Vec4[] projectedVertexBuffer = ru.applyProjection(cameraSpaceVertexBuffer);
        return ru.applyPerspectiveDivide(projectedVertexBuffer);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        ru = new RenderingUtilities(getWidth(), getHeight(), 45, 1, 1200);
        for (Object3D object: world.getObjects()) {
            Vec3[] vertexBuffer = runRenderingPipeline(object.getVertexBuffer(), world.getCamera());
            int[] indexBuffer = object.getIndexBuffer();

            g.setColor(vertexColor);
            for (Vec3 v : vertexBuffer) {
                Vec3 vc = new Vec3(v.x, v.y, v.z);
                v.x = v.x * getWidth() / 2.0 + getWidth() / 2.0;
                v.y = v.y * getHeight() / 2.0 + getHeight() / 2.0;

                g.fillOval((int) (v.x - 2), (int) (v.y - 2), 4, 4);
                g.drawString(vc.toString(), (int) v.x, (int) v.y - 10);
            }

            for (int i = 0; i < indexBuffer.length; i+= 3) {
                Vec3 v1 = vertexBuffer[indexBuffer[i]];
                Vec3 v2 = vertexBuffer[indexBuffer[i + 1]];
                Vec3 v3 = vertexBuffer[indexBuffer[i + 2]];

                g.setColor(triangleColor);
                g.fillPolygon(
                        new int[] {(int) v1.x, (int) v2.x, (int) v3.x},
                        new int[] {(int) v1.y, (int) v2.y, (int) v3.y},
                        3
                );

                g.setColor(wireframeColor);
                g.drawLine((int) v1.x, (int) v1.y, (int) v2.x, (int) v2.y);
                g.drawLine((int) v2.x, (int) v2.y, (int) v3.x, (int) v3.y);
                g.drawLine((int) v3.x, (int) v3.y, (int) v1.x, (int) v1.y);
            }
        }
    }
}
