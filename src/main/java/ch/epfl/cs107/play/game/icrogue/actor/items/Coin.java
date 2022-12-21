package ch.epfl.cs107.play.game.icrogue.actor.items;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Coin extends Item{

    private Sprite sprite;
    public Coin(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, false);
        sprite = new Sprite("zelda/coin", 0.75f, 0.75f, this, new RegionOfInterest(0,0,16,16), new Vector(0, 0));
        //TODO Auto-generated constructor stub
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        // TODO Auto-generated method stub
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean isCellInteractable() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith( this, isCellInteraction);
    }
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
    }
    
}
