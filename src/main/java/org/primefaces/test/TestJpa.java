package org.primefaces.test;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TestJpa {

    @Id
    private Long id;
    private String stringCol;
    private Long numberCol;
    private BigDecimal decimalCol;
    private Date dateCol;
    @JoinColumn
    @ManyToOne
    private ChildJpa child;

}
