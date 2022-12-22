package ch.epfl.cs107.play.game.icrogue.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Marchand extends ICRogueActor{

    private Sprite sprite;
    public Marchand(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        sprite =new Sprite("assistant.fixed", 0.75f, 0.75f, this, new RegionOfInterest(0,0,16,24), new Vector(0, 0));
        //TODO Auto-generated constructor stub
    }


    /**
     * Indicate if the current Interactable take the whole cell space or not
     * @return (boolean)
     */
    @Override
    public boolean takeCellSpace() {
        return true;
    }
    
    //DRAW
    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas);
        // TODO Auto-generated method stub
        
    }
    
}
