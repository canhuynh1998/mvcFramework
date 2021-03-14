package mvc;
/*
 *   Hoc Can 3/9/2021: created files
 *   Hoc Can 3/14/2021: modified
 * */
public interface AppFactory {
    public Model makeModel();

    public View makeView(Model model);

    public  String getTitle();

    public String[] getHelp();

    public String about();

    public String[] getEditCommands();

    public Command makeEditCommand(Model model, String type);

}
