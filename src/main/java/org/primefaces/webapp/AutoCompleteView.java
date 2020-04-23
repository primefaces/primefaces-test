package org.primefaces.webapp;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class AutoCompleteView implements Serializable {

    private String txt6;


    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }

        return results;
    }

    /**
     * Called when the user selects a material. Adds the selected material to the
     * list of selected materials
     *
     * @param e the event that carries the information about the selected material
     */
    public void onMatSelect(final AjaxBehaviorEvent e) {
        System.out.println("select event is ");
        System.out.println(e.toString());
        new RuntimeException("Svině!").printStackTrace(System.out);
    }

    /**
     * Creates a new material for the current PST
     */
    public void newMaterial() {
        System.out.println("action called");
    }

    public String getTxt6() {
        return txt6;
    }

    public void setTxt6(String txt6) {
        this.txt6 = txt6;
    }

}