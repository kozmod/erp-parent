package ru.aora.erp.domain;

import ru.aora.erp.model.entity.business.User;

import java.util.List;
import java.util.Optional;

public interface UserGateway {

    Optional<User> findByName(String name);

    List<User> findAll();

    Optional<User> update(User user);

    Optional<User> delete(User user);

}

