package com.charles.admin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class TravelRoute extends BaseDomain {
    @Column
    private String departure;
    @Column
    private String destination;

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }
}
