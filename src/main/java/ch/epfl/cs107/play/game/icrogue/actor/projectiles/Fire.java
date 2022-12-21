package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCell;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCellType;
import ch.epfl.cs107.play.game.icrogue.actor.enemies.Turret;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Fire extends Projectile{

    private ICRogueFireInteractionHandler handler;

    // * CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     */
    public Fire(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, 1,5);
        sprite = new Sprite("zelda/fire", 1f, 1f, this,new RegionOfInterest(0, 0, 16, 16), new Vector(0, 0));
        handler = new ICRogueFireInteractionHandler();
        super.setDmg(2);
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
    private class ICRogueFireInteractionHandler implements ICRogueInteractionHandler{
       @Override
       public void interactWith(ICRogueCell cell, boolean isCellInteraction) {
           if(cell.getType() == ICRogueCellType.WALL || cell.getType() == ICRogueCellType.HOLE){
                consume();
           }
       }

       @Override
       public void interactWith(Turret turret, boolean isCellInteraction){
            consume();
            turret.reciveDmg(getDmg());
       }
    }
    

    //* UPDATE
    /**
     * Update the actor
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime){
        super.update(deltaTime);
    }

    //* DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
       super.draw(canvas);
    }

 }
