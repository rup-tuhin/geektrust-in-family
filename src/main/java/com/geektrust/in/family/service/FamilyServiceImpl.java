package com.geektrust.in.family.service;

import com.geektrust.in.family.model.Gender;
import com.geektrust.in.family.model.Person;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

import static com.geektrust.in.family.model.Gender.Female;
import static com.geektrust.in.family.model.Gender.Male;
import static com.geektrust.in.family.utils.Constants.*;

public class FamilyServiceImpl implements FamilyService {

    private Person hof;

    @Override
    public Person haveChildren(Person mother, String childName, Gender gender) {
        if (mother.getGender() != Female) {
            throw new IllegalArgumentException("Only female persons can have a child");
        }
        if (getHof() == null) {
            throw new IllegalStateException("Family is not initialized yet");
        }
        Person father = mother.getSpouse();
        Person newBornBaby = new Person(childName, gender, father, mother);
        father.getChildren().add(newBornBaby);
        mother.getChildren().add(newBornBaby);
        return newBornBaby;
    }

    @Override
    public Person searchByName(String name) {
        Person personNode = breadthFirstSearch(p -> name.equals(p.getName()), getHof());
        if (personNode != null) {
            return personNode;
        } else {
            personNode = breadthFirstSearch(p -> (p.getSpouse() != null && name.equals(p.getSpouse().getName())), getHof());
            if (personNode != null) {
                return personNode.getSpouse();
            }
        }
        return null;
    }

    @Override
    public void initializeFamilyTree() {
        //generation One
        Person shan = new Person("Shan", Male);
        addFamilyHead(shan);
        settleChild(shan, new Person("Anga", Female), shanChildMap);
        //generation Two
        settleChild(searchByName("Chit"), new Person("Amba", Female), chitChildMap);
        settleChild(searchByName("Vich"), new Person("Lika", Female), vichChildMap);
        settleChild(searchByName("Aras"), new Person("Chitra", Female), arasChildMap);
        settleChild(new Person("Vyan", Male), searchByName("Satya"), vyanChildMap);
        //generation Thee
        settleChild(new Person("Jaya", Male), searchByName("Dritha"), jayaChildMap);
        settleChild(new Person("Arit", Male), searchByName("Jnki"), aritChildMap);
        settleChild(searchByName("Asva"), new Person("Satvy", Female), asvaChildMap);
        settleChild(searchByName("Vyas"), new Person("Krpi", Female), vyasChildMap);
    }

    private void settleChild(Person male, Person female, Map<String, Gender> childMap) {
        getMarried(male, female);
        growFamily(female, childMap);
    }

    private void growFamily(Person mother, Map<String, Gender> childMap) {
        for (String childName : childMap.keySet()) {
            haveChildren(mother, childName, childMap.get(childName));
        }
    }

    private void setHof(Person hof) {
        this.hof = hof;
    }

    private Person getHof() {
        return this.hof;
    }

    private Person breadthFirstSearch(Function<Person, Boolean> function, Person head) {
        Queue<Person> queue = new LinkedList<>();
        queue.add(head);
        while (queue.peek() != null) {
            Person current = queue.poll();
            if (function.apply(current)) {
                return current;
            } else queue.addAll(current.getChildren());
        }
        return null;
    }

    private void addFamilyHead(Person head) {
        if (getHof() == null) {
            setHof(head);
        } else {
            throw new IllegalStateException("Family head already present");
        }
    }

    private void getMarried(Person male, Person female) {
        if (male == null || female == null) {
            throw new NullPointerException("Cannot get married with null spouse");
        }
        male.getMarried(female);
        female.getMarried(male);
    }
}
