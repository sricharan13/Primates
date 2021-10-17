import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import sanctuary.PrimateSanctuary;
import sanctuary.Sanctuary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * test class for the Sanctuary.
 * performs unit tests for all the methods and functionalities provided by the Sanctuary interface.
 */
public class SanctuaryTest {

  Sanctuary jungleFriends;

  // a helper method to create a new PrimateSanctuary.
  private void createPrimateSanctuary(int n, int m, int area) {
    jungleFriends = PrimateSanctuary.newPrimateSanctuary(n, m, area);
  }

  // a helper method to add primate to sanctuary.
  private void provideHomeHelper(String name, int age, char sex, String species,
                          int size, int weight, String favFood) {
    jungleFriends.provideHome(name,age, sex, species, size, weight, favFood);

  }

  /**
   * tests for an new sanctuary - check if all the values are correctly initialized.
   * and proper exceptions are thrown. Perform various tests related to the sanctuary.
    */
  @Test
  public void testNewSanctuary() {
    // illegal arguments - cages cannot be negative.
    try {
      createPrimateSanctuary(-1, 10, 20);
      fail("number of cages can't be negative");
    }
    catch (IllegalArgumentException e) {
      assertEquals("negative arguments are not accepted", e.getMessage());
    }
    // illegal arguments - troops cannot be negative.
    try {
      createPrimateSanctuary(10, -1, 20);
      fail("number of enclosures can't be negative");
    }
    catch (IllegalArgumentException e) {
      assertEquals("negative arguments are not accepted", e.getMessage());
    }
    // illegal arguments - area cannot be negative.
    try {
      createPrimateSanctuary(10, 10, -1);
      fail("enclosure area can't be negative");
    }
    catch (IllegalArgumentException e) {
      assertEquals("negative arguments are not accepted", e.getMessage());
    }
    // create a sanctuary with n cages, 15 troops and 20 sq-m area per troop/ecnlosure.
    createPrimateSanctuary(10, 15, 20);
    // check the number of cages in sanctuary.
    assertEquals(10, jungleFriends.getNumOfCages());
    // check the number of free cages in sanctuary.
    assertEquals(10, jungleFriends.getFreeCages());
    // check the number of troops in sanctuary.
    assertEquals(15, jungleFriends.getNumOfTroops());
    // check the number of primates in sanctuary.
    assertEquals(0, jungleFriends.getPrimateCount());


    HashSet<String> expected = new HashSet<>(Arrays.asList("DRILL", "GUEREZA",
            "HOWLER", "MANGABEY", "SAKI", "SPIDER", "SQUIRREL", "TAMARIN"));

    // check the sanctuary allows the predetermined species to be permitted in.
    assertTrue(jungleFriends.getSpeciesAllowed().containsAll(expected));
    // add a new species to the allowed list.
    jungleFriends.addSpeciesAllowed("Apes");
    expected.add("APES");
    // check if the allowed species list is updated.
    assertTrue(jungleFriends.getSpeciesAllowed().containsAll(expected));

    expected = new HashSet<>(Arrays.asList("EGGS", "FRUITS",
            "INSECTS", "LEAVES", "NUTS", "SEEDS", "SAP"));

    // check the sanctuary provides predetermined food.
    assertTrue(jungleFriends.getFoodAllowed().containsAll(expected));
    // add a new food item to the allowed list.
    jungleFriends.addFoodAllowed("RAISINS");
    expected.add("RAISINS");
    // check if the allowed food items are updated.
    assertTrue(jungleFriends.getFoodAllowed().containsAll(expected));

  }

  /**
   * check if increasing the number of cages and validate the updates.
   */
  @Test
  public void increaseCagesBy() {
    // create a sanctuary with n cages, 15 troops and 20 sq-m area per troop/ecnlosure.
    createPrimateSanctuary(10, 15, 20);
    // check the number of cages in sanctuary.
    assertEquals(10, jungleFriends.getNumOfCages());
    // check the number of free cages in sanctuary.
    assertEquals(10, jungleFriends.getFreeCages());
    // increase number of cages by 10.
    jungleFriends.increaseCagesBy(10);
    // check the number of cages in sanctuary (correctly updated?).
    assertEquals(20, jungleFriends.getNumOfCages());
    // check the number of free cages in sanctuary (correctly updated?).
    assertEquals(20, jungleFriends.getFreeCages());
  }

