package com.inpivota.omega.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class DataService {

    private EntityManager entityManager;
    @Autowired
    public DataService(
            EntityManager entityManager
    ){
        this.entityManager = entityManager;
    }

    public List<String> getAllDataTypes(){
        List<String> types = new ArrayList<>();
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();
        entityTypes.forEach(entityType -> types.add(entityType.getName()));
        return types;
    }
}
