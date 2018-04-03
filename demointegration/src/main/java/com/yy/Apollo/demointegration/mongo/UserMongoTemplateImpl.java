package com.yy.Apollo.demointegration.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.yy.Apollo.demointegration.mongo.entity.User;

public class UserMongoTemplateImpl implements UserMongoTemplate<User> {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<User> search(User t, Pageable pageable, Sort sort) {
        List<Criteria> criterias = new ArrayList<>();
        if (null != t.getId()) {
            Criteria criteria = Criteria.where(User.FILED.FILED_ID).regex(t.getId());
            criterias.add(criteria);
        }
        if (null != t.getName()) {
            Criteria criteria = Criteria.where(User.FILED.FILED_NAME).is(t.getName());
            criterias.add(criteria);
        }
        Criteria or = new Criteria();
        or.orOperator(criterias.toArray(new Criteria[criterias.size()]));
        Query query = Query.query(or);
        if (null != pageable) {
            query.with(pageable);
        }
        if (null != sort) {
            query.with(sort);
        }
        return mongoTemplate.find(query, User.class);
    }

}
