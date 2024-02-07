import gui.Controller;
import gui.UserInterface;
import util.Vec3;
import world.World;
import world.objects.Cube;
import world.objects.ObjObject;
import world.objects.Orientation;
import world.objects.Pyramid;

import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.addObject(new Pyramid(new Vec3(0, 0, 0), 5));
        world.addObject(new Cube(new Vec3(10, 0, -50), 5));
        world.addObject(new ObjObject(Main.class.getResource("ressources/uploads_files_3757122_mig-29.obj").getPath()));

        Controller.world = world;
        UserInterface ui = new UserInterface();
    }
}
