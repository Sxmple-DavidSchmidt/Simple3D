package world.objects;

import util.Vec3;

public interface Object3D {
    public Vec3 getOrigin();
    public Vec3[] getVertexBuffer();
    public int[] getIndexBuffer();
}
