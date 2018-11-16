package ru.aora.erp.model.entity.user;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public final class UsersDto {
    private List<User> users;

    private UsersDto(List<User> users) {
        this.users = users;
    }

    public static UsersDto of(List<User> users){
        return new UsersDto(users);
    }

    public static UsersDto of(User ... users){
        return new UsersDto(Arrays.asList(users));
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UsersDto.class.getSimpleName() + "[", "]")
                .add("users=" + users)
                .toString();
    }
}
