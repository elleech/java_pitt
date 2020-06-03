/**
 * Class MenuManager
 * @author yec24
 * created: 11/6/2019
 */

import java.util.ArrayList;
import java.util.Random;

public class MenuManager {
	private ArrayList<Entree> entrees = new ArrayList<Entree>();
	private ArrayList<Side> sides = new ArrayList<Side>();
	private ArrayList<Salad> salads = new ArrayList<Salad>();
	private ArrayList<Dessert> desserts = new ArrayList<Dessert>();
	
	/**
	 * Constructor
	 * @param dishfile a String of filename
	 */
	public MenuManager(String dishfile) {
		ArrayList<MenuItem> menuItems = FileManager.readItems(dishfile);
		for (MenuItem dish : menuItems) {
			if(dish != null) {
				if (dish instanceof Entree) {this.entrees.add((Entree) dish);}
				if (dish instanceof Side) {this.sides.add((Side) dish);}
				if (dish instanceof Salad) {this.salads.add((Salad) dish);}
				if (dish instanceof Dessert) {this.desserts.add((Dessert) dish);}
			}
		}
	}
	
	/**
	 * Method for creating a random menu
	 * @param name a String of menu name
	 * @return a Menu of random pick dishes
	 */
	public Menu randomMenu(String name) {
		Random rand = new Random();
		Entree randEntree = this.entrees.get(rand.nextInt(this.entrees.size()));
		Side randSide = this.sides.get(rand.nextInt(this.sides.size()));
		Salad randSalad = this.salads.get(rand.nextInt(this.salads.size()));
		Dessert randDessert = this.desserts.get(rand.nextInt(this.desserts.size()));
		return new Menu(name, randEntree, randSide, randSalad, randDessert);
	}
	
	/**
	 * Method for creating a menu that contains minimum calories
	 * @param name a String of menu name
	 * @return a Menu of minimum calories dishes
	 */
	public Menu minCaloriesMenu(String name) {
		Entree minCalEntree = this.entrees.get(0);
		Side minCalSide = this.sides.get(0);
		Salad minCalSalad = this.salads.get(0);
		Dessert minCalDessert = this.desserts.get(0);
		
		int calEntree = this.entrees.get(0).getCalories();
		int calSide = this.sides.get(0).getCalories();
		int calSalad = this.salads.get(0).getCalories();
		int calDessert = this.desserts.get(0).getCalories();
		
		for (Entree entree: this.entrees) {
			if (entree.getCalories() < calEntree) {
				calEntree = entree.getCalories();
				minCalEntree = entree;
			}
		}
		for (Side side: this.sides) {
			if (side.getCalories() < calSide) {
				calSide = side.getCalories();
				minCalSide = side;
			}
		}
		for (Salad salad: this.salads) {
			if (salad.getCalories() < calSalad) {
				calSalad = salad.getCalories();
				minCalSalad = salad;
			}
		}
		for (Dessert dessert: this.desserts) {
			if (dessert.getCalories() < calDessert) {
				calDessert = dessert.getCalories();
				minCalDessert = dessert;
			}
		}
		
		return new Menu(name, minCalEntree, minCalSide, minCalSalad, minCalDessert);
	}
	
	/**
	 * Method for creating a menu that contains maximum calories
	 * @param name a String of menu name
	 * @return a Menu of maximum calories dishes
	 */
	public Menu maxCaloriesMenu(String name) {
		Entree maxCalEntree = this.entrees.get(0);
		Side maxCalSide = this.sides.get(0);
		Salad maxCalSalad = this.salads.get(0);
		Dessert maxCalDessert = this.desserts.get(0);
		
		int calEntree = this.entrees.get(0).getCalories();
		int calSide = this.sides.get(0).getCalories();
		int calSalad = this.salads.get(0).getCalories();
		int calDessert = this.desserts.get(0).getCalories();
		
		for (Entree entree: this.entrees) {
			if (entree.getCalories() > calEntree) {
				calEntree = entree.getCalories();
				maxCalEntree = entree;
			}
		}
		for (Side side: this.sides) {
			if (side.getCalories() > calSide) {
				calSide = side.getCalories();
				maxCalSide = side;
			}
		}
		for (Salad salad: this.salads) {
			if (salad.getCalories() > calSalad) {
				calSalad = salad.getCalories();
				maxCalSalad = salad;
			}
		}
		for (Dessert dessert: this.desserts) {
			if (dessert.getCalories() > calDessert) {
				calDessert = dessert.getCalories();
				maxCalDessert = dessert;
			}
		}
		
		return new Menu(name, maxCalEntree, maxCalSide, maxCalSalad, maxCalDessert);
	}

	public ArrayList<Entree> getEntrees() {return entrees;}
	public void setEntrees(ArrayList<Entree> entrees) {this.entrees = entrees;}
	public ArrayList<Side> getSides() {return sides;}
	public void setSides(ArrayList<Side> sides) {this.sides = sides;}
	public ArrayList<Salad> getSalads() {return salads;}
	public void setSalads(ArrayList<Salad> salads) {this.salads = salads;}
	public ArrayList<Dessert> getDesserts() {return desserts;}
	public void setDesserts(ArrayList<Dessert> desserts) {this.desserts = desserts;}

}
