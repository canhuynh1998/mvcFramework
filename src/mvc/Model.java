package mvc;
/*
 *   Hoc Can 3/9/2021: created files
 *   Hoc Can 3/14/2021: modified
 * */
public abstract class Model extends Bean{
    private Boolean unsaveChanged;
    private String fileName;

    public Model(){
        unsaveChanged = false;
        fileName = null;
    }

    public String getFileName(){
        return fileName;
    }

    public void setFileName(String fileName){ fileName = fileName; }

    public Boolean getUnsavedChanges(){ return unsaveChanged; }

    public void setUnsavedChanges(boolean unsavedChanges){ unsaveChanged = unsavedChanges; }

    public void changed(){

    }
}
