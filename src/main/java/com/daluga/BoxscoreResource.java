package com.daluga;

import com.daluga.model.Boxscore;
import com.daluga.model.Venue;
import com.daluga.repository.BoxscoreRepository;
import com.daluga.repository.BoxscoreRepositoryStub;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

// Read   = Get      http://localhost:8080/webapi/boxscores (Return all boxscores)
//                   http://localhost:8080/webapi/boxscores/1234 (Return a specific boxscore)
// Create = Post     http://localhost:8080/webapi/boxscores/boxscore
// Update = Put      http://localhost:8080/webapi/boxscores/1234 (Update existing record, need whole object)
// Update = Patch    http://localhost:8080/webapi/boxscores/1234 (Don't need to send the whole object, not in Jersey yet)
// Delete = Delete   http://localhost:8080/webapi/boxscores/1234

@Path("boxscores") // http://localhost:8080/webapi/boxscores
public class BoxscoreResource {

    private static final Logger logger = LoggerFactory.getLogger(BoxscoreResource.class);

    // TODO: Inject the class to get a reference to the repository.
    private BoxscoreRepository boxscoreRepository = new BoxscoreRepositoryStub();

    //-----------------------------------------------------------------------------------------
    // Use a HTTP Get to retrieve ALL resources in the repository.
    // Example: http://localhost:8080/webapi/boxscores
    //-----------------------------------------------------------------------------------------

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAllBoxscores() {

        logger.debug("Received a request to get ALL boxscores.");

        List<Boxscore> boxscores = boxscoreRepository.findAllBoxscores();

        return Response.ok().entity(new GenericEntity<List<Boxscore>>(boxscores) {}).build();
    }

    //-----------------------------------------------------------------------------------------
    // Use a HTTP Get to retrieve a single resource in the repository.
    // Example: http://localhost:8080/webapi/boxscores/1234
    //-----------------------------------------------------------------------------------------

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Path("{boxscoreId}")
//    public Boxscore getBoxscore(@PathParam("boxscoreId") String boxscoreId) {
//        return boxscoreRepository.findBoxscore(boxscoreId);
//    }

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getIt() {
//
//        return "Got it!";
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{boxscoreId}")
    public Response getBoxscore(@PathParam("boxscoreId") String boxscoreId) {

        logger.debug("Received a request to get a single boxscore.");

        //logger.debug("Boxscore Id length: " + boxscoreId.length());

        if (boxscoreId == null || boxscoreId.length() == 0 ) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        Boxscore boxscore = boxscoreRepository.findBoxscore(boxscoreId);

        if (boxscore == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(boxscore).build();
    }

    //-----------------------------------------------------------------------------------------
    // Use a HTTP Get to retrieve a single resource in the repository. In this scenario, we
    // are getting a related object underneath the main entity.
    // Example: http://localhost:8080/webapi/boxscores/1234/venue
    //-----------------------------------------------------------------------------------------

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{boxscoreId}/venue")
    public Response getBoxscoreVenue(@PathParam("boxscoreId") String boxscoreId) {

        logger.debug("Received a request to get a single venue.");

        Venue venue = boxscoreRepository.findBoxscore(boxscoreId).getVenue();

        return Response.ok().entity(venue).build();
    }

    //-----------------------------------------------------------------------------------------
    // Use a HTTP Post to create a resource in the repository.
    // This version send a boxscore in either a JSON or XML format that represents a boxscore
    // entity.
    //-----------------------------------------------------------------------------------------

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("boxscore")
    public Response createBoxscore(Boxscore boxscore) {

        logger.debug("Creating boxscore id: " + boxscore.getId() + " with name: " + boxscore.getName());

        boxscoreRepository.create(boxscore);

        return Response.ok().entity(boxscore).build();
    }

    //-----------------------------------------------------------------------------------------
    // Use a HTTP Post to create a resource in the repository.
    // This version sends individual form parameters that represent the attributes in the
    // entity.
    //-----------------------------------------------------------------------------------------

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("boxscore") // http://localhost:8080/webapi/boxscores/boxscore
    public Response createBoxscoreParams(MultivaluedMap<String, String> formParams) {

        logger.debug("Creating boxscore id: " + formParams.getFirst("id") + " with name: " + formParams.getFirst("name"));

        Boxscore boxscore = new Boxscore();
        boxscore.setId(Integer.parseInt(formParams.getFirst("id")));
        boxscore.setName(formParams.getFirst("name"));

        boxscore = boxscoreRepository.create(boxscore);

        return Response.ok().entity(boxscore).build();
    }

    //-----------------------------------------------------------------------------------------
    // Use a HTTP Put to update a resource in the repository.
    //-----------------------------------------------------------------------------------------

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{boxscoreId}")
    public Response update(Boxscore boxscore) {

        logger.debug("Updating boxscore id: " + boxscore.getId());

        boxscore = boxscoreRepository.update(boxscore);

        return Response.ok().entity(boxscore).build();
    }

    //-----------------------------------------------------------------------------------------
    // Use a HTTP Delete to delete a resource from the repository.
    //-----------------------------------------------------------------------------------------

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{boxscoreId}")
    public Response delete(@PathParam("boxscoreId") String boxscoreId) {

        logger.debug("Deleting boxscore id: " + boxscoreId);

        boxscoreRepository.delete(boxscoreId);

        return Response.ok().build();
    }

}