package ch.epfl.cs107.play.game.icrogue.actor.items;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;

public interface Stand extends Interactable{
    public int cost();

     /**
     * Indicate if the current Interactable take the whole cell space or not
     * @return (boolean)
     */
    default public boolean takeCellSpace(){
        return true;
    }
    
     /**
     * @return (boolean): true if this is able to have view interactions
     */
    default public boolean isViewInteractable() {
        return true;
    }
    
}
