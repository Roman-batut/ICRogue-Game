package ch.epfl.cs107.play.game.icrogue.actor;

import java.util.concurrent.DelayQueue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Fireball;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Projectile;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class ICRoguePlayer extends ICRogueActor {

    private float hp;
	private Sprite sprite;
    private Projectile fireball;
	/// Animation duration in frame number
    private final static int MOVE_DURATION = 8;

	//* Demo actor
    
	public ICRoguePlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
        super(owner, orientation, coordinates);
		this.hp = 10;
		sprite = new Sprite(spriteName, 1.f, 1.f,this);
        
		resetMotion();
	}

    @Override
    public boolean takeCellSpace(){
        return true;
    }
    
	@Override
	public void draw(Canvas canvas) {
        switch (getOrientation()) {
            case UP ->
                sprite = new Sprite("zelda/player", .75f, 1.5f, this,new RegionOfInterest(0, 64, 16, 32), new Vector(.15f,-.15f));
            case DOWN ->
                sprite = new Sprite("zelda/player", .75f, 1.5f, this,new RegionOfInterest(0, 0, 16, 32), new Vector(.15f, -.15f)); 
            case LEFT ->
                sprite = new Sprite("zelda/player", .75f, 1.5f, this,new RegionOfInterest(0, 96, 16, 32), new Vector(.15f,-.15f));
            case RIGHT ->
                sprite = new Sprite("zelda/player", .75f, 1.5f, this,new RegionOfInterest(0, 32, 16, 32), new Vector(.15f,-.15f));
        }
        
        sprite.draw(canvas);
    }
    
    //*UPDATE

    public void update(float deltaTime){
        Keyboard keyboard = getOwnerArea().getKeyboard();
        
        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        
        launchFireball(getOrientation(), keyboard.get(Keyboard.X), deltaTime);
        
	    super.update(deltaTime);
    }
    
    private void moveIfPressed(Orientation orientation, Button b){
        if(b.isDown()) {
            if (!isDisplacementOccurs()){
                orientate(orientation);
                move(MOVE_DURATION);
            }
        }
    }

    private void launchFireball(Orientation orientation, Button b, float deltaTime){
        if(b.isPressed()) {
            if (!isDisplacementOccurs()){
                fireball = new Fireball(getOwnerArea(), orientation, getCurrentMainCellCoordinates(), 1, 5);
                fireball.update(deltaTime);
            }
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }
    
}