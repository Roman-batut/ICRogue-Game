package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

abstract public class Level0ItemRoom extends Level0Room {

    List<Item> items;

    // * Constructor
    /**
     * @param roomCoordinates
     */
    public Level0ItemRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
        items = new ArrayList<Item>();
    }


    // * Add
    /**
     * Add an item to the room
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }
    

    //* CREATE AREA

    @Override
    protected void createArea() {
        super.createArea();
        for (Item val : items) {
            registerActor(val);
        }
    }

}
