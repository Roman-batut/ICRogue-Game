package ch.epfl.cs107.play.game.icrogue;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.game.icrogue.area.level0.Level0;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICRogue extends AreaGame{

    public final static float CAMERA_SCALE_FACTOR = 11.f;

	private ICRogueRoom currentRoom;
	private ICRoguePlayer player;

	//* INIT LEVEL
	private void initLevel(){
		Level0 level = new Level0(this);
		currentRoom = (ICRogueRoom)setCurrentArea(level.getStartingRoom().getTitle(), true);
		player = new ICRoguePlayer(currentRoom, Orientation.UP, new DiscreteCoordinates(2, 2), "zelda/player");
		currentRoom.registerActor(player);
	}


	//* GETTER
	@Override
	public String getTitle() {
		return "ICRogue";
	}


	//* BEGIN
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			initLevel();
			return true;
		}
		return false;
	}
    
	//* UPDATE
	@Override
	public void update(float deltaTime) {
		Keyboard keyboard = currentRoom.getKeyboard();
        restart(keyboard.get(Keyboard.R));
		if(player.getPassing()){
			switchRoom();
		}
		super.update(deltaTime);
	}

	//* END
	@Override
	public void end() {
	}

	//* RESTART
	private void restart(Button b){
        if(b.isPressed()){
            begin(getWindow(), getFileSystem());
        }
    }

	//* SWITCH AREA 
	protected void switchRoom() {
		
		player.leaveRoom();
		
		currentRoom = (ICRogueRoom)setCurrentArea(player.getdestination(), false);
		
		player.enterRoom(currentRoom, new DiscreteCoordinates(2, 1));
		player.setPassing(false);
	}

}
