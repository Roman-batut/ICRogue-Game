package ch.epfl.cs107.play.game.icrogue.actor.items;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

abstract public class Item extends CollectableAreaEntity{

    private Sprite sprite;

    public Item(Area area, Orientation orientation, DiscreteCoordinates position, boolean isCollected) {
        super(area, orientation, position, isCollected);
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean takeCellSpace(){
        return false;
    }

    public void draw() {
        if(!isCollected()){
            sprite.draw(null);
        } 
    }
    
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
    }
    
}
