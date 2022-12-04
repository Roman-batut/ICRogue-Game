package ch.epfl.cs107.play.game.icrogue.actor.items;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Staff extends Item{

    private Sprite sprite;

    public Staff(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position, false);
        sprite = new Sprite("zelda/staff_water.icon", .5f, .5f, this);

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
        return true;
    }
    
    @Override
    public boolean takeCellSpace(){
        return true;
    }

    @Override
    public void draw(Canvas canvas) {
       
        sprite.draw(canvas);    
    }
    
}
