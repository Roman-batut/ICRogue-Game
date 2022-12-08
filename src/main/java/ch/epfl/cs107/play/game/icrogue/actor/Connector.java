package ch.epfl.cs107.play.game.icrogue.actor;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Connector extends AreaEntity implements Interactable{

    private final static int NO_KEY_ID = -1;

    public enum State{
		
		OPEN, 
		CLOSED, 
		LOCK, 
		INVISIBLE;

	}

    private State type;

    private String destination;
    private DiscreteCoordinates coordinates;
    private int keyID;
    
    private Sprite sprite;

    //* CONSTRUCTOR
    public Connector(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation.opposite(), position);

        type = State.INVISIBLE;
        
        destination = area.getTitle();
        // coordinates = .getPlayerSpawnPosition(); 
        keyID = NO_KEY_ID;
    }


    //* GETTERS
    public State getType(){ 
        return type; 
    }
    
    //* SETTERS
    public void setType(State type){ 
        this.type = type; 
    }


    //* REDEFINE Interactable
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        DiscreteCoordinates coord = getCurrentMainCellCoordinates();
        return List.of(coord, coord.jump(new Vector((getOrientation().ordinal()+1)%2, getOrientation().ordinal()%2)));
    }

    @Override
    public boolean takeCellSpace() {
        if(type == State.OPEN){
            return false;
        }
        return true; 
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true; 
    }
    

    //* DRAW
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
