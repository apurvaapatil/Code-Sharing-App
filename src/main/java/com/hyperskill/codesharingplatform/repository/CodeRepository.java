package com.hyperskill.codesharingplatform.repository;

import com.hyperskill.codesharingplatform.entity.Code;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface CodeRepository extends MongoRepository<Code, UUID> {
    List<Code> findFirst10ByTimeIsAndViewsIsOrderByDateDesc(long i, long i1);
}
