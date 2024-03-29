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
	private boolean hasWin;

	// * INIT LEVEL
	/**
	 * Init the level
	 */
	private void initLevel(){
		Level level = new Level0(this, true);
		currentRoom = (ICRogueRoom)setCurrentArea(level.getStartingRoom().getTitle(), true);
		currentLevel = level;
		player = new ICRoguePlayer(currentRoom, Orientation.UP, new DiscreteCoordinates(2, 2), "zelda/player");
		player.enterRoom(currentRoom, new DiscreteCoordinates(2, 1));
	}

	// * GETTER
	@Override
	public String getTitle() {
		return "ICRogue";
	}


	// * BEGIN
	/**
     * Initialises game state : display and controls
     * @param window (Window): display context. Not null
     * @param fileSystem (FileSystem): given file system. Not null
     * @return (boolean): whether the game was successfully started
     */
	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		if (super.begin(window, fileSystem)) {
			initLevel();
			return true;
		}
		return false;
	}
    
	// * UPDATE
	/**
     * Simulates a single time step.
     * @param deltaTime elapsed time since last update, in seconds, non-negative
     */
	@Override
	public void update(float deltaTime) {
		Keyboard keyboard = currentRoom.getKeyboard();
        restart(keyboard.get(Keyboard.R));
		if(player.getPassing()){
			switchRoom();
		}
		if(currentLevel.getBossRoom().isOn() && !hasWin){
			System.out.println("Win");
			hasWin = true;
		}
		if(!player.isAlive()){
			System.out.println("Game Over");
			begin(getWindow(), getFileSystem());
		}
		super.update(deltaTime);
	}

	// * END
	/**
	 * Cleans up things, called even if initialisation failed.
	 */
	@Override
	public void end() {

	}

	// * RESTART
	/**
	 * Restart the game
	 * @param b (Button): the button to restart
	 */
	private void restart(Button b){
        if(b.isPressed()){
			hasWin =false;
            begin(getWindow(), getFileSystem());
        }
    }

	// * SWITCH AREA 
	/**
	 * Switch the area
	 */
	protected void switchRoom() {
		player.leaveRoom();
		
		currentRoom = (ICRogueRoom)setCurrentArea(player.getDestination(), false);
		
		player.enterRoom(currentRoom, player.getDestinationOfArrive());
		player.setPassing(false);
	}

}
