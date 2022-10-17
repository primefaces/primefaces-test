package org.primefaces.test;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.primefaces.model.JpaLazyDataModel;
import org.primefaces.model.LazyDataModel;

import lombok.Data;

@Data
@Named
@ViewScoped
public class TestView implements Serializable {

    private String string;

    @Inject
    private EntityManager em;
    private LazyDataModel<TestJpa> lazyDataModel;

    @PostConstruct
    public void init() {
        string = "Welcome to PrimeFaces!!!";

        lazyDataModel = new JpaLazyDataModel<>(TestJpa.class, () -> em);
    }

    public LazyDataModel<TestJpa> getLazyDataModel() {
        return lazyDataModel;
    }

}
