package ch.epfl.cs107.play.game.icrogue.area;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import ch.epfl.cs107.play.game.icrogue.ICRogue;
import ch.epfl.cs107.play.game.icrogue.RandomHelper;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Level{

    private ICRogueRoom[][] Carte;
    private DiscreteCoordinates bossRoomCoordinates;
    private ICRogueRoom bossRoom;
    private ICRogueRoom StartingRoom;
    private ICRogue jeu;
    private int[] roomsDistribution;
    private int nbRooms;

    // * Constructor
    /**
     * @param randomMap
     * @param startPosition
     * @param roomsDistribution
     * @param width
     * @param height
     * @param jeu
     */
    Level(boolean randomMap, DiscreteCoordinates startPosition,int[] roomsDistribution, int width, int height, ICRogue jeu) {
        bossRoomCoordinates = new DiscreteCoordinates(0, 0);
        this.jeu = jeu;
        this.roomsDistribution = roomsDistribution;
        
        if(!randomMap){
            Carte = new ICRogueRoom[height][width];
            generateFixedMap();
            setBossRoom(bossRoomCoordinates);
        } else{
            nbRooms = 0; 

            for(int room : roomsDistribution){
                nbRooms += room;
            }

            Carte = new ICRogueRoom[nbRooms][nbRooms];
            generateRandomMap();
        }

    }

    // * Getter
    public ICRogueRoom getStartingRoom(){
        return StartingRoom;
    }

    public ICRogueRoom getBossRoom(){
        return bossRoom;
    }
    

    // * Methods
    protected void setRoom(DiscreteCoordinates coords, ICRogueRoom room) {
        Carte[coords.x][coords.y] = room;
        jeu.addArea(room);
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
        StartingRoom = (ICRogueRoom)Carte[coords.x][coords.y];

    }
    
    protected void setBossRoom(DiscreteCoordinates coords) {
        bossRoom = (ICRogueRoom)Carte[coords.x][coords.y];

    }

    abstract protected void generateFixedMap();
    
    // Random
    protected void generateRandomMap(){
        
    }
    
    protected MapState[][] generateRandomRoomPlacement(){
        int roomsToPlace = nbRooms;
        
        MapState[][] placementmap = new MapState[Carte.length][Carte.length];
        
        for(MapState[] ligne : placementmap){
            for(MapState col : ligne){
                col = MapState.NULL; 
            }
        }

        int posx = Carte.length/2;
        int posy =  Carte.length/2;
        placementmap[posx][posy] = MapState.PLACED;
        roomsToPlace --;

        while(roomsToPlace > 0){
            int[] freeSlots = freeSlots(placementmap, posx, posy);
            int nbfreeSlots = freeSlots[4];
            int maxRoomsToPlace;

            ArrayList<Integer> list = new ArrayList<Integer>();

            if(nbfreeSlots < roomsToPlace){
                for(int i=0 ; i<nbfreeSlots ; i++){ list.add(i); }

                maxRoomsToPlace = RandomHelper.chooseKInList(1, list).get(0);
            } else{
                for(int i=0 ; i<roomsToPlace ; i++){ list.add(i); }

                maxRoomsToPlace = RandomHelper.chooseKInList(1, list).get(0);
            }

            ArrayList<Integer> list1 = new ArrayList<Integer>();
            for(int v : roomsDistribution){ list1.add(v); }

            for(int i=0 ; i<maxRoomsToPlace ; i++){
                List<Integer> roomToPlace = RandomHelper.chooseKInList(1, list1);
                
                

            }            

        }
        
        
        return placementmap;
    }

    private int[] freeSlots(MapState[][] map, int x, int y){
        int[] freeSlots = {0,0,0,0,0}; //HAUT, BAS, GAUCHE, DROITE, nbfreeSlots

        if(x+1 <= map[0].length && map[x+1][y] == MapState.NULL){
            freeSlots[0] = 1;
            freeSlots[4]++;
        }
        if(x-1 >= 0 && map[x-1][y] == MapState.NULL){
            freeSlots[1] = 1;
            freeSlots[4]++;
        }
        if(y-1 <= map.length && map[x][y-1] == MapState.NULL){
            freeSlots[2] = 1;
            freeSlots[4]++;
        }
        if(y+1 >= 0 && map[x][y+1] == MapState.NULL){
            freeSlots[3] = 1;
            freeSlots[4]++;
        }

        return freeSlots;
    }


    protected enum MapState {
    NULL, // Empty space
    PLACED, // The room has been placed but not yet explored by the room placement algorithm 
    EXPLORED, // The room has been placed and explored by the algorithm
    BOSS_ROOM, // The room is a boss room
    CREATED; // The room has been instantiated in the room map
    
    @Override
    public String toString() {
        return Integer.toString(ordinal()); }
    }

    
}
