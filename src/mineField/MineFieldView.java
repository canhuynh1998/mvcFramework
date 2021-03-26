package Minefield;

import mvc.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.beans.PropertyChangeEvent;

/*
 *  Hoc Can 3/15/2021: created file
 *  Hoc Can 3/21/2021: Initialized constructor
 *  Paul Soriano 3/22/2021: Overrode propertyChanged().
 */
public class MineFieldView extends View {
    private MineField model;
    private static int GRIDSIZE = 20;
    private JLabel[][] grids;
    private int newRow;
    private int newCol;
    final Border BLACKLINE = BorderFactory.createLineBorder(Color.BLACK);
    final Border WHITELINE = BorderFactory.createLineBorder(Color.WHITE);
    final Border GREENLINE = BorderFactory.createLineBorder(Color.GREEN);
    public MineFieldView(MineField mod) {
        super(mod);
        model = mod;
        grids = new JLabel[GRIDSIZE][GRIDSIZE];
        this.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE));


        //Initial
        for(int i = 0; i< GRIDSIZE;i++){
            for(int j = 0; j < GRIDSIZE; j++){
                grids[i][j] = new JLabel("?");
                grids[i][j].setBorder(BLACKLINE);
                this.add(grids[i][j]);
            }
        }
        // Set exit as green.
        grids[GRIDSIZE-1][GRIDSIZE-1].setBorder(GREENLINE);

        // Set starting location.
        grids[0][0].setBorder(WHITELINE);
        grids[0][0].setBackground(Color.BLUE);
        grids[0][0].setText(String.valueOf(model.field[0][0].getMinedNeighbors()));
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("locationX")){
            newRow = (Integer) evt.getNewValue();
        }
        if (evt.getPropertyName().equals("locationY")){
            newCol = (Integer) evt.getNewValue();
        }
        if (evt.getPropertyName().equals("visited")){
            grids[newCol][newRow].setBorder(WHITELINE);
            grids[newCol][newRow].setBackground(Color.BLUE);
            grids[newCol][newRow].setText(String.valueOf(model.field[newCol][newRow].getMinedNeighbors()));
            // For debugging
            System.out.printf("View Changed. X: %d, Y: %d%n", newRow, newCol);
        }
    }
}
