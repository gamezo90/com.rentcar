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
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String manufacturer;

    @Column
    private String model;

    @Column(name = "engine_volume")
    private Float engineVolume;

    @Column
    private Boolean conditioner;

    @Column(name = "year_of_manufacture")
    private Timestamp yearOfManufacture;

    @Column
    private String color;

    @Column
    private String photo;

    @Column
    private String region;

    @Column
    private Double price;

    @Column(name = "registration_Number")
    private String registrationNumber;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "is_banned")
    private Boolean isBanned;

    @Column(name = " creation_date")
    @JsonIgnore
    private Timestamp creationDate;

    @Column(name = "modification_date")
    @JsonIgnore
    private Timestamp modificationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonBackReference
    private User user;

}

