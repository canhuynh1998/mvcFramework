package Minefield;
import mvc.AppFactory;
import mvc.AppPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/*
*   Hoc Can 3/15/2021: implemented MineFieldPanel
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

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            if (cmd.equals("NW")){

            } else if (cmd.equals("N")) {
                ;
            } else {
            }
        }catch(Exception er) {
            handleException(er);
        }
    }

    public static void main(String[] args) {
        AppFactory factory = new MineFieldFactory();
        AppPanel panel = new MineFieldPanel(factory);
        panel.display();
    }
}
