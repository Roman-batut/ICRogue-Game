package ch.epfl.cs107.play.game.icrogue.actor.items;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Item{

    private Sprite sprite;
    private int ID;

    //* CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     * @param ID
     */
    public Key(Area area, Orientation orientation, DiscreteCoordinates position, int ID) {
        super(area, orientation, position, false);
        sprite = new Sprite("icrogue/key", 0.6f, 0.6f, this);
        this.ID = ID;
    }


    //* GETTERS
    public int getID(){ 
        return ID; 
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

    //* REDEFINE Interactable
    /** 
     * Call directly the interaction on this if accepted
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction (boolean) : true if the interaction is on a cell
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith((Key)this, isCellInteraction);
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
        return false;
    }
    

    //* DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }

}

