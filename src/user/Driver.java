package user;

import sanctuary.PrimateSanctuary;
import sanctuary.Sanctuary;

import java.util.NoSuchElementException;

/**
 * driver class for sanctuary.
 */
public class Driver {
  /**
   * main method in sanctuary driver class.
   * @param args - command line arguments
   */
  public static void main(String[] args) {
    Sanctuary jungleFriends;

    jungleFriends = PrimateSanctuary.newPrimateSanctuary(5, 3, 20);
    System.out.println("Welcome to the Jungle Friends Primate Sanctuary, "
            + "We provide a home for Primates");
    System.out.println("\nThe Sanctuary currently accepts the following New World Primates");
    System.out.println(jungleFriends.getSpeciesAllowed());
    System.out.println("But, you can add more species of primates later if you wish!");
    System.out.println("\nAnd these are the food items that we currently provide to our Primates");
    System.out.println(jungleFriends.getFoodAllowed());
    System.out.println("But, more items can be added in the future...");
    System.out.println("\nWe currently have:");
    System.out.println(jungleFriends.getNumOfCages() + " Isolation Cages and");
    System.out.println(jungleFriends.getNumOfTroops() + " Troops");
    System.out.println("Each troop has an area of 20 Sq-m");
    System.out.println("------------------------------------------------------------------------");

    System.out.println("\n-----------------------RUN - 1-----------------------------\n");
    System.out.println("Adding a Primate to the Sanctuary, The details of the Primate are:");
    System.out.println("Name: M1, Age: 10, Sex: M, "
            + "Species: drill, Size: 20, Weight: 40, Fav Food: Sap");
    // adds a primate to one of the empty isolation cages
    jungleFriends.provideHome("M1", 10, 'M', "drill", 20, 40, "sap");
    System.out.println("\nAdding another Primate to the Sanctuary, "
            + "The details of the Primate are:");
    System.out.println("Name: A2, Age: 15, Sex: F, "
            + "Species: drill, Size: 10, Weight: 50, Fav Food: leaves");
    jungleFriends.provideHome("A2", 15, 'F', "drill", 10, 50, "leaves");
    System.out.println("\nAdding 3 more Primates to the Sanctuary, "
            + "The details of the Primates are:");
    System.out.println("Name: C3, Age: 15, Sex: F, "
            + "Species: mangabey, Size: 30, Weight: 20, Fav Food: sap");
    System.out.println("Name: C2, Age: 10, Sex: M, "
            + "Species: saki, Size: 20, Weight: 15, Fav Food: insects");
    System.out.println("Name: A1, Age: 12, Sex: F, "
            + "Species: tamarin, Size: 30, Weight: 20, Fav Food: sap");
    jungleFriends.provideHome("C3", 15, 'F', "mangabey", 30, 20, "sap");
    jungleFriends.provideHome("C2", 10, 'M', "saki", 20, 15, "insects");
    jungleFriends.provideHome("A1", 12, 'F', "tamarin", 30, 20, "sap");

    System.out.println("\nThat's it, the sanctuary does not have any free cages!");
    System.out.println("The free cages in Sanctuary are: " + jungleFriends.getFreeCages());
    System.out.println("\nWhat happens, when we add more Primates?");
    try {
      System.out.println("\nTrying to add another Primate...");
      System.out.println("Name: A3, Age: 12, Sex: F, "
              + "Species: howler, Size: 30, Weight: 20, Fav Food: fruits");
      jungleFriends.provideHome("A3", 12, 'F', "howler", 30, 20, "fruits");
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("\nError: " + e.getMessage());
    }
    System.out.println("\nBut we can add more cages, Let's try that");
    jungleFriends.increaseCagesBy(3);
    System.out.println("\nTotal number of cages "
            + "in Sanctuary are: " + jungleFriends.getNumOfCages());
    System.out.println("\nHow many free cages does the sanctuary have?");
    System.out.println("The free cages in Sanctuary are: " + jungleFriends.getFreeCages());

    try {
      System.out.println("\nTrying to add the Primate once again...");
      System.out.println("Name: A3, Age: 12, Sex: F, "
              + "Species: howler, Size: 30, Weight: 20, Fav Food: fruits");
      jungleFriends.provideHome("A3", 12, 'F', "howler", 30, 20, "fruits");
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("\nError: " + e.getMessage());
    }
    System.out.println("Primate added successfully");

    System.out.println("\nNow lets search where a particular species is in the Sanctuary");
    System.out.println("\nWhere are the Drills?");
    System.out.println(jungleFriends.getHousing("drill"));
    System.out.println("\nWhere are the Mangabeys?");
    System.out.println(jungleFriends.getHousing("mangabey"));
    System.out.println("\nWhere are the Sakis?");
    System.out.println(jungleFriends.getHousing("saki"));
    System.out.println("\nWhere are the Tamarins?");
    System.out.println(jungleFriends.getHousing("tamarin"));
    System.out.println("\nWhere are the Howlers?");
    System.out.println(jungleFriends.getHousing("howler"));

    System.out.println("\nWhat will happen if we try to search for a primate that is not in "
            + "sanctuary");
    try {
      System.out.println("Trying to find if and where a Guereza is...");
      System.out.println(jungleFriends.getHousing("guereza"));
    }
    catch (NoSuchElementException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nSearching for individual details is every tiring, "
            + "Let's look at the sanctuaries current state:");
    System.out.println("\nThe Primates (Alphabetically) and their housing in the sanctuary are:");
    System.out.println(jungleFriends.getPrimateList());
    System.out.println("\nThe Species (Alphabetically) and their housing in the sanctuary are:");
    System.out.println(jungleFriends.getSpeciesList());
    System.out.println("\nThe Shopping List of Sanctuary is:");
    System.out.println(jungleFriends.getShoppingList());

    System.out.println("\n-----------------------RUN - 2-----------------------------\n");

    System.out.println("Let's try to create a sanctuary with -ve number of cages/ troops /area");
    try {
      System.out.println("\nSanctuary with -ve cages");
      PrimateSanctuary.newPrimateSanctuary(-1, 4, 5);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    try {
      System.out.println("\nSanctuary with -ve troops");
      PrimateSanctuary.newPrimateSanctuary(1, -4, 5);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    try {
      System.out.println("\nSanctuary with -ve area");
      PrimateSanctuary.newPrimateSanctuary(1, 4, -5);
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }


    System.out.println("\nNow let's create a sanctuary with the following intial configuration");
    System.out.println("5, 0, 20 - cages, enclosures and enclosure area");
    jungleFriends = PrimateSanctuary.newPrimateSanctuary(5, 0, 20);

    System.out.println("\nLet's try to add primates with Illegal arguments");

    try {
      System.out.println("\nnegative age: -10");
      jungleFriends.provideHome("M1", -10, 'M', "chimpanzee", 20, 40, "trees");
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    try {
      System.out.println("\nnegative size: -20");
      jungleFriends.provideHome("M1", 10, 'M', "chimpanzee", -20, 40, "trees");
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    try {
      System.out.println("\nnegative weight: -40");
      jungleFriends.provideHome("M1", 10, 'M', "chimpanzee", 20, -40, "trees");
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nWe cannot add animals with illegal arguments the "
            + "arguments should be valid");

    System.out.println("\nAdding 4 Primates to the Sanctuary, The details of the Primates are:");
    System.out.println("Name: M1, Age: 10, Sex: M, "
            + "Species: drill, Size: 20, Weight: 40, Fav Food: Sap");
    System.out.println("Name: A2, Age: 15, Sex: F, "
            + "Species: drill, Size: 10, Weight: 50, Fav Food: leaves");
    System.out.println("Name: C3, Age: 15, Sex: F, "
            + "Species: mangabey, Size: 30, Weight: 20, Fav Food: sap");
    System.out.println("Name: C2, Age: 10, Sex: M, "
            + "Species: saki, Size: 20, Weight: 15, Fav Food: insects");
    jungleFriends.provideHome("M1", 10, 'M', "drill", 20, 40, "sap");
    jungleFriends.provideHome("A2", 15, 'F', "drill", 10, 50, "leaves");
    jungleFriends.provideHome("C3", 15, 'F', "mangabey", 30, 20, "sap");
    jungleFriends.provideHome("C2", 10, 'M', "saki", 20, 15, "insects");


    System.out.println("\nLet's see what happens when we want add a new species - ape");
    try {
      jungleFriends.provideHome("A1", 12, 'F', "ape", 30, 20, "sap");
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("What are the species allowed in our sanctuary?");
    System.out.println(jungleFriends.getSpeciesAllowed());
    System.out.println("\nWe'll add a new species - ape, to our allowed species");
    jungleFriends.addSpeciesAllowed("ape");
    System.out.println("The species allowed are:");
    System.out.println(jungleFriends.getSpeciesAllowed());

    System.out.println("\nNow we can add a new species to our sanctuary");
    jungleFriends.provideHome("A1", 12, 'F', "ape", 30, 20, "sap");

    System.out.println("\nHere is a list of all primates in our sanctuary");
    System.out.println(jungleFriends.getSpeciesList());

    System.out.println("\nHow many animals are in our sanctuary?");
    System.out.println("Total animals in Sacntuary:" + jungleFriends.getPrimateCount());

    System.out.println("\nCan we move a primate to enclosure?");
    System.out.println("let's move primate A1 to enclosure and see what happens");

    try {
      jungleFriends.moveToEnclosure("A1");
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nWe can increase the number of troops");
    System.out.println("current enclosure space: " + jungleFriends.getNumOfTroops());
    jungleFriends.increaseTroopsBy(3, 15);
    System.out.println("after updating the enclosure space is: " + jungleFriends.getNumOfTroops());

    System.out.println("\n-----------------------RUN - 3-----------------------------\n");

    System.out.println("Creating a primate sanctuary with 5 cages, 3 enclosures and 15 sq-m area");
    jungleFriends = PrimateSanctuary.newPrimateSanctuary(5, 3, 15);
    System.out.println("\nAdd a new species ape and new food item raisins to allowed list");
    jungleFriends.addSpeciesAllowed("ape");
    jungleFriends.addFoodAllowed("raisins");

    System.out.println("\nAdding 5 Primates to the Sanctuary, The details of the Primates are:");
    System.out.println("Name: M1, Age: 10, Sex: M, "
            + "Species: drill, Size: 20, Weight: 40, Fav Food: leaves");
    System.out.println("Name: A2, Age: 15, Sex: F, "
            + "Species: drill, Size: 10, Weight: 50, Fav Food: leaves");
    System.out.println("Name: C3, Age: 15, Sex: F, "
            + "Species: mangabey, Size: 30, Weight: 20, Fav Food: sap");
    System.out.println("Name: C2, Age: 10, Sex: M, "
            + "Species: saki, Size: 20, Weight: 15, Fav Food: raisins");
    System.out.println("Name: A1, Age: 12, Sex: F, "
            + "Species: tamarin, Size: 30, Weight: 20, Fav Food: sap");
    jungleFriends.provideHome("M1", 10, 'M', "drill", 20, 40, "leaves");
    jungleFriends.provideHome("A2", 15, 'F', "drill", 10, 50, "leaves");
    jungleFriends.provideHome("C3", 15, 'F', "mangabey", 30, 20, "sap");
    jungleFriends.provideHome("C2", 10, 'M', "ape", 20, 15, "raisins");
    jungleFriends.provideHome("A1", 12, 'F', "tamarin", 30, 20, "sap");

    try {
      System.out.println("\nTry to move an animal not in sanctuary to enclosure");
      jungleFriends.moveToEnclosure("M3");
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    try {
      System.out.println("\nMoving primate A1 to enclosure - not enough area for this primate");
      jungleFriends.moveToEnclosure("A1");
    }
    catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nMoving primate M1 to enclosure");
    jungleFriends.moveToEnclosure("M1");
    System.out.println("\nCheck to see if housing changed");
    System.out.println("\nHousing of M1 is: " + jungleFriends.getHousing("drill"));

    System.out.println("\nThe primates in sanctuary are: ");
    System.out.println(jungleFriends.getPrimateList());

    System.out.println("\nTo move M1 to isolation again");
    try {
      jungleFriends.moveToEnclosure("M1");
    }
    catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    System.out.println("\nDid the Isolation cage free up?");
    System.out.println(jungleFriends.getFreeCages());

    System.out.println("\nAdding a new primate to isolation");
    System.out.println("Details are: B1, 12, F, saki, 10, 20, sap");
    jungleFriends.provideHome("B1", 12, 'F', "saki", 10, 20, "sap");

    System.out.println("\nWhat is the housing of saki species?");
    System.out.println(jungleFriends.getHousing("saki"));
    System.out.println("\nLet's add Primate A2 to enclosure");
    jungleFriends.moveToEnclosure("A2");

    System.out.println("\nAdding new Primate B2 to sanctuary with the following details");
    System.out.println("B2, 12, F, drill, 10, 20, sap");
    jungleFriends.provideHome("B2", 12, 'F', "drill", 10, 20, "sap");

    System.out.println("\nMoving B2 and B1 to enclosure");
    jungleFriends.moveToEnclosure("B2");
    jungleFriends.moveToEnclosure("B1");

    System.out.println("Adding new Primate B3 "
            + "with details: B3, 12, F, drill, 5, 20, fruits");
    jungleFriends.provideHome("B3", 12, 'F', "drill", 5, 20, "fruits");
    jungleFriends.moveToEnclosure("B3");

    System.out.println("\nCurrent Primate List:");
    System.out.println(jungleFriends.getPrimateList());

    System.out.println("\nENCLOSURE-3's Enclosure sign");
    System.out.println(jungleFriends.getEnclosureSign("ENCLOSURE-3"));

    System.out.println("\nENCLOSURE-1 should not have any free space, let's see...");
    System.out.println("Free space in ENCLOSURE-1 is: "
            + jungleFriends.getEnclosureFreeArea("ENCLOSURE-1"));

    System.out.println("\nIncrease the area of ENCLOSURE-1 by 10");
    jungleFriends.increaseEnclosureAreaBy("ENCLOSURE-1", 10);
    System.out.println("\nThe current free area of ENCLOSURE-1 is: "
            + jungleFriends.getEnclosureFreeArea("ENCLOSURE-1"));

    System.out.println("\nPrimate List: " + jungleFriends.getPrimateList());
    System.out.println("Species List: " + jungleFriends.getSpeciesList());
    System.out.println("Shopping List: " + jungleFriends.getShoppingList());

    System.out.println("\nLet's try to move a mangabey to Enclosure");
    System.out.println("This cannot happen"
            + " because all the enclosures are filled with other species");
    try {
      jungleFriends.moveToEnclosure("C3");
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println("\nWe'll create a new sanctuary and move the primate here");
    Sanctuary otherSanctuary = PrimateSanctuary.newPrimateSanctuary(1, 2, 3);
    jungleFriends.moveToAnotherSanctuary("C3", otherSanctuary);
    System.out.println("\nLet's see if the primate moved...");
    System.out.println("Primate List jungleFriends:" + jungleFriends.getPrimateList());
    System.out.println("primate list otherSanctuary:" + otherSanctuary.getPrimateList());
  }
}
