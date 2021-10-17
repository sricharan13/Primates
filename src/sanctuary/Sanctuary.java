package sanctuary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * An interface to the Sanctuary class.
 * Provides a list of functionalities offered by a Sanctuary.
 */
public interface Sanctuary {

  /**
   * adds an primate to Isolation, if an Isolation cage is empty.
   * @param name - name of the Primate.
   * @param age - age of the Primate.
   * @param sex - sex of the Primate.
   * @param species - species of the Primate.
   * @param size - size of the Primate.
   * @param weight - weight of the Primate.
   * @param favFood - favFood of the Primate.
   */
  void provideHome(String name, int age, char sex,
                          String species, int size, int weight, String favFood);

  /**
   * returns the number of primates in sanctuary.
   */
  int getPrimateCount();

  /**
   * returns the number of free Isolation cages in Sanctuary.
   * @return - number of free cages in Isolation.
   */
  int getFreeCages();

  /**
   * returns the total number Isolation cages in Sanctuary.
   * @return - number of free cages in Isolation.
   */
  int getNumOfCages();

  /**
   * returns the total number troops cages in Sanctuary.
   * @return - number of free cages in Isolation.
   */
  int getNumOfTroops();

  /**
   * increases number of Isolation cages in the Sanctuary.
   * @param n - number of cages to add.
   */
  void increaseCagesBy(int n);

  /**
   * increases number of troops allowed in the Sanctuary.
   * @param m - number of troops to add.
   * @param area - area of enclosure.
   */
  void increaseTroopsBy(int m, int area);

  /**
   * returns the area of a given enclosure.
   * @param id - enclosureId
   */
  int getEnclosureArea(String id);

  /**
   * returns the area of a given enclosure.
   * @param id - enclosureId
   */
  int getEnclosureFreeArea(String id);

  /**
   * returns a list of all species that are accepted by the sanctuary.
   * @return - a list of species
   */
  HashSet<String> getSpeciesAllowed();

  /**
   * returns a list of valid food items that are accepted by the sanctuary.
   * @return - a list of food items
   */
  HashSet<String> getFoodAllowed();

  /**
   * adds the given species to the allowed species list.
   * @param species - species to be added.
   */
  void addSpeciesAllowed(String species);

  /**
   * adds the given food item to the allowed food list.
   * @param food - food to be added.
   */
  void addFoodAllowed(String food);

  /**
   * Increases the area of an enclosure.
   *
   * @param id - enclosureId
   * @param area - area by which enclosure should be increased.
   */
  void increaseEnclosureAreaBy(String id, int area);

  /**
   * moves an primate from enclosure to isolation, if isolation is free.
   * @param name - primateName.
   */
  void moveToIsolation(String name);

  /**
   * moves an primate from isolation to an enclosure, if enclosures are free.
   * @param name - primateName.
   */
  void moveToEnclosure(String name);

  /**
   * returns a list of all the species in the sanctuary and where they are located.
   *
   * @return - a map - SpeciesName=(HousingId, HousingId)
   */
  TreeMap<String, ArrayList<String>> getSpeciesList();

  /**
   * returns a sign for a given enclosure. A sign contains a list of all the animals
   * in an enclosure along with their name age and favourite food.
   *
   * @param id - enclosureId.
   * @return - a list of String lists - (PrimateName, PrimateAge, PrimateFavFood).
   */
  ArrayList<ArrayList<String>> getEnclosureSign(String id);

  /**
   * returns a list of housings in which a species can be found.
   * @param species - species to find for.
   * @return - a list of Strings - (PrimateName, PrimateAge, PrimateFavFood), ......
   */
  ArrayList<String> getHousing(String species);

  /**
   * returns a list of food items and the quantity required for each item.
   *
   * @return - a map - FoodItem=Quantity
   */
  TreeMap<String, Integer> getShoppingList();

  /**
   * returns a list of all the monkeys in alphabetical order along with their respective housing.
   *
   * @return - a map - PrimateName=HousingId,HousingId
   */
  TreeMap<String, String> getPrimateList();

  /**
   * move a primate in isolation to another sanctuary.
   * @param name - name of the monkey to add to sanctuary.
   * @param sanctuary - sanctuary to which primate should move to.
   */
  void moveToAnotherSanctuary(String name, Sanctuary sanctuary);
}
