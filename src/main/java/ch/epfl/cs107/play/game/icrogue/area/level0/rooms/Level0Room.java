package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;


import java.util.List;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.game.icrogue.area.ConnectorInRoom;
// import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0Room extends ICRogueRoom{

	public enum Level0Connectors implements ConnectorInRoom {
	
	W (new DiscreteCoordinates(0, 4),new DiscreteCoordinates(8, 5),Orientation.LEFT), 
	S (new DiscreteCoordinates(4, 0), new DiscreteCoordinates(5, 8), Orientation.DOWN),
	E (new DiscreteCoordinates(9, 4), new DiscreteCoordinates(1, 5), Orientation.RIGHT), 
	N (new DiscreteCoordinates(4, 9), new DiscreteCoordinates(5, 1), Orientation.UP);

	private Level0Connectors(DiscreteCoordinates position, DiscreteCoordinates desination, Orientation orientation){

	}
	// TODO WITH STATIC FUNCTION AND TODO quoi c'est relou cette parti genre je sais pas faire les fonctions d'un enum 
	@Override
	public DiscreteCoordinates getDestination(){
		return  null;
	}

	@Override 
	public int getIndex(){
		return 0;
	}

	}	
	static List<Orientation> getAllConnectorsOrientation(){
		return null;
	}
	
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
		registerActor(new Staff(this, Orientation.DOWN, new DiscreteCoordinates(4, 3)));
		registerActor(new Cherry(this, Orientation.DOWN, new DiscreteCoordinates(6, 3)));
        // registerActor(new Foreground(this, getBehaviorName()));
        // registerActor(new SimpleGhost(new Vector(20, 10), "ghost.2"));
        }

	@Override
	public int getIndex() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DiscreteCoordinates getDestination() {
		// TODO Auto-generated method stub
		return null;
	}
}
