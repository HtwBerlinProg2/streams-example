package de.htwberlin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) {
    List<Person> people = getPeople();

    // Imperative approach
    List<Person> females = new ArrayList<>();
    for (Person person : people) {
      if (person.getGender().equals(Gender.FEMALE)) {
        females.add(person);
      }
    }
//    for (Person femalePerson : females) {
//      System.out.println(femalePerson);
//    }

    /*
     * Declarative approach
     */

    // filter & collect
    List<Person> females2 = people.stream()
      .filter(person -> person.getGender().equals(Gender.FEMALE))
      .collect(Collectors.toList());

//    females2.forEach(System.out::println);

    // sorted
    List<Person> sorted = people.stream()
      .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
      .collect(Collectors.toList());

//    sorted.forEach(System.out::println);

    // allMatch
    boolean allMatch = people.stream()
      .allMatch(person -> person.getAge() > 8);

//    System.out.println(allMatch);

    // anyMatch
    boolean anyMatch = people.stream()
      .anyMatch(person -> person.getAge() > 121);

//    System.out.println(anyMatch);

    // noneMatch
    boolean noneMatch = people.stream()
      .noneMatch(person -> person.getName().equals("Antonio"));

//    System.out.println(noneMatch);

    // max()
    var oldestPerson = people.stream()
      .max(Comparator.comparing(Person::getAge));
//    oldestPerson.ifPresent(System.out::println);

    // min()
    var youngestPerson = people.stream().min(Comparator.comparing(Person::getAge));
//    youngestPerson.ifPresent(System.out::println);

    // map()
    var maleNames = people.stream()
      .filter(person -> person.getGender() == Gender.MALE)
      .map(Person::getName) // equivalent to .map(person -> person.getName())
      .collect(Collectors.joining(", "));

//    System.out.println(maleNames);

    // groupingBy
    Map<Gender, List<Person>> groupByGender = people.stream()
      .collect(Collectors.groupingBy(Person::getGender));

//    for (Gender gender : groupByGender.keySet()) {
//      System.out.println(gender);
//      System.out.println(groupByGender.get(gender));
//    }
  }

  private static List<Person> getPeople() {
    return List.of(
      new Person("Antonio", 20, Gender.MALE),
      new Person("Alina Smith", 33, Gender.FEMALE),
      new Person("Helen White", 57, Gender.FEMALE),
      new Person("Alex Boz", 14, Gender.MALE),
      new Person("Jamie Goa", 99, Gender.MALE),
      new Person("John Doe", 33, Gender.MALE),
      new Person("Anna Cook", 7, Gender.FEMALE),
      new Person("Zelda Brown", 120, Gender.FEMALE)
    );
  }
}
