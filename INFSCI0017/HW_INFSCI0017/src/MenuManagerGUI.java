/**
 * Class MenuManagerGUI
 * @author yec24
 * created: 11/24/2019
 */

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.text.Utilities;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuManagerGUI {

	private JFrame frame;
	private JTextArea textArea;
	private JComboBox comboBoxEntree;
	private JComboBox comboBoxSide;
	private JComboBox comboBoxSalad;
	private JComboBox comboBoxDessert;
	
	private MenuManager mm;
	private ArrayList<Entree> entreeItems = new ArrayList<>();
	private ArrayList<Side> sideItems = new ArrayList<>();
	private ArrayList<Salad> saladItems = new ArrayList<>();
	private ArrayList<Dessert> dessertItems = new ArrayList<>();
	
	private int i = 0;
	private String select = "";
	private ArrayList<String> menuNames = new ArrayList<>();
	private ArrayList<Menu> menus = new ArrayList<>();
	private ArrayList<Entree> entrees = new ArrayList<>();
	private ArrayList<Side> sides = new ArrayList<>();
	private ArrayList<Salad> salads = new ArrayList<>();
	private ArrayList<Dessert> desserts = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuManagerGUI window = new MenuManagerGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuManagerGUI() {
		mm = new MenuManager("data/dishes.txt");
		for (Entree en : mm.getEntrees()) {entreeItems.add(en);}
		for (Side si : mm.getSides()) {sideItems.add(si);}
		for (Salad sa : mm.getSalads()) {saladItems.add(sa);}
		for (Dessert de : mm.getDesserts()) {dessertItems.add(de);}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Menu Manager");
		frame.setBounds(50, 50, 590, 445);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblBuildMenu = new JLabel("Build your own Menu");
		lblBuildMenu.setBounds(20, 15, 160, 20);
		frame.getContentPane().add(lblBuildMenu);
		
		JPanel panelBuildMenu = new JPanel();
		panelBuildMenu.setBounds(15, 30, 220, 180);
		frame.getContentPane().add(panelBuildMenu);
		panelBuildMenu.setLayout(null);
		
		JLabel lblEntree = new JLabel("Entree");
		lblEntree.setBounds(15, 15, 60, 20);
		panelBuildMenu.add(lblEntree);
		
		comboBoxEntree = new JComboBox(entreeItems.toArray());
		comboBoxEntree.setBounds(75, 15, 130, 20);
		panelBuildMenu.add(comboBoxEntree);
		
		JLabel lblSide = new JLabel("Side");
		lblSide.setBounds(15, 45, 60, 20);
		panelBuildMenu.add(lblSide);
		
		comboBoxSide = new JComboBox(sideItems.toArray());
		comboBoxSide.setBounds(75, 45, 130, 20);
		panelBuildMenu.add(comboBoxSide);
		
		JLabel lblSalad = new JLabel("Salad");
		lblSalad.setBounds(15, 75, 60, 20);
		panelBuildMenu.add(lblSalad);
		
		comboBoxSalad = new JComboBox(saladItems.toArray());
		comboBoxSalad.setBounds(75, 75, 130, 20);
		panelBuildMenu.add(comboBoxSalad);
		
		JLabel lblDessert = new JLabel("Dessert");
		lblDessert.setBounds(15, 105, 60, 20);
		panelBuildMenu.add(lblDessert);
		
		comboBoxDessert = new JComboBox(dessertItems.toArray());
		comboBoxDessert.setBounds(75, 105, 130, 20);
		panelBuildMenu.add(comboBoxDessert);
		
		JButton btnCreateMenu = new JButton("Create Menu");
		btnCreateMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMenu(i, "create");
				i++;
			}
		});
		btnCreateMenu.setBounds(15, 140, 190, 25);
		panelBuildMenu.add(btnCreateMenu);
		
		JLabel lblGenerateMenu = new JLabel("Or generate a Menu");
		lblGenerateMenu.setBounds(20, 230, 150, 20);
		frame.getContentPane().add(lblGenerateMenu);
		
		JPanel panelGenerateMenu = new JPanel();
		panelGenerateMenu.setBounds(15, 245, 220, 125);
		frame.getContentPane().add(panelGenerateMenu);
		panelGenerateMenu.setLayout(null);
		
		JButton btnRandomMenu = new JButton("Random Menu");
		btnRandomMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createMenu(i, "random");
				i++;
			}
		});
		btnRandomMenu.setBounds(15, 15, 190, 25);
		panelGenerateMenu.add(btnRandomMenu);
		
		JButton btnMinCaloriesMenu = new JButton("Min Calories Menu");
		btnMinCaloriesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMenu(i, "minCal");
				i++;
			}
		});
		btnMinCaloriesMenu.setBounds(15, 50, 190, 25);
		panelGenerateMenu.add(btnMinCaloriesMenu);
		
		JButton btnMaxCaloriesMenu = new JButton("Max Calories Menu");
		btnMaxCaloriesMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createMenu(i, "maxCal");
				i++;
			}
		});
		btnMaxCaloriesMenu.setBounds(15, 85, 190, 25);
		panelGenerateMenu.add(btnMaxCaloriesMenu);
		
		JLabel lblCreatedMenu = new JLabel("Created Menu");
		lblCreatedMenu.setBounds(250, 15, 100, 20);
		frame.getContentPane().add(lblCreatedMenu);
		
		JButton btnDetails = new JButton("Details");
		btnDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Menu m : menus) {
					if (m.getName().equals(select)) {
						displayDetail(m);

					}
				}
			}
		});
		btnDetails.setBounds(250, 345, 80, 25);
		frame.getContentPane().add(btnDetails);
		
		JButton btnDeleteAll = new JButton("Delete All");
		btnDeleteAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuNames.clear();
				entrees.clear();
				sides.clear();
				salads.clear();
				desserts.clear();
				select = "";
				textArea.setText(null);
			}
		});
		btnDeleteAll.setBounds(335, 345, 100, 25);
		frame.getContentPane().add(btnDeleteAll);
		
		JButton btnSaveToFile = new JButton("Save to File");
		btnSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileManager.writeMenus("data/menus.txt", menus);
			}
		});
		btnSaveToFile.setBounds(440, 345, 115, 25);
		frame.getContentPane().add(btnSaveToFile);
		
		textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				int position = textArea.getCaretPosition();
				try {
					int start = Utilities.getRowStart(textArea, position);
					int end = Utilities.getRowEnd(textArea, position);
					textArea.setSelectionStart(start);
					textArea.setSelectionEnd(end);
					textArea.setSelectionColor(Color.orange);
					select = textArea.getSelectedText();
				} catch (Exception e) {
				}
			}
		});
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textArea.setBounds(250, 35, 305, 305);
		frame.getContentPane().add(textArea);
		textArea.setColumns(10);
	}
	
	/**
	 * Store data into ArrayLists when pressing buttons.
	 * @param index the integer of the position in ArrayList
	 * @param tag the String tag for different button
	 */
	private void createMenu(int index, String tag) {
		String menuName = "";
		do {
			menuName = JOptionPane.showInputDialog("Please enter the menu name: ");
		} while (menuName.equals(null) || menuName.equals(""));
		
		menuNames.add(index, menuName);
		textArea.append(menuNames.get(index) + "\n");
		
		switch (tag) {
			case "create":
				entrees.add(index, (Entree) comboBoxEntree.getSelectedItem());
				sides.add(index, (Side) comboBoxSide.getSelectedItem());
				salads.add(index, (Salad) comboBoxSalad.getSelectedItem());
				desserts.add(index, (Dessert) comboBoxDessert.getSelectedItem());
				menus.add(index, new Menu(menuNames.get(index), entrees.get(index), sides.get(index), salads.get(index), desserts.get(index)));
				break;
			case "random":
				entrees.add(index, mm.randomMenu(menuName).getEntree());
				sides.add(index, mm.randomMenu(menuName).getSide());
				salads.add(index, mm.randomMenu(menuName).getSalad());
				desserts.add(index, mm.randomMenu(menuName).getDessert());
				menus.add(index, new Menu(menuNames.get(index), entrees.get(index), sides.get(index), salads.get(index), desserts.get(index)));
				break;
			case "minCal":
				entrees.add(index, mm.minCaloriesMenu(menuName).getEntree());
				sides.add(index, mm.minCaloriesMenu(menuName).getSide());
				salads.add(index, mm.minCaloriesMenu(menuName).getSalad());
				desserts.add(index, mm.minCaloriesMenu(menuName).getDessert());
				menus.add(index, new Menu(menuNames.get(index), entrees.get(index), sides.get(index), salads.get(index), desserts.get(index)));
				break;
			case "maxCal":
				entrees.add(index, mm.maxCaloriesMenu(menuName).getEntree());
				sides.add(index, mm.maxCaloriesMenu(menuName).getSide());
				salads.add(index, mm.maxCaloriesMenu(menuName).getSalad());
				desserts.add(index, mm.maxCaloriesMenu(menuName).getDessert());
				menus.add(index, new Menu(menuNames.get(index), entrees.get(index), sides.get(index), salads.get(index), desserts.get(index)));
				break;
		}
	}
	
	/**
	 * Display another window for Menu details.
	 * @param input a Menu 
	 */
	private void displayDetail(Menu input) {
		Entree inputEntree = menus.get(menus.indexOf(input)).getEntree();
		Side inputSide = menus.get(menus.indexOf(input)).getSide();
		Salad inputSalad = menus.get(menus.indexOf(input)).getSalad();
		Dessert inputDessert = menus.get(menus.indexOf(input)).getDessert();
		
		JFrame frameDisplayDetail = new JFrame("Menu Details: " + input.getName());
		frameDisplayDetail.setBounds(200, 200, 500, 435);
		frameDisplayDetail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameDisplayDetail.getContentPane().setLayout(null);
		
		JLabel lblEntree = new JLabel("Entree");
		lblEntree.setBounds(20, 15, 60, 20);
		frameDisplayDetail.getContentPane().add(lblEntree);
		
		JTextArea textAreaEntree = new JTextArea();
		textAreaEntree.setText(inputEntree.getName() + "\n" + inputEntree.getDescription() + ". Calories: " + inputEntree.getCalories() + ". Price: $ " + inputEntree.getPrice());
		textAreaEntree.setWrapStyleWord(true);
		textAreaEntree.setLineWrap(true);
		textAreaEntree.setEditable(false);
		textAreaEntree.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaEntree.setBounds(80, 17, 380, 65);
		frameDisplayDetail.getContentPane().add(textAreaEntree);
		textAreaEntree.setColumns(10);
		
		JLabel lblSide = new JLabel("Side");
		lblSide.setBounds(20, 90, 60, 20);
		frameDisplayDetail.getContentPane().add(lblSide);
		
		JTextArea textAreaSide = new JTextArea();
		textAreaSide.setText(inputSide.getName() + "\n" + inputSide.getDescription() + ". Calories: " + inputSide.getCalories() + ". Price: $ " + inputSide.getPrice());
		textAreaSide.setWrapStyleWord(true);
		textAreaSide.setLineWrap(true);
		textAreaSide.setEditable(false);
		textAreaSide.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaSide.setBounds(80, 92, 380, 65);
		frameDisplayDetail.getContentPane().add(textAreaSide);
		textAreaSide.setColumns(10);
		
		JLabel lblSalad = new JLabel("Salad");
		lblSalad.setBounds(20, 165, 60, 20);
		frameDisplayDetail.getContentPane().add(lblSalad);
		
		JTextArea textAreaSalad = new JTextArea();
		textAreaSalad.setText(inputSalad.getName() + "\n" + inputSalad.getDescription() + ". Calories: " + inputSalad.getCalories() + ". Price: $ " + inputSalad.getPrice());
		textAreaSalad.setWrapStyleWord(true);
		textAreaSalad.setLineWrap(true);
		textAreaSalad.setEditable(false);
		textAreaSalad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaSalad.setBounds(80, 167, 380, 65);
		frameDisplayDetail.getContentPane().add(textAreaSalad);
		textAreaSalad.setColumns(10);
		
		JLabel lblDessert = new JLabel("Dessert");
		lblDessert.setBounds(20, 240, 60, 20);
		frameDisplayDetail.getContentPane().add(lblDessert);
		
		JTextArea textAreaDessert = new JTextArea();
		textAreaDessert.setText(inputDessert.getName() + "\n" + inputDessert.getDescription() + ". Calories: " + inputDessert.getCalories() + ". Price: $ " + inputDessert.getPrice());
		textAreaDessert.setWrapStyleWord(true);
		textAreaDessert.setLineWrap(true);
		textAreaDessert.setEditable(false);
		textAreaDessert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaDessert.setBounds(80, 242, 380, 65);
		frameDisplayDetail.getContentPane().add(textAreaDessert);
		textAreaDessert.setColumns(10);
		
		JLabel lblTotalCal = new JLabel("Total Calories:");
		lblTotalCal.setBounds(20, 325, 90, 20);
		frameDisplayDetail.getContentPane().add(lblTotalCal);
		
		JTextArea textAreaTotalCal = new JTextArea();
		textAreaTotalCal.setText(String.valueOf(menus.get(menus.indexOf(input)).totalCalories()));
		textAreaTotalCal.setEditable(false);
		textAreaTotalCal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaTotalCal.setBounds(115, 327, 70, 17);
		frameDisplayDetail.getContentPane().add(textAreaTotalCal);
		textAreaTotalCal.setColumns(10);
		
		JLabel lblTotalPrice = new JLabel("Total Price:   $ ");
		lblTotalPrice.setBounds(20, 355, 90, 20);
		frameDisplayDetail.getContentPane().add(lblTotalPrice);
		
		JTextArea textAreaTotalPrice = new JTextArea();
		textAreaTotalPrice.setText(String.valueOf(inputEntree.getPrice()+inputSide.getPrice()+inputSalad.getPrice()+inputDessert.getPrice()));
		textAreaTotalPrice.setEditable(false);
		textAreaTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		textAreaTotalPrice.setBounds(115, 357, 70, 17);
		frameDisplayDetail.getContentPane().add(textAreaTotalPrice);
		textAreaTotalPrice.setColumns(10);
		
		frameDisplayDetail.setVisible(true);
	}
}
