package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.actor.ICRogueActor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

abstract public class Projectile extends ICRogueActor implements Consumable, Interactor{

    private final static int DEFAULT_DAMAGE = 1;
    private final static int DEFAULT_MOVE_DURATION = 10;

    protected Sprite sprite; //TODO private ?
    private int frames;
    private int dmg;
    private boolean isConsumed ;
        
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

    //* GETTERS
    public int getDmg(){
        return dmg;
    }

    //* SETTERS
    public void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    protected void setDmg(int dmg){
        this.dmg = dmg;
    }
    

    //* REDEFINE Consumable
    @Override
    public void consume(){
        if (!isConsumed) {
            isConsumed = getOwnerArea().unregisterActor(this);
        }
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
        sprite.draw(canvas);
    }

}

