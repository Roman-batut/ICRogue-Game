package ch.epfl.cs107.play.game.icrogue.actor.Health;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.naming.directory.InvalidAttributeIdentifierException;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.icrogue.actor.ICRoguePlayer;
import ch.epfl.cs107.play.game.icrogue.actor.Health.Heart.HealthState;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class healthbar extends AreaEntity{
    
    private String spritename;
    List<Heart> health;
    private ICRoguePlayer owner;

    public healthbar(Area area, Orientation orientation, DiscreteCoordinates position, ICRoguePlayer player) {
        super(area, orientation, position);
        health= new ArrayList<Heart>();
        health.add(new Heart(area, orientation, position));
        health.add(new Heart(area, orientation, new DiscreteCoordinates(position.x+1, position.y)));
        health.add(new Heart(area, orientation, new DiscreteCoordinates(position.x+2, position.y)));
        owner = player;
        //TODO Auto-generated constructor stub
    }
    
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        // TODO Auto-generated method stub
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }
    @Override
    public boolean takeCellSpace() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean isCellInteractable() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean isViewInteractable() {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public void draw(Canvas canvas) {
        for(Heart val: health){
            val.draw(canvas);
        }
        // TODO Auto-generated method stub
    }
    public void update(float deltaTime){
            // switch (owner.hp()){
            //     case 6 :
            //         for(Heart val: health){
            //             val.setState(HealthState.FULL);
            //         }  
            //     case 5 : 
            //         health.get(0 ).setState(HealthState.HALF);
            //         health.get(1 ).setState(HealthState.FULL);
            //         health.get(2).setState(HealthState.FULL);
            //     case 4 :
            //         health.get(0 ).setState(HealthState.EMPTY);
            //         health.get(1 ).setState(HealthState.FULL);
            //         health.get(2).setState(HealthState.FULL);
            //     case 3:
            //         health.get(0 ).setState(HealthState.EMPTY);
            //         health.get(1 ).setState(HealthState.HALF);
            //         health.get(2).setState(HealthState.FULL);
            //     case 2:
            //         health.get(0 ).setState(HealthState.EMPTY);
            //         health.get(1 ).setState(HealthState.EMPTY);
            //         health.get(2).setState(HealthState.FULL);
            //     case 1 : 
            //         health.get(0 ).setState(HealthState.EMPTY);
            //         health.get(1 ).setState(HealthState.EMPTY);
            //         health.get(2).setState(HealthState.HALF);
            //     case 0 :
            //         for(Heart val: health){
            //             val.setState(HealthState.EMPTY);
            //         }  
            //     break;
                    
            // }
            if(owner.hp() == 0){
                for(Heart val: health){
                            val.setState(HealthState.EMPTY);
                    } 
            }else if(owner.hp() == 1){
                health.get(2 ).setState(HealthState.EMPTY);
                health.get(1 ).setState(HealthState.EMPTY);
                 health.get(0).setState(HealthState.HALF);
            }else if(owner.hp() == 2){
                health.get(2 ).setState(HealthState.EMPTY);
                health.get(1 ).setState(HealthState.EMPTY);
                 health.get(0).setState(HealthState.FULL);
            }else if(owner.hp() == 3){
                health.get(2 ).setState(HealthState.EMPTY);
                health.get(1 ).setState(HealthState.HALF);
                 health.get(0).setState(HealthState.FULL);
            }else if(owner.hp() == 4){
                health.get(2).setState(HealthState.EMPTY);
                health.get(1 ).setState(HealthState.FULL);
                 health.get(0).setState(HealthState.FULL);
            }else if(owner.hp() == 5){
                health.get(2 ).setState(HealthState.HALF);
                health.get(1 ).setState(HealthState.FULL);
                 health.get(0).setState(HealthState.FULL);
            }else if(owner.hp() == 6){
                health.get(2 ).setState(HealthState.FULL);
                health.get(1 ).setState(HealthState.FULL);
                 health.get(0).setState(HealthState.FULL);

            }
    }

}