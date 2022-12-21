package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCell;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCellType;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class Arrow extends Projectile{

    private ICRogueArrowInteractionHandler handler;
    
    // * CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     */
    public Arrow(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, 1,5);
        sprite = new Sprite("zelda/arrow", 1f, 1f, this,new RegionOfInterest(32*orientation.ordinal(), 0, 32, 32),new Vector(0, 0));
       handler = new ICRogueArrowInteractionHandler();
        super.setDmg(1);
    }

    
    // * REDEFINE Interactable
    /** 
     * Call directly the interaction on this if accepted
     * @param v (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction (boolean) : true if the interaction is on a cell
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith(this, isCellInteraction);
    }

    //INTERACTIONS
    @Override
   public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
        
    }
    private class ICRogueArrowInteractionHandler implements ICRogueInteractionHandler{
       @Override
       //TODO UPgrade in projectile all common part
       public void interactWith(ICRogueCell cell, boolean isCellInteraction) {
           if(cell.getType() == ICRogueCellType.WALL || cell.getType() == ICRogueCellType.HOLE){
                consume();
           }
       }
       public void interactWith(ICRoguePlayer player, boolean isCellInteraction){
            consume();
            player.reciveDmg(getDmg());
       }
    }


    // * UPDATE
    /**
     * Update the actor
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
    }

    // * DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
       super.draw(canvas);
    }

}
