package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.icrogue.actor.enemies.Turret;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;

import java.util.ArrayList;
import java.util.Arrays;

public class Level0TurretRoom extends Level0EnemyRoom{

    // * CONSTRUCTOR
    public Level0TurretRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);

        super.addEnemy(new Turret(this, Orientation.UP, new DiscreteCoordinates(1,8), new ArrayList<Orientation>(Arrays.asList(Orientation.DOWN, Orientation.RIGHT))));
        super.addEnemy(new Turret(this, Orientation.UP, new DiscreteCoordinates(8, 1), new ArrayList<Orientation>(Arrays.asList(Orientation.UP, Orientation.LEFT))));

    }


    // * CREATE AREA
    @Override
    protected void createArea(){
        super.createArea();
    }
    
}
