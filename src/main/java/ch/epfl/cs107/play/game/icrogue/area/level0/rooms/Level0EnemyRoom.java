package ch.epfl.cs107.play.game.icrogue.area.level0.rooms;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.icrogue.actor.enemies.Enemy;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0EnemyRoom extends Level0Room{

    private List<Enemy> ennemies;

    // * CONSTRUCTOR
    /**
     * @param roomCoordinates
     */
    public Level0EnemyRoom(DiscreteCoordinates roomCoordinates) {
        super(roomCoordinates);
        ennemies = new ArrayList<Enemy>();
    }


    // * REDEFINE Logic
    /**
     * @return (boolean): true if the signal is considered as on
     */
    @Override
    public boolean isOn() {
        if(ennemies.size()== 0){
            return super.isOn();
        }
        return false;
    }

    /**
     * @return (boolean): true if the signal is considered as off
     */
    @Override
    public boolean isOff() {
        return !isOn();
    }

    
    // * METHODS 
    protected void addEnemy(Enemy e){
        ennemies.add(e);
    }
    

    // * CREATE AREA 
    @Override
    protected void createArea() {
        super.createArea();
        for(Enemy enemy : ennemies){
            registerActor(enemy);
        }
    }

    // * UPDATE
    /**
     * Update the player
     * @param deltaTime (float) : elapsed time since last update, in seconds, non negative
     */
    @Override
    public void update(float deltaTime){
        for(int i = 0; i<ennemies.size(); i++){
            if(!ennemies.get(i).isAlive()){
                unregisterActor(ennemies.get(i));
                ennemies.remove(i);
            }
        }
        super.update(deltaTime);
    }
    
}
