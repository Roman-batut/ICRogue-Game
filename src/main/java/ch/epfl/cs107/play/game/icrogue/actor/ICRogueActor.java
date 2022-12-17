package ch.epfl.cs107.play.game.icrogue.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

abstract public class ICRogueActor extends MovableAreaEntity{

    //* CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     */
    public ICRogueActor(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }


    //* REDEFINE Interactable
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
        return false;
    }
    
    /**
     * @return (boolean): true if this require cell interaction 
     */
    @Override
    public boolean isCellInteractable() {
        return true;
    }
    
    /**
     * @return (boolean): true if this require view interaction 
     */
    @Override
    public boolean isViewInteractable() {
        return false;
    }
    
    
    //* UPDATE
    /**
     * Update the actor
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }

}
