package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.swing.SoundItem;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCell;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Fire;
import ch.epfl.cs107.play.game.icrogue.handler.ICRogueInteractionHandler;

import java.util.List;


/**
 * Represent Interactable object (i.e. Interactor can interact with it)
 * @see Interactor
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactable {

    /**
     * Get this Interactor's current occupying cells coordinates
     * @return (List of DiscreteCoordinates). May be empty but not null
     */
    List<DiscreteCoordinates> getCurrentCells();

    /**
     * Indicate if the current Interactable take the whole cell space or not
     * i.e. only one Interactable which takeCellSpace can be in a cell
     * (how many Interactable which don't takeCellSpace can also be in the same cell)
     * @return (boolean)
     */
    boolean takeCellSpace();


    /**@return (boolean): true if this is able to have cell interactions*/
    boolean isCellInteractable();

    /**@return (boolean): true if this is able to have view interactions*/
    boolean isViewInteractable();

    /** Call directly the interaction on this if accepted
     * @param v (AreaInteractionVisitor) : the visitor
     * */
    default void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        if(this instanceof ICRogueCell){((ICRogueInteractionHandler) v).interactWith((ICRogueCell)this, isCellInteraction);}
        else if(this instanceof ICRoguePlayer){((ICRogueInteractionHandler) v).interactWith((ICRoguePlayer)this, isCellInteraction);}
        else if(this instanceof Cherry){((ICRogueInteractionHandler) v).interactWith((Cherry)this, isCellInteraction);}
        else if(this instanceof Staff){((ICRogueInteractionHandler) v).interactWith((Staff)this, isCellInteraction);}
        else if(this instanceof Fire){((ICRogueInteractionHandler) v).interactWith((Fire)this, isCellInteraction);}

    }


    /**
     * Called when this Interactable leaves a cell
     * @param coordinates left cell coordinates
     */
    void onLeaving(List<DiscreteCoordinates> coordinates);

    /**
     * Called when this Interactable enters a cell
     * @param coordinates entered cell coordinates
     */
    void onEntering(List<DiscreteCoordinates> coordinates);


    /// Interactable Listener
    interface Listener{
        /**
         * Indicate if the given Interactable entity can leave the cell at given coordinates
         * @param entity (Interactable). Not null
         * @param coordinates (List of DiscreteCoordinates). Not null
         * @return (boolean): true if the entity can leave all the given cell
         */
        boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates);

        /**
         * Indicate if the given Interactable entity can enter the cell at given coordinates
         * @param entity (Interactable). Not null
         * @param coordinates (List of DiscreteCoordinates). Not null
         * @return (boolean): true if the entity can enter all the given cell
         */
        boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates);

        /**
         * Do the given interactable entity leave the given cells
         * @param entity (Interactable). Not null
         * @param coordinates (List of DiscreteCoordinates). Not null
         */
        void leave(Interactable entity, List<DiscreteCoordinates> coordinates);

        /**
         * Do the given interactable entity enter the given cells
         * @param entity (Interactable). Not null
         * @param coordinates (List of DiscreteCoordinates). Not null
         */
        void enter(Interactable entity, List<DiscreteCoordinates> coordinates);
    }

}
