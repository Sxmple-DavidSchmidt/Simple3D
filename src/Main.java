import gui.Controller;
import gui.UserInterface;
import util.Vec3;
import world.World;
import world.objects.ObjObject;
import world.objects.Pyramid;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        World world = new World();
        world.addObject(new Pyramid(
                new Vec3(0, 0, 0),
                new Vec3(0, 0, 0),
                20)
        );

        world.addObject(new ObjObject(
                Main.class.getResource("ressources/uploads_files_3757122_mig-29.obj").openStream(),
                new Vec3(0, 40, -200),
                new Vec3(0, 0, 0),
                30
        ));

        Controller.world = world;
        new UserInterface();
    }
}
