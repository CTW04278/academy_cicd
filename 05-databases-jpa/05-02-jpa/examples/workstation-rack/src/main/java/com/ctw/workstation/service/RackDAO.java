package com.ctw.workstation.service;


import com.ctw.workstation.entity.Rack;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RackDAO {

    @Inject
    EntityManager entityManager;

    public List<Rack> getAll() {
        return entityManager.createNamedQuery(Rack.GET_ALL, Rack.class)
                .getResultList();
    }
}

