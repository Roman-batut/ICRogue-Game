package ch.epfl.cs107.play.game.icrogue.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Connector extends AreaEntity{

    private final static int NO_KEY_ID = -1;

    public enum State{
		
		OPEN, 
		CLOSED, 
		LOCK, 
		INVISIBLE;

	}

    private State type;

    private String destination;
    private DiscreteCoordinates CoordinatesOfArrive;
    private int keyID;
    private boolean hasOpen;
    
    private Sprite sprite;

    // * CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     */
    public Connector(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation.opposite(), position);

        type = State.INVISIBLE;
        keyID = NO_KEY_ID;
        hasOpen = false;
    }


    // * GETTERS
    public State getType(){ 
        return type; 
    }

    public int getKeyId(){
        return keyID;
    }

    public String getDestination(){
        return destination;
    }
    
    public DiscreteCoordinates getCoordinatesofarrive(){
        return CoordinatesOfArrive;
    }

    public boolean gethasOpen(){
        return hasOpen;
    }

    // * SETTERS
    public void setType(State type){ 
        this.type = type; 
        if(type == State.OPEN){
            hasOpen = true;
        }
    }

    public void setDestination(String destination){ 
        this.destination = destination; 
    } 

    public void setDestinationCoordinates(DiscreteCoordinates CoordinatesOfArrive){
        this.CoordinatesOfArrive = CoordinatesOfArrive;
    }

    public void setKeyId(int keyId){
        this.keyID = keyId;
    }
    

    // * REDEFINE Interactable
    /**
     * Get this Interactor's current occupying cells coordinates
     * @return (List of DiscreteCoordinates). May be empty but not null
     */
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        DiscreteCoordinates coord = getCurrentMainCellCoordinates();
        return List.of(coord, coord.jump(new Vector((getOrientation().ordinal()+1)%2, getOrientation().ordinal()%2)));
    }

    /**
     * Indicate if the current Interactable take the whole cell space or not
     * @return (boolean)
     */
    @Override
    public boolean takeCellSpace() {
        return !(type.equals(State.OPEN));
    
    }

    /**
     * @return (boolean): true if this is able to have cell interactions
     */
    @Override
    public boolean isCellInteractable() {
        return(type.equals(State.OPEN));
    }

    /**
     * @return (boolean): true if this is able to have view interactions
     */
    @Override
    public boolean isViewInteractable() {
        return true; 
    }
    
    /** 
     * Call directly the interaction on this if accepted
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction (boolean) : true if the interaction is on a cell
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith(this, isCellInteraction);
    }

    
    // * DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        if(type != State.OPEN){
            if(type == State.INVISIBLE){sprite = new Sprite("icrogue/invisibleDoor_"+getOrientation().ordinal(),(getOrientation().ordinal()+1)%2+1, getOrientation().ordinal()%2+1, this);}
            if(type == State.CLOSED){sprite = new Sprite("icrogue/door_"+getOrientation().ordinal(),(getOrientation().ordinal()+1)%2+1, getOrientation().ordinal()%2+1, this);}
            if(type == State.LOCK){sprite =new Sprite("icrogue/lockedDoor_"+getOrientation().ordinal(), (getOrientation().ordinal()+1)%2+1, getOrientation().ordinal()%2+1, this);}
            sprite.draw(canvas);
        }
    }
    
}
