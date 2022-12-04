package ch.epfl.cs107.play.game.icrogue.handler;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior.ICRogueCell;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.actor.items.Cherry;
import ch.epfl.cs107.play.game.icrogue.actor.items.Staff;
import ch.epfl.cs107.play.game.icrogue.actor.projectiles.Fire;

public interface ICRogueInteractionHandler extends AreaInteractionVisitor{
    
    default void interactWith(ICRogueCell cell, boolean isCellInteraction){
    }
    
    default void interactWith(ICRoguePlayer player, boolean isCellInteraction){
    }

    default void interactWith(Cherry cherry, boolean isCellInteraction){
    }

    default void interactWith(Staff staff, boolean isCellInteraction){
    }
    
    default void interactWith(Fire fireball, boolean isCellInteraction){
    }
    
}
