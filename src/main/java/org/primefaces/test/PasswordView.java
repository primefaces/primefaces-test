package org.primefaces.test;

import lombok.Data;
import org.primefaces.event.FileUploadEvent;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Named
@ViewScoped
public class PasswordView implements Serializable {

    private String password6 = "OpenSesame";

    public String getPassword6() {
        return password6;
    }
}
