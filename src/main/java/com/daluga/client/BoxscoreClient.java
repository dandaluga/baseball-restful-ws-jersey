package com.daluga.client;

import com.daluga.model.Boxscore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class BoxscoreClient {

    private Client client;

    private static final Logger logger = LoggerFactory.getLogger(BoxscoreClient.class);

    public BoxscoreClient() {
        this.client = ClientBuilder.newClient();
    }

    public Boxscore get(String id) {

        logger.debug("Get method with id was called.");

        WebTarget target = client.target("http://localhost:8080/webapi/");

        // Will convert to the binding object
        //Boxscore response = target.path("boxscores/" + id).request().get(Boxscore.class);

        // Will return XML
        //String response = target.path("boxscores/" + id).request().get(String.class);

        // Will return JSON
        //String response = target.path("boxscores/" + id).request(MediaType.APPLICATION_JSON).get(String.class);

        Response response = target.path("boxscores/" + id).request().get(Response.class);

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(Boxscore.class);
    }

    public List<Boxscore> get() {

        logger.debug("Get method called to return all boxscores.");

        WebTarget target = client.target("http://localhost:8080/webapi/");

        List<Boxscore> response = target.path("boxscores").request(MediaType.APPLICATION_JSON).get(new GenericType<List<Boxscore>>() {});

        return response;
    }

    public Boxscore create(Boxscore boxscore) {

        logger.debug("Create method was called");

        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("boxscores/boxscore")
                .request()
                .post(Entity.entity(boxscore, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(Boxscore.class);
    }

    public Boxscore update(Boxscore boxscore) {

        logger.debug("Update method was called");

        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("boxscores/" + boxscore.getId())
                .request()
                .put(Entity.entity(boxscore, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(Boxscore.class);
    }

    public void delete(String boxscoreId) {

        logger.debug("Delete method was called");

        WebTarget target = client.target("http://localhost:8080/webapi/");

        Response response = target.path("boxscores/" + boxscoreId)
                .request(MediaType.APPLICATION_JSON)
                .delete();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

    }
}
