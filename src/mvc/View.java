package mvc;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
 *   Hoc Can 3/9/2021: created files
 *   Hoc Can 3/14/2021: modified
 * */
public class View extends JPanel implements PropertyChangeListener {
    private Model model;

    public View(Model mod){
        model = mod;
        model.addPropertyChangeListener(this);
    }

    public void setModel(Model mod){
        model.removePropertyChangeListener(this);
        model = mod;
        model.initSupport();
        model.addPropertyChangeListener(this);
        repaint();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        repaint();
    }
}
