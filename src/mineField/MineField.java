package Minefield;

import mvc.Command;
import mvc.Model;

import java.util.ArrayList;
import java.util.List;

public class MineField extends Model {
    // private int field[][]   //this is the grid.
    private int locationX;    // current x-location in field[][]
    private int locationY;    // current y-location in field[][]
    private List<Command> commands = new ArrayList<>();
    private MoveCommand move = new MoveCommand(this);
    // private List<Patch> visited;
    // public static int PERCENT_MINED = 5; // percentage of patches to be set as mined.


    // Author: Paul Junver Soriano
    // Last Revision Date: 3/20/2021, 6:50 PM
    // Revisions: Updated MoveCommand and Patch classes.
    //            Added possible instance variables and methods as comments.
    class MoveCommand extends MineFieldCommand {
        Heading heading;

        private int playerX; // variable to hold current x-location.
        private int playerY; // variable to hold current y-location.

        public MoveCommand(Model mod) {
            super(mod);
            playerY = locationY;
            playerX = locationX;
        }

        // Uncomment change() after it is implemented.
        // Uncomment validate() after field[][] is instantiated.
        public void execute() throws Exception {
            if (!(model instanceof MineField)){
                throw new Exception("Model must be a Minefield.");
            }
            switch(heading){
                case N -> {
                    playerY--;
                    // validate();
                }
                case E -> {
                    playerX --;
                    // validate();
                }
                case S -> {
                    playerY++;
                    // validate();
                }
                case W -> {
                    playerX ++;
                    // validate();
                }

                case NE -> {
                    playerX --;
                    playerY --;
                    // validate();
                }
                case NW -> {
                    playerX ++;
                    playerY --;
                    // validate();
                }
                case SE -> {
                    playerX --;
                    playerY ++;
                    // validate();
                }
                case SW -> {
                    playerX ++;
                    playerY ++;
                    // validate();
                }
                default -> throw new Exception("Invalid move");
            }

            // Set new location after move is validated.
            locationX = playerX;
            locationY = playerY;

            //field[locationX][locationY].setMined(); //Set this mine to be mined.
            // Minefield mine = (Minefield) model
            //mine.change(); //Fire Property Change.
        }

        public void validate(){
            // if !((playerX > field.length || playerX < 0) && (field > patches[0].length || playerY < 0)) {
            // throw new Exception("Cannot move outside the field.")
        }


        public void setHeading(Heading h){
            heading = h;
        }
    }
      public void addCommand (Command cmmd){
        commands.add(cmmd);
      }


}
