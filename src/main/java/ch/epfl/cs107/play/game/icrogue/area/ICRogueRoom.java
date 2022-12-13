package ch.epfl.cs107.play.game.icrogue.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.ICRogue;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.game.icrogue.actor.Connector.State;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room.Level0Connectors;

abstract public class ICRogueRoom extends Area{

	private ICRogueBehavior behavior;
    private DiscreteCoordinates roomCoordinates;
    private String behaviorName; 

    private ArrayList<Connector> connectors;

    
    //* CONSTRUCTOR
    public ICRogueRoom(List<DiscreteCoordinates> connectorsCoordinates, List<Orientation> orientations, String behaviorName, DiscreteCoordinates roomCoordinates){
        assert connectorsCoordinates.size() == orientations.size();
        this.roomCoordinates = roomCoordinates;
        this.behaviorName = behaviorName;
        
        connectors = new ArrayList<>();
        int i = 0;
        for(DiscreteCoordinates val : connectorsCoordinates){
            Connector connector = new Connector((Area)this, orientations.get(i), val);
            connectors.add(connector);
            i++;
        }
    }


    //* REDEFINE Area
    @Override
    public final float getCameraScaleFactor() {
        return ICRogue.CAMERA_SCALE_FACTOR;
    }


    //* GETTERS
    public abstract DiscreteCoordinates getPlayerSpawnPosition();
    
    public String getBehaviorName(){ 
        return behaviorName; 
    }

    
    //* Setters
    public void setConnectorDestination(String destination, ConnectorInRoom connector){
        Connector currentConnector = connectors.get(connector.getIndex());
        currentConnector.setDestination(destination);
    }
    public void setConnector(String destination, ConnectorInRoom connector){
        Connector currentConnector = connectors.get(connector.getIndex());
        setConnectorDestination(destination, connector);
        currentConnector.setType(State.OPEN);
    }
    public void lockRoomConnector(ConnectorInRoom connector, int keyId){
        Connector currentConnector = connectors.get(connector.getIndex());
        currentConnector.setType(State.LOCK);
        currentConnector.setKeyId(keyId);
    }
    
    //* CREATE AREA
    protected void createArea(){
        for(Connector val : connectors){
            registerActor(val);
        }   
    }
    
    //* BEGIN
    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Set the behavior map
        	behavior = new ICRogueBehavior(window, behaviorName);
            setBehavior(behavior);
            createArea();
            return true;
        }
        return false;
    }
    
    //* UPDATE
    @Override
    public void update(float deltaTime){
        // Keyboard keyboard = this.getKeyboard();
        
        // if(keyboard.get(Keyboard.O).isPressed()){
        //     for(Connector val : connectors){
        //         val.setType(State.OPEN);
        //     }
        // }
        // if(keyboard.get(Keyboard.L).isPressed()){
        //     connectors.get(0).setType(State.LOCK);
        // }
        // if(keyboard.get(Keyboard.T).isPressed()){
        //     for(Connector val : connectors){
        //         if(val.getType() == State.CLOSED)
        //             val.setType(State.OPEN);
        //         if(val.getType() == State.OPEN)
        //             val.setType(State.CLOSED);
        //     }
        // }
        
        super.update(deltaTime);
    }
    
}
