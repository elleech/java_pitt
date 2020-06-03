/**
 * Class MenuTest
 * @author yec24
 * created: 9/25/2019
 */

import java.util.ArrayList;

public class Tester {

	public static void main(String[] args) {
		// HW3 tester starts
		ArrayList<MenuItem> menuItems = FileManager.readItems("data/dishes.txt");
		for (MenuItem dish : menuItems) {
			System.out.println(dish.getName() + " " + dish.getCalories());
		}
		System.out.println("=====================================");
		MenuManager menu = new MenuManager("data/dishes.txt");
		System.out.println(menu.maxCaloriesMenu("MAX").getEntree().getName());
		
		/* HW2 below
		MenuRandomize randomize = new MenuRandomize("data/entrees.txt", 
													"data/sides.txt", 
													"data/salads.txt", 
													"data/desserts.txt");
		Menu myMenu = randomize.randomMenu();
		System.out.println(myMenu.description() + "\nTotal calories: " + myMenu.totalCalories());
		*/
		
		/* HW1 below
		// 1st menu: 1 entree, 1 side
		Entree entree1 = new Entree("Fried Calamari", 
									"Fried calamari, garlic dip, and cocktail sauce", 
									1520);
		Side side1 = new Side("Chinese Chicken Salad", 
							  "Chicken breast, rice noodles, lettuce, green onions, almonds, crisp wontons, bean sprouts, orange, sesame seeds, and chinese plum dressing", 
							  1050);
		Menu menu1 = new Menu("Light Satiscation @TheCheesecakeFactory", entree1, side1);
		System.out.println(menu1.getName() + " | Total Calories: " + menu1.totalCalories());
		System.out.println(menu1.description());
		
		// 2nd menu: 1 entree, 1 side, 1 salad, 1 dessert
		Entree entree2 = new Entree("Double Quarter Pounder with Cheese", 
									"Beef patties, salt, pepper, onions, pickles, cheese, and sesame seed bun", 
									780);
		Side side2 = new Side("Buttermilk Crispy Tenders", 
							  "Breaded white-meat chicken", 
							  480);
		Salad salad2 = new Salad("Southwest Grilled Chicken Salad", 
								 "Grilled white-meat chicken, salt, garlic powder, parsley, black beans, roasted corn, tomatoes, poblano peppers, cheddar, jack cheeses, chili-lime tortilla strips, cilantro, romaine, baby spinach, baby kale, red leaf lettuce, ribbon cut carrots, lime wedge, and Newman's Own Southwest dressing",  
								 350);
		Dessert dessert2 = new Dessert("Fruit 'N Yogurt Parfait", 
									   "Low-fat vanilla yogurt, blueberries, strawberries, and granola topping", 
									   210);
		Menu menu2 = new Menu("Cheap Junks @McDonald's", entree2, side2, salad2, dessert2);
		System.out.println(menu2.getName() + " | Total Calories: " + menu2.totalCalories());
		System.out.println(menu2.description());
		*/
	}

}
