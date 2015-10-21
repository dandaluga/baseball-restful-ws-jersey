package com.daluga;

import com.daluga.model.Boxscore;
import com.daluga.model.BoxscoreSearch;
import com.daluga.repository.BoxscoreRepository;
import com.daluga.repository.BoxscoreRepositoryStub;
import com.daluga.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

@Path("search/boxscores")
public class BoxscoreSearchResource {

    private static final Logger logger = LoggerFactory.getLogger(BoxscoreSearchResource.class);

    // TODO: Inject the class to get a reference to the repository.
    private BoxscoreRepository boxscoreRepository = new BoxscoreRepositoryStub();

    // Note: The Get style is problematic as the code to modify the search parameters will ripple
    //       thru all the various layers in the application to pass them to each layer. However,
    //       this style is more Restful.

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchForBoxscores(@QueryParam(value = "name") List<String> names,
                                       @QueryParam(value = "fromDate") String fromDate,
                                       @QueryParam(value = "toDate") String toDate) {

        logger.debug(names + ", from date  " + fromDate + " to date " + toDate);

        Date from = DateUtil.formatDate(fromDate);
        Date to = DateUtil.formatDate(toDate);

        List<Boxscore> boxscores = boxscoreRepository.findByDescription(names, from, to);

        if (boxscores == null || boxscores.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Boxscore>>(boxscores) {}).build();
    }

    // Note: The Post style is much easier in terms of maintenance and the ripple effect on the layers
    //       as an object is used to contain all the various search parameters. The problem with this
    //       approach is that some don't consider this as Restful as the Get style above.

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchForBoxscores(BoxscoreSearch search) {

        logger.debug("Search criteria: " + search);

        List<Boxscore> boxscores = boxscoreRepository.findByDescription(search);

        if (boxscores == null || boxscores.size() <= 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(new GenericEntity<List<Boxscore>>(boxscores) {}).build();
    }

}