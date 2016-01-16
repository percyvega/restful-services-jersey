package com.percyvega;

import com.percyvega.model.Activity;
import com.percyvega.repository.ActivityRepository;
import com.percyvega.repository.ActivityRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("search/activities")
public class ActivitySearchResource {

    private ActivityRepository activityRepository = new ActivityRepositoryStub();

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response searchForActivities(@QueryParam(value = "description") List<String> descriptions) {
        System.out.println(descriptions);

        List<Activity> activities = activityRepository.findByDescription(descriptions);

        return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {}).build();
    }

}
