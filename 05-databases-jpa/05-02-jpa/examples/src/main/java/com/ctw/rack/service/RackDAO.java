package com.ctw.rack.service;

import com.ctw.rack.entity.Rack;
import jakarta.inject.Inject;

import javax.persistence.EntityManager;
import java.util.List;

public class RackDAO {

    @Inject
    EntityManager entityManager;

    public List<Rack> getAll() {
        return entityManager.createNamedQuery(Rack.GET_ALL, Rack.class)
                .getResultList();
    }
}

