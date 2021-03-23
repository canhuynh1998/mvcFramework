package Minefield;

import mvc.Command;
import mvc.Model;
import tools.Utilities;

/*
 *   Hoc Can 3/15/2021: created file
 *   Paul Soriano 3/22/2021, 2:50 PM Changed execute() method.
 */
public class MineFieldCommand extends Command {
    Heading heading;

    public MineFieldCommand(Model mod) {
        super(mod);
    }

    @Override
    public void execute() throws Exception {
        if (!(model instanceof MineField)) {
            Utilities.error(new Exception("Model must instantiate Stoplight"));
        }
        MineField mine = (MineField) model;
        mine.move(this);
        mine.changed();
    }

}

