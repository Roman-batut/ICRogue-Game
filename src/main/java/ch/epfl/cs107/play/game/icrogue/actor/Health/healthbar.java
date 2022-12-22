package ch.epfl.cs107.play.game.icrogue.actor.Health;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.actor.Health.Heart.HealthState;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Healthbar extends AreaEntity{
    
    List<Heart> health;
    private ICRoguePlayer owner;

    // * CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     * @param player
     */
    public Healthbar(Area area, Orientation orientation, DiscreteCoordinates position, ICRoguePlayer player) {
        super(area, orientation, position);
        health= new ArrayList<Heart>();
        health.add(new Heart(area, orientation, position));
        health.add(new Heart(area, orientation, new DiscreteCoordinates(position.x+1, position.y)));
        health.add(new Heart(area, orientation, new DiscreteCoordinates(position.x+2, position.y)));
        owner = player;
    }
    

    // * REDEFINE Interactable
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
    public boolean takeCellSpace() {
        return false;
    }

    /**
     * @return (boolean): true if this is able to have cell interactions
     */
    @Override
    public boolean isCellInteractable() {
        return false;
    }

    /**
     * @return (boolean): true if this require view interaction
     */
    @Override
    public boolean isViewInteractable() {
        return false;
    }

    
    // * UPDATE
    /**
     * Update the healthbar
     * @param deltaTime (float) : elapsed time since last update, in seconds, non negative
     */
    @Override
    public void update(float deltaTime){
        if(owner.hp() == 0){
            for(Heart val: health){
                val.setState(HealthState.EMPTY);
            } 
        }else if(owner.hp() == 1){
            health.get(2 ).setState(HealthState.EMPTY);
            health.get(1 ).setState(HealthState.EMPTY);
            health.get(0).setState(HealthState.HALF);
        }else if(owner.hp() == 2){
            health.get(2 ).setState(HealthState.EMPTY);
            health.get(1 ).setState(HealthState.EMPTY);
            health.get(0).setState(HealthState.FULL);
        }else if(owner.hp() == 3){
            health.get(2 ).setState(HealthState.EMPTY);
            health.get(1 ).setState(HealthState.HALF);
            health.get(0).setState(HealthState.FULL);
        }else if(owner.hp() == 4){
            health.get(2).setState(HealthState.EMPTY);
            health.get(1 ).setState(HealthState.FULL);
            health.get(0).setState(HealthState.FULL);
        }else if(owner.hp() == 5){
            health.get(2 ).setState(HealthState.HALF);
            health.get(1 ).setState(HealthState.FULL);
            health.get(0).setState(HealthState.FULL);
        }else if(owner.hp() == 6){
            health.get(2 ).setState(HealthState.FULL);
            health.get(1 ).setState(HealthState.FULL);
            health.get(0).setState(HealthState.FULL);
        }
    }       

    // * DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        for(Heart val: health){
            val.draw(canvas);
        }
    }

}