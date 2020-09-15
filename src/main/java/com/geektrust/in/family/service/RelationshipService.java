package com.geektrust.in.family.service;

import com.geektrust.in.family.model.Person;
import com.geektrust.in.family.model.Relationship;

import java.util.List;

public interface RelationshipService {
    List<Person> searchForRelationship(Person source, Relationship relationship);
}
