package world.objects;

import util.Vec3;

public interface Object3D {
    void setOrigin(Vec3 origin);
    Vec3 getOrigin();
    Vec3[] getVertexBuffer();
    int[] getIndexBuffer();
}
