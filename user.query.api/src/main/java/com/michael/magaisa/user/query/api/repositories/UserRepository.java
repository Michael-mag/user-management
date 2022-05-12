package com.michael.magaisa.user.query.api.repositories;

import com.michael.magaisa.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

}
