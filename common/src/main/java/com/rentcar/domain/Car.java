package com.rentcar.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@EqualsAndHashCode(exclude = {
        "orders", "users"
})
@ToString(exclude = {
        "orders", "users"
})
@Table(name = "cars")
@Cacheable("cars")
@javax.persistence.Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String manufacturer;

    @Column
    private String model;

    @Column(name = "year_of_manufacture")
    private Timestamp yearOfManufacture;

    @Column(name = "engine_volume")
    private Float engineVolume;

    @Column
    private String color;

    @Column
    private Boolean conditioner;

    @Column(name = "registration_Number")
    private String registrationNumber;

    @Column
    private Double price;

    @Column(name = "user_id", insertable = false, updatable = false)
    private Long userId;

    @Column
    private String photo;

    @Column
    private String region;

    @Column(name = " creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

//    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @JsonManagedReference
//    private Set<Order> orders;
}

