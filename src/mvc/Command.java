package mvc;
/*
 *   Hoc Can 3/9/2021: created files
 *   Hoc Can 3/14/2021: modified
 * */
public abstract class Command {
    private Model model;
    public Command(Model model){
    }
    public abstract void execute();


}
