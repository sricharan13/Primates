package sanctuary;

import java.util.ArrayList;
import java.util.Objects;

class Enclosure {
  private final String id;
  private int totalArea;
  private int occupiedArea;
  private String species;
  private final ArrayList<Primate> troop = new ArrayList<>(0);

  Enclosure(int id, int area) {
    this.id = "ENCLOSURE-" + (id);
    this.totalArea = area;
    this.occupiedArea = 0;
    species = null;
  }

  private Primate searchTroopFor(String name) {
    return troop.stream().filter(p -> name.equals(p.getName())).findFirst().orElse(null);
  }

  String getId() {
    return id;
  }

  String getTroopSpecies() {
    return species;
  }

  int getTotalArea() {
    return totalArea;
  }

  int getFreeArea() {
    return totalArea - occupiedArea;
  }

  boolean containsPrimate(String name) {
    return searchTroopFor(name) != null;
  }

  void putPrimate(Primate primate) {
    troop.add(primate);
    occupiedArea += Primate.getSpaceRequirement(primate.getSize());
    if (species == null) {
      species = primate.getSpecies();
    }
  }

  ArrayList<Information> getPrimateDetails() {
    ArrayList<Information> info = new ArrayList<>();
    for (Primate primate : troop) {
      info.add(new Information(id, primate.getName(), primate.getAge(), primate.getSex(),
              primate.getSpecies(), primate.getSize(), primate.getWeight(), primate.getFavFood()));
    }
    return info;
  }

  Primate popPrimate(String name) {
    Primate primate = searchTroopFor(name);
    occupiedArea = occupiedArea - Primate.getSpaceRequirement(primate.getSize());
    troop.remove(primate);
    return primate;
  }

  void increaseAreaBy(int area) {
    totalArea += area;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Enclosure enclosure = (Enclosure) o;
    return id.equals(enclosure.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
