package ru.aora.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aora.erp.model.user.User;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT U FROM User U WHERE U.username = :username")
    Optional<User> findByName(
            @Param("username") String username
    );

}