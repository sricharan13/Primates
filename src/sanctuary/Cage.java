package sanctuary;

import java.util.Objects;

final class Cage {

  private final String id;
  private boolean empty;
  private Primate primate;

  Cage(int id) {
    this.id = "CAGE-" + (id);
    primate = null;
    empty = true;
  }

  String getId() {
    return id;
  }

  Information getPrimateDetails() {
    return new Information(this.id, primate.getName(), primate.getAge(), primate.getSex(),
            primate.getSpecies(), primate.getSize(), primate.getWeight(), primate.getFavFood());
  }

  boolean isEmpty() {
    return empty;
  }

  void putPrimate(Primate primate) {
    this.primate = primate;
    this.empty = false;
  }

  Primate popPrimate() {
    Primate p = primate;
    this.primate = null;
    this.empty = true;
    return p;
  }

  void removePrimate() {
    this.primate = null;
    this.empty = true;
  }

  boolean containsPrimate(String name) {
    return name.equals(primate.getName());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Cage cage = (Cage) o;
    return id.equals(cage.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
