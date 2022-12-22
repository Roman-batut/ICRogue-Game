package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;


import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0StaffRoom extends Level0ItemRoom{

    private Staff staff;

    // * CONSTRUCTOR
    /**
     * @param roomCoordinates
     */
    public Level0StaffRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
        staff = new Staff(this, Orientation.UP, new DiscreteCoordinates(5,5));
        super.addItem(staff);
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
