package com.percyvega.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by percy on 1/11/2016.
 */
@XmlRootElement
public class User {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
