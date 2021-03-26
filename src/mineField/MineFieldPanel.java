package minefield;

import mvc.AppFactory;
import mvc.AppPanel;
import tools.Utilities;

import javax.swing.*;
import java.awt.*;

/*
*   Hoc Can 3/15/2021: implemented MineFieldPanel
*   Hoc Can 3/26/2021: implement handleException()
* */
public class MineFieldPanel extends AppPanel {
    private JButton northWest;
    private JButton north;
    private JButton northEast;
    private JButton west;
    private JButton east;
    private JButton southWest;
    private JButton southEast;
    private JButton south;

    public MineFieldPanel(AppFactory factory) {
        super(factory);
        controlPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));

        /**********List of Buttons**********/
        northWest = new JButton("NW");
        northWest.addActionListener(this);
        controlPanel.add(northWest);

        north = new JButton("N");
        north.addActionListener(this);
        controlPanel.add(north);

        northEast = new JButton("NE");
        northEast.addActionListener(this);
        controlPanel.add(northEast);

        east = new JButton("E");
        east.addActionListener(this);
        controlPanel.add(east);

        west = new JButton("W");
        west.addActionListener(this);
        controlPanel.add(west);

        southWest = new JButton("SW");
        southWest.addActionListener(this);
        controlPanel.add(southWest);

        southEast = new JButton("SE");
        southEast.addActionListener(this);
        controlPanel.add(southEast);

        south = new JButton("S");
        south.addActionListener(this);
        controlPanel.add(south);


        //this.setLayout(new BorderLayout());
//        change = new JButton("Change");
//        change.addActionListener(this);
//        //JPanel buttonPanel = new JPanel();
//        //buttonPanel.setLayout(new FlowLayout());
//        controlPanel.add(change);
        //add(buttonPanel);
        //add(view);
    }

    //This method will create new game when stepped on mine or won the game
    @Override
    public void handleException(Exception e){
        if(e.getMessage().equals ("Stepped on a mine. Game over.")) {
            //Stepped on mine
            Utilities.inform("You died.");
            model= factory.makeModel();
            view.setModel(model);
        }
        else if(e.getMessage().equals("Congratulations! You won.")){
            //Won the game
            Utilities.inform("You won.");
            model = factory.makeModel();
            view.setModel(model);
        }
        else{
            Utilities.error("Cannot find this command!");
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
}
