package world.objects;

import util.Vec3;

public interface Object3D {
    public void setOrigin(Vec3 origin);
    public Vec3 getOrigin();
    public Vec3[] getVertexBuffer();
    public int[] getIndexBuffer();
}
