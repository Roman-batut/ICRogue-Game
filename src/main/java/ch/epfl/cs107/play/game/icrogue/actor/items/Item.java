package ch.epfl.cs107.play.game.icrogue.actor.items;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

abstract public class Item extends CollectableAreaEntity {

    private Sprite sprite;

    //* CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     * @param isCollected
     */
    public Item(Area area, Orientation orientation, DiscreteCoordinates position, boolean isCollected) {
        super(area, orientation, position, isCollected);
    }
    

    //* REDEFINE Interactable
    /**
     * Indicate if the current Interactable take the whole cell space or not
     * @return (boolean)
     */
    @Override
    public boolean takeCellSpace(){
        return false;
    }

    //* DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    public void draw(Canvas canvas) {
        
        sprite.draw(canvas);
        
    }    

    
}
