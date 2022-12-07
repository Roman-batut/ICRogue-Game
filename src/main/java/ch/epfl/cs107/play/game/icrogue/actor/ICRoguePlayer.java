package ch.epfl.cs107.play.game.icrogue.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Fire;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;
import java.util.Collections;
import java.util.List;
public class ICRoguePlayer extends ICRogueActor implements Interactor{

    private final static int MOVE_DURATION = 8;
    
    private float hp;
	private Sprite sprite;
    private boolean equipW;
    private boolean distInteraction;
    private ICRoguePlayerInteractionHandler handler;

	//* CONSTRUCTOR
	public ICRoguePlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
        super(owner, orientation, coordinates);

		this.hp = 10;
		sprite = new Sprite(spriteName, 1.f, 1.f,this);
        distInteraction = false;
        handler = new ICRoguePlayerInteractionHandler();
        equipW = false;
        resetMotion();
	}


    //* REDEFINE ICRogueActor
    @Override
    public boolean takeCellSpace(){
        return true;
    }
    
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        ((ICRogueInteractionHandler) v).interactWith((ICRoguePlayer)this, isCellInteraction);
    }


    //* REDEFINE Interactor
    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
       return Collections.singletonList (getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return distInteraction;
    }
    
    // INTERACTIONS
    @Override
    public void interactWith(Interactable other, boolean isCellInteraction) {
        other.acceptInteraction(handler, isCellInteraction);
    }

    private class ICRoguePlayerInteractionHandler implements ICRogueInteractionHandler{
        @Override
        public void interactWith(Cherry cherry, boolean isCellInteraction) {
            cherry.collect();
        
        }
        @Override
        public void interactWith(Staff staff, boolean isCellInteraction) {
            if(wantsViewInteraction()){
                staff.collect();
                equipW = true;
            }
        
        }
    }
    
    
    //* KEY ACTION
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
            new Fire(getOwnerArea(), orientation, getCurrentMainCellCoordinates().jump(getOrientation().toVector()));
            // fireball.setSprite(new Sprite("zelda/bridge", 1f, 1f, fireball));
        }
    }

    private void viewInteraction(Button b){
        if(b.isPressed()) {
            distInteraction = true;
        }
        if (isDisplacementOccurs()){
            distInteraction = false;
        }
    }

    
    //* UPDATE
    public void update(float deltaTime){
        Keyboard keyboard = getOwnerArea().getKeyboard();
        
        moveIfPressed(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        moveIfPressed(Orientation.UP, keyboard.get(Keyboard.UP));
        moveIfPressed(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        moveIfPressed(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
        if (equipW){
            launchFireball(getOrientation(), keyboard.get(Keyboard.X));
        }
            
        viewInteraction(keyboard.get(Keyboard.W));

        super.update(deltaTime);
    }

    //* DRAW
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

}