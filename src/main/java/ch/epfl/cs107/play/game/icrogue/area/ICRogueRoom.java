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

    // * CONSTRUCTOR
    /**
     * @param connectorsCoordinates
     * @param orientations
     * @param behaviorName
     * @param roomCoordinates
     */
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
    
    public DiscreteCoordinates getCoordinates(){
        return roomCoordinates;
    }

    // * SETTERS
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
    public void setConnectorDestinationcoords(ConnectorInRoom connector){
        Connector currentConnector = connectors.get(connector.getIndex());
        currentConnector.setDestinationCoordinates(connector.getDestination());
    }


    // * REDEFINE Area
    /** 
     * @return (float): camera scale factor, assume it is the same in x and y direction 
     */
    @Override
    public final float getCameraScaleFactor() {
        return ICRogue.CAMERA_SCALE_FACTOR;
    }

    // * REDEFINE Logic 
    /**
     * @return (boolean): true if the signal is considered as on
     */
    @Override
    public boolean isOn() {
        return isVisited;
    }

    /**
     * @return (boolean): true if the signal is considered as off
     */
    @Override
    public boolean isOff() {
        return !isVisited;
    }

    @Override
    public float getIntensity() {
        return 0;
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
    

    // * CREATE AREA
    /**
     * Create the area
     * Register all actors
     */
    protected void createArea(){
        for(Connector val : connectors){
            registerActor(val);
        }   
    }
    
    // * BEGIN
    /**
     * Begin the room
     * @param window (Window) : graphic context, not null
     * @param fileSystem (FileSystem) : file system, not null
     * @return (boolean) : true if the room is correctly initialized
     */
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
    
    // * UPDATE
    /**
     * Update the room
     * @param deltaTime (float) : elapsed time since last update, in seconds, non negative
     */
    @Override
    public void update(float deltaTime){
        if(isOn() && !open){
            openConnectors();
            open = true;
        }
        super.update(deltaTime);
        
    }

}
