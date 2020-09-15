# geektrust-in-family
**Welcome to Family tree implementation**

We have two services namely: 

    FamilyService - this is used to add, update relations in the family

    RelationshipService - this is used to extract relationship from source person.

The data structure used here is a linked tree.
A `Person` has one `Person` as spouse, `List<Person>` as children,
one `Person` as father & one `Person` as mother.

Hence we have established the link between persons in the family.

The `Constant` class holds the list of children for each father.
 
The `FamilyService#settle#settleChild` method performs a marriage between two person and then let the mother have the children.

The `FamilyService#getMarried` method performs marriages between members.
Members can either be a person from the family, being searched by name,
 Or a new person introduced from outside. This updates the spouse information for a person.

 _Note : assuming a person can get married to only one._
 
 The `FamilyService#growFamily` method lets the mother have the children.

The `FamilyServiceImpl#initializeFamilyTree` spins up the family tree with initial records.

The `FamilyService#haveChildren` allows the mother the have a new born baby. 
This service is to be used to add child operation later on via main class.

The `RelationshipService#searchForRelationship` is used to search with a given relationship.


Check `Relationship` Enum for supported relationship.

_Note: to extend, please add on new relationships. And have new implementations in the RelationshipService'._
