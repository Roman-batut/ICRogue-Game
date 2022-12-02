package ch.epfl.cs107.play.game.icrogue.actor.projectiles;

import java.util.concurrent.DelayQueue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

public class Fireball extends Projectile{

    public Fireball(Area area, Orientation orientation, DiscreteCoordinates position, int dmg, int frames) {
        super(area, orientation, position, dmg, frames);
        //TODO Auto-generated constructor stub
    }
    @Override
    public void acceptInteraction(AreaInteractionVisitor v, boolean isCellInteraction) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw(Canvas canvas) {
        setSprite(new Sprite("zelda/fire", 1f, 1f, this,new RegionOfInterest(0, 0, 16, 16), new Vector(0, 0)));
        sprite.draw(canvas);
    }
    public void update(float deltaTime){
        super.update(deltaTime);
    }

 }
