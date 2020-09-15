package com.geektrust.in.family.model;

import java.util.LinkedList;
import java.util.List;

public class Person {
    private String name;
    private Gender gender;
    private MaritialStatus married;
    private Person spouse;
    private List<Person> children;
    private Person father;
    private Person mother;

    public Person(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
        this.married = MaritialStatus.UNMARRIED;
    }

    public Person(String name, Gender gender, Person father, Person mother) {
        this.name = name;
        this.gender = gender;
        this.married = MaritialStatus.UNMARRIED;
        this.father = father;
        this.mother = mother;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public void getMarried(Person spouse) {
        if (this.spouse != null) {
            throw new IllegalArgumentException("This person is already married");
        }
        this.spouse = spouse;
        this.married = MaritialStatus.MARRIED;
    }

    public boolean isMarried() {
        return MaritialStatus.MARRIED == married;
    }

    public Person getSpouse() {
        return spouse;
    }

    public List<Person> getChildren() {
        if (this.children == null) {
            this.children = new LinkedList<>();
        }
        return this.children;
    }

    public Person getFather() {
        return father;
    }

    public Person getMother() {
        return mother;
    }

}
