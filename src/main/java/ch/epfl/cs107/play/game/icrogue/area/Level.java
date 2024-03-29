package ch.epfl.cs107.play.game.icrogue.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.icrogue.ICRogue;
import ch.epfl.cs107.play.game.icrogue.RandomHelper;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public abstract class Level{

    protected enum MapState {
        NULL, // Empty space
        PLACED, // The room has been placed but not yet explored by the room placement algorithm 
        EXPLORED, // The room has been placed and explored by the algorithm
        BOSS_ROOM, // The room is a boss room
        CREATED; // The room has been instantiated in the room map
        
        @Override
        public String toString() {
            return Integer.toString(ordinal()); 
        }
    }

    private ICRogueRoom[][] Carte;
    private DiscreteCoordinates bossRoomCoordinates;
    private DiscreteCoordinates startingRoomCoordinates;
    private ICRogueRoom bossRoom;
    private ICRogueRoom StartingRoom;
    private ICRogue jeu;
    private int nbRooms;
    private MapState[][] roomsPlacement;
    private int[] roomsDistribution;

    // * Constructor
    /**
     * @param randomMap
     * @param startPosition
     * @param roomsDistribution
     * @param width
     * @param height
     * @param jeu
     */
    protected Level(boolean randomMap, DiscreteCoordinates startPosition,int[] roomsDistribution,  int height,int width, ICRogue jeu) {
        this.jeu = jeu;
        this.roomsDistribution = roomsDistribution;

        if(!randomMap){
            Carte = new ICRogueRoom[height][width];
            bossRoomCoordinates = new DiscreteCoordinates(0, 0);
            startingRoomCoordinates = new DiscreteCoordinates(1, 1);
            generateFixedMap();
            setBossRoom(bossRoomCoordinates);
            setStartingRoom(startingRoomCoordinates);
            
        } else{
            nbRooms = 0; 

            for(int room : roomsDistribution){
                nbRooms += room;
            }

            Carte = new ICRogueRoom[nbRooms][nbRooms];
            roomsPlacement = generateRandomRoomPlacement();
            generateRandomMap();
        }

    }

    // * GETTER
    public ICRogueRoom getStartingRoom(){
        return StartingRoom;
    }

    public ICRogueRoom getBossRoom(){
        return bossRoom;
    }
    

    // * METHODS
    /**
     * Set a given room at the given coordinates
     * @param coords
     * @param room
     */
    protected void setRoom(DiscreteCoordinates coords, ICRogueRoom room) {
        Carte[coords.x][coords.y] = room;
        jeu.addArea(room);
    }
    
    /**
     * Set destination of a given connector in a given room coordinates to a room name
     * @param coords
     * @param destination
     * @param connector
    */
    protected void setRoomConnectorDestination(DiscreteCoordinates coords, String destination, ConnectorInRoom connector) {
        ICRogueRoom currentRoom = Carte[coords.x][coords.y];
        
        currentRoom.setConnectorDestination(destination, connector);
    }

    /**
     * Set a connector in a given room coordinates
     * @param coords
     * @param destination
     * @param connector
     */
    protected void setRoomConnector(DiscreteCoordinates coords, String destination, ConnectorInRoom connector) {
        ICRogueRoom currentRoom = Carte[coords.x][coords.y];
    
        currentRoom.setConnector(destination, connector);
    }

    /**
     * Set a locked connector in a given room coordinates with a given key id
     * @param coords
     * @param connector
     * @param keyId
     */
    protected void lockRoomConnector(DiscreteCoordinates coords, ConnectorInRoom connector, int keyId) {
        ICRogueRoom currentRoom = Carte[coords.x][coords.y];

        currentRoom.lockRoomConnector(connector, keyId);
    }

    /**
     * Set destination of a given connector to a given room coordinates
     * @param coords
     * @param connector
    */
    protected void setConnectorDestinationcoords(DiscreteCoordinates coords, ConnectorInRoom connector){
        ICRogueRoom currentRoom = Carte[coords.x][coords.y];

        currentRoom.setConnectorDestinationcoords(connector);
    }
    
    /**
     * Set the starting room of the level
     * @param coords
     */
    protected void setStartingRoom(DiscreteCoordinates coords) {
        StartingRoom = (ICRogueRoom)Carte[coords.x][coords.y];

    }
    
    /**
     * Set the boss room of the level
     * @param coords
     */
    protected void setBossRoom(DiscreteCoordinates coords) {
        bossRoom = (ICRogueRoom)Carte[coords.x][coords.y];
    }

    /**
     * Generate a fixed map
     */
    abstract protected void generateFixedMap();

    /**
     * Set up the connectors of a given room
     * @param roomsPlacement
     * @param room
    */
    abstract protected void setUpConnector(MapState[][] roomsPlacement, ICRogueRoom room);

    /**
     * Create a specific room at a given coordinates
     * @param ordinal
     * @param coordinates
     */
    abstract protected ICRogueRoom createRoom(int ordinal, DiscreteCoordinates coordinates);

    // * RANDOM
    // * Define the random type for each good position
    protected void generateRandomMap(){
        for(int i=0 ; i<roomsDistribution.length ; ++i){
            int k = roomsDistribution[i];

            //Usable map coords
            List<Integer[]> usableRoomsPlacement = new ArrayList<Integer[]>();
            for(int j=0 ; j<roomsPlacement.length ; j++){
                for(int h=0 ; h<roomsPlacement[0].length ; h++){
                    if(roomsPlacement[j][h] == MapState.PLACED || roomsPlacement[j][h] == MapState.EXPLORED){
                        Integer[] tab = {j,h};
                        usableRoomsPlacement.add(tab);
                    }
                    if(roomsPlacement[j][h] == MapState.BOSS_ROOM){
                        bossRoomCoordinates = new DiscreteCoordinates(j, h);
                    }
                }
            }

            //Emplacements map coords
            List<Integer> index = new ArrayList<Integer>();
            for(int val = 0; val<usableRoomsPlacement.size(); val++){
                index.add(val);
            }

            List<Integer> emplacements = RandomHelper.chooseKInList(k, index);            
            
            //Placement
            for(int l : emplacements){
                int x = usableRoomsPlacement.get(l)[0]; int y = usableRoomsPlacement.get(l)[1];
                DiscreteCoordinates coords = new DiscreteCoordinates(x,y);
                setRoom(coords, createRoom(i, coords));
                if(i == 4){
                    setStartingRoom(coords);
                }
                
                roomsPlacement[x][y] = MapState.CREATED;
                setUpConnector(roomsPlacement, Carte[x][y]);
            }
        }
        setRoom(bossRoomCoordinates, createRoom(-1, bossRoomCoordinates));
        setBossRoom(bossRoomCoordinates);
        roomsPlacement[bossRoomCoordinates.x][bossRoomCoordinates.y] = MapState.CREATED;
        setUpConnector(roomsPlacement, Carte[bossRoomCoordinates.x][bossRoomCoordinates.y]);
    
    }
    // * Define the random position

    protected MapState[][] generateRandomRoomPlacement(){
        int roomsToPlace = nbRooms;
        
        MapState[][] placementmap = new MapState[Carte.length][Carte.length];
        
        for(int i=0 ; i<placementmap.length ; i++){
            for(int j=0 ; j<placementmap[0].length ; j++){
                placementmap[i][j] = MapState.NULL;
            }
        }

        int posx = Carte.length/2; int posy =  Carte.length/2;
        placementmap[posx][posy] = MapState.PLACED;
        roomsToPlace --;
        
        while(roomsToPlace > 0){
            List<Integer[]> freeSlotsPlacement = freeSlots(placementmap, posx, posy, MapState.NULL);
            int freeSlots = freeSlotsPlacement.size();
            
            List<Integer> freeSlotsPlacementIndex = new ArrayList<Integer>();
            for(int i = 0; i<freeSlotsPlacement.size(); i++){
                freeSlotsPlacementIndex.add(i);
            }

            int maxRoomsToPlace = maxRoomsToPlace(minimum(roomsToPlace, freeSlots));

            List<Integer> chosePlaces = RandomHelper.chooseKInList(maxRoomsToPlace, freeSlotsPlacementIndex);
            
            ArrayList<Integer> roomsDistributionArray = new ArrayList<Integer>();
            for(int v : roomsDistribution){ roomsDistributionArray.add(v); }
    
            for(int index : chosePlaces){
                int x = freeSlotsPlacement.get(index)[0];
                int y = freeSlotsPlacement.get(index)[1];
                if(x != -1 && y != -1){
                    placementmap[x][y] = MapState.PLACED;
                }
            }    

            placementmap[posx][posy] = MapState.EXPLORED;
            Integer[] pos = {0};  
            if(chosePlaces.size() == 1){ 
                pos = freeSlotsPlacement.get(chosePlaces.get(0)); 
            }
            else{
                pos = freeSlotsPlacement.get(RandomHelper.chooseKInList(1, chosePlaces).get(0));
            }
            posx = pos[0]; posy = pos[1];
            roomsToPlace -= maxRoomsToPlace;
        }            

        boolean isPlaced = false;
        List<Integer> indexList =new ArrayList<Integer>();
        for(int i = 0; i<placementmap.length; i++){
            indexList.add(i);        
        }
        while(!isPlaced){
            int indexx = RandomHelper.chooseKInList(1, indexList).get(0);
            int indexy = RandomHelper.chooseKInList(1, indexList).get(0);
            int nbrfreeslots = freeSlots(placementmap , indexx, indexy, MapState.PLACED).size();
            nbrfreeslots += freeSlots(placementmap , indexx, indexy, MapState.EXPLORED).size();
            if(nbrfreeslots >0 && nbrfreeslots <4){
                if(placementmap[indexx][indexy]!= MapState.PLACED && placementmap[indexx][indexy]!= MapState.EXPLORED){
                    placementmap[indexx][indexy] = MapState.BOSS_ROOM;
                    isPlaced =true;
                }
            }
        }
        
        return placementmap;
    }

   //Methods
   /**
    * Returns a list of free slots around a given position
    * @param map
    * @param x
    * @param y
    * @param type
    */
    private List<Integer[]> freeSlots(MapState[][] map, int x, int y, MapState type){
        List<Integer[]> freeSlots = new ArrayList<Integer[]>();

        if(x+1 < map[0].length && map[x+1][y].equals(type)){
            Integer[] tab = {x+1, y};
            freeSlots.add(tab);
        }
        if(x-1 >= 0 && map[x-1][y].equals(type)){
            Integer[] tab = {x-1, y};
            freeSlots.add(tab);
        }
        if(y-1 >= 0 && map[x][y-1].equals(type)){
            Integer[] tab = {x, y-1};
            freeSlots.add(tab);
        }
        if(y+1 < map.length && map[x][y+1].equals(type)){
            Integer[] tab = {x, y+1};
            freeSlots.add(tab);
        }
        
        return freeSlots;
    }

    /**
     * Returns a random number between 1 and max
     * @param max
     */
    private int maxRoomsToPlace(int max){
        ArrayList<Integer> list = new ArrayList<Integer>();

        for(int i=1 ; i<=max ; i++){ 
            list.add(i); 
        }

        return (RandomHelper.chooseKInList(1, list).get(0));
    }

    /**
     * Returns the minimum between a and b
     * @param a
     * @param b
     */
    private int minimum(int a, int b){
        if(a<b){return a;}
        return b;

    }

}