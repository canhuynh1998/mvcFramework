package mvc;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.*;

/*
*   Hoc Can 3/9/2021: created files
*   Hoc Can 3/14/2021: modified
* */
public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener {
    private View view;
    private Model model;
    private ControlPanel controls;
    private AppFactory factory;


    public void display() {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        try {
//            // handle control actions here
//        } catch (Exception e) {
//            //handleException(e);
//        }
    }
}
