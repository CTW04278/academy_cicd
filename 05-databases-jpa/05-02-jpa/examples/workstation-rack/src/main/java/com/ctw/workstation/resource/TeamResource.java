package com.ctw.workstation.resource;

import com.ctw.workstation.entity.Team;
import com.ctw.workstation.service.TeamRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/teams")
public class TeamResource {

    @Inject
    TeamRepository teamRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Team> getAllTeams() {
        return teamRepository.listAll();
    }
}