  /**
   * check if increasing the number of troops and validate the updates.
   */
  @Test
  public void increaseTroopsBy() {
    // create a sanctuary with n cages, 15 troops and 20 sq-m area per troop/ecnlosure.
    createPrimateSanctuary(10, 15, 20);
    // check the number of troops in sanctuary.
    assertEquals(15, jungleFriends.getNumOfTroops());
    // increase number of troops by 10 with 20 sq-m area.
    jungleFriends.increaseTroopsBy(10, 20);
    // check the number of troops in sanctuary (correctly updated?).
    assertEquals(25, jungleFriends.getNumOfTroops());
  }

  /**
   * add new primates to sanctuary and check validate the updates.
   * checks if the proper exceptions are being thrown.
   * uses various getter methods of the sanctuary class to check if correct values are returned.
   */
  @Test
  public void testProvideHome() {
    createPrimateSanctuary(5, 3, 20);
    // illegal species argument
    try {
      provideHomeHelper("M1", 10, 'M', "drill", 20, 40, "trees");
      fail("species not in allowed species list");
    }
    catch (IllegalArgumentException e) {
      assertEquals("incorrect food/species given", e.getMessage());
    }
    // illegal food argument
    try {
      provideHomeHelper("M1", 10, 'M', "chimpanzee", 20, 40, "trees");
      fail("food not in allowed food list");
    }
    catch (IllegalArgumentException e) {
      assertEquals("incorrect food/species given", e.getMessage());
    }
    // illegal age argument
    try {
      provideHomeHelper("M1", -10, 'M', "chimpanzee", 20, 40, "trees");
      fail("age cannot be negative");
    }
    catch (IllegalArgumentException e) {
      assertEquals("age, size and weight cannot be negative", e.getMessage());
    }
    // illegal size argument
    try {
      provideHomeHelper("M1", 10, 'M', "chimpanzee", -20, 40, "trees");
      fail("size cannot be negative");
    }
    catch (IllegalArgumentException e) {
      assertEquals("age, size and weight cannot be negative", e.getMessage());
    }
    // illegal weight argument
    try {
      provideHomeHelper("M1", 10, 'M', "chimpanzee", 20, -40, "trees");
      fail("weight cannot be negative");
    }
    catch (IllegalArgumentException e) {
      assertEquals("age, size and weight cannot be negative", e.getMessage());
    }
    // check free cages in sanctuary.
    assertEquals(5, jungleFriends.getFreeCages());
    // adding a new primate to sanctuary.
    provideHomeHelper("M1", 10, 'M', "drill", 20, 40, "leaves");
    // check free cages after adding 1 primate.
    assertEquals(4, jungleFriends.getFreeCages());
    // check primate count after adding one primate.
    assertEquals(1, jungleFriends.getPrimateCount());
    // illegal monkey name - name not unique.
    try {
      provideHomeHelper("M1", 10, 'M', "drill", 20, 40, "leaves");
      fail("primates can't have same name");
    }
    catch (IllegalArgumentException e) {
      assertEquals("names should be unique", e.getMessage());
    }
    // adding a new primate to sanctuary.
    provideHomeHelper("A2", 15, 'F', "drill", 10, 50, "leaves");
    // check free cages after adding 2nd primate.
    assertEquals(3, jungleFriends.getFreeCages());
    // check primate count after adding one primate.
    assertEquals(2, jungleFriends.getPrimateCount());
    // adding a new primate to sanctuary.
    provideHomeHelper("C3", 15, 'F', "mangabey", 30, 20, "sap");
    // adding a new species to allowed list.
    jungleFriends.addSpeciesAllowed("ape");
    // adding a new food item to allowed list.
    jungleFriends.addFoodAllowed("raisins");
    // adding a new primate to sanctuary belonging to new species with new food.
    provideHomeHelper("C2", 10, 'M', "ape", 20, 15, "raisins");
    // adding a new primate to sanctuary.
    provideHomeHelper("A1", 12, 'F', "tamarin", 30, 20, "sap");
    // check free cages after adding 3 new primate.
    assertEquals(0, jungleFriends.getFreeCages());
    // check primate count after 3 new primates.
    assertEquals(5, jungleFriends.getPrimateCount());
    // index out of bounds - no more free cages
    try {
      provideHomeHelper("M6", 10, 'M', "squirrel", 20, 40, "Nuts");
      fail("should not add to sanctuary");
    }
    catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("isolation cages are full", e.getMessage());
    }

    // primates are added to the first cage that is empty. So,
    // M1 - species: drill, size: 20, food: leaves, should be in CAGE-1
    // A2 - species: drill, size: 10, food: leaves, should be in CAGE-2
    // C3 - species: mangabey, size: 30, food: sap, should be in CAGE-3
    // C2 - species: ape, size: 20, food: raisins, should be in CAGE-4
    // A1 - species: tamarain, size: 30, food: sap, should be in CAGE-5
    // food quantity of sap is 1000
    // food quantity of leaves are 500
    // food quantity of raisins are 250

