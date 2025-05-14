package com.jpa1prueba.mongodbmodule.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.jpa1prueba.mongodbmodule.entities.ApiLog;

@Repository
public interface ApiLogRepository extends MongoRepository<ApiLog, String> {
    // Las operaciones CRUD básicas son proporcionadas por MongoRepository
}