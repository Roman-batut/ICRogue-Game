package ch.epfl.cs107.play.game.icrogue.actor.enemies;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.RandomHelper;
import ch.epfl.cs107.play.game.icrogue.actor.ICRogueActor;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.actor.items.Coin;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Enemy extends ICRogueActor implements Interactor, Interactable {

    private Sprite sprite; 
    private ICRogueEnemyInteractionHandler handler;
    private int hp;
    private boolean isAlive;

    // * CONSTRUCTOR
    /**
     * @param area
     * @param orientation
     * @param position
     */
    public Enemy(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        hp = 10;
        isAlive = true;
        handler = new ICRogueEnemyInteractionHandler();
    }

    // * GETTERS
    public boolean isAlive() {
        return isAlive;
    }

    // * SETTERS
    protected void setSprite(Sprite sprite){
        this.sprite = sprite;
    }

    // * REDEFINE Interactor
    /**
     * Indicate if the current Interactable take the whole cell space or not
     * @return (boolean)
     */
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    // * REDEFINE Interactor
    /**
     * Get this Interactor's current field of view cells coordinates
     * @return (List of DiscreteCoordinates). May be empty but not null
     */
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    /**
     * @return (boolean): true if this require cell interaction
     */
    @Override
    public boolean wantsCellInteraction() {
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
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
    }

    private class ICRogueEnemyInteractionHandler implements ICRogueInteractionHandler {
        // Player
        public void interactWith(ICRoguePlayer player, boolean isCellInteraction) {
            MoneyDrop();
            die();
        }
    }

    // * METHODS
    /**
     * Kill the enemy
     */
    public void die() {
        isAlive = false;
    }

    /**
     * Receive damage
     * @param Dmg
     */
    public void reciveDmg(int Dmg) {
        hp -= Dmg;
    }

    /**
     * Drop money on death
     */
    public void MoneyDrop() {
        List<Integer> proba = new ArrayList<Integer>();
        proba.add(0);
        proba.add(1);
        int pick = RandomHelper.chooseKInList(1, proba).get(0);
        if (pick == 1) {
            getOwnerArea().registerActor(new Coin(getOwnerArea(), Orientation.UP, getCurrentMainCellCoordinates()));
        }
    }

    
    // * UPDATE
    /**
     * Update the actor
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
    @Override
    public void update(float deltaTime) {
        if (hp <= 0) {
            die();
            MoneyDrop();
        }

        super.update(deltaTime);
    }

    // * DRAW
    /**
     * Renders itself on specified canvas.
     * @param canvas target, not null
     */
    @Override
    public void draw(Canvas canvas) {
        if (isAlive) {
            sprite.draw(canvas);
        }
    }

}
