package Minefield;

import mvc.Command;
import mvc.Model;

public class Minefield extends Model {
    // private int field[][]   //this is the grid.
    private int locationX;    // current x-location in field[][]
    private int locationY;    // current y-location in field[][]
    // private MoveCommand move;
    // private List<Patch> visited;
    // public static int PERCENT_MINED = 5; // percentage of patches to be set as mined.



    class MoveCommand extends Command {
        Heading heading;
        Model model;

        private int playerX; // variable to hold current x-location.
        private int playerY; // variable to hold current y-location.

        public MoveCommand(Model mod) {
            super(mod);
            model = mod;
            playerY = locationY;
            playerX = locationX;
        }

        // Uncomment change() after it is implemented.
        // Uncomment validate() after field[][] is instantiated.
        public void execute() throws Exception {
            if (!(model instanceof Minefield)){
                throw new Exception("Model must be a Minefield.");
            }
            switch(heading){
                case N -> {
                    // validate();
                    playerY--;
                }
                case E -> {
                    // validate();
                    playerX --;
                }
                case S -> {
                    // validate();
                    playerY++;
                }
                case W -> {
                    // validate();
                    playerX ++;
                }

                case NE -> {
                    // validate();
                    playerX --;
                    playerY --;
                }
                case NW -> {
                    // validate();
                    playerX ++;
                    playerY --;
                }
                case SE -> {
                    // validate();
                    playerX --;
                    playerY ++;
                }
                case SW -> {
                    // validate();
                    playerX ++;
                    playerY ++;
                }
                default -> throw new Exception("Invalid move");
            }

            // Set new location after move is validated.
            locationX = playerX;
            locationY = playerY;

            //field[locationX][locationY].setMined(); //Set this mine to be mined.
            //model.change(); //Fire Property Change.
        }

        public void validate(){
           // if !((playerX > field.length || playerX < 0) && (field > patches[0].length || playerY < 0)) {
            // throw new Exception("Cannot move outside the field.")
        }


        public void setHeading(Heading h){
            heading = h;
        }
    }



}