    // check that primate count updated correctly.
    assertEquals(5, jungleFriends.getPrimateCount());

    // check if primates are added to the correct cages.
    TreeMap<String, String> primateActual = jungleFriends.getPrimateList();
    assertEquals("CAGE-1", primateActual.get("M1"));
    assertEquals("CAGE-2", primateActual.get("A2"));
    assertEquals("CAGE-3", primateActual.get("C3"));
    assertEquals("CAGE-4", primateActual.get("C2"));
    assertEquals("CAGE-5", primateActual.get("A1"));

    // check if the housing of a species is correctly returned.
    ArrayList<String> expected = new ArrayList<>(Arrays.asList("CAGE-1", "CAGE-2"));
    assertTrue(jungleFriends.getHousing("drill").containsAll(expected));
    assertTrue(jungleFriends.getHousing("ape").contains("CAGE-4"));

    // check if providing wrong species name throws an exception.
    try {
      assertTrue(jungleFriends.getHousing("drilll").containsAll(expected));
    }
    catch (IllegalArgumentException e) {
      assertEquals("incorrect species given", e.getMessage());
    }

    // check if shopping list is generated correctly.
    TreeMap<String, Integer> shopActual = jungleFriends.getShoppingList();
    assertEquals(500, (int) shopActual.get("LEAVES"));
    assertEquals(250, (int) shopActual.get("RAISINS"));
    assertEquals(1000, (int) shopActual.get("SAP"));

  }

  /**
   * test move primate from isolation to enclosure and check the validity of
   * the sanctuary records.
   */
  @Test
  public void testMoveToEnclosure() {
    // creating sanctuary and adding primates to it.
    createPrimateSanctuary(5, 3, 15);
    jungleFriends.addSpeciesAllowed("ape");
    jungleFriends.addFoodAllowed("raisins");
    provideHomeHelper("M1", 10, 'M', "drill", 20, 40, "leaves");
    provideHomeHelper("A2", 15, 'F', "drill", 10, 50, "leaves");
    provideHomeHelper("C3", 15, 'F', "mangabey", 30, 20, "sap");
    provideHomeHelper("C2", 10, 'M', "ape", 20, 15, "raisins");
    provideHomeHelper("A1", 12, 'F', "tamarin", 30, 20, "sap");
    // check if in correct isolation cages.
    TreeMap<String, String> primateActual = jungleFriends.getPrimateList();
    assertEquals("CAGE-1", primateActual.get("M1"));
    assertEquals("CAGE-2", primateActual.get("A2"));
    assertEquals("CAGE-3", primateActual.get("C3"));
    assertEquals("CAGE-4", primateActual.get("C2"));
    assertEquals("CAGE-5", primateActual.get("A1"));
    // moving animal to enclosure - illegal argument animal not in sanctuary
    try {
      jungleFriends.moveToEnclosure("M3");
      fail("this animal is not in sanctuary");
    }
    catch (IllegalArgumentException e) {
      assertEquals("animal is not in sanctuary", e.getMessage());
    }
    // moving animal to enclosure - not enough area
    try {
      jungleFriends.moveToEnclosure("A1");
      fail("should not add animal");
    }
    catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("no space in enclosure", e.getMessage());
    }
    // moving primate to enclosure
    jungleFriends.moveToEnclosure("M1");
    // checking to see that primate's housing changed.
    primateActual = jungleFriends.getPrimateList();
    assertNotEquals("CAGE-1", primateActual.get("M1"));
    assertEquals("ENCLOSURE-1", primateActual.get("M1"));
    // check if the housing of a species is correctly returned.
    ArrayList<String> expected = new ArrayList<>(Arrays.asList("CAGE-2", "ENCLOSURE-1"));
    assertTrue(jungleFriends.getHousing("drill").containsAll(expected));
    // illegal argument - primate not in isolation.
    try {
      jungleFriends.moveToEnclosure("M1");
      fail("animal should not be in isolation");
    }
    catch (IllegalArgumentException e) {
      assertEquals("primate not in isolation", e.getMessage());
    }
    // check to see if there is an empty isolation cage.
    assertEquals(1, jungleFriends.getFreeCages());
    // adding a new primate to isolation.
    provideHomeHelper("B1", 12, 'F', "saki", 10, 20, "sap");
    assertTrue(jungleFriends.getHousing("saki").contains("CAGE-1"));
    // moving animal A2 to enclosure
    jungleFriends.moveToEnclosure("A2");
    // add a new primate
    provideHomeHelper("B2", 12, 'F', "drill", 10, 20, "sap");
    jungleFriends.moveToEnclosure("B2");
    jungleFriends.moveToEnclosure("B1");
    provideHomeHelper("B3", 12, 'F', "drill", 5, 20, "fruits");
    jungleFriends.moveToEnclosure("B3");
    primateActual = jungleFriends.getPrimateList();
    // check to see if primate is being added to same troop.
    assertEquals("ENCLOSURE-1", primateActual.get("A2"));
    assertEquals("ENCLOSURE-1", primateActual.get("B2"));
    // check if primate with new species is going to new troop
    assertEquals("ENCLOSURE-2", primateActual.get("B1"));
    // check primate is going to new enclosure even if same troop exists (no space in troop)
    assertNotEquals("ENCLOSURE-1", primateActual.get("B3"));
    assertEquals("ENCLOSURE-3", primateActual.get("B3"));
    // get the sign of enclosure.

    ArrayList<ArrayList<String>> actualSign = jungleFriends.getEnclosureSign("ENCLOSURE-3");
    for (ArrayList<String> list : actualSign) {
      assertSame("B3", list.get(0));
    }
    assertEquals(0, jungleFriends.getEnclosureFreeArea("ENCLOSURE-1"));
    // increase enclosure area and validate
    jungleFriends.increaseEnclosureAreaBy("ENCLOSURE-1", 10);
    assertEquals(10, jungleFriends.getEnclosureFreeArea("ENCLOSURE-1"));

  }

  /**
   * tests to move primates from enclosure to isolation and check the validity of the operation.
   */
  @Test
  public void moveToIsolation() {
    // creating sanctuary and adding primates to it.
    createPrimateSanctuary(5, 3, 15);
    jungleFriends.addSpeciesAllowed("ape");
    jungleFriends.addFoodAllowed("raisins");
    provideHomeHelper("M1", 10, 'M', "drill", 20, 40, "leaves");
    provideHomeHelper("A2", 15, 'F', "drill", 10, 50, "leaves");
    provideHomeHelper("C3", 15, 'F', "mangabey", 30, 20, "sap");
    provideHomeHelper("C2", 10, 'M', "ape", 20, 15, "raisins");
    provideHomeHelper("A1", 12, 'F', "tamarin", 30, 20, "sap");
    // moving primates to enclosures.
    jungleFriends.moveToEnclosure("A2"); // moves to Enclosure 1, cage 2 empty
    provideHomeHelper("B2", 12, 'F', "drill", 10, 20, "sap");
    jungleFriends.moveToEnclosure("B2"); // moves to Enclosure 1, cage 2 empty
    provideHomeHelper("B3", 12, 'F', "drill", 5, 20, "fruits");
    jungleFriends.moveToEnclosure("B3"); // moves to Enclosure 1, cage 2 empty
    assertEquals(4, jungleFriends.getEnclosureFreeArea("ENCLOSURE-1"));
    // illegal argument - animal not in sanctuary
    try {
      jungleFriends.moveToIsolation("B1");
      fail("primate not in sanctuary");
    }
    catch (IllegalArgumentException e) {
      assertEquals("animal is not in sanctuary", e.getMessage());
    }
    // illegal argument - animal not in enclosure
    try {
      jungleFriends.moveToIsolation("M1");
      fail("primate not in enclosure");
    }
    catch (IllegalArgumentException e) {
      assertEquals("animal is not in enclosure", e.getMessage());
    }
    TreeMap<String, String> primateActual = jungleFriends.getPrimateList();
    assertEquals("ENCLOSURE-1", primateActual.get("B3"));
    jungleFriends.moveToIsolation("B3");
    primateActual = jungleFriends.getPrimateList();
    assertEquals("CAGE-2", primateActual.get("B3"));
    // illegal argument - no space in enclosure
    try {
      jungleFriends.moveToIsolation("B2");
      fail("no space in isolation, should not move primate");
    }
    catch (ArrayIndexOutOfBoundsException e) {
      assertEquals("isolation cages are full", e.getMessage());
    }
  }

  /**
   * test to move to another sanctuary. check if move to another sanctuary works correctly and
   * validate the move.
   */
  @Test
  public void testMoveToAnotherSanctuary() {
    createPrimateSanctuary(1, 2, 3);
    Sanctuary otherSanctuary = PrimateSanctuary.newPrimateSanctuary(1, 2, 3);
    provideHomeHelper("M1", 10, 'M', "drill", 20, 40, "leaves");
    jungleFriends.moveToAnotherSanctuary("M1", otherSanctuary);
    assertTrue(otherSanctuary.getHousing("drill").contains("CAGE-1"));
    assertEquals(0, jungleFriends.getPrimateCount());
  }
}