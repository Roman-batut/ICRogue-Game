package ch.epfl.cs107.play.game.icrogue.area;

import java.util.ArrayList;
import java.util.List;

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
    protected Level(boolean randomMap, DiscreteCoordinates startPosition,int[] roomsDistribution, int width, int height, ICRogue jeu) {
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
            // generateRandomMap();
            printMap(generateRandomRoomPlacement());
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
        int roomsToPlace = nbRooms-1;
        
        MapState[][] placementmap = new MapState[Carte.length][Carte.length];
        
        for(int i=0 ; i<placementmap.length ; i++){
            for(int j=0 ; j<placementmap[0].length ; j++){
                placementmap[i][j] = MapState.NULL;
            }
        }

        int posx = Carte.length/2; int posy =  Carte.length/2;
        placementmap[posx][posy] = MapState.PLACED;

        while(roomsToPlace > 0){
            int[][] freeSlotsPlacement = freeSlots(placementmap, posx, posy, MapState.NULL)[0];
            int freeSlots = freeSlots(placementmap, posx, posy, MapState.NULL)[1][0][0];
            
            List<Integer> freeSlotsPlacementIndex = new ArrayList<Integer>();
            for(int i = 0; i<freeSlotsPlacement.length; i++){
                freeSlotsPlacementIndex.add(i);
            }

            int maxRoomsToPlace = maxRoomsToPlace(minimum(roomsToPlace, freeSlots));

            List<Integer> chosePlaces = RandomHelper.chooseKInList(maxRoomsToPlace, freeSlotsPlacementIndex);
            
            ArrayList<Integer> roomsDistributionArray = new ArrayList<Integer>();
            for(int v : roomsDistribution){ roomsDistributionArray.add(v); }
    
            for(int index : chosePlaces){
                int x = freeSlotsPlacement[index][0];
                int y = freeSlotsPlacement[index][1];
                placementmap[x][y] = MapState.PLACED;
            }

            placementmap[posx][posy] = MapState.EXPLORED;
            int[] pos = {0};  
            if(chosePlaces.size() == 1){ 
                pos = freeSlotsPlacement[chosePlaces.get(0)]; 
            }
            else{
                pos = freeSlotsPlacement[RandomHelper.chooseKInList(1, chosePlaces).get(0)];
            }
            posx = pos[0]; posy = pos[1];
        }            

        List<Integer> notplacedListx = new ArrayList<Integer>(); 
        List<Integer> notplacedListy = new ArrayList<Integer>();
        for(int i=0 ; i<placementmap.length ; i++){
            for(int j=0 ; j<placementmap[0].length ; j++){
                if(placementmap[i][j] == MapState.NULL){
                    notplacedListx.add(i); notplacedListy.add(j);
                }
            }
        }

        boolean isPlaced = false;
        while(!isPlaced){
            int x = RandomHelper.chooseKInList(1, notplacedListx).get(0);
            int y = notplacedListy.get(notplacedListx.indexOf(x));
            int nbrfreeslots = freeSlots(placementmap , x, y, MapState.PLACED)[1][0][0];
            if(nbrfreeslots > 0){
                placementmap[x][y] = MapState.BOSS_ROOM;
                isPlaced =true;
            }
        }
        
        return placementmap;
    }


    private int[][][] freeSlots(MapState[][] map, int x, int y, MapState type){
        int[][][] freeSlots = {{{0,0},{0,0},{0,0},{0,0}},{{0}}}; //HAUT, BAS, GAUCHE, DROITE, nbfreeSlots

        if(x+1 <= map[0].length && map[x+1][y] == type){
            freeSlots[0][0][0] = x+1;
            freeSlots[0][0][1] = y;
            freeSlots[1][0][0]++;
        }
        if(x-1 >= 0 && map[x-1][y] == type){
            freeSlots[0][1][0] = x-1;
            freeSlots[0][1][1] = y;
            freeSlots[1][0][0]++;
        }

        if(y-1 >= 0 && map[x][y-1] == type){
            freeSlots[0][2][0] = x;
            freeSlots[0][2][1] = y-1;
            freeSlots[1][0][0]++;
        }
        if(y+1 <= map.length && map[x][y+1] == type){
            freeSlots[0][3][0] = x;
            freeSlots[0][3][1] = y+1;
            freeSlots[1][0][0]++;
        }

        return freeSlots;
    }

    private int maxRoomsToPlace(int max){
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i=0 ; i<max ; i++){ 
            list.add(i); 
        }

        return (RandomHelper.chooseKInList(1, list).get(0));
    }

    private int minimum(int a, int b){
        if(a<b){return a;}
        return b;

    }

    private void printMap(MapState[][] map) { System.out.println("Generated map:");
        System.out.print(" | ");
        for (int j = 0; j < map[0].length; j++) {
            System.out.print(j + " "); }
            System.out.println(); System.out.print("--|-");
        for (int j = 0; j < map[0].length; j++) {
            System.out.print("--"); }
            System.out.println();
        for (int i = 0; i < map.length; i++) { System.out.print(i + " | ");
        for (int j = 0; j < map[i].length; j++) {
            System.out.print(map[j][i] + " "); }
            System.out.println(); }
            System.out.println();
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
