package Minefield;

import mvc.Command;
import mvc.Model;
import tools.Utilities;

import java.util.ArrayList;
import java.util.List;

// Author: Paul Junver Soriano
// Last Revision Date: 3/21/2021, 11:50 PM
// Revisions: Created the MineField class.
public class MineField extends Model {
    protected Patch field[][];   //this is the grid.
    private int locationX;    // current x-location in field[][]
    private int locationY;    // current y-location in field[][]
    private List<Command> commands; // List of commands.
    private MoveCommand move; // Move command specific for MineField.

    // Constants.
    public final int  PERCENT_MINED = 5; // percentage of patches to be set as mined.
    public final int  FIELDSIZE = 20; // Size of Grid.

    // MoveCommand nested class extending Command
    class MoveCommand extends Command {
        Heading heading;
        private int playerX; // variable to hold current x-location.
        private int playerY; // variable to hold current y-location.

        public MoveCommand(Model mod) {
            super(mod);
            playerY = locationY;
            playerX = locationX;
        }

        public void execute() throws Exception {
            if (!(model instanceof MineField)){
                throw new Exception("Model must be a Minefield.");
            }
            switch(heading){
                case N: {
                    playerY--;
                    break;
                }
                case E: {
                    playerX ++;
                    break;
                }
                case S: {
                    playerY++;
                    break;
                }
                case W: {
                    playerX --;
                    break;
                }

                case NE: {
                    playerX ++;
                    playerY --;
                    break;
                }
                case NW: {
                    playerX --;
                    playerY --;
                    break;
                }
                case SE: {
                    playerX ++;
                    playerY ++;
                    break;
                }
                case SW: {
                    playerX --;
                    playerY ++;
                    break;
                }
                default: Utilities.error(new Exception("Invalid move"));
            }

            if (!validMove()){
                throw new Exception("Cannot move outside the grid.");
            } else if (field[playerX][playerY].isMined()){
                Utilities.error(new Exception("Stepped on a mine. Game over."));
            } else {
                // Store old location values.
                int oldX = locationX;
                int oldY = locationY;

                // Set new location after move is validated.
                locationX = playerX;
                locationY = playerY;
                field[locationX][locationY].setMined(); //Set this mine to be mined.
                field[locationX][locationY].setVisited();
                // Fire Property Change
                model.firePropertyChange("locationX", oldX, locationX);
                model.firePropertyChange("locationY", oldY, locationY);
            }
        }

        public boolean validMove() {
            return !((playerX > FIELDSIZE || playerX < 0) && (playerY > FIELDSIZE || playerY < 0));
        }

        public void setHeading(Heading h){
            heading = h;
        }
    }

    public MineField() {
        // Create new List of Commands.
        commands = new ArrayList<>();

        // Create new MoveCommand object.
        move = new MoveCommand(this);

        // Initialize grid with all Patches unmined.
        field = new Patch[FIELDSIZE][FIELDSIZE];
        for (int row = 0; row < FIELDSIZE; row++) {
            for (int col = 0; col < FIELDSIZE; col++) {
                field[row][col] = new Patch();
                field[row][col].setX(row);
                field[row][col].setY(col);
            }
        }
        // Make the bottom right patch the exit.
        field[FIELDSIZE-1][FIELDSIZE-1].setExit();

        // Set random 5% patches as mined.
        int counter = 0;
        while (counter <= (FIELDSIZE*FIELDSIZE*PERCENT_MINED) / 100 ){
            int row = Utilities.rng.nextInt(FIELDSIZE);
            int col = Utilities.rng.nextInt(FIELDSIZE);
            if (!field[row][col].isMined()){
                field[row][col].setMined();
                addMinedPatchToNeighbors(field[row][col]);
                counter++;
            }
        }

        // Start at the top left Patch.
        locationX = 0;
        locationY = 0;

        // Mark this patch as a visited patch.
        field[locationX][locationY].setVisited();

        // For debugging purposes:
//        for (int row = 0; row < FIELDSIZE; row++) {
//            for (int col = 0; col < FIELDSIZE; col++) {
//                Patch p = field[row][col];
//                int x = p.getX();
//                int y = p.getY();
//                int minedNeighbors = p.getMinedNeighbors();
//                System.out.printf("Patch at x: %d, y:%d --- minedNeighbors: %d, mined: %b, exit: %b%n", x, y, minedNeighbors, p.isMined(), p.isExit());
//            }
//        }
    }

    public void addCommand(Command cmmd){
        commands.add(cmmd);
    }

    public void move(Heading h){
        move.setHeading(h);
        try {
            move.execute();
        } catch (Exception e) {
            Utilities.error(e);
        }
    }

    // Helper method to setMinedNeighbors
    private void addMinedPatchToNeighbors(Patch p){
        // Check if there is a East neighbor.
        if (p.getX() + 1 < FIELDSIZE) {
            field[p.getX() + 1][p.getY()].addMinedNeighbors();
        }
        // Check if there is a West neighbor.
        if (p.getX() - 1 >= 0) {
            field[p.getX() - 1][p.getY()].addMinedNeighbors();
        }
        // Check if there is a South neighbor.
        if (p.getY() + 1 < FIELDSIZE) {
            field[p.getX()][p.getY() +1].addMinedNeighbors();
        }
        // Check if there is a North neighbor.
        if (p.getY() - 1 >= 0) {
            field[p.getX()][p.getY() - 1].addMinedNeighbors();
        }
        // Check if there is a NorthWest neighbor.
        if (p.getY() - 1 >= 0 && p.getX() - 1 >= 0) {
            field[p.getX() - 1][p.getY() - 1].addMinedNeighbors();
        }
        // Check if there is a NorthEast neighbor.
        if (p.getX() + 1 < FIELDSIZE && p.getY() - 1 >= 0) {
            field[p.getX() + 1][p.getY() - 1].addMinedNeighbors();
        }
        // Check if there is a SouthEast neighbor.
        if (p.getX() + 1 < FIELDSIZE && p.getY() + 1 < FIELDSIZE) {
            field[p.getX() + 1][p.getY() + 1].addMinedNeighbors();
        }
        // Check if there is a SouthWest neighbor.
        if (p.getX() - 1 >= 0 && p.getY() + 1 < FIELDSIZE) {
            field[p.getX() - 1][p.getY() + 1].addMinedNeighbors();
        }
    }

//    @Override
//    public void changed(){
//        firePropertyChange("Property",null,this);
//    }

//    public static void main (String[] args){
//        MineField m = new MineField();
//    }
}
