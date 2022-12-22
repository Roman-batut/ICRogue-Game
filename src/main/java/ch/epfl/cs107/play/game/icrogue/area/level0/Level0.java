package ch.epfl.cs107.play.game.icrogue.area.level0;

import ch.epfl.cs107.play.game.icrogue.ICRogue;
import ch.epfl.cs107.play.game.icrogue.area.ICRogueRoom;
import ch.epfl.cs107.play.game.icrogue.area.Level;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0BossRoom;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0KeyRoom;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0Room;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0ShopRoom;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0StaffRoom;
import ch.epfl.cs107.play.game.icrogue.area.level0.rooms.Level0TurretRoom;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends Level{

    public enum RoomType {
        TURRET_ROOM(5), // type and number of roon 
        STAFF_ROOM(1),
        BOSS_KEY(1),
        SPAWN(1),
        NORMAL(3),
        SHOP_ROOM(1);


        int occurence;
        
        // Constructor
        private RoomType(int numberOf){
            this.occurence = numberOf;
		}

        //Getter
        public static int[] getroomDistrubution(){
           int[] RoomT = new int[RoomType.values().length];
           int i =0;
            for(RoomType room : RoomType.values()){
			    RoomT[i] = (room.occurence);
                i++;
            }
            return RoomT;
        }

    }

    private int BOSS_KEY_ID = 0;
    private int PART_1_KEY_ID = 0;
    
    // * CONSTRUCTORS
    /**
     * @param jeu
     * @param randomMap
     */
    public Level0(ICRogue jeu, boolean randomMap) {
        super(randomMap, new DiscreteCoordinates(2, 0), RoomType.getroomDistrubution() ,4,2,jeu);
    }

    /**
     * @param jeu
     */
    public Level0(ICRogue jeu){
        super(true, new DiscreteCoordinates(2, 0), RoomType.getroomDistrubution(), 4,2, jeu);
    }
    
    // * REDEFINE Level
    @Override
    public void generateFixedMap(){
        generateMapFinal();
    }

    //Put the connector of the room regarding of the rooms around
    @Override
    protected void setUpConnector(MapState[][] roomsPlacement, ICRogueRoom room) {
        DiscreteCoordinates roomCoordinates = room.getCoordinates();

        int x = roomCoordinates.x;
        int y = roomCoordinates.y;
        if(x+1 <roomsPlacement[0].length && roomsPlacement[x+1][y] != MapState.NULL){
            if(roomsPlacement[x+1][y] == MapState.BOSS_ROOM){
                lockRoomConnector(roomCoordinates, Level0Room.Level0Connectors.N, PART_1_KEY_ID);
                setRoomConnectorDestination(roomCoordinates, "icrogue/level0"+(x+1)+y, Level0Room.Level0Connectors.N);
            }else{
                setRoomConnector(roomCoordinates, "icrogue/level0"+(x+1)+y, Level0Room.Level0Connectors.N);
            }
            setConnectorDestinationcoords(roomCoordinates,Level0Room.Level0Connectors.N);
        }
        if(x-1 >= 0 && roomsPlacement[x-1][y]!= MapState.NULL){
            if(roomsPlacement[x-1][y] == MapState.BOSS_ROOM){
                lockRoomConnector(roomCoordinates, Level0Room.Level0Connectors.S, PART_1_KEY_ID);
                setRoomConnectorDestination(roomCoordinates, "icrogue/level0"+(x-1)+y, Level0Room.Level0Connectors.S);
            }else{
                setRoomConnector(roomCoordinates, "icrogue/level0"+(x-1)+y, Level0Room.Level0Connectors.S);
            }
            setConnectorDestinationcoords(roomCoordinates,Level0Room.Level0Connectors.S);
        }

        if(y-1 >= 0 && roomsPlacement[x][y-1]!= MapState.NULL){
            if(roomsPlacement[x][y-1] == MapState.BOSS_ROOM){
                lockRoomConnector(roomCoordinates, Level0Room.Level0Connectors.W, PART_1_KEY_ID);
                setRoomConnectorDestination(roomCoordinates, "icrogue/level0"+x+(y-1), Level0Room.Level0Connectors.W);
            }else{
                setRoomConnector(roomCoordinates,"icrogue/level0"+x+(y-1), Level0Room.Level0Connectors.W);
            }
            setConnectorDestinationcoords(roomCoordinates,Level0Room.Level0Connectors.W);
        }
        if(y+1 <roomsPlacement.length && roomsPlacement[x][y+1]!= MapState.NULL){
            if(roomsPlacement[x][y+1] == MapState.BOSS_ROOM){
                lockRoomConnector(roomCoordinates, Level0Room.Level0Connectors.E, PART_1_KEY_ID);
                setRoomConnectorDestination(roomCoordinates, "icrogue/level0"+x+(y+1), Level0Room.Level0Connectors.E);
            }else{
                setRoomConnector(roomCoordinates, "icrogue/level0"+x+(y+1), Level0Room.Level0Connectors.E);
            }
            setConnectorDestinationcoords(roomCoordinates, Level0Room.Level0Connectors.E);
        }
    }

    // create the room regarding of the ordinality in the enum type
    @Override
    protected ICRogueRoom createRoom(int ordinal, DiscreteCoordinates coordinates){
        ICRogueRoom room = new Level0BossRoom(coordinates);

        switch (ordinal) {
            case 0 -> room = new Level0TurretRoom(coordinates);
            case 1 -> room = new Level0StaffRoom(coordinates);
            case 2 -> room = new Level0KeyRoom(coordinates, BOSS_KEY_ID);
            case 3 -> room = new Level0Room(coordinates);
            case 4 -> room = new Level0Room(coordinates);
            case 5 -> room = new Level0ShopRoom(coordinates);
        }

        return room;
    }
    

    // * METHODS
    /**
     * Generate the map fixed
     */
    private void generateMapFinal() {
        DiscreteCoordinates room00 = new DiscreteCoordinates(0, 0);
        setRoom(room00, new Level0TurretRoom(room00));
        setRoomConnector(room00, "icrogue/level010", Level0Room.Level0Connectors.E);
        setConnectorDestinationcoords(room00,Level0Room.Level0Connectors.N);

        DiscreteCoordinates room10 = new DiscreteCoordinates(1,0);
        setRoom(room10, new Level0Room(room10));
        setRoomConnector(room10, "icrogue/level011", Level0Room.Level0Connectors.S);
        setConnectorDestinationcoords(room10,Level0Room.Level0Connectors.S);
        setRoomConnector(room10, "icrogue/level020", Level0Room.Level0Connectors.E);
        setConnectorDestinationcoords(room10,Level0Room.Level0Connectors.E);

        lockRoomConnector(room10, Level0Room.Level0Connectors.W,  PART_1_KEY_ID);
        setRoomConnectorDestination(room10, "icrogue/level000", Level0Room.Level0Connectors.W);
        setConnectorDestinationcoords(room10,Level0Room.Level0Connectors.W);

        DiscreteCoordinates room20 = new DiscreteCoordinates(2,0);
        setRoom(room20,  new Level0StaffRoom(room20));
        setRoomConnector(room20, "icrogue/level010", Level0Room.Level0Connectors.W);
        setConnectorDestinationcoords(room20,Level0Room.Level0Connectors.W);
        setRoomConnector(room20, "icrogue/level030", Level0Room.Level0Connectors.E);
        setConnectorDestinationcoords(room20,Level0Room.Level0Connectors.E);

        DiscreteCoordinates room30 = new DiscreteCoordinates(3,0);
        setRoom(room30, new Level0KeyRoom(room30, BOSS_KEY_ID));
        setRoomConnector(room30, "icrogue/level020", Level0Room.Level0Connectors.W);
        setConnectorDestinationcoords(room30,Level0Room.Level0Connectors.W);

        DiscreteCoordinates room11 = new DiscreteCoordinates(1, 1);
        setRoom (room11, new Level0Room(room11));
        setRoomConnector(room11, "icrogue/level010", Level0Room.Level0Connectors.N);
        setConnectorDestinationcoords(room11,Level0Room.Level0Connectors.N);
    }

}
