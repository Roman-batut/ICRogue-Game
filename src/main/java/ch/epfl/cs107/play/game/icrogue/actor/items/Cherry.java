package ch.epfl.cs107.play.game.icrogue.actor.items;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Cherry extends Item{

    private Sprite sprite;

    public Cherry(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, false);
        sprite = new Sprite("icrogue/cherry", 0.6f, 0.6f, this);

    }
    
    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
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
    
    
}
