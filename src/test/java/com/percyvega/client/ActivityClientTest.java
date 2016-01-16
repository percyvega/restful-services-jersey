package com.percyvega.client;

import com.percyvega.model.Activity;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ActivityClientTest {

    @Test
    public void testPut() throws Exception {
        ActivityClient activityClient = new ActivityClient();

        Activity activity = new Activity();
        activity.setId("22");
        activity.setDuration(27);
        activity.setDescription("Jogging");

        activity = activityClient.update("123", activity);

        System.out.println(activity);

        assertNotNull(activity);
    }

    @Test
    public void testDelete() throws Exception {
        ActivityClient activityClient = new ActivityClient();

        Activity activity = new Activity();
        activity.setId("22");

        boolean success = activityClient.delete("123");

        assertTrue(success);
    }

    @Test
    public void testCreate() throws Exception {
        ActivityClient activityClient = new ActivityClient();

        Activity activity = new Activity();
        activity.setDuration(65);
        activity.setDescription("Jogging");

        activity = activityClient.create(activity);

        System.out.println(activity);

        assertNotNull(activity);
    }

    @Test
    public void testGetById() throws Exception {
        ActivityClient activityClient = new ActivityClient();

        Activity activity = activityClient.get("23");

        System.out.println(activity);

        assertNotNull(activity);
    }

    @Test(expected = RuntimeException.class)
    public void testGetByIdNotFound() {
        ActivityClient activityClient = new ActivityClient();

        Activity activity = activityClient.get("23");

        System.out.println(activity);

        assertNotNull(activity);
    }

    @Test
    public void testGet() throws Exception {
        ActivityClient activityClient = new ActivityClient();

        List<Activity> activityList = activityClient.get();

        System.out.println(activityList);

        assertNotNull(activityList);
    }

    @Test
    public void testSearch() {
        ActivitySearchClient searchClient = new ActivitySearchClient();

        String param = "description";
        List<String> searchValues = new ArrayList<String>();
        searchValues.add("Swimming");
        searchValues.add("Cycling");

        List<Activity> activities = searchClient.search(param, searchValues);

        System.out.println(activities.size());

        assertNotNull(activities);
    }
}