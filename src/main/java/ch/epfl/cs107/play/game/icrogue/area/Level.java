package ch.epfl.cs107.play.game.icrogue.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.icrogue.actor.Connector;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room.Level0Connectors;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Level{
    ICRogueRoom [][] Carte;
    DiscreteCoordinates bossRoomCoordinates;
    public Level(int height, int width, DiscreteCoordinates spawnpos){
        Carte = new ICRogueRoom[height][width];
        bossRoomCoordinates =new DiscreteCoordinates(0, 0);
        generateFixedMap();
    }
    protected void setRoom(DiscreteCoordinates coords, ICRogueRoom room){
        Carte[coords.x][coords.y]= room; 
    }
    protected void setRoomConnectorDestination(DiscreteCoordinates coords, String destination, ConnectorInRoom connector){

    }
    protected void setRoomConnector(DiscreteCoordinates coords, String destination, ConnectorInRoom connector){

    }
    protected void lockRoomConnector(DiscreteCoordinates coords, ConnectorInRoom connector, int keyId){

    }
    protected void setRoomName(){

    }
    abstract void generateFixedMap();
    //TODO FUNCTION IN LEVEL
} 
