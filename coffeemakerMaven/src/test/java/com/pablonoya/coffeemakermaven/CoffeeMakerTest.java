package com.pablonoya.coffeemakermaven;

import com.pablonoya.coffeemakermaven.exceptions.InventoryException;
import com.pablonoya.coffeemakermaven.exceptions.RecipeException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoffeeMakerTest {

    /**
     * The object under test.
     */
    private CoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker} object
     * we wish to test.
     *
     * @throws RecipeException if there was an error parsing the ingredient
     * amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new CoffeeMaker();

        //Set up for r1
        recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new Recipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new Recipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new Recipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");
    }

    /**
     * Given a coffee maker with the default inventory When we add inventory
     * with well-formed quantities Then we do not get an exception trying to
     * read the inventory quantities.
     *
     * @throws InventoryException if there was an error parsing the quanity to a
     * positive integer.
     */
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4", "7", "0", "9");
    }

    /**
     * Given a coffee maker with the default inventory When we add inventory
     * with malformed quantities (i.e., a negative quantity and a non-numeric
     * string) Then we get an inventory exception
     *
     * @throws InventoryException if there was an error parsing the quanity to a
     * positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws InventoryException {
        coffeeMaker.addInventory("4", "-1", "asdf", "3");
    }

    /**
     * Given a coffee maker with one valid recipe When we make coffee, selecting
     * the valid recipe and paying more than the coffee costs Then we get the
     * correct change back.
     */
    @Test
    public void testMakeCoffee() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }

    @Test
    public void testRecipeNameUnique() throws RecipeException {
        String name = "Macchiato";
        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();

        r1.setName(name);
        r2.setName(name);

        coffeeMaker.addRecipe(r2);
    }

    @Test
    public void testDeleteRecipe() {
        int index = -1;
        String result = coffeeMaker.deleteRecipe(index);
        assertNull(result);
    }

    @Test
    public void testEditRecipe() {
        int index = -1;
        Recipe r1 = new Recipe();

        String result = coffeeMaker.editRecipe(index, r1);
        assertNull(result);
    }

    @Test
    public void testEditRecipeName() throws RecipeException {
        coffeeMaker.addRecipe(recipe1);

        int index = 0;
        Recipe r1 = new Recipe();
        r1.setAmtCoffee("99");

        String name = recipe1.getName();

        assertEquals(name, coffeeMaker.editRecipe(index, r1));
        assertEquals(99, coffeeMaker.getRecipes()[index].getAmtCoffee());
    }

    @Test
    public void testAddInventory2132() throws InventoryException {
        coffeeMaker.addInventory("2", "1", "3", "2");
        String result = coffeeMaker.checkInventory();
        String expected = "Coffee: 17\n"
                + "Milk: 16\n"
                + "Sugar: 18\n"
                + "Chocolate: 17\n";

        System.out.println(result);
        assertEquals(expected, result);
    }

    @Test
    public void testCheckInventory() {
        String result = coffeeMaker.checkInventory();
        String expected = "Coffee: 15\n"
                + "Milk: 15\n"
                + "Sugar: 15\n"
                + "Chocolate: 15\n";

        System.out.println(result);
        assertEquals(expected, result);
    }

    @Test
    public void testPurchaseBeverageWrongIndices() {
        coffeeMaker.addRecipe(recipe1);
        int amountPaid = 60;

        assertEquals(amountPaid, coffeeMaker.makeCoffee(-1, amountPaid));
        assertEquals(amountPaid, coffeeMaker.makeCoffee(100, amountPaid));
    }

    @Test
    public void testPurchaseBeverageNotEnoughIngredients() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName("Super Cofee");
        recipe.setAmtChocolate("20");
        recipe.setAmtCoffee("20");
        recipe.setAmtMilk("20");
        recipe.setAmtSugar("20");
        recipe.setPrice("60");

        coffeeMaker.addRecipe(recipe);

        int amountPaid = 60;

        assertEquals(amountPaid, coffeeMaker.makeCoffee(0, amountPaid));
    }

    @Test
    public void testPurchaseBeverageNullRecipe() {
        int amountPaid = 100;
        assertEquals(amountPaid, coffeeMaker.makeCoffee(1, amountPaid));
    }

    @Test
    public void testPurchaseBeveragePaidLess() {
        coffeeMaker.addRecipe(recipe1);
        int amountPaid = 40;

        assertEquals(amountPaid, coffeeMaker.makeCoffee(0, amountPaid));
    }

    @Test
    public void testPurchaseBeveragePaidOK() {
        coffeeMaker.addRecipe(recipe1);
        int amountPaid = 60;
        int change = amountPaid - recipe1.getPrice();

        assertEquals(change, coffeeMaker.makeCoffee(0, amountPaid));
    }
}
