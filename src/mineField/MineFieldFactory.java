package mineField;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

/*
 *   Hoc Can 3/15/2021: implemented MineFieldFactory( all methods but makeEditCommand)
 * */
public class MineFieldFactory implements AppFactory {
    @Override
    public Model makeModel() { return new MineField(); }

    @Override
    public View makeView(Model model) {
        return new MineFieldView((MineField)model);
    }

    public String getTitle() {
        return "Mine Field";
    }

    @Override
    public String[] getHelp() {
        return new String[] {"Click on direction buttons to move and the goal is to reach to the bottom right"};
    }

    @Override
    public String about() {
        return "Mine Field by Hoc Can Huynh, Paul Junver Soriano, Austin Adair";
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"NW", "N", "NE", "S", "SE", "SW", "W", "S"};
    }

    @Override
    public Command makeEditCommand(Model model, String type) {
        return null;
    }
}
