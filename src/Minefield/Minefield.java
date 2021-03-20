package Minefield;

import mvc.Command;
import mvc.Model;

public class Minefield extends Model {
    private int playerX;
    private int playerY;
    class MoveCommand extends Command {
        Heading heading;
        Model model;

        public MoveCommand(Model mod) {
            super(mod);
            model = mod;
        }

        // Uncomment change() after it is implemented.
        public void execute() throws Exception {
            if (!(model instanceof Minefield)){
                throw new Exception("Model must be a Minefield.");
            }
            switch(heading){
                case N -> {
                    playerY--;
                    // model.change();
                }
                case E -> {
                    playerX --;
                    // model.change();
                }
                case S -> {
                    playerY++;
                    // model.change();
                }
                case W -> {
                    playerX ++;
                    // model.change();
                }

                case NE -> {
                    playerX --;
                    playerY --;
                    // model.change();
                }
                case NW -> {
                    playerX ++;
                    playerY --;
                    // model.change();
                }
                case SE -> {
                    playerX --;
                    playerY ++;
                    // model.change();
                }
                case SW -> {
                    playerX ++;
                    playerY ++;
                    // model.change();
                }
                default -> System.out.println("Invalid move");
            }
        }

        public void setHeading(Heading h){
            heading = h;
        }
    }

    private MoveCommand move;

}
