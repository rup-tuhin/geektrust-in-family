package com.geektrust.in.family.service;

import com.geektrust.in.family.model.Person;
import com.geektrust.in.family.model.Relationship;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.geektrust.in.family.model.Gender.Female;
import static com.geektrust.in.family.model.Gender.Male;

public class RelationshipServiceImpl implements RelationshipService {

    @Override
    public List<Person> searchForRelationship(Person source, Relationship relationship) {
        Person father = source.getFather();
        Person mother = source.getMother();
        Person spouse = source.getSpouse();
        Predicate<Person> male = p -> Male.equals(p.getGender());
        Predicate<Person> female = p -> Female.equals(p.getGender());
        switch (relationship) {
            case PATERNAL_UNCLE:
                if (father != null && father.getFather() != null) {
                    return father.getFather().getChildren().stream()
                            .filter(male)
                            .filter(p -> !p.getName().equals(father.getName()))
                            .collect(Collectors.toList());
                }
                break;
            case MATERNAL_UNCLE:
                if (mother != null && mother.getFather() != null) {
                    return mother.getFather().getChildren().stream()
                            .filter(male)
                            .filter(p -> !p.getName().equals(mother.getName()))
                            .collect(Collectors.toList());
                }
                break;
            case PATERNAL_AUNT:
                if (father != null && father.getFather() != null) {
                    return father.getFather().getChildren().stream()
                            .filter(female)
                            .filter(p -> !p.getName().equals(father.getName()))
                            .collect(Collectors.toList());
                }
                break;
            case MATERNAL_AUNT:
                if (mother != null && mother.getFather() != null) {
                    return mother.getFather().getChildren().stream()
                            .filter(female)
                            .filter(p -> !p.getName().equals(mother.getName()))
                            .collect(Collectors.toList());
                }
                break;
            case SISTER_IN_LAW:
                List<Person> sil = new ArrayList<>();
                if (spouse != null && spouse.getFather() != null) {
                    spouse.getFather().getChildren().stream()
                            .filter(female)
                            .filter(p -> !p.getName().equals(spouse.getName()))
                            .forEach(sil::add);
                }
                if (father != null) {
                    father.getChildren().stream()
                            .filter(male)
                            .filter(Person::isMarried)
                            .filter(p -> !p.getName().equals(source.getName()))
                            .forEach(person -> sil.add(person.getSpouse()));
                }
                return sil;
            case BROTHER_IN_LAW:
                List<Person> bil = new ArrayList<>();
                if (spouse != null && spouse.getFather() != null) {
                    spouse.getFather().getChildren().stream()
                            .filter(male)
                            .filter(p -> !p.getName().equals(source.getName()))
                            .forEach(bil::add);
                }
                if (father != null) {
                    father.getChildren().stream()
                            .filter(female)
                            .filter(Person::isMarried)
                            .filter(p -> !p.getName().equals(source.getName()))
                            .forEach(person -> bil.add(person.getSpouse()));
                }
                return bil;
            case SON:
                if (father != null) {
                    return father.getChildren().stream()
                            .filter(male)
                            .collect(Collectors.toList());
                }
                break;
            case DAUGHTER:
                if (father != null) {
                    return father.getChildren().stream()
                            .filter(female)
                            .collect(Collectors.toList());
                }
                break;
            case SIBLINGS:
                if (father != null) {
                    return father.getChildren().stream()
                            .filter(p -> !p.getName().equals(source.getName()))
                            .collect(Collectors.toList());
                }
                break;
            default:
                break;
        }
        return null;
    }
}
