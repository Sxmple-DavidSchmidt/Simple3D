package util;

import com.mokiat.data.front.parser.*;
import world.objects.Triangle;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ObjUtilities {
    public static Triangle[] triangles (String objPath){
        OBJModel model;

        try (InputStream in = new FileInputStream(objPath)) {
            IOBJParser parser = new OBJParser();
            model = parser.parse(in);
        } catch (IOException e) {
            return new Triangle[0];
        }

        ArrayList<Triangle> triangles = new ArrayList<>();
        for (OBJObject object : model.getObjects()) {
            for (OBJMesh mesh : object.getMeshes()) {
                for (OBJFace face : mesh.getFaces()) {
                    int reference_count = face.getReferences().size();
                    if (reference_count > 4) {
                        System.out.println("invalid face size " + reference_count);
                        continue;
                    }

                    List<OBJDataReference> refs = face.getReferences();
                    if (reference_count == 3) {
                        OBJVertex ov1 = model.getVertex(refs.get(0));
                        OBJVertex ov2 = model.getVertex(refs.get(1));
                        OBJVertex ov3 = model.getVertex(refs.get(2));

                        Vec3 v1 = new Vec3(ov1.x, ov1.y, ov1.z);
                        Vec3 v2 = new Vec3(ov2.x, ov2.y, ov2.z);
                        Vec3 v3 = new Vec3(ov3.x, ov3.y, ov3.z);
                        triangles.add(new Triangle(v1, v2, v3));
                    } else if (reference_count == 4) {
                        OBJVertex ov1 = model.getVertex(refs.get(0));
                        OBJVertex ov2 = model.getVertex(refs.get(1));
                        OBJVertex ov3 = model.getVertex(refs.get(2));
                        OBJVertex ov4 = model.getVertex(refs.get(3));

                        Vec3 v1 = new Vec3(ov1.x, ov1.y, ov1.z);
                        Vec3 v2 = new Vec3(ov2.x, ov2.y, ov2.z);
                        Vec3 v3 = new Vec3(ov3.x, ov3.y, ov3.z);
                        Vec3 v4 = new Vec3(ov4.x, ov4.y, ov4.z);

                        triangles.add(new Triangle(v1, v2, v3));
                        triangles.add(new Triangle(v1, v3, v4));
                    }
                }
            }
        }

        return triangles.toArray(new Triangle[0]);
    }

}
