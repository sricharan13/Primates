package sanctuary;

class Information {
  private final String housingId;
  private final String name;
  private final int age;
  private final char sex;
  private final String species;
  private final int size;
  private final int weight;
  private final String favFood;

  Information(String housingId, String name, int age, char sex, String species,
                     int size, int weight, String favFood) {
    this.housingId = housingId;
    this.name = name;
    this.age = age;
    this.sex = sex;
    this.species = species;
    this.size = size;
    this.weight = weight;
    this.favFood = favFood;
  }

  public String getHousingId() {
    return housingId;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public char getSex() {
    return sex;
  }

  public String getSpecies() {
    return species;
  }

  public int getSize() {
    return size;
  }

  public int getWeight() {
    return weight;
  }

  public String getFavFood() {
    return favFood;
  }
}
