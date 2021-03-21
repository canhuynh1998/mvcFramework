package Minefield;
// Author: Paul Junver Soriano
// Last Revision Date: 3/20/2021, 4:27 PM
// Revisions: Updated MoveCommand and Patch classes.
//            Added possible instance variables and methods as comments.
public class Patch {
    private boolean mined;
    private boolean exit;

    public boolean isMined() {
        return mined;
    }

    public boolean isExit() {
        return exit;
    }

    public void setMined() {
        mined = true;
    }

    public void setUnmined(){
        mined = false;
    }

}
