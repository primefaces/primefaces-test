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
public class FileUploadView implements Serializable {

    private Map<String, InputStream> fileList;
    private List<Exception> testExceptions;
    private String title;
    
    @PostConstruct  
    public void init() {
        fileList = new HashMap<>();
        testExceptions = new ArrayList<>();
        title = "File Upload Tester";
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            fileList.put(event.getFile().getFileName(), event.getFile().getInputStream());
        } catch (Exception ex) {
            testExceptions.add(ex);
        }
    }

    public void clearLogList() {
        fileList = new HashMap<>();
        testExceptions = new ArrayList<>();
    }

    public List<String> getUploadedFiles() {
        return new ArrayList<>(fileList.keySet());
    }

    public int getExceptionCount() {
        return testExceptions.size();
    }
}
