package com.ctw.workstation.resource;

import com.ctw.workstation.entity.Rack;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/racks")
public class RackResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rack> getAllRacks() {
        return Rack.listAll();
    }

    public void examples() {
        // create rack
        Rack rack = new Rack();
        rack.serialNumber = "2345-12";

        // persist rack
        rack.persist();

        // find rack
        long rackId = 1213;
        Rack.findById(rackId);

        // list all
        Rack.listAll();

        // count all racks
        Long countAll = Rack.count();

        // delete by attribute
        Rack.delete("serialNumber", "2345-12");

        // delete all
        Rack.deleteAll();

        // rack update by id
        Rack.update("serialNumber= '2345-11' where id= ?1", 123456);
    }
}
