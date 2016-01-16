package com.percyvega.repository;

import com.percyvega.model.Activity;

import java.util.List;

/**
 * Created by percy on 1/11/2016.
 */
public interface ActivityRepository {
    List<Activity> findAllActivities();

    Activity findActivity(String activityId);

    void create(Activity activity);

    Activity update(String id, Activity activity);

    void delete(String id);

    List<Activity> findByDescription(List<String> descriptions);
}
