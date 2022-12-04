package ch.epfl.cs107.play.game.icrogue;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICRogue extends AreaGame{

    public final static float CAMERA_SCALE_FACTOR = 11.f;

	private ICRoguePlayer player;
	
    private Level0Room currentRoom;

	/**
	 * Add all the areas
	 */
	private void initLevel(){
		currentRoom = new Level0Room(new DiscreteCoordinates(0,0));
		addArea(currentRoom);
        setCurrentArea(currentRoom.getTitle(), true);
		player = new ICRoguePlayer(currentRoom, Orientation.UP, new DiscreteCoordinates(2, 2), "zelda/player");
		currentRoom.registerActor(player);
	}

	@Override
	public String getTitle() {
		return "ICRogue";
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			initLevel();
			
			return true;
		}
		return false;
	}
    
	@Override
	public void update(float deltaTime) {
		Keyboard keyboard = currentRoom.getKeyboard();
        restart(keyboard.get(Keyboard.R));
		super.update(deltaTime);
		
	}

	@Override
	public void end() {
	}

	private void restart(Button b){
        if(b.isPressed()){
            begin(getWindow(), getFileSystem());
        }
    }

}
