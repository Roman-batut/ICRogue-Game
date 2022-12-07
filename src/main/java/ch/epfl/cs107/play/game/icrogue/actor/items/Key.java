package ch.epfl.cs107.play.game.icrogue.actor.items;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends Item{

    private Sprite sprite;
    private int ID;

    public Key(Area area, Orientation orientation, DiscreteCoordinates position, int ID) {
        super(area, orientation, position, false);
        sprite = new Sprite("icrogue/key", 0.6f, 0.6f, this);
        this.ID = ID;

    }
    
    @Override
    public void draw(Canvas canvas) {
        if (!isCollected()){
            sprite.draw(canvas);
        }
        
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith((Key)this, isCellInteraction);
    }
    
    public int getID(){ return ID; }

}

