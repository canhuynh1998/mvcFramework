package mvc;

import tools.Bean;

/*
 *   Hoc Can 3/9/2021: created files
 *   Hoc Can 3/14/2021: modified
 * */
public abstract class Model extends Bean {
    private Boolean unsaveChanged;
    private String fileName;

    public Model(){
        unsaveChanged = false;
        fileName = null;
    }

    /*************Getter*************/
    public Boolean getUnsavedChanges(){ return unsaveChanged; }

    public String getFileName(){
        return fileName;
    }

    /*************Setter*************/
    public void setFileName(String name){ fileName = name; }

    public void setUnsavedChanges(boolean unsavedChanges){ unsaveChanged = unsavedChanges; }


    /*************Essential Methods*************/
    public void saved(){ unsaveChanged = false; }

    public void changed(){
        firePropertyChange("Property",null,this);
        unsaveChanged = true;
    }
}
