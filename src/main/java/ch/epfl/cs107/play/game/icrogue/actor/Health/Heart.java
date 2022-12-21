package ch.epfl.cs107.play.game.icrogue.actor.Health;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Heart extends AreaEntity{
    enum HealthState {
        FULL,
        HALF,
        EMPTY;
    }

    private Sprite Coeur1;
    private HealthState state;
    public Heart(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        Coeur1 = new Sprite("zelda/heartDisplay", 1.1f, 1.1f, this);
        state =HealthState.FULL;
        //TODO Auto-generated constructor stub
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        // TODO Auto-generated method stub
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        // TODO Auto-generated method stub
        return false;
    }
    public void setState(HealthState aState){
        state =aState;
    }

    @Override
    public void draw(Canvas canvas) {
        if(state == HealthState.FULL){Coeur1 = new Sprite("zelda/heartDisplay", 1.1f, 1.1f, this,new RegionOfInterest(32, 0, 16, 16),new Vector(0, 0));}
        if(state == HealthState.HALF){Coeur1 = new Sprite("zelda/heartDisplay", 1.1f, 1.1f, this,new RegionOfInterest(16, 0, 16, 16),new Vector(0, 0));}
        if(state == HealthState.EMPTY){Coeur1 = new Sprite("zelda/heartDisplay", 1.1f, 1.1f, this, new RegionOfInterest(0, 0, 16, 16),new Vector(0, 0));}
        Coeur1.draw(canvas);
        
    }
    
}
