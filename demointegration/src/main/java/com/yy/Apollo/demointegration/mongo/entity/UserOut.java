package com.yy.Apollo.demointegration.mongo.entity;

import java.io.Serializable;
import java.util.List;

import com.yy.Apollo.demointegration.mongo.entity.User.Address;

public class UserOut implements Serializable {
    private static final long serialVersionUID = 1L;
    String id;
    String name;
    String phone;
    Address address;
    List<String> friends;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }
}
