package org.primefaces.test;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

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
