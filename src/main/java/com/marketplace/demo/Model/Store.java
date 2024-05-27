package com.marketplace.demo.Model;

import jakarta.persistence.*;
import lombok.Data;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
//@Table(name = "store_test")
public class Store {

    @Id
    @GeneratedValue
    private Long id; //primary key
    private String name;
    private String number;
    private String logo;
    private String date;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Product> products;

}
