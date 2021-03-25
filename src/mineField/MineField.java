package Minefield;

import mvc.Model;
import tools.Utilities;

// Author: Paul Junver Soriano
// Last Revision Date: 3/22/2021, 2:50 PM Changed changed() method.
// Revisions: 3/21/2021, 11:50 PM Created the MineField class.
public class MineField extends Model {
    protected Patch field[][];   //this is the grid.
    private int locationX;    // current x-location in field[][]
    private int locationY;    // current y-location in field[][]
    private MineFieldCommand move; // Move command specific for MineField.

    // Constants.
    public final int  PERCENT_MINED = 5; // percentage of patches to be set as mined.
    public final int  FIELDSIZE = 20; // Size of Grid.

    public MineField() {
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
        firePropertyChange("locationX", locationX, locationX);
        firePropertyChange("locationY", locationY, locationY);

        // Set this Patch as visited.
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

    public void move(MineFieldCommand command) {
        move = command;
    }

    public int currentX(){
        return locationX;
    }

    public int currentY(){
        return locationY;
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

    @Override
    public void changed(){
        int playerY = locationY;
        int playerX = locationX;
        switch(move.heading){
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

        if (playerX > FIELDSIZE || playerX < 0 || playerY > FIELDSIZE || playerY < 0){
            Utilities.error(new Exception("Cannot move outside the grid."));
        } else if (field[playerX][playerY].isMined()){
            Utilities.error(new Exception("Stepped on a mine. Game over."));
        } else if (field[playerX][playerY].isExit()){
            field[playerX][playerY].setVisited();
            Utilities.error(new Exception("Congratulations! You won."));
        }
        else {
            // Store old location values.
            int oldX = locationX;
            int oldY = locationY;

            // Set new location after move is validated.
            locationX = playerX;
            locationY = playerY;
            field[locationX][locationY].setVisited();

            // Fire Property Change
            firePropertyChange("locationX", oldX, locationX);
            firePropertyChange("locationY", oldY, locationY);
            firePropertyChange("visited", false, true);

            //To debug:
            System.out.printf("Moved %s. New location X: %d, Y: %d%n", move.heading.toString(), locationX, locationY);
        }
    }

//    public static void main (String[] args){
//        MineField m = new MineField();
//    }
}
