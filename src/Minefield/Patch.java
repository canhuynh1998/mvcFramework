package Minefield;

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
        mined = !mined;
    }

}
