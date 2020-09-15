package com.geektrust.in.family.service;

import com.geektrust.in.family.model.Gender;
import com.geektrust.in.family.model.Person;
import com.geektrust.in.family.model.Relationship;

import java.util.List;

public interface FamilyService {
    void initializeFamilyTree();
    Person haveChildren(Person mother, String childName, Gender gender);
    Person searchByName(String name);
}
