package ch.epfl.cs107.play.game.icrogue;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;
import ch.epfl.cs107.play.window.Window;

public class ICRogueBehavior extends AreaBehavior{

    public enum ICRogueCellType{
		NONE(0,false), // Should never been used except in the toType method
		GROUND(-16777216, true), // traversable 
		WALL(-14112955, false), // non traversable 
		HOLE(-65536, true);

		final int type;
		final boolean isWalkable;

		// Constructor
		ICRogueCellType(int type, boolean isWalkable){
			this.type = type;
			this.isWalkable = isWalkable;
		}

		// ToType
		public static ICRogueCellType toType(int type){
			for(ICRogueCellType ict : ICRogueCellType.values()){
				if(ict.type == type)
					return ict;
			}
			// When you add a new color, you can print the int value here before assign it to a type
			System.out.println(type);
			return NONE;
		}
	}

	/**
	 * Default ICRogueBehavior Constructor
	 * @param window (Window), not null
	 * @param name (String): Name of the Behavior, not null
	 */
	public ICRogueBehavior(Window window, String name){
		super(window, name);
		int height = getHeight();
		int width = getWidth();
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width ; x++) {
				ICRogueCellType color = ICRogueCellType.toType(getRGB(height-1-y, x));
				setCell(x,y, new ICRogueCell(x,y,color));
			}
		}
	}
	
	/**
	 * Cell adapted to the ICRogue game
	 */
	public class ICRogueCell extends Cell {
		/// Type of the cell following the enum
		private final ICRogueCellType type;
		
		/**
		 * Default ICRogueCell Constructor
		 * @param x (int): x coordinate of the cell
		 * @param y (int): y coordinate of the cell
		 * @param type (EnigmeCellType), not null
		 */
		public ICRogueCell(int x, int y, ICRogueCellType type){
			super(x, y);
			this.type = type;
		}
	
		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			for(Interactable val : entities){
				if(val.takeCellSpace() && entity.takeCellSpace()){
					return false;
				}
			}
			return !(!type.isWalkable && entity.takeCellSpace());

	    }
	    
		@Override
		public boolean isCellInteractable() {
			return true;
		}

		@Override
		public boolean isViewInteractable() {
			return false;
		}

		public ICRogueCellType getType(){
			return this.type;
		}

		public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
			((ICRogueInteractionHandler) v).interactWith(this, isCellInteraction);
		}

	}
}
