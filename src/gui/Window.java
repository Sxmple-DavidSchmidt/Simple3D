package gui;

import util.Int2;
import util.RenderingUtilities;
import util.Vec3;
import world.Triangle;
import world.World;
import world.objects.*;

import javax.swing.*;
import java.awt.*;

public class Window extends JComponent {
    private final World world;

    private final Color wireframeColor;
    private final Color triangleColor;

    public Window() {
        world = Controller.world;

        wireframeColor = Color.BLACK;
        triangleColor = new Color(0.078f, 0.875f, 0.836f, 0.25f);
    }

    public Int2[] transformToScreenCoordinate(Vec3[] vertexBuffer) {
        Int2[] screenCoordinateVertexBuffer = new Int2[vertexBuffer.length];
        double wf = getWidth() / 2.0;
        double hf = getHeight() / 2.0;
        for (int i = 0; i < vertexBuffer.length; i++) {
            screenCoordinateVertexBuffer[i] = new Int2(
                    (int) ((vertexBuffer[i].x + 1) * wf),
                    (int) ((1 - vertexBuffer[i].y) * hf)
            );
        }

        return screenCoordinateVertexBuffer;
    }

    private void renderObject(Graphics g, Object3D object, RenderingUtilities ru, Color fillColor) {
        Triangle[] triangles = ru.runTransformationPipeline(object.getTriangles(), world.getCamera());
        for (Triangle triangle: triangles) {
            Int2[] screenCoordinates = transformToScreenCoordinate(triangle.getVertices());
            Int2 v1 = screenCoordinates[0];
            Int2 v2 = screenCoordinates[1];
            Int2 v3 = screenCoordinates[2];

            g.setColor(fillColor);
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
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        RenderingUtilities ru = new RenderingUtilities(getWidth(), getHeight(), 45, 1, 100);

        Triangle[] triangles = ru.runTransformationPipeline(new Orientation().getTriangles(), world.getCamera());
        for (Triangle triangle: triangles) {
            Int2[] screenCoordinates = transformToScreenCoordinate(triangle.getVertices());
            Int2 v1 = screenCoordinates[0];
            Int2 v2 = screenCoordinates[1];
            Int2 v3 = screenCoordinates[2];

            g.setColor(new Color(0f, 0f, 0f, 0.01f));
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

        for (Object3D object: world.getObjects())
            renderObject(g, object, ru, triangleColor);
    }
}