package com.daluga.client;

import com.daluga.model.Boxscore;
import com.daluga.model.BoxscoreSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

public class BoxscoreSearchClient {

    private Client client;

    private static final Logger logger = LoggerFactory.getLogger(BoxscoreSearchClient.class);

    public BoxscoreSearchClient() {

        client = ClientBuilder.newClient();
    }

    public List<Boxscore> search(String nameParam, List<String> names, String fromDateParam, String fromDate, String toDateParam, String toDate) {

        logger.debug("Get method called to search for boxscores.");

        URI uri = UriBuilder.fromUri("http://localhost:8080/webapi")
                .path("search/boxscores")
                .queryParam(nameParam, names)
                .queryParam(fromDateParam, fromDate)
                .queryParam(toDateParam, toDate)
                .build();

        logger.debug(uri.toString());

        WebTarget target = client.target(uri);

        List<Boxscore> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Boxscore>>() {});

        logger.debug(response.toString());

        return response;
    }

    public List<Boxscore> search(BoxscoreSearch search) {

        logger.debug("Post method called to search for boxscores.");

        URI uri = UriBuilder.fromUri("http://localhost:8080/webapi")
                .path("search/boxscores")
                .build();

        WebTarget target = client.target(uri);

        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(search, MediaType.APPLICATION_JSON));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");
        }

        return response.readEntity(new GenericType<List<Boxscore>>() {});
    }

}
