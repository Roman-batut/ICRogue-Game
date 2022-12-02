package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.actor.ICRogueActor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

abstract public class Projectile extends ICRogueActor implements Consumable{

    private final int DEFAULT_DAMAGE = 1;
    private final int DEFAULT_MOVE_DURATION = 10;

    protected Sprite sprite;
    private int frames;
    private int dmg;
    private boolean isConsumed;
    
    public Projectile(Area area, Orientation orientation, DiscreteCoordinates position, int dmg, int frames) {
        super(area, orientation, position);
        this.dmg= dmg;
        this.frames =frames;
    }
    
    public Projectile(Area area, Orientation orientation, DiscreteCoordinates position){
        super(area, orientation, position);
        // TODO better ?
        dmg = DEFAULT_DAMAGE; 
        frames = DEFAULT_MOVE_DURATION;
    }  

    
    @Override
    public void update(float deltaTime) {
        // TODO Auto-generated method stub
        super.update(deltaTime);
        frames --;
        if(!isConsumed){
            move(frames);
        }
        
    }


    protected void setSprite(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void consume(){
        isConsumed = true;
    }
    public boolean isConsumed(){
        return isConsumed;
    }

    @Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

}
