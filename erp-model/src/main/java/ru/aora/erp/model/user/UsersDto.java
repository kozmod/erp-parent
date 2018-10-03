package ru.aora.erp.model.user;

import java.util.List;

public class UsersDto {
    private List<User> users;

    public UsersDto(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return "UsersDto{" +
                "users=" + users +
                '}';
    }
}
