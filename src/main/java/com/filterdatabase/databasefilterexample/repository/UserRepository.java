package com.filterdatabase.databasefilterexample.repository;

import com.filterdatabase.databasefilterexample.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.stream.Stream;


@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByTckNo(String tckNO);

//    Stream<User> findByUser(String User);

    Optional<User> findByName(String name);
}
