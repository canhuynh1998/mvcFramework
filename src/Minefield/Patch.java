package minefield;
// Author: Paul Junver Soriano
// Last Revision Date: 3/21/2021, 10:36 PM
// Revisions: Added new instance variables.
// Hoc Can 3/26/2021 add constructor
public class Patch {
    private int x;
    private int y;
    private boolean mined;
    private boolean exit;
    private boolean visited;
    protected int minedNeighbors;

    public Patch() {
        x = 0;
        y = 0;
        mined = false;
        exit = false;
        visited = false;
        minedNeighbors = 0;
    }

    public Patch(int x, int y){
        this.x = x;
        this.y = y;
        mined = false;
        exit = false;
        visited = false;
        minedNeighbors = 0;
    }
//    public Patch (Patch p){
//        this.x = p.x;
//        this.y = p.y;
//        this.mined = p.mined;
//        this.exit = p.exit;
//        this.minedNeighbors = p.minedNeighbors;
//        this.visited = p.visited;
//    }

    public int getMinedNeighbors() {
        return minedNeighbors;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isMined() {
        return mined;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setMined() {
        mined = true;
    }

    public void setExit() {
        exit = true;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setVisited() {
        visited = true;
    }

    public void addMinedNeighbors(){
        minedNeighbors++;
    }

}
