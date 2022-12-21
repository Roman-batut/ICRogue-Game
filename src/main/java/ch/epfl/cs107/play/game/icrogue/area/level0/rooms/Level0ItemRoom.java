package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

abstract public class Level0ItemRoom extends Level0Room {

    List<Item> items;

    // * CONSTRUCTOR
    /**
     * @param roomCoordinates
     */
    public Level0ItemRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
        items = new ArrayList<Item>();
    }


    // * REDEFINE Logic
    /**
     * @return (boolean): true if the signal is considered as on
     */
    @Override
    public boolean isOn() {
        for(Item item : items){
            if(!item.isCollected()){
                return false;
            }
        }
        return super.isOn();
    }

    /**
     * @return (boolean): true if the signal is considered as off
     */
    @Override
    public boolean isOff() {
        return !isOn();
    }


    // * ADD
    /**
     * Add an item to the room
     * @param item
     */
    public void addItem(Item item) {
        items.add(item);
    }
    

    // * CREATE AREA
    @Override
    protected void createArea() {
        super.createArea();
        for (Item val : items) {
            registerActor(val);
        }
    }

}
