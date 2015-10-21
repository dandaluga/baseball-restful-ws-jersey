package com.daluga.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Venue {

    private int venueId;
    private String park;

    public int getVenueId() {
        return venueId;
    }

    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }

    public String getPark() {
        return park;
    }

    public void setPark(String park) {
        this.park = park;
    }
}
