package com.percyvega.client;

import com.percyvega.model.Activity;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.List;

public class ActivitySearchClient {

    private Client client;
    private WebTarget webTarget;

    public ActivitySearchClient() {
        client = ClientBuilder.newClient();
        webTarget = client.target("http://localhost:8080/webapi/search/activities/");
    }

    public List<Activity> search(String param, List<String> searchValues) {
//        List<Activity> response = target.queryParam(param, searchValues).request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {});

        URI uri = UriBuilder.fromUri("http://localhost:8080/webapi")
                .path("search/activities")
                .queryParam(param, searchValues)
                .build();

        WebTarget target = client.target(uri);

        List<Activity> response = target.request(MediaType.APPLICATION_JSON).get(new GenericType<List<Activity>>() {});

        System.out.println(response);

        return response;
    }

}
