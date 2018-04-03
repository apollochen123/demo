package com.yy.Apollo.demointegration.mongo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface UserMongoTemplate<T> {
    public List<T> search(T t, Pageable pageable,Sort sort);
}
