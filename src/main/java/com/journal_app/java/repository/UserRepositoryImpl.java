package com.journal_app.java.repository;

import com.journal_app.java.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
        List<User> users =  mongoTemplate.find(query,User.class);
        return users;
    }


    /*
    public List<User> showingOrOperator(){
        Query query = new Query();
        Criteria criteria = new Criteria(); //creating object for using and/or operator
        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
                Criteria.where("sentimentAnalysis").is(true))); // for using or operator
        // similarly AND operator can be used but we will be using normal query as the also equal to AND operator
        return mongoTemplate.find(query,User.class);
    }
     */



}
