package com.duvan.crudnew.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@ToString
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(unique = true)
    @Getter
    @Setter
    private String productName;
    @Getter
    @Setter
    private double productPrice;
    @Getter
    @Setter
    private LocalDate date;
    @Transient
    @Setter
    private int timeOfProduct;

    public ProductEntity(Long id, String productName, double productPrice, LocalDate date) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
        this.date = date;
    }

    public ProductEntity(String productName, double productPrice, LocalDate date) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.date = date;

    }

    public int getTimeOfProduct() {
        return Period.between(this.date,LocalDate.now()).getYears();
    }
}
