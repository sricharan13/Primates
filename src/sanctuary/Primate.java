package sanctuary;

import java.util.Objects;

class Primate {

  static int getSpaceRequirement(int size) {
    if (size < 10) {
      return 1;
    }
    if (size <= 20) {
      return 5;
    }
    return 20;
  }

  static int getFoodRequirement(int size) {
    if (size < 10) {
      return 100;
    }
    if (size <= 20) {
      return 250;
    }
    return 500;
  }

  private final String name;
  private final char sex;
  private final String species;
  private int age;
  private int size;
  private int weight;
  private String favFood;

  Primate(String name, int age, char sex, String species, int size, int weight, String favFood) {
    this.species = species;
    this.sex = sex;
    this.name = name;
    this.age = age;
    this.size = size;
    this.weight = weight;
    this.favFood = favFood;
  }

  String getSpecies() {
    return species;
  }
  
  char getSex() {
    return sex;
  }
  
  String getName() {
    return name;
  }
  
  int getAge() {
    return age;
  }

  void setAge(int age) {
    this.age = age;
  }
  
  int getSize() {
    return size;
  }

  void setSize(int size) {
    this.size = size;
  }
  
  int getWeight() {
    return weight;
  }

  void setWeight(int weight) {
    this.weight = weight;
  }
  
  String getFavFood() {
    return favFood;
  }

  void setFavFood(String favFood) {
    this.favFood = favFood;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Primate primate = (Primate) o;
    return getName().equals(primate.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }
}
