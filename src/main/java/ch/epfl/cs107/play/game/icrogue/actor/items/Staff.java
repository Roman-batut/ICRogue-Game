package ch.epfl.cs107.play.game.icrogue.actor.items;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.actor.Text;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;


public class Staff extends Item{

    private Sprite sprite;
    private Text message;
    
    //* CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     */
    public Staff(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, false);
        sprite = new Sprite("zelda/staff_water.icon", .5f, .5f, this);
        message = new Text(("Press W to grab"), new DiscreteCoordinates(position.x, position.y+1),area,true,0.4f, Color.WHITE);
    }


    //* REDEFINE Item
    /**
     * Get this Interactor's current occupying cells coordinates
     * @return (List of DiscreteCoordinates). May be empty but not null
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    /**
     * Indicate if the current Interactable take the whole cell space or not
     * @return (boolean)
     */
    @Override
    public boolean takeCellSpace(){
        return true;
    }

    
    //* REDEFINE Interactable
    /** 
     * Call directly the interaction on this if accepted
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction (boolean) : true if the interaction is on a cell
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith((Staff)this, isCellInteraction);
    }

    /**
     * @return (boolean): true if this is able to have cell interactions
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }

    /**
     * @return (boolean): true if this is able to have view interactions
     */
    @Override
    public boolean isViewInteractable() {
        return true;
    }
    

    //* DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas); 
        message.draw(canvas);
    }

}
