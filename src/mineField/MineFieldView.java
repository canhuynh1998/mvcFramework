package Minefield;

import mvc.Model;
import mvc.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;

/*
 *  Hoc Can 3/15/2021: created file
 *  Hoc Can 3/21/2021: Initialized constructor
 */
public class MineFieldView extends View {
    private Model model;
    private static int GRIDSIZE = 20;
    private JLabel[][] grids;
    public MineFieldView(Model mod) {
        super(mod);
        model = new MineField();
        grids = new JLabel[GRIDSIZE][GRIDSIZE];
        this.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));
        Border blackLine = BorderFactory.createLineBorder(Color.BLACK);
        Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
        Border greenLine = BorderFactory.createLineBorder(Color.GREEN);

        //Initial
        for(int i = 0; i< GRIDSIZE;i++){
            for(int j = 0; j < GRIDSIZE; j++){
                grids[i][j] = new JLabel("?");
                grids[i][j].setBorder(blackLine);
                this.add(grids[i][j]);
            }
        }
        // Set exit as green
        grids[GRIDSIZE-1][GRIDSIZE-1].setBorder(greenLine);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO
       if (evt.getPropertyName().equals("visited")){
           for(int i = 0; i< GRIDSIZE;i++) {
               for (int j = 0; j < GRIDSIZE; j++) {

               }
           }
       }
    }
}
