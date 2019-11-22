package com.stackroute.favouriteservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.model.UserArticle;

@Repository
public interface ArticleRepository extends MongoRepository<UserArticle, String> {

}
