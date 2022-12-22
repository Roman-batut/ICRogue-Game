package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.actor.Marchand;
import ch.epfl.cs107.play.game.icrogue.actor.items.BoostDmgStand;
import ch.epfl.cs107.play.game.icrogue.actor.items.CoeurStand;
import ch.epfl.cs107.play.game.icrogue.actor.items.Item;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0ShopRoom  extends Level0ItemRoom{

    private Marchand seller;
    public Level0ShopRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
        seller =new Marchand(this,Orientation.UP, new DiscreteCoordinates(5, 3));
        super.addItem(new BoostDmgStand(this, Orientation.UP, new DiscreteCoordinates(4, 4)));
        super.addItem(new CoeurStand(this, Orientation.UP, new DiscreteCoordinates(6, 4)));
        //TODO Auto-generated constructor stub
    }

    // * REDEFINE Logic
    /**
     * @return (boolean): true if the signal is considered as on(always escapable)
     */
    @Override
    public boolean isOn() {
        return true;
    }

    /**
     * @return (boolean): true if the signal is considered as off
     */
    @Override
    public boolean isOff() {
        return !isOn();
    }

    //CREATE AREA
    
    @Override
    protected void createArea() {
        registerActor(seller);
        super.createArea();

    }

    
}
