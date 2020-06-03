/**
 * Class MenuItem
 * @author yec24
 * created: 11/6/2019
 */

public class MenuItem {
	private String name;
	private String description;
	private int calories;
	private double price;
	
	/**
	 * Constructor
	 * @param name a String of the menu item's name
	 * @param desc a String of the menu item's description
	 * @param cal an integer of the menu item's calories
	 * @param price a double of the menu item's price
	 */
	public MenuItem(String name, String desc, int cal, double price) {
		this.name = name;
		this.description = desc;
		this.calories = cal;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	public int getCalories() {return calories;}
	public void setCalories(int calories) {this.calories = calories;}
	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;}

}
