package mvc;

import tools.Utilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/*
*   Hoc Can 3/9/2021: created files
*   Hoc Can 3/14/2021: modified files
*   Hoc Can 3/15/2021: modified AppPanel, Command, Model, View
* */
public class AppPanel extends JPanel implements PropertyChangeListener, ActionListener {
    protected View view;
    protected Model model;
    protected AppFactory factory;
    protected JFrame frame;
    protected JPanel controlPanel;
    public static int FRAME_WIDTH = 500;
    public static int FRAME_HEIGHT = 300;

    public AppPanel(AppFactory fct){
        factory = fct;
        model = factory.makeModel();
        view = factory.makeView(model);
        controlPanel = new JPanel();

        setLayout(new GridLayout(1,2));
        add(controlPanel);
        add(view);

        controlPanel.setBackground(Color.CYAN);
        view.setBackground(Color.PINK);
        frame = new JFrame();
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(createMenu());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle(factory.getTitle());
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public JMenuBar createMenu(){
        //Menu Bar
        JMenuBar menu = new JMenuBar();
        JMenu fileMenu =
                Utilities.makeMenu("File", new String[] {"New", "Save", "Save as", "Open", "Quit"}, this);
        menu.add(fileMenu);
        JMenu editMenu =
                Utilities.makeMenu("Edit", factory.getEditCommands(), this);
        menu.add(editMenu);
        JMenu helpMenu =
                Utilities.makeMenu("Help", new String[] {"About", "Help"}, this);
        menu.add(helpMenu);
        return menu;
    }

    public void display() {frame.setVisible(true);}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        view.repaint();
    }

    private void save() throws Exception{
        if (model.getFileName() == null){
            saveAs();
        }else{
            // set unsaveChanged to false
            model.saved();
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(model.getFileName()));
            os.writeObject(model);
            os.close();
        }
    }

    private void saveAs() throws Exception{
        String fileName = Utilities.getFileName(null, false);
        if(!fileName.isEmpty()) {
            model.setFileName(fileName);
            // set unsaveChanged to false
            model.saved();
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            os.writeObject(model);
            os.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            if (cmd.equals("New")) {
                model = factory.makeModel();
                view.setModel(model);
            } else if (cmd.equals("Save")) {
                save();
            } else if (cmd.equals("Save as")) {
                saveAs();
            } else if (cmd.equals("Open")) {
                String fileName = Utilities.getFileName(model.getFileName(), true);
                if(!fileName.isEmpty()) {
                    ObjectInputStream is = new ObjectInputStream((new FileInputStream((fileName))));
                    model = (Model) is.readObject();
                    view.setModel(model);
                }
            } else if (cmd.equals("Quit")) {
                // Request for save if there is unsaved change
                if(model.getUnsavedChanges() && Utilities.confirm("Save changes before quitting?")) {
                    save();
                }
                System.exit(1);
            } else if (cmd.equals("Help")) {
                Utilities.inform(factory.getHelp());

            } else if (cmd.equals("About")) {
                Utilities.inform(factory.about());

            } else {
                Command editCommand = factory.makeEditCommand(model, cmd);
                editCommand.execute();
            }
        }catch(Exception er) {
            handleException(er);
        }
    }
    protected void handleException(Exception e){
        if(e == null){
            Utilities.error("Unrecognized Command!!");
        }else{
            Utilities.error(e);
        }

    }
}
