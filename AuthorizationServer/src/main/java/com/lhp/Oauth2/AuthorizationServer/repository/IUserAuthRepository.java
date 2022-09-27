package com.lhp.Oauth2.AuthorizationServer.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lhp.Oauth2.AuthorizationServer.pojo.User;

@Repository
public interface IUserAuthRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsernameLike(String username);

}
