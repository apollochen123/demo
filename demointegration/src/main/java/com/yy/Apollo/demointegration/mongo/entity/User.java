package com.yy.Apollo.demointegration.mongo.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

public class User {

    @Id
    String id;
    String name;
    String phone;
    Address address;
    List<String> friends;

    public User() {
        super();
    }

    public User(String id, String name, String phone, Address address, List<String> friends) {
        super();
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.friends = friends;
    }

    public User(UserOut userOut) {
        this.id = userOut.getId() == null ? null : userOut.getId();
        this.name = userOut.getName() == null ? null : userOut.getName();
        this.phone = userOut.getPhone() == null ? null : userOut.getPhone();
        this.address = userOut.getAddress() == null ? null : userOut.getAddress();
        this.friends = userOut.getFriends() == null ? null : userOut.getFriends();
    }

    static class Address {
        String home;
        String company;
        String other;

        public String getHome() {
            return home;
        }

        public void setHome(String home) {
            this.home = home;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }

        public String getOther() {
            return other;
        }

        public void setOther(String other) {
            this.other = other;
        }

        public Address(String home, String company, String other) {
            super();
            this.home = home;
            this.company = company;
            this.other = other;
        }

        public Address() {
            super();
        }
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (phone == null) {
            if (other.phone != null)
                return false;
        } else if (!phone.equals(other.phone))
            return false;
        return true;
    }

    public static class FILED {
        public static final String FILED_ID = "_id";
        public static final String FILED_NAME = "name";
        public static final String FILED_PHONE = "phone";
        public static final String FILED_ADDRESS = "address";
    }

}
