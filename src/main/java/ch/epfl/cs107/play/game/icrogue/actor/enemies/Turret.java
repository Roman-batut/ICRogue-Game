package ch.epfl.cs107.play.game.icrogue.actor.enemies;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Arrow;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Turret extends Enemy{
    
    public final static float COOLDOWN = 2.f;

    private float time;
    private List<Orientation> shootOrientations;

    // * CONSTRUTOR
    public Turret(Area area, Orientation orientation, DiscreteCoordinates position, List<Orientation> shootOrientations) {
        super(area, orientation, position);
        sprite = new Sprite("icrogue/static_npc", 1.5f, 1.5f, this, null, new Vector(-0.25f, 0));
        this.shootOrientations = shootOrientations;

    }


    // * REDEFINE ICRogueActor
    /**
     * Call directly the interaction on this if accepted
     * @param v                 (AreaInteractionVisitor) : the visitor
     * @param isCellInteraction (boolean) : true if the interaction is a cell interaction
     */
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith(this, isCellInteraction);
    }


    // * METHODS 
    private void shoot(){
        for(Orientation ort : shootOrientations){
            new Arrow(getOwnerArea(), ort, getCurrentMainCellCoordinates().jump(ort.toVector()));
        }
    }
    
    private float calculCooldown(float dt){
        time += dt;
        return (time);
    }


    // * UPDATE
    @Override
    public void update(float deltaTime) {
        if(isAlive()){
            if(calculCooldown(deltaTime) > COOLDOWN){
                shoot();
                time =0;
            }
        }
        super.update(deltaTime);
        
    }
    
    // * DRAW
    @Override
    public void draw(Canvas canvas) {
        if(isAlive()){
            sprite.draw(canvas);
        }
    }
    
}
