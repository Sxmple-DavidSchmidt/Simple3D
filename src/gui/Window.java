package gui;

import util.RenderingUtilities;
import util.Vec3;
import util.Vec4;
import world.World;
import world.objects.Cube;
import world.objects.Object3D;
import world.objects.SillyPoint;

import javax.swing.*;
import java.awt.*;

public class Window extends JComponent {
    private Vec3[] vertices;
    private Object3D o;

    public void runRenderingPipeline(World world) {
        RenderingUtilities ru = new RenderingUtilities(getWidth(), getHeight(), 45, 0, 1200);
        o = new Cube(new Vec3(0, 0, 350), 500);
        o = new SillyPoint(new Vec3(0.25, 0.25, 0.5), new Vec3(0.0, 0.0, 1), 10);

        Vec3[] cvb = o.getVertexBuffer();
        vertices = new Vec3[cvb.length];
        System.out.println("TRANSFORMING");
        for (int i = 0; i < cvb.length; i++) {
            System.out.println(cvb[i]);
            Vec4 temp4 = ru.applyProjection(cvb[i]);
            Vec3 temp3 = ru.applyPerspectiveDivide(temp4);
            vertices[i] = temp3;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        System.out.println("Rendering!");
        runRenderingPipeline(null);
        Vec3 centerOffset = new Vec3(getWidth() / 2.0, getHeight() / 2.0, 0);

        Vec3[] vertexBuffer = o.getVertexBuffer();
        int[] indexBuffer = o.getIndexBuffer();
        for (int i = 0; i < o.getIndexBuffer().length / 3; i++) {
            // TODO: i am here
            Vec3 v1 = vertexBuffer[indexBuffer[i+0]].add(centerOffset);
            Vec3 v2 = vertexBuffer[indexBuffer[i+1]].add(centerOffset);
            Vec3 v3 = vertexBuffer[indexBuffer[i+2]].add(centerOffset);
            g.drawPolygon(
                    new int[] {(int) v1.x, (int) v2.x, (int) v3.x},
                    new int[] {(int) v1.y, (int) v2.y, (int) v3.y},
                    3
            );

            g.drawOval((int) (v1.x - 3), (int) (v1.y - 3), 6 ,6);
            g.drawOval((int) (v2.x - 3), (int) (v2.y - 3), 6 ,6 );
            g.drawOval((int) (v3.x - 3), (int) (v3.y - 3), 6 ,6 );
        }
    }
}
