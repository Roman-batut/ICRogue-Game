package ch.epfl.cs107.play.game.icrogue.actor;

import java.util.concurrent.DelayQueue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Fire;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Projectile;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import java.util.ArrayList;
public class ICRoguePlayer extends ICRogueActor {

    private float hp;
	private Sprite sprite;
    private Fire fireball;
    private ArrayList<Projectile> projectiles; 
	/// Animation duration in frame number
    private final static int MOVE_DURATION = 8;

	//* Demo actor
    
	public ICRoguePlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
        super(owner, orientation, coordinates);
		this.hp = 10;
		sprite = new Sprite(spriteName, 1.f, 1.f,this);
        
        projectiles = new ArrayList<Projectile>();
        
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

        for(Projectile proj : projectiles){
            proj.draw(canvas);
        }
        
    }
    
    //*UPDATE

    public void update(float deltaTime){
        Keyboard keyboard = getOwnerArea().getKeyboard();
        
        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        
        launchFireball(getOrientation(), keyboard.get(Keyboard.X));
        for(Projectile proj : projectiles){
            proj.update(deltaTime);;
        }

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

    private void launchFireball(Orientation orientation, Button b){
        if(b.isPressed()) {
            if (!isDisplacementOccurs()){
                fireball = new Fire(getOwnerArea(), orientation, getCurrentMainCellCoordinates());
                // fireball.setSprite(new Sprite("zelda/bridge", 1f, 1f, fireball));
                projectiles.add(fireball);
            }
        }
    }
    
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {

    }
    
}