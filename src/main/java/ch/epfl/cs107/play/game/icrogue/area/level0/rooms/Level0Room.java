package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;


import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.area.ConnectorInRoom;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0Room extends ICRogueRoom{

	public enum Level0Connectors implements ConnectorInRoom {
	
		W (new DiscreteCoordinates(0, 4),new DiscreteCoordinates(8, 5),Orientation.LEFT), 
		S (new DiscreteCoordinates(4, 0), new DiscreteCoordinates(5, 8), Orientation.DOWN),
		E (new DiscreteCoordinates(9, 4), new DiscreteCoordinates(1, 5), Orientation.RIGHT), 
		N (new DiscreteCoordinates(4, 9), new DiscreteCoordinates(5, 1), Orientation.UP);

		private DiscreteCoordinates position;
		private DiscreteCoordinates destination;
		private Orientation orientation;
		
		// CONSTRUCTOR
		private Level0Connectors(DiscreteCoordinates position, DiscreteCoordinates destination, Orientation orientation){
			this.position = position;
			this.destination = destination;
			this.orientation = orientation;
		}
		
		// GETTERS
		@Override
		public DiscreteCoordinates getDestination(){
			return destination;
		}

		@Override 
		public int getIndex(){
			return this.ordinal();
		}
		
	}	
	
	private DiscreteCoordinates roomCoordinates;
	
	// * CONSTRUCTOR
	/**
	 * @param roomCoordinates
	 */
	public Level0Room(DiscreteCoordinates roomCoordinates){
        super(getAllConnectorsPosition(), getAllConnectorsOrientation(),"icrogue/Level0Room", roomCoordinates);
		this.roomCoordinates = roomCoordinates;
	}
	
	// * GETTERS
	@Override
	public String getTitle() {
		return "icrogue/level0" + roomCoordinates.x + roomCoordinates.y;
	}

	public static List<DiscreteCoordinates> getAllConnectorsPosition(){
		List<DiscreteCoordinates> tab = new ArrayList<DiscreteCoordinates>();
		
		for(Level0Connectors connectors : Level0Connectors.values()){
			tab.add(connectors.position);
		}

		return tab;
	}
	
	public static List<Orientation> getAllConnectorsOrientation(){
		List<Orientation> tab = new ArrayList<Orientation>();
		
		for(Level0Connectors connectors : Level0Connectors.values()){
			tab.add(connectors.orientation);
		}

		return tab;
	}

	
	// * REDEFINE ICRogueRoom
	@Override
	public DiscreteCoordinates getPlayerSpawnPosition() {
		return new DiscreteCoordinates(5,15);
	}


	// * CREATE AREA
	/**
	 * Create the area
	 * Register all actors
	 */
	@Override
	protected void createArea() {
		super.createArea();
        registerActor(new Background(this, getBehaviorName()));
    }

}
