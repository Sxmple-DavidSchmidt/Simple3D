import gui.Controller;
import gui.UserInterface;
import util.Vec3;
import world.World;
import world.objects.ObjObject;
import world.objects.Pyramid;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.addObject(new Pyramid(new Vec3(0, 0, 100), 5));
        world.addObject(new ObjObject(
                Main.class.getResource("ressources/uploads_files_3757122_mig-29.obj").getPath(),
                new Vec3(0, 20, -100),
                new Vec3(0, 0, 0)
        ));

        Controller.world = world;
        new UserInterface();
    }
}
