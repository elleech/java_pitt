/**
 * Class FileManager
 * @author yec24
 * created: 11/1/2019
 * modified: 11/6/2019
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	
	/**
	 * Method for reading a text file and saving the data to an ArrayList<MenuItem>
	 * @param filename a String of filename
	 * @return an ArrayList of MenuItems
	 */
	public static ArrayList<MenuItem> readItems(String filename) {
		ArrayList<MenuItem> menuItems = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] line = br.readLine().split("@@");
				if (line[1].equalsIgnoreCase("entree")) {
					Entree entreeItem = new Entree(line[0], line[2], Integer.parseInt(line[3]), Double.parseDouble(line[4]));
					menuItems.add(entreeItem);
				}
				if (line[1].equalsIgnoreCase("side")) {
					Side sideItem = new Side(line[0], line[2], Integer.parseInt(line[3]), Double.parseDouble(line[4]));
					menuItems.add(sideItem);
				}
				if (line[1].equalsIgnoreCase("salad")) {
					Salad saladItem = new Salad(line[0], line[2], Integer.parseInt(line[3]), Double.parseDouble(line[4]));
					menuItems.add(saladItem);
				}
				if (line[1].equalsIgnoreCase("dessert")) {
					Dessert dessertItem = new Dessert(line[0], line[2], Integer.parseInt(line[3]), Double.parseDouble(line[4]));
					menuItems.add(dessertItem);
				}
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return menuItems;
	}
	
	/**
	 * Method for writing and saving a text file from an ArrayList<Menu>
	 * @param filename a String of filename
	 * @param menus an ArrayList of Menu
	 */
	public static void writeMenus(String filename, ArrayList<Menu> menus) {
		try {
			FileWriter fw = new FileWriter(filename);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < menus.size(); i++) {
				double totalPrice = menus.get(i).getEntree().getPrice() + menus.get(i).getSide().getPrice() + menus.get(i).getSalad().getPrice() + menus.get(i).getDessert().getPrice();
				bw.write(menus.get(i).getName() + "@@" + menus.get(i).totalCalories() + "@@" + totalPrice + "\n");
				bw.write(menus.get(i).getEntree().getName() + "@@" + menus.get(i).getEntree().getDescription() + "@@" + menus.get(i).getEntree().getCalories() + "@@" + menus.get(i).getEntree().getPrice() + "\n");
				bw.write(menus.get(i).getSide().getName() + "@@" + menus.get(i).getSide().getDescription() + "@@" + menus.get(i).getSide().getCalories() + "@@" + menus.get(i).getSide().getPrice() + "\n");
				bw.write(menus.get(i).getSalad().getName() + "@@" + menus.get(i).getSalad().getDescription() + "@@" + menus.get(i).getSalad().getCalories() + "@@" + menus.get(i).getSalad().getPrice() + "\n");
				bw.write(menus.get(i).getDessert().getName() + "@@" + menus.get(i).getDessert().getDescription() + "@@" + menus.get(i).getDessert().getCalories() + "@@" + menus.get(i).getDessert().getPrice() + "\n\n");
			}
			bw.close();
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	// Assignment 2 below, not applicable to assignment 3 or later
	
	/*
	 * Method for reading entrees.txt file and save data to an ArrayList
	 * @param a String of filename
	 * @return an ArrayList of entrees
	 *
	public static ArrayList<Entree> readEntrees(String filename) {
		ArrayList<Entree> entrees = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] line = br.readLine().split("@@");
				entrees.add(new Entree(line[0], line[1], Integer.parseInt(line[2])));
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return entrees;
	}
	
	/**
	 * Method for reading sides.txt file and save data to an ArrayList
	 * @param a String filename
	 * @return an ArrayList of sides
	 *
	public static ArrayList<Side> readSides(String filename) {
		ArrayList<Side> sides = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] line = br.readLine().split("@@");
				sides.add(new Side(line[0], line[1], Integer.parseInt(line[2])));
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return sides;
	}
	
	/**
	 * Method for reading salads.txt file and save data to an ArrayList
	 * @param a String of filename
	 * @return an ArrayList of salads
	 *
	public static ArrayList<Salad> readSalads(String filename) {
		ArrayList<Salad> salads = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] line = br.readLine().split("@@");
				salads.add(new Salad(line[0], line[1], Integer.parseInt(line[2])));
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return salads;
	}
	
	/**
	 * Method for reading desserts.txt file and save data to an ArrayList
	 * @param a String of filename
	 * @return an ArrayList of desserts
	 *
	public static ArrayList<Dessert> readDesserts(String filename) {
		ArrayList<Dessert> desserts = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			while (br.ready()) {
				String[] line = br.readLine().split("@@");
				desserts.add(new Dessert(line[0], line[1], Integer.parseInt(line[2])));
			}
			br.close();
			fr.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return desserts;
	}
	*/

}
