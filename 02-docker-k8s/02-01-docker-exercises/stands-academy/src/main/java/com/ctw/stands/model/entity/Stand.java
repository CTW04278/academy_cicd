package com.ctw.stands.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Stand")
@NamedQuery(name = Stand.GET_ALL_STANDS, query = "select s from Stand s")
@NamedQuery(name = Stand.GET_BY_NAME, query = "select s from Stand s where UPPER(s.name) = UPPER(:standName)")
public class Stand {
    public static final String GET_ALL_STANDS = "Stand.getAllStands";
    public static final String GET_BY_NAME = "Stand.getByName";
    public static final String DELETE_BY_ID = "Stand.deleteById";

    @Id
    @SequenceGenerator(name = "standIdGenerator", sequenceName = "stand_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "standIdGenerator")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @OneToMany(mappedBy = "stand", fetch = FetchType.LAZY)
    private List<Car> cars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
