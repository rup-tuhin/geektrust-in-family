package com.geektrust.in.family;


import com.geektrust.in.family.model.Gender;
import com.geektrust.in.family.model.Person;
import com.geektrust.in.family.model.Relationship;
import com.geektrust.in.family.service.FamilyService;
import com.geektrust.in.family.service.FamilyServiceImpl;
import com.geektrust.in.family.service.RelationshipService;
import com.geektrust.in.family.service.RelationshipServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.geektrust.in.family.utils.Constants.*;

public class GeektrustApplication {

    private static FamilyService familyService = new FamilyServiceImpl();
    private static RelationshipService relationshipService = new RelationshipServiceImpl();

    public static void main(String[] args) {
        familyService.initializeFamilyTree();
        if (args.length > 0) {
            try {
                String filePath = args[0];
                File file = new File(filePath);
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    String operation = s.next();
                    if (ADD_CHILD.equals(operation)) {
                        addChildOperation(s);
                    } else if (GET_RELATIONSHIP.equals(operation)) {
                        getRelationshipOperation(s);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private static void getRelationshipOperation(Scanner s) {
        String personName = s.next();
        String relationShipName = s.next();
        Person person = familyService.searchByName(personName);
        if (person != null) {
            List<Person> result = relationshipService.searchForRelationship(person,
                    Relationship.valueOf(relationShipName.replace("-", "_").toUpperCase()));
            if (result == null || result.isEmpty()) {
                System.out.println(NONE);
            } else {

                System.out.println(result.stream().map(Person::getName).collect(Collectors.joining(" ")));
            }
        } else {
            System.out.println(PERSON_NOT_FOUND);
        }
    }

    private static void addChildOperation(Scanner s) {
        String motherName = s.next();
        String childName = s.next();
        String gender = s.next();
        Person mother = familyService.searchByName(motherName);
        if (mother != null) {
            try {
                if (null != familyService.haveChildren(mother, childName, Gender.valueOf(gender))) {
                    System.out.println(CHILD_ADDITION_SUCCEEDED);
                } else {
                    System.out.println(CHILD_ADDITION_FAILED);
                }
            } catch (RuntimeException e) {
                System.out.println(CHILD_ADDITION_FAILED);
            }
        } else {
            System.out.println(PERSON_NOT_FOUND);
        }
    }
}
