package org.acme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.math.BigInteger;
import java.util.List;

@Path("/fruits")
public class FruitResource {

    private final Logger log = LoggerFactory.getLogger(FruitResource.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruit> getAll() {
        log.info("Fruits Count: {}", Fruit.count());

        return Fruit.listAll();
    }
}