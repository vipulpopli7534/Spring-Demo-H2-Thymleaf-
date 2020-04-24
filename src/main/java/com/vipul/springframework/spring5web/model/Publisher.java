package com.vipul.springframework.spring5web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long publisherId;
    private String name;
    private String address;

    public Publisher(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher = (Publisher) o;
        return publisherId == publisher.publisherId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(publisherId);
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
