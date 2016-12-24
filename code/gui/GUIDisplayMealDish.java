package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFormattedTextField;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import java.awt.MenuItem;

import restaurantSetUp.Dessert;
import restaurantSetUp.FullMeal;
import restaurantSetUp.HalfMeal;
import restaurantSetUp.MainDish;
import restaurantSetUp.Meal;
import restaurantSetUp.Starter;
import users.Restaurant;

public class GUIDisplayMealDish {

	private JList<Meal> jListMeal = new JList<>();
	private JList<Starter> jListStarter = new JList<>();
	private JList<MainDish> jListMainDish = new JList<>();
	private JList<Dessert> jListDessert = new JList<>();

	private JPanel mealLabelPanel = new JPanel(new GridLayout(0, 1));
	private JPanel mealValuePanel = new JPanel(new GridLayout(0, 1));
	private JPanel dishPanel = new JPanel();

	private Button goBack_button = new Button("GO BACK");

	private JFormattedTextField starterDesc = new JFormattedTextField("Starter: ");
	private JFormattedTextField mainDishDesc = new JFormattedTextField("Main Dish: ");
	private JFormattedTextField dessertDesc = new JFormattedTextField("Dessert: ");
	private JFormattedTextField priceDesc = new JFormattedTextField("Total Price: ");
	private JFormattedTextField addDishDesc = new JFormattedTextField("2nd Choice: ");

	private JFormattedTextField starterT = new JFormattedTextField();
	private JFormattedTextField mainDishT = new JFormattedTextField();
	private JFormattedTextField dessertT = new JFormattedTextField();
	private JFormattedTextField priceT = new JFormattedTextField();
	private JFormattedTextField addDishT = new JFormattedTextField();
	
	private JMenu dishMenu = new JMenu("menu");

	private Button selectButton = new Button("SELECT");

	public GUIDisplayMealDish() {
		super();
	}

	public JPanel display(Meal meal, Restaurant rest) {
		
		if (meal instanceof FullMeal) {
			FullMeal fullMeal = (FullMeal) meal;
			String starter = fullMeal.getListOfDish().get(0).getName() + " of type "
					+ fullMeal.getListOfDish().get(0).getType();
			String mainDish = fullMeal.getListOfDish().get(1).getName() + " of type "
					+ fullMeal.getListOfDish().get(1).getType();
			String dessert = fullMeal.getListOfDish().get(2).getName() + " of type "
					+ fullMeal.getListOfDish().get(2).getType();
			String addInfo = rest.isMealSpecial(fullMeal) ? "$ = special offer" : "$";
			String price = rest.getPrice(fullMeal) + addInfo;

			starterT.setText(starter);
			mainDishT.setText(mainDish);
			dessertT.setText(dessert);
			priceT.setText(price);

			mealLabelPanel.removeAll();
			mealLabelPanel.add(starterDesc);
			mealLabelPanel.add(mainDishDesc);
			mealLabelPanel.add(dessertDesc);
			mealLabelPanel.add(priceDesc);

			mealValuePanel.removeAll();
			mealValuePanel.add(starterT);
			mealValuePanel.add(mainDishT);
			mealValuePanel.add(dessertT);
			mealValuePanel.add(priceT);

		} else {

			HalfMeal halfMeal = (HalfMeal) meal;

			String mainDish = halfMeal.getListOfDish().get(0).getName() + " of type "
					+ halfMeal.getListOfDish().get(0).getType();
			String addDish = halfMeal.getListOfDish().get(1).getName() + " of type "
					+ halfMeal.getListOfDish().get(1).getType();
			String addInfo = rest.isMealSpecial(halfMeal) ? "$ which is the special offer." : "$.";
			String price = rest.getPrice(halfMeal) + addInfo;

			mainDishT.setText(mainDish);
			addDishT.setText(addDish);
			priceT.setText(price);

			mealLabelPanel.removeAll();
			mealLabelPanel.add(mainDishDesc);
			mealLabelPanel.add(addDishDesc);
			mealLabelPanel.add(priceDesc);

			mealValuePanel.removeAll();
			mealValuePanel.add(mainDishT);
			mealValuePanel.add(addDishT);
			mealValuePanel.add(priceT);

		}
		dishPanel.removeAll();
		dishPanel.add(mealLabelPanel, BorderLayout.CENTER);
		dishPanel.add(mealValuePanel, BorderLayout.LINE_END);
		dishPanel.add(goBack_button);

		return dishPanel;
	}

	public void setTextFields(Restaurant rest) {

		dishPanel.setBorder(BorderFactory.createEmptyBorder());

		starterDesc.setEditable(false);
		mainDishDesc.setEditable(false);
		dessertDesc.setEditable(false);
		addDishDesc.setEditable(false);
		priceDesc.setEditable(false);

		starterT.setEditable(false);
		mainDishT.setEditable(false);
		dessertT.setEditable(false);
		addDishT.setEditable(false);
		priceT.setEditable(false);
	}

	public void fillPanelMeal(Restaurant rest){
		DefaultListModel<Meal> model = new DefaultListModel<Meal>();
		for (Meal meal : rest.getListOfMeal()) {
			model.addElement(meal);
		}
		jListMeal.setModel(model);
		jListMeal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public void fillPanelStarter(Restaurant rest){
		DefaultListModel<Starter> model = new DefaultListModel<Starter>();
		for (Starter start : rest.getMenu().getListOfStarter()) {
			model.addElement(start);
		}
		jListStarter.setModel(model);
		jListMeal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public void fillPanelMainDish(Restaurant rest){
		DefaultListModel<MainDish> model = new DefaultListModel<MainDish>();
		for (MainDish mainDish : rest.getMenu().getListOfMainDish()) {
			model.addElement(mainDish);
		}
		jListMainDish.setModel(model);
		jListMeal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	public void fillPanelDessert(Restaurant rest){
		DefaultListModel<Dessert> model = new DefaultListModel<Dessert>();
		for (Dessert dessert: rest.getMenu().getListOfDessert()) {
			model.addElement(dessert);
		}
		jListDessert.setModel(model);
		jListMeal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	/**
	 * @return the goBack_button
	 */
	public Button getGoBack_button() {
		return goBack_button;
	}

	/**
	 * @return the dishMenu
	 */
	public JMenu getDishMenu() {
		return dishMenu;
	}

	/**
	 * @return the jListMeal
	 */
	public JList<Meal> getjListMeal() {
		return jListMeal;
	}

	/**
	 * @return the jListMainDish
	 */
	public JList<MainDish> getjListMainDish() {
		return jListMainDish;
	}

	/**
	 * @return the jListDessert
	 */
	public JList<Dessert> getjListDessert() {
		return jListDessert;
	}

	/**
	 * @return the jListStarter
	 */
	public JList<Starter> getjListStarter() {
		return jListStarter;
	}
}
