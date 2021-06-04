# Einführung in Java Streams

### `filter()`

- Filtert bestimmte Elemente, die einer Bedingung entsprechen
- Verwendet `Predicate<T>`
- Intermediate Operation

```java
List<Person> females = people.stream()
    .filter(person -> person.getGender().equals(Gender.FEMALE))
    .collect(Collectors.toList());

females.forEach(System.out::println);
```

### `sort()`

- Sortiert Elemente anhand eines Comparators
- Verwendet `Comparator<T>`
- Intermediate Operation

```java
List<Person> sorted = people.stream()
    .sorted(Comparator.comparing(Person::getAge).thenComparing(Person::getGender).reversed())
    .collect(Collectors.toList());

sorted.forEach(System.out::println);
```

### `allMatch`

- Prüft, ob alle Elemente einer Bedingung entsprechen
- Verwendet `Predicate<T>`
- Terminal Operation

```java
boolean allMatch = people.stream()
    .allMatch(person -> person.getAge() > 8);

System.out.println(allMatch);
```

### `anyMatch`

- Prüft, ob mind. ein Element der Bedingung entspricht
- Verwendet `Predicate<T>`
- Terminal Operation

```java
boolean anyMatch = people.stream()
    .anyMatch(person -> person.getAge() > 121);

System.out.println(anyMatch);
```

### `noneMatch`

- Prüft, ob kein Element der Bedingung entspricht
- Verwendet `Predicate<T>`
- Terminal Operation

```java
boolean noneMatch = people.stream()
    .noneMatch(person -> person.getName().equals("Antonio"));

System.out.println(noneMatch);
```

### `max()`

- Ermittelt das höchste Element (z. B. älteste Person)
- Verwendet `Comparator<T>`
- Rückgabe ist ein `Optional<T>`
- Terminal Operation

```java
var oldestPerson = people.stream().max(Comparator.comparing(Person::getAge));
oldestPerson.ifPresent(System.out::println);
```

### `min()`

- Ermittelt das höchste Element (z. B. jüngste Person)
- Verwendet `Comparator<T>`
- Rückgabe ist ein `Optional<T>`
- Terminal Operation

```java
var youngestPerson = people.stream().min(Comparator.comparing(Person::getAge));
youngestPerson.ifPresent(System.out::println);
```

### `map()`

- Verändert den Datentyp eines Streams (z. B. von `Person` auf `String`)
- Verwendet `Function<T, U>`
- Intermediate Operation

```java
var maleNames = people.stream()
  .filter(person -> person.getGender() == Gender.MALE)
  .map(Person::getName) // equivalent to .map(person -> person.getName())
  .collect(Collectors.joining(", "));

System.out.println(maleNames);
```

### `groupingBy()`

- Ein spezieller Collector
- Gruppiert eine Menge anhand eines Kriteriums (z. B. `Gender`)

```java
Map<Gender, List<Person>> groupByGender = people.stream()
  .collect(Collectors.groupingBy(Person::getGender));

for (Gender gender : groupByGender.keySet()) {
  System.out.println(gender);
  System.out.println(groupByGender.get(gender));
}
```
