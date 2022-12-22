package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import java.util.ArrayList;
import java.util.Arrays;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.enemies.Turret;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0BossRoom extends Level0EnemyRoom{

    public Level0BossRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
        
        super.addEnemy(new Turret(this, Orientation.UP, new DiscreteCoordinates(1,8), new ArrayList<Orientation>(Arrays.asList(Orientation.DOWN, Orientation.RIGHT))));
        super.addEnemy(new Turret(this, Orientation.DOWN, new DiscreteCoordinates(8, 1), new ArrayList<Orientation>(Arrays.asList(Orientation.UP, Orientation.LEFT))));
        super.addEnemy(new Turret(this, Orientation.UP, new DiscreteCoordinates(1,1), new ArrayList<Orientation>(Arrays.asList(Orientation.UP, Orientation.RIGHT))));
        super.addEnemy(new Turret(this, Orientation.DOWN, new DiscreteCoordinates(8, 8), new ArrayList<Orientation>(Arrays.asList(Orientation.DOWN, Orientation.LEFT))));
    }
    
    
    // * CREATE AREA
    @Override
    protected void createArea(){
        super.createArea();
    }
    
}
