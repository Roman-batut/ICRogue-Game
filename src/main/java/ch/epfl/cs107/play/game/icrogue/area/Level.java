package ch.epfl.cs107.play.game.icrogue.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Level{

    private ICRogueRoom[][] Carte;
    private DiscreteCoordinates bossRoomCoordinates;
    private ICRogueRoom bossRoom;
    //private String StartingRoomString;
    private ICRogueRoom StartingRoom;
    

    // * Constructor
    public Level(int height, int width, DiscreteCoordinates spawnpos) {
        Carte = new ICRogueRoom[height][width];
        bossRoomCoordinates = new DiscreteCoordinates(0, 0);
        setRoom(bossRoomCoordinates, bossRoom);
        generateFixedMap();
    }

    protected void setRoom(DiscreteCoordinates coords, ICRogueRoom room) {
        Carte[coords.x][coords.y] = room;
    }
    
    protected void setRoomConnectorDestination(DiscreteCoordinates coords, String destination, ConnectorInRoom connector) {
        ICRogueRoom currentRoom = Carte[coords.x][coords.y];
        
        currentRoom.setConnectorDestination(destination, connector);
    }

    protected void setRoomConnector(DiscreteCoordinates coords, String destination, ConnectorInRoom connector) {
        ICRogueRoom currentRoom = Carte[coords.x][coords.y];
    
        currentRoom.setConnector(destination, connector);
    }

    protected void lockRoomConnector(DiscreteCoordinates coords, ConnectorInRoom connector, int keyId) {
        ICRogueRoom currentRoom = Carte[coords.x][coords.y];

        currentRoom.lockRoomConnector(connector, keyId);
    }
    
    protected void setStartingRoom(DiscreteCoordinates coords) {
       // StartingRoomString = Carte[coords.x][coords.y].getTitle()+coords.x+coords.y;
        StartingRoom = Carte[coords.x][coords.y];

    }

    abstract protected void generateFixedMap();
    

    //* Getter
    public ICRogueRoom[][] getCarte(){
        return Carte.clone();
    }

    public ICRogueRoom getStartingRoom(){
        return StartingRoom;
    }
}
