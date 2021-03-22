package Minefield;

import mvc.Command;
import mvc.Model;
/*
 *   Hoc Can 3/15/2021: created file
 */
public class MineFieldCommand extends Command {
    Heading heading;

    public MineFieldCommand(Model mod) {
        super(mod);
    }

    @Override
    public void execute() throws Exception { }
}
