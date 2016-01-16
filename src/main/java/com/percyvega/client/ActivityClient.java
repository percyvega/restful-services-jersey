package com.percyvega.client;

import com.percyvega.model.Activity;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by percy on 1/11/2016.
 */
public class ActivityClient {

    private Client client;
    private WebTarget webTarget;

    public ActivityClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:8080/webapi/activities/");
    }

    public Activity get(String id) {
        Invocation.Builder builder = webTarget.path(id).request();
        Response response = builder.get(Response.class);

        if(response.getStatus() == Response.Status.NOT_FOUND.getStatusCode())
            throw new RuntimeException("Activity with code " + id + " was not found.");

        return response.readEntity(Activity.class);
    }

    public List<Activity> get() {
        Invocation.Builder builder = webTarget.request();
        List<Activity> activityList = builder.get(new GenericType<List<Activity>>(){});

        return activityList;
    }

    public Activity create(Activity activity) {
        Invocation.Builder builder = webTarget.path("activity").request(MediaType.APPLICATION_JSON);
        Response response = builder.post(Entity.entity(activity, MediaType.APPLICATION_JSON));

        if(response.getStatus() != Response.Status.OK.getStatusCode())
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");

        return response.readEntity(Activity.class);
    }

    public Activity update(String id, Activity activity) {
        Invocation.Builder builder = webTarget.path(id).request(MediaType.APPLICATION_JSON);
        Response response = builder.put(Entity.entity(activity, MediaType.APPLICATION_JSON));

        if(response.getStatus() != Response.Status.OK.getStatusCode())
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");

        return response.readEntity(Activity.class);
    }

    public boolean delete(String id) {
        Invocation.Builder builder = webTarget.path(id).request(MediaType.APPLICATION_JSON);
        Response response = builder.delete();

        if(response.getStatus() != Response.Status.OK.getStatusCode())
            throw new RuntimeException(response.getStatus() + ": there was an error on the server.");

        return true;
    }
}
