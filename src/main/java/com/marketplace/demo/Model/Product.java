package com.marketplace.demo.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Parent;

import javax.annotation.processing.Generated;
import java.math.BigDecimal;

@Entity
@Data
//@Table(name = "product_test")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;
}
