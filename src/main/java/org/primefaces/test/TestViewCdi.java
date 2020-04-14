package org.primefaces.test;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class TestViewCdi {
    
    private String testString;
    
    @PostConstruct  
    public void init() {
        testString = "Welcome to PrimeFaces (from CDI!) !!!";
    }

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }    
}
