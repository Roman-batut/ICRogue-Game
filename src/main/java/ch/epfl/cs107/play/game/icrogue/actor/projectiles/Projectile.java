package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCell;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCellType;
import ch.epfl.cs107.play.game.icrogue.actor.ICRogueActor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

abstract public class Projectile extends ICRogueActor implements Consumable, Interactor{

    private final static int DEFAULT_DAMAGE = 1;
    private final static int DEFAULT_MOVE_DURATION = 10;

    protected Sprite sprite;
    private int frames;
    private int dmg;
    private boolean isConsumed ;
    private ICRogueProjInteractionHandler handler;
        
    //* CONSTRUCTORS
    /**
     * @param area
     * @param orientation
     * @param position
     * @param dmg
     * @param frames
     */
    public Projectile(Area area, Orientation orientation, DiscreteCoordinates position, int dmg, int frames) {
        super(area, orientation, position);
        this.frames = frames;
        this.dmg = dmg;
        isConsumed =false;
        handler = new ICRogueProjInteractionHandler();
        
        area.registerActor(this);
    }
    
    /**
     * @param area
     * @param orientation
     * @param position
     */
    public Projectile(Area area, Orientation orientation, DiscreteCoordinates position){
        this(area, orientation, position, DEFAULT_DAMAGE, DEFAULT_MOVE_DURATION);
    }  

    //* SETTERS
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }
    

    //* REDEFINE Consumable
    @Override
    public void consume(){
        isConsumed = true;
    }

    @Override
    public boolean isConsumed(){
       return isConsumed;
    }


    //* REDEFINE Interactor
    /**
     * Get this Interactor's current field of view cells coordinates
     * @return (List of DiscreteCoordinates). May be empty but not null
     */
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
       return Collections.singletonList (getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    /**
     * @return (boolean): true if this require cell interaction 
     */
    @Override
    public boolean wantsCellInteraction(){
        return true;
    }
    
    /**
     * @return (boolean): true if this require view interaction 
     */
    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    // INTERACTIONS
    //TODO Mettre dans Fire ?
    /**
     * Do this Interactor interact with the given Interactable
     * The interaction is implemented on the interactor side !
     * @param other (Interactable). Not null
     * @param isCellInteraction True if this is a cell interaction
     */
    @Override
   public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
        
    }
    private class ICRogueProjInteractionHandler implements ICRogueInteractionHandler{
        //TODO
       @Override
       public void interactWith(ICRogueCell cell, boolean isCellInteraction) {
           if(cell.getType() == ICRogueCellType.WALL || cell.getType() == ICRogueCellType.HOLE){
                consume();
           }
       }
    }


    //* UPDATE
    /**
     * Update the actor
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        move(frames);
    }
    

    //* DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        if(!isConsumed()){
            sprite.draw(canvas);
        }
    }

}

