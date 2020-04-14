package org.primefaces.test;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "testView")
@ViewScoped
public class TestView implements Serializable {
    
    private String testString;
    
    @PostConstruct  
    public void init() {
        testString = "Welcome to PrimeFaces!!!";
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }    
}
