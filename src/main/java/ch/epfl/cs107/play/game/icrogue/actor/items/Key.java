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

    //* CONSTRUCTOR
    public Key(Area area, Orientation orientation, DiscreteCoordinates position, int ID) {
        super(area, orientation, position, false);
        sprite = new Sprite("icrogue/key", 0.6f, 0.6f, this);
        this.ID = ID;

    }


    //* GETTERS
    public int getID(){ 
        return ID; 
    }
    

    //* REDEFINE Item
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    //* REDEFINE Interactable
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith((Key)this, isCellInteraction);
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }
    
    @Override
    public boolean isViewInteractable() {
        return false;
    }
    

    //* DRAW
    @Override
    public void draw(Canvas canvas) {
        if (!isCollected()){
            sprite.draw(canvas);
        }
    }

}

