package com.percyvega;

import com.percyvega.model.Activity;
import com.percyvega.model.User;
import com.percyvega.repository.ActivityRepository;
import com.percyvega.repository.ActivityRepositoryStub;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("activities")
public class ActivityResource {

    private ActivityRepository activityRepository = new ActivityRepositoryStub();

    @POST
    @Path("activity")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Activity createActivityParams(Activity activity) {
        System.out.println("createActivityParams( " + activity + " )");

        activityRepository.create(activity);

        return activity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response updateActivity(@PathParam("id") String id, Activity activity) {
        System.out.println("updateAsctivity( " + activity + " )");

        Response response;

        Activity activityUpdated = activityRepository.update(id, activity);

        if(activityUpdated == null) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok(activityUpdated).build();
        }

        return response;
    }

    @DELETE
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response deleteActivity(@PathParam("id") String id) {
        System.out.println("deleteActivity( " + id + " )");

        activityRepository.delete(id);

        return Response.ok().build();
    }

    @POST
    @Path("activity")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Activity createActivityParams(MultivaluedMap<String, String> formParams) {
        System.out.println("createActivityParams()");

        System.out.println(formParams.getFirst("description"));
        System.out.println(formParams.getFirst("duration"));

        Activity activity = new Activity();
        activity.setDescription(formParams.getFirst("description"));
        activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));

        activityRepository.create(activity);

        return activity;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Activity> getAllActivities() {
        System.out.println("getActivities()");
        return activityRepository.findAllActivities();
    }

//    @GET
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    @Path("{activityId}")
//    public Activity getActivity(@PathParam("activityId") String activityId) {
//        System.out.println("getActivity(" + activityId + ")");
//        return activityRepository.findActivity(activityId);
//    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{activityId}")
    public Response getActivity(@PathParam("activityId") String activityId) {
        Response response;
        System.out.println("getActivity(" + activityId + ")");
        Activity activity = activityRepository.findActivity(activityId);

        if(activity == null) {
            response = Response.status(Response.Status.NOT_FOUND).build();
        } else {
            response = Response.ok(activity).build();
        }

        return response;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("{activityId}/user")
    public User getUser(@PathParam("activityId") String activityId) {
        System.out.println("getActivity(" + activityId + ")");

        Activity activity = activityRepository.findActivity(activityId);
        return activity.getUser();
    }

}
