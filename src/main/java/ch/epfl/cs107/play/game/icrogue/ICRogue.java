package ch.epfl.cs107.play.game.icrogue;

import ch.epfl.cs107.play.game.areagame.AreaGame;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.game.icrogue.area.Level;
import ch.epfl.cs107.play.game.icrogue.area.level0.Level0;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class ICRogue extends AreaGame{

    public final static float CAMERA_SCALE_FACTOR = 11.f;

	private ICRogueRoom currentRoom;
	private Level currentLevel;
	private ICRoguePlayer player;

	//* INIT LEVEL
	private void initLevel(){
		Level level = new Level0(this, true);
		currentRoom = (ICRogueRoom)setCurrentArea(level.getStartingRoom().getTitle(), true);
		currentLevel = level;
		player = new ICRoguePlayer(currentRoom, Orientation.UP, new DiscreteCoordinates(2, 2), "zelda/player");
		player.enterRoom(currentRoom, new DiscreteCoordinates(2, 1));
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
		if(currentLevel.getBossRoom().isOn()){
			System.out.println("Win");
		}
		if(!player.isAlive()){
			System.out.println("Game Over");
			begin(getWindow(), getFileSystem());
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
		
		currentRoom = (ICRogueRoom)setCurrentArea(player.getDestination(), false);
		
		player.enterRoom(currentRoom, new DiscreteCoordinates(0, 2));//player.getDestinationOfArrive());
		player.setPassing(false);
	}

}
