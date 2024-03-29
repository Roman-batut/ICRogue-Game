package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.items.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0KeyRoom extends Level0ItemRoom {
    
    private Key key;

    // * CONSTRUCTOR
    /**
     * @param roomCoordinates
     * @param keyId
     */
    public Level0KeyRoom(DiscreteCoordinates roomCoordinates, int keyId) {
        super(roomCoordinates);
        key = new Key(this, Orientation.UP, new DiscreteCoordinates(5,5), keyId);
        super.addItem(key);
    }
    

    // * CREATE AREA
    /**
     * Create the area
     * Register all actors
     */
    @Override
    protected void createArea(){
        super.createArea();
    }
    
}
