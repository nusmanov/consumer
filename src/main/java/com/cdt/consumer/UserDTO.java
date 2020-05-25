package com.cdt.consumer;

import java.util.StringJoiner;

public class UserDTO {
    private String user;
    private Integer id;

    public String getUser() {
        return user;
    }
    public Integer getId() {
        return id;
    }

    public UserDTO() {}
    public UserDTO(String user, Integer id) {
        this.user = user;
        this.id = id;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserDTO.class.getSimpleName() + "[", "]")
            .add("user='" + user + "'")
            .add("id=" + id)
            .toString();
    }
}
