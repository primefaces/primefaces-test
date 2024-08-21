package org.primefaces.test;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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

        ChildJpa child1 = ChildJpa.builder().id(1l).stringCol("Child 1").numberCol(456l)
                .decimalCol(BigDecimal.valueOf(456.45))
                .dateCol(new Date()).build();
        ChildJpa child2 = ChildJpa.builder().id(2l).stringCol("Child 2").numberCol(456l)
                .decimalCol(BigDecimal.valueOf(456.45))
                .dateCol(new Date()).build();
        em.persist(child1);
        em.persist(child2);

        em.persist(TestJpa.builder().id(1l).stringCol("New York").numberCol(123l).decimalCol(BigDecimal.valueOf(123.45))
                .dateCol(new Date()).child(child1).build());
        em.persist(TestJpa.builder().id(2l).stringCol("Los Angeles").numberCol(234l)
                .decimalCol(BigDecimal.valueOf(234.45)).dateCol(new Date()).child(child1).build());
        em.persist(TestJpa.builder().id(3l).stringCol("Philladelphia").numberCol(345l)
                .decimalCol(BigDecimal.valueOf(345.45)).dateCol(new Date()).child(child1).build());
        em.persist(TestJpa.builder().id(4l).stringCol("Detroit").numberCol(456l).decimalCol(BigDecimal.valueOf(456.45))
                .dateCol(new Date()).child(child2).build());
        em.persist(TestJpa.builder().id(5l).stringCol("Boston").numberCol(567l).decimalCol(BigDecimal.valueOf(567.45))
                .dateCol(new Date()).child(child2).build());

        em.getTransaction().commit();
    }
}
