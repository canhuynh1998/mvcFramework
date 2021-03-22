package Minefield;
// Author: Paul Junver Soriano
// Last Revision Date: 3/21/2021, 12:02 PM
// Revisions: Added Enum labels to correspond with MinefieldPanel.
public enum Heading {
    N ("N"),
    E ("E"),
    W ("W"),
    S ("S"),
    NE ("NE"),
    NW ("NW"),
    SE ("SE"),
    SW ("SW");

    public final String label;

    private Heading (String label){
        this.label = label;
    }

    public static Heading valueOfLabel(String label) {
        for (Heading h : values()) {
            if (h.label.equals(label)) {
                return h;
            }
        }
        return null;
    }

}
