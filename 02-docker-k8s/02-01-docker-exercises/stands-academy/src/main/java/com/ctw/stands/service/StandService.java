package com.ctw.stands.service;

import com.ctw.stands.model.dao.StandDAO;
import com.ctw.stands.model.dto.StandDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import org.eclipse.microprofile.faulttolerance.Retry;

@ApplicationScoped
public class StandService {
    private int requestCounter = 0;

    @Inject
    StandDAO standDAO;

    @Retry(maxRetries = 2)
    public List<StandDTO> getAllStands() {
        if (requestCounter < 2) {
            requestCounter++;
            throw new IllegalStateException("Counter = " + requestCounter);
        }
        return standDAO.getAllStands();
    }

    public StandDTO create(StandDTO standDTO) {
        return standDAO.create(standDTO);
    }

}

