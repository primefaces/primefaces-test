package org.primefaces.test;

import java.io.Serializable;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;

import org.primefaces.model.JPALazyDataModel;
import org.primefaces.model.LazyDataModel;

import lombok.Data;

@Data
@Named
@ViewScoped
public class TestView implements Serializable {

    private String string;

    @Inject
    private EntityManager em;
    private JPALazyDataModel<TestJpa> lazyDataModel;

    @PostConstruct
    public void init() {
        string = "PrimeFaces JPA DataTable";

        lazyDataModel = JPALazyDataModel.<TestJpa>builder()
            .entityClass(TestJpa.class)
            .entityManager(() -> em)
            .caseSensitive(false)
            .build();
    }
}
