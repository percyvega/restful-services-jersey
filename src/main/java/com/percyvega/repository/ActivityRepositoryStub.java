package com.percyvega.repository;

import com.percyvega.model.Activity;
import com.percyvega.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by percy on 1/11/2016.
 */
public class ActivityRepositoryStub implements ActivityRepository {

    private static List<Activity> activities = new ArrayList<Activity>();

    static {
        User user1 = new User();
        user1.setId("11");
        user1.setName("Percy");

        Activity activity1 = new Activity();
        activity1.setId("1");
        activity1.setDescription("Swimming");
        activity1.setDuration(55);
        activity1.setUser(user1);
        activities.add(activity1);

        User user2 = new User();
        user2.setId("22");
        user2.setName("Fran");

        Activity activity2 = new Activity();
        activity2.setId("2");
        activity2.setDescription("Cycling");
        activity2.setDuration(120);
        activity2.setUser(user2);
        activities.add(activity2);
    }

    @Override
    public List<Activity> findAllActivities() {
        return activities;
    }

    @Override
    public Activity findActivity(String activityId) {
        System.out.println("ActivityRepositoryStub.findActivity(" + activityId + ")");

        Activity activityReturn = null;

        for (Activity activity : activities)
            if(activity.getId().equals(activityId))
                activityReturn = activity;

        return activityReturn;
    }

    @Override
    public void create(Activity activity) {
        System.out.println("ActivityRepositoryStub.create(" + activity + ")");

        activity.setId(Integer.toString(new Random().nextInt(10000)));
        activities.add(activity);
    }

    @Override
    public Activity update(String id, Activity activity) {
        System.out.println("ActivityRepositoryStub.update(" + activity + ")");

        Activity activityFound = findActivity(id);

        if(activityFound != null) {
//            activityFound.setId(activity.getId());
            activityFound.setDescription(activity.getDescription());
            activityFound.setDuration(activity.getDuration());
        }

        return activityFound;
    }

    @Override
    public void delete(String id) {
        System.out.println("ActivityRepositoryStub.delete(" + id + ")");

        Activity activity = findActivity(id);
        activities.remove(activity);
    }


    @Override
    public List<Activity> findByDescription(List<String> descriptions) {
        System.out.println("ActivityRepositoryStub.findByDescription(" + descriptions + ")");

        List<Activity> activitiesFound = new ArrayList<Activity>();

        for (Activity activity : activities) {
            for (String description : descriptions) {
                if (activity.getDescription().equals(description)) {
                    activitiesFound.add(activity);
                }
            }
        }

        return activitiesFound;
    }

}
