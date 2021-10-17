package sanctuary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.TreeMap;

/**
 * A primate sanctuary that implements the Sanctuary interface.
 */
public class PrimateSanctuary implements Sanctuary {

  /**
   * A factory method for the PrimateSanctuary class.
   *
   * @param n - number of cages
   * @param m - number of troops
   * @param area - area of each troop
   * @return - a new PrimateSanctuary.
   */
  public static Sanctuary newPrimateSanctuary(int n, int m, int area) {
    if (n < 0 || m < 0 || area < 0) {
      throw new IllegalArgumentException("negative arguments are not accepted");
    }
    return new PrimateSanctuary(n, m, area);
  }

  private int n = 0;
  private int m = 0;
  private int freeCages;
  private int primateCount;

  private final ArrayList<Cage> isolation = new ArrayList<>(n);
  private final ArrayList<Enclosure> enclosures = new ArrayList<>(m);
  private final HashSet<String> animalNames = new HashSet<>();
  private final HashSet<String> speciesAllowed = new HashSet<>(Arrays.asList("DRILL", "GUEREZA",
          "HOWLER", "MANGABEY", "SAKI", "SPIDER", "SQUIRREL", "TAMARIN"));
  private final HashSet<String> foodAllowed = new HashSet<>(Arrays.asList("EGGS", "FRUITS",
          "INSECTS", "LEAVES", "NUTS", "SEEDS", "SAP"));

  private PrimateSanctuary(int n, int m, int area) {
    for (int i = this.n + 1; i <= this.n + n; i ++) {
      isolation.add((new Cage(i)));
    }
    for (int i = this.m + 1; i <= this.m + m; i ++) {
      enclosures.add((new Enclosure(i, area)));
    }
    this.freeCages = this.n = n;
    this.m = m;
  }

  @Override
  public int getFreeCages() {
    return freeCages;
  }

  @Override
  public int getNumOfCages() {
    return n;
  }

  @Override
  public int getNumOfTroops() {
    return m;
  }

