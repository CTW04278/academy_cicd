package com.ctw.stands.controller;

import com.ctw.stands.model.dto.StandDTO;
import com.ctw.stands.service.StandService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/stands")
@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StandController {
    private final StandService standService;

    public StandController(final StandService standService) {
        this.standService = standService;
    }

    @GET
    public Response getAllStands() {
        final List<StandDTO> stands = this.standService.getAllStands();
        return Response.status(Response.Status.OK).entity(stands).build();
    }

    @POST
    public Response create(StandDTO standDTO) {
        final StandDTO stand = this.standService.create(standDTO);
        return Response.status(Response.Status.CREATED).entity(stand).build();
    }
}
