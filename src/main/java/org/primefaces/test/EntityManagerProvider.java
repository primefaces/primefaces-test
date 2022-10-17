package org.primefaces.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProvider {

    private EntityManagerFactory emf;

    @PostConstruct
    public void init() {
        emf = Persistence.createEntityManagerFactory("ExampleDatasource");
        insertData();
    }

    @Produces
    @RequestScoped
    public EntityManager produce() {
        return emf.createEntityManager();
    }

    public void disposes(@Disposes EntityManager em) {
        em.close();
    }

    private void insertData() {
        EntityManager em = produce();
        em.getTransaction().begin();

        em.persist(TestJpa.builder().id(1l).stringCol("New York").numberCol(123l).decimalCol(BigDecimal.valueOf(123.45))
                .dateCol(new Date()).build());
        em.persist(TestJpa.builder().id(2l).stringCol("Los Angeles").numberCol(234l)
                .decimalCol(BigDecimal.valueOf(234.45)).dateCol(new Date()).build());
        em.persist(TestJpa.builder().id(3l).stringCol("Philladelphia").numberCol(345l)
                .decimalCol(BigDecimal.valueOf(345.45)).dateCol(new Date()).build());
        em.persist(TestJpa.builder().id(4l).stringCol("Detroit").numberCol(456l).decimalCol(BigDecimal.valueOf(456.45))
                .dateCol(new Date()).build());
        em.persist(TestJpa.builder().id(5l).stringCol("Boston").numberCol(567l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).build());

        em.getTransaction().commit();
    }
}
