package gui;

import util.RenderingUtilities;
import util.Vec3;
import util.Vec4;
import world.World;
import world.objects.Cube;
import world.objects.Object3D;

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
        world.addObject(new Cube(new Vec3(0, 0, 20), 10));

        vertexColor = Color.RED;
        wireframeColor = Color.BLACK;
        triangleColor = new Color(0, 0, 0, 0.05f);
    }

    public Vec3[] runRenderingPipeline(Vec3[] worldSpaceVertexBuffer) {
        Vec3[] vertexBuffer = new Vec3[worldSpaceVertexBuffer.length];
        for (int i = 0; i < worldSpaceVertexBuffer.length; i++) {
                Vec4 temp4 = ru.applyProjection(worldSpaceVertexBuffer[i]);
                Vec3 temp3 = ru.applyPerspectiveDivide(temp4);
                vertexBuffer[i] = temp3;
        }

        return vertexBuffer;
    }

    @Override
    public void paintComponent(Graphics g) {
        ru = new RenderingUtilities(getWidth(), getHeight(), 45, 0, 1200);

        Vec3 centerOffset = new Vec3(getWidth() / 2.0, getHeight() / 2.0, 0);
        for (Object3D object: world.getObjects()) {
            Vec3[] vertexBuffer = runRenderingPipeline(object.getVertexBuffer());
            int[] indexBuffer = object.getIndexBuffer();

            for (int i = 0; i < indexBuffer.length; i+= 3) {
                Vec3 v1 = vertexBuffer[indexBuffer[i]].add(centerOffset);
                Vec3 v2 = vertexBuffer[indexBuffer[i + 1]].add(centerOffset);
                Vec3 v3 = vertexBuffer[indexBuffer[i + 2]].add(centerOffset);

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

            g.setColor(vertexColor);
            for (int i = 0; i < vertexBuffer.length; i++) {
                Vec3 v = vertexBuffer[i].add(centerOffset);
                g.fillOval((int) (v.x - 2), (int) (v.y - 2), 4, 4);
                g.drawString(String.valueOf(i), (int) v.x, (int) v.y - 10);
            }
        }

        g.drawString(String.valueOf((System.currentTimeMillis() % 10000.0) / 10000), (int) centerOffset.x, 10);
    }
}
