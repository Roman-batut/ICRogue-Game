package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.areagame.actor.Background;
// import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
// import ch.epfl.cs107.play.math.Vector;

public class Level0Room extends ICRogueRoom{

	public Level0Room(DiscreteCoordinates roomCoordinates){
        super("icrogue/Level0Room", roomCoordinates);
	}

    @Override
	public String getTitle() {
		return "icrogue/level0" + roomCoordinates.x + roomCoordinates.y;
	}
	
	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return new DiscreteCoordinates(5,15);
	}
	
	protected void createArea() {
        // Base
        registerActor(new Background(this, getBehaviorName()));
        // registerActor(new Foreground(this, getBehaviorName()));
        // registerActor(new SimpleGhost(new Vector(20, 10), "ghost.2"));
        }
}
