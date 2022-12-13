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
    public Projectile(Area area, Orientation orientation, DiscreteCoordinates position, int dmg, int frames) {
        super(area, orientation, position);
        this.frames = frames;
        this.dmg = dmg;
        isConsumed =false;
        handler = new ICRogueProjInteractionHandler();
        
        area.registerActor(this);
    }
    
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
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
       return Collections.singletonList (getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction(){
        return true;
    }
    
    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    // INTERACTIONS
    //TODO Mettre dans Fire ?
    @Override
   public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
        
    }
    private class ICRogueProjInteractionHandler implements ICRogueInteractionHandler{
       @Override
       public void interactWith(ICRogueCell cell, boolean isCellInteraction) {
           if(cell.getType() == ICRogueCellType.WALL || cell.getType() == ICRogueCellType.HOLE){
                consume();
           }
       }
    }


    //* UPDATE
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        move(frames);
    }
    

    //* DRAW
    @Override
    public void draw(Canvas canvas) {
        if(!isConsumed()){
            sprite.draw(canvas);
        }
    }

}

