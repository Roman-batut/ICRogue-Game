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
		Level0 level0 = new Level0();

		level0.generateFixedMap();
		ICRogueRoom[][] carte = level0.getCarte();

		for (ICRogueRoom[] rooms : carte) {
			for (ICRogueRoom room : rooms) {
				addArea(room);
			}
		}
		
		ICRogueRoom currentRoom = level0.getStartingRoom();
		player = new ICRoguePlayer(currentRoom, Orientation.UP, new DiscreteCoordinates(2, 2), "zelda/player");
		
        setCurrentArea(currentRoom.getTitle(), true);
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

}
