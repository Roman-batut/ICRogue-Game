package ch.epfl.cs107.play.game.icrogue.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.icrogue.ICRogue;
import ch.epfl.cs107.play.game.icrogue.ICRogueBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.game.icrogue.actor.Connector.State;

abstract public class ICRogueRoom extends Area implements Logic{

	private ICRogueBehavior behavior;
    private DiscreteCoordinates roomCoordinates;
    private String behaviorName; 
    private boolean isVisited;
    private boolean open;

    private List<Connector> connectors;

    
    //* CONSTRUCTOR
    public ICRogueRoom(List<DiscreteCoordinates> connectorsCoordinates, List<Orientation> orientations, String behaviorName, DiscreteCoordinates roomCoordinates){
        assert connectorsCoordinates.size() == orientations.size();
        this.roomCoordinates = roomCoordinates;
        this.behaviorName = behaviorName;
        isVisited = Logic.TRUE.isOff();
        
        connectors = new ArrayList<>();
        int i = 0;
        for(DiscreteCoordinates val : connectorsCoordinates){
            Connector connector = new Connector((Area)this, orientations.get(i), val);
            connectors.add(connector);
            i++;
        }
    }

    // * GETTERS
    public abstract DiscreteCoordinates getPlayerSpawnPosition();
    
    public String getBehaviorName(){ 
        return behaviorName; 
    }

    // * Setters
    public void setConnectorDestination(String destination, ConnectorInRoom connector){
        Connector currentConnector = connectors.get(connector.getIndex());
        currentConnector.setDestination(destination);
    }

    public void setConnector(String destination, ConnectorInRoom connector){
        Connector currentConnector = connectors.get(connector.getIndex());
        setConnectorDestination(destination, connector);
        currentConnector.setType(State.CLOSED);
    }

    public void lockRoomConnector(ConnectorInRoom connector, int keyId){
        Connector currentConnector = connectors.get(connector.getIndex());
        currentConnector.setType(State.LOCK);
        currentConnector.setKeyId(keyId);
    }


    // * METHODS 
    public void openConnectors(){
        for(Connector val : connectors){
            if(val.getType() == State.CLOSED){
                val.setType(State.OPEN);
            }
        }
    }

    public void isVisited(){
        isVisited = true;
    }


    // * REDEFINE Area
    @Override
    public final float getCameraScaleFactor() {
        return ICRogue.CAMERA_SCALE_FACTOR;
    }

    // * REDEFINE Logic 
    @Override
    public boolean isOn() {
        return isVisited;
    }

    @Override
    public boolean isOff() {
        return !isVisited;
    }

    @Override
    public float getIntensity() {
        return 0;
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
        if(isOn() && !open){
            openConnectors();
            open = true;
        }
        super.update(deltaTime);
        
    }

}
