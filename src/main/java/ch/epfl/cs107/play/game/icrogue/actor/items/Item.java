package ch.epfl.cs107.play.game.icrogue.actor.items;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

abstract public class Item extends CollectableAreaEntity {

    private Sprite sprite;

    //* CONSTRUCTOR
    public Item(Area area, Orientation orientation, DiscreteCoordinates position, boolean isCollected) {
        super(area, orientation, position, isCollected);
    }
    

    //* REDEFINE Interactable
    @Override
    public boolean takeCellSpace(){
        return false;
    }


    //* DRAW
    public void draw(Canvas canvas) {
        if(!isCollected()){
            sprite.draw(canvas);
        } 
    }    
    
}
