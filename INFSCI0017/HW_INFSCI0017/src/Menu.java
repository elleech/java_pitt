/**
 * Class Menu
 * @author yec24
 * created: 9/25/2019
 */

public class Menu {
	private String name;
	private Entree entree;
	private Salad salad;
	private Side side;
	private Dessert dessert;
	
	/**
	 * Constructor
	 * @param name a String of the menu's name
	 */
	public Menu(String name) {
		this.name = name;
		this.entree = null;
		this.side = null;
		this.salad = null;
		this.dessert = null;
	}
	
	/**
	 * Overloaded constructor
	 * @param name a String of the menu's name
	 * @param entree an Entree of the entree
	 * @param side a Side of the side
	 */
	public Menu(String name, Entree entree, Side side) {
		this(name);
		this.entree = entree;
		this.side = side;
	}
	
	/**
	 * Overloaded constructor
	 * @param name a String of the menu's name
	 * @param entree an Entree of the entree
	 * @param side a Side of the side
	 * @param salad a Salad of the salad
	 * @param dessert a Dessert of the dessert
	 */
	public Menu(String name, Entree entree, Side side, Salad salad, Dessert dessert) {
		this(name, entree, side);
		this.salad = salad;
		this.dessert = dessert;
	}
	
	/**
	 * Method for calculating total calories of the menu
	 * @return an integer of total calories
	 */
	public int totalCalories() {
		int entreeCal = 0, sideCal = 0, saladCal = 0, dessertCal = 0;
		if (entree != null) {entreeCal = entree.getCalories();}
		if (side != null) {sideCal = side.getCalories();}
		if (salad != null) {saladCal = salad.getCalories();}
		if (dessert != null) {dessertCal = dessert.getCalories();}
		return entreeCal + sideCal + saladCal + dessertCal;
	}
	
	/**
	 * Method for showing all descriptions of the courses
	 * @return a String of description
	 */
	public String description() {
		String des = "";
		
		if (entree == null) {des += "Entree: N/A\n";}
		else if (entree != null) {des += ("Entree: " + entree.getName() + "\n\t" + entree.getDescription() + "\n");}
		
		if (side == null) {des += "Side: N/A\n";}
		else if (side != null) {des += ("Side: " + side.getName() + "\n\t" + side.getDescription() + "\n");}
		
		if (salad == null) {des += "Salad: N/A\n";}
		else if (salad != null) {des += ("Salad: " + salad.getName() + "\n\t" + salad.getDescription() + "\n");}
		
		if (dessert == null) {des += "Dessert: N/A\n";}
		else if (dessert != null) {des += ("Dessert: " + dessert.getName() + "\n\t" + dessert.getDescription() + "\n");}
		
		return des;
	}
	
	@Override
	public String toString() {
		return name;
	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public Entree getEntree() {return entree;}
	public void setEntree(Entree entree) {this.entree = entree;}
	public Salad getSalad() {return salad;}
	public void setSalad(Salad salad) {this.salad = salad;}
	public Side getSide() {return side;}
	public void setSide(Side side) {this.side = side;}
	public Dessert getDessert() {return dessert;}
	public void setDessert(Dessert dessert) {this.dessert = dessert;}
}