  @Override
  public void increaseCagesBy(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("negative arguments are not accepted");
    }
    for (int i = this.n + 1; i <= this.n + n; i ++) {
      isolation.add((new Cage(i)));
    }
    this.n += n;
    this.freeCages += n;
  }

  @Override
  public HashSet<String> getFoodAllowed() {
    return foodAllowed;
  }

  @Override
  public HashSet<String> getSpeciesAllowed() {
    return speciesAllowed;
  }

  @Override
  public void addFoodAllowed(String food) {
    foodAllowed.add(food.toUpperCase());
  }

  @Override
  public void addSpeciesAllowed(String species) {
    speciesAllowed.add(species.toUpperCase());
  }

  @Override
  public void increaseTroopsBy(int m, int area) {
    if (m < 0 || area < 0) {
      throw new IllegalArgumentException("negative arguments are not accepted");
    }
    for (int i = this.m + 1; i <= this.m + m; i ++) {
      enclosures.add((new Enclosure(i, area)));
    }
    this.m += m;
  }

  @Override
  public int getEnclosureArea(String id) {
    for (Enclosure e : enclosures) {
      if (e.getId().equals(id)) {
        return e.getTotalArea();
      }
    }
    throw new IllegalArgumentException("incorrect enclosure id given");
  }

  @Override
  public int getEnclosureFreeArea(String id) {
    for (Enclosure e : enclosures) {
      if (e.getId().equals(id)) {
        return e.getFreeArea();
      }
    }
    throw new IllegalArgumentException("incorrect enclosure id given");
  }

  @Override
  public void increaseEnclosureAreaBy(String id, int area) {
    if (area < 0) {
      throw new IllegalArgumentException("negative arguments are not accepted");
    }
    Enclosure enclosure =
            enclosures.stream().filter(e -> id.equals(e.getId())).findFirst().orElse(null);
    if (enclosure == null) {
      throw new IllegalArgumentException("incorrect enclosure id");
    }
    enclosure.increaseAreaBy(area);
  }

  @Override
  public void provideHome(String name, int age, char sex, String species,
                          int size, int weight, String favFood) {
    sex = Character.toUpperCase(sex);
    species = species.toUpperCase();
    favFood = favFood.toUpperCase();
    if (freeCages == 0) {
      throw new ArrayIndexOutOfBoundsException("isolation cages are full");
    }
    if (animalNames.contains(name)) {
      throw new IllegalArgumentException("names should be unique");
    }
    if (age < 0 || size < 0 || weight < 0) {
      throw new IllegalArgumentException("age, size and weight cannot be negative");
    }
    if (!speciesAllowed.contains(species) || !foodAllowed.contains(favFood)) {
      throw new IllegalArgumentException("incorrect food/species given");
    }
    for (Cage cage : isolation) {
      if (cage.isEmpty()) {
        cage.putPrimate(new Primate(name,age, sex, species, size, weight, favFood));
        animalNames.add(name);
        primateCount += 1;
        break;
      }
    }
    freeCages -= 1;
  }

  @Override
  public int getPrimateCount() {
    return primateCount;
  }

  @Override
  public void moveToEnclosure(String name) {
    if (!animalNames.contains(name)) {
      throw new IllegalArgumentException("animal is not in sanctuary");
    }
    boolean flag = false;
    boolean contains = false;
    for (Cage cage : isolation) {
      if (!cage.isEmpty() && cage.containsPrimate(name)) {
        contains = true;
        Information details = cage.getPrimateDetails();
        // check if there exists an enclosure with similar species and the free space.
        for (Enclosure enclosure : enclosures) {
          if (enclosure.getTroopSpecies() != null
                  && enclosure.getTroopSpecies().equals(details.getSpecies())
                  && enclosure.getFreeArea() >= Primate.getSpaceRequirement(details.getSize())) {
            enclosure.putPrimate(cage.popPrimate());
            freeCages += 1;
            flag = true;
          }
          if (flag) {
            break;
          }
        }
        // if no enclosure exists with similar species and free space.
        // try to add species to free enclosures.
        if (!flag) {
          for (Enclosure enclosure : enclosures) {
            if (enclosure.getTroopSpecies() == null
                    && enclosure.getFreeArea() >= Primate.getSpaceRequirement(details.getSize())) {
              flag = true;
              enclosure.putPrimate(cage.popPrimate());
              freeCages += 1;
            }
            if (flag) {
              break;
            }
          }
        }
      }
      if (flag) {
        break;
      }
    }
    if (!contains) {
      throw new IllegalArgumentException("primate not in isolation");
    }
    if (!flag) {
      throw new ArrayIndexOutOfBoundsException("no space in enclosure");
    }
  }

  @Override
  public void moveToAnotherSanctuary(String name, Sanctuary sanctuary) {
    if (!animalNames.contains(name)) {
      throw new IllegalArgumentException("animal is not in sanctuary");
    }
    if (sanctuary.getFreeCages() == 0) {
      throw new ArrayIndexOutOfBoundsException("no free cages in new sanctuary");
    }
    boolean flag = false;
    for (Cage cage : isolation) {
      if (!cage.isEmpty() && cage.containsPrimate(name)) {
        Information info = cage.getPrimateDetails();
        flag = true;
        sanctuary.provideHome(info.getName(), info.getAge(), info.getSex(),
                info.getSpecies(), info.getSize(), info.getWeight(), info.getFavFood());
        cage.removePrimate();
        primateCount -= 1;
        freeCages -= 1;
        break;
      }
    }
    if (!flag) {
      throw new NoSuchElementException("primate not in isolation");
    }
  }

  @Override
  public void moveToIsolation(String name) {
    if (!animalNames.contains(name)) {
      throw new IllegalArgumentException("animal is not in sanctuary");
    }
    if (freeCages == 0) {
      throw new ArrayIndexOutOfBoundsException("isolation cages are full");
    }
    boolean flag = false;
    for (Enclosure enclosure : enclosures) {
      if (enclosure.containsPrimate(name)) {
        flag = true;
        for (Cage cage : isolation) {
          if (cage.isEmpty()) {
            cage.putPrimate(enclosure.popPrimate(name));
          }
        }
        freeCages -= 1;
        break;
      }
    }
    if (!flag) {
      throw new IllegalArgumentException("animal is not in enclosure");
    }
  }

  @Override
  public TreeMap<String, ArrayList<String>> getSpeciesList() {
    TreeMap<String, ArrayList<String>> speciesList = new TreeMap<>();
    ArrayList<Information> info = gatherPrimateDetails();
    ArrayList<String> list;
    for (Information i : info) {
      if (speciesList.containsKey(i.getSpecies())) {
        list = speciesList.get(i.getSpecies());
        list.add(i.getHousingId());
      }
      else {
        list = new ArrayList<>();
        list.add(i.getHousingId());
        speciesList.put(i.getSpecies(), list);
      }
    }
    return speciesList;
  }

  @Override
  public ArrayList<String> getHousing(String species) {
    species = species.toUpperCase();
    if (!speciesAllowed.contains(species)) {
      throw new IllegalArgumentException("incorrect species given");
    }
    ArrayList<Information> info = gatherPrimateDetails();
    ArrayList<String> housingList = new ArrayList<>();
    for (Information i : info) {
      if (i.getSpecies().equals(species)) {
        housingList.add(i.getHousingId());
      }
    }
    if (housingList.size() == 0) {
      throw new NoSuchElementException("No such species found");
    }
    return housingList;
  }

  @Override
  public ArrayList<ArrayList<String>> getEnclosureSign(String enclosureId) {
    ArrayList<Information> info = null;
    for (Enclosure enclosure : enclosures) {
      if (enclosure.getId().equals(enclosureId)) {
        info = enclosure.getPrimateDetails();
        break;
      }
    }
    if (info == null) {
      throw new IllegalArgumentException("incorrect enclosure id given");
    }
    ArrayList<ArrayList<String>> sign = new ArrayList<>();
    for (Information i : info) {
      ArrayList<String> details = new ArrayList<>(
              Arrays.asList(i.getName(), i.getFavFood(), String.valueOf(i.getSex())));
      sign.add(details);
    }
    return sign;
  }

  @Override
  public TreeMap<String, String> getPrimateList() {
    TreeMap<String, String> primateList = new TreeMap<>();
    ArrayList<Information> info = gatherPrimateDetails();
    for (Information i: info) {
      primateList.put(i.getName(), i.getHousingId());
    }
    return primateList;
  }

  @Override
  public TreeMap<String, Integer> getShoppingList() {
    TreeMap<String, Integer> foodList = new TreeMap<>();
    ArrayList<Information> info = gatherPrimateDetails();
    for (Information i: info) {
      if (foodList.containsKey(i.getFavFood())) {
        foodList.put(i.getFavFood(),
                Primate.getFoodRequirement(i.getSize()) + foodList.get(i.getFavFood()));
      }
      else {
        foodList.put(i.getFavFood(), Primate.getFoodRequirement(i.getSize()));
      }
    }
    return foodList;
  }

  private ArrayList<Information> gatherPrimateDetails() {
    ArrayList<Information> info = new ArrayList<>();
    for (Cage cage : isolation) {
      if (!cage.isEmpty()) {
        info.add(cage.getPrimateDetails());
      }
    }
    for (Enclosure enclosure : enclosures) {
      if (enclosure.getTroopSpecies() != null) {
        info.addAll(enclosure.getPrimateDetails());
      }
    }
    return info;
  }

}
