package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import core.Core;
import core.Order;
import policies.FidCardPlanBasic;
import policies.FidCardPlanPoints;
import restaurantSetUp.Dish;
import restaurantSetUp.Meal;
import users.Customer;
import users.Restaurant;
import users.User;

public class GUICustomerFrame extends GUIUserFrame {
	
	private GUICustomerFrame instance;
	private Customer customer;
	private GUIDisplayMealDish mealDishDisplay = new GUIDisplayMealDish();
	
	private Meal currentMeal;
	private Order currentOrder;
	private Restaurant currentRestaurant;
	private Core core = GUIStartFrame.getCore();
	
	private JList<Restaurant> restaurants = new JList<Restaurant>();
	private JList<Dish> dishes;
	private JList<Order> orders;
	private JMenu orderMenu = new JMenu("New Order");
	
	private JScrollPane jScrollType;
	private JScrollPane jScrollOrders;
	
	private JPanel orderPanel = new JPanel();
	private JPanel scrollPanel = new JPanel();
	private JPanel menuMealsPanel = new JPanel();
	private JPanel addToOrderPanel = new JPanel();
	private JPanel finishOrderPanel = new JPanel();
	private JPanel fidPlanPanel = new JPanel();
	private JPanel notificationPanel = new JPanel();
	
	private JRadioButton fidPlanBasic = new JRadioButton("Basic");
	private JRadioButton fidPlanPoints = new JRadioButton("Points");
	private JRadioButton fidPlanLottery = new JRadioButton("Lottery");
	private ButtonGroup fidPlan = new ButtonGroup();
	
	private JRadioButton notificationOff = new JRadioButton("Off");
	private JRadioButton notificationOn = new JRadioButton("On");
	private ButtonGroup notification = new ButtonGroup();
	
	private boolean isOrdering = false;
	
	private JToggleButton mealsToggleButton;
	private JToggleButton menuToggleButton;
	
	private Button addToOrderButton;
	private Button finishOrderButton = new Button("FINISH ORDER");
	
	private JTextField quantityTextField = new JTextField("quantity as int");
	
	public GUICustomerFrame() {
		super();
		instance = this;
	}
	
	/*************************************************/
	//Constructor
	
	@Override
	public GUIUserFrame getInstance(User user) {
		
		if(user instanceof Customer){
			
			GUIStartFrame.getFrame().setVisible(false);
			this.customer = (Customer) user;
			fillAndSetMenuBarCustomer(customer);
			initGUI(customer, Color.orange, Color.yellow, "Customer Area", "Just Dwaggit...");
			fillCustomerInit();
			instance.open(0, 0, 600, 400);
			return instance;
		}
			
		return null;
	}
	
	
	
	
	/*************************************************/
	//fill functions
	
	private void fillRest(Restaurant rest) {

		mealDishDisplay.setTextFields(rest);
		mealDishDisplay.setGoBack_button(new Button("GO BACK"));
		mealDishDisplay.getGoBack_button()
				.addActionListener((ActionEvent e)-> {displayMeals(currentRestaurant);});
		mealDishDisplay.getjListMealShow().addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					Meal meal = rest.getListOfMeal().get(index);
					JPanel mealPanel1 = mealDishDisplay.display(meal, rest);
					currentMeal = meal;
					setUpMealOrdering(mealPanel1, rest);
					setCurrentPanel(mealPanel1);
				}
			}
		});
	}

			private void setUpMealOrdering(JPanel mealPanel, Restaurant rest) {
//				orderPanel.removeAll();
				scrollPanel.removeAll();
				addToOrderPanel.removeAll();
				addToOrderButton = new Button("ADD TO ORDER");
				
				addToOrderButton.addActionListener((ActionEvent e)->{
					if(!isOrdering){
					System.out.println("isOrdering is true");
					isOrdering = true;
					currentOrder = new Order(core.getCurrent_customer(), rest);
					}
					try {
						int quantity = Integer.parseInt(quantityTextField.getText());
						currentOrder.addMeal(currentMeal, quantity);
					} catch (NumberFormatException e2) {
						String message = "Please insert the amount of meals that you want to order as an Integer";
					}
					
				});

				addToOrderPanel.removeAll();
				addToOrderPanel.add(addToOrderButton);
				addToOrderPanel.add(quantityTextField);
				mealPanel.add(addToOrderPanel, BorderLayout.SOUTH);
			}
	
	private void fillOrderPanelScroll(JList<?> jlist){
		orderPanel.removeAll();
		jScrollType = new JScrollPane(jlist);
		scrollPanel.removeAll();
		scrollPanel.add(jScrollType);
		orderPanel.add(scrollPanel, BorderLayout.CENTER);
	}
	
	private void displayMeals(Restaurant rest) {
		fillRest(rest);
		mealDishDisplay.fillPanelMealShow(rest);
		mealDishDisplay.getjListMealShow().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fillOrderPanelScroll(mealDishDisplay.getjListMealShow());
		fillMealMenuToogleButtons(rest);
		orderPanel.add(menuMealsPanel ,BorderLayout.NORTH);
		
		if(isOrdering){
			mealsToggleButton.setVisible(false);
			menuToggleButton.setVisible(false);
			finishOrderPanel.removeAll();
			finishOrderPanel.removeAll();
			finishOrderPanel.add(home_button);
			finishOrderPanel.add(finishOrderButton);
			orderPanel.add(finishOrderPanel,BorderLayout.SOUTH);
		}else {
			orderPanel.add(home_button, BorderLayout.SOUTH);
		}
		
		setCurrentPanel(orderPanel);
	}
	
	private void displayRestaurants() {
		fillRestaurants();
		restaurants.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		fillOrderPanelScroll(restaurants);
		orderPanel.add(home_button, BorderLayout.SOUTH);
	}
	private void fillRestaurants() {
		DefaultListModel<Restaurant> model = new DefaultListModel<Restaurant>();
		for (Restaurant rest : core.getRestaurantList()) {
			model.addElement(rest);
		}
		restaurants.setModel(model);
	}
	
private void fillMealMenuToogleButtons(Restaurant rest){
		
		mealsToggleButton = new JToggleButton("Meals");
		menuToggleButton = new JToggleButton("Menu");
		mealsToggleButton.setSelected(true);
		menuMealsPanel.removeAll();
		menuMealsPanel.add(mealsToggleButton);
		menuMealsPanel.add(menuToggleButton);
		
		mealsToggleButton.addItemListener((ItemEvent e)->{
			int state = e.getStateChange();
			if(state == ItemEvent.SELECTED){
				menuToggleButton.setSelected(false);
				System.out.println("Meal is selected");
				displayMeals(rest);
			}
			else{
				menuToggleButton.setSelected(true);
				
			}
		});
		
		menuToggleButton.addItemListener((ItemEvent e)->{
			int state = e.getStateChange();
			if(state == ItemEvent.SELECTED){
				mealsToggleButton.setSelected(false);
				System.out.println("Menu is selected");
				displayDishs(rest);
			}
			else{
				mealsToggleButton.setSelected(true);
			}
		});
	}

	private void displayDishs(Restaurant rest) {
		dishes = mealDishDisplay.filljListAllDishes(rest);
		fillOrderPanelScroll(dishes);
		addToOrderPanel.removeAll();
		addToOrderPanel.add(home_button);
		addToOrderButton = new Button("ADD TO ORDER");
		
		addToOrderButton.addActionListener((ActionEvent e)->{
			if(!isOrdering){
			isOrdering = true;
			mealsToggleButton.setVisible(false);
			menuToggleButton.setVisible(false);
			scrollPanel.add(finishOrderButton);
			currentOrder = new Order(core.getCurrent_customer(), rest);
			}
			try {
				int quantity = Integer.parseInt(quantityTextField.getText());
				Dish dish = mealDishDisplay.getjListAllDish().getSelectedValue();
				currentOrder.addDish(dish, quantity);
			} catch (NumberFormatException e2) {
				String message = "Please insert the amount of meals that you want to order as an Integer";
			}
			
		});
		
		addToOrderPanel.add(addToOrderButton);
		addToOrderPanel.add(quantityTextField );
		orderPanel.add(addToOrderPanel, BorderLayout.SOUTH);
		orderPanel.add(menuMealsPanel, BorderLayout.NORTH);
		setCurrentPanel(orderPanel);
	}
	
	private void setScrollOrdersPanel() {
		getInfoPanel().removeAll();
		ArrayList<Order> orders = core.getHistoryOfOrders();
		DefaultListModel<Order> model = new DefaultListModel<Order>();
		for(Order order : orders)
			model.addElement(order);
		try{
		this.orders.setModel(model);
		} catch(NullPointerException e) {
			String message = "There are no orders saved for you";
			//TODO Pop-up
		}
		jScrollOrders = new JScrollPane(this.orders);
		getInfoPanel().add(jScrollOrders);
	}
	
	private void fillSetPanelFidPlan(Customer cust) {
		getSettingPanel().removeAll();
		if(cust.getFidCardPlan() instanceof FidCardPlanBasic){
			fidPlanBasic.setSelected(true);
		} else if (cust.getFidCardPlan() instanceof FidCardPlanPoints) {
			fidPlanPoints.setSelected(true);
		} else {
			fidPlanLottery.setSelected(true);
		}
		getSettingPanel().add(fidPlanPanel);
		getSetButtonPanel().removeAll();
		getSetButtonPanel().add(home_button);
		getSetButtonPanel().add(save_button);
		getSettingPanel().add(getSetButtonPanel(),BorderLayout.SOUTH);
	}
	
	private void fillSetPanelNotification(Customer cust) {
		getSettingPanel().removeAll();
		if(cust.isBeNotified()){
			notificationOn.setSelected(true);
		} else {
			notificationOff.setSelected(true);
		}
		getSettingPanel().add(notificationPanel);
		getSetButtonPanel().removeAll();
		getSetButtonPanel().add(home_button);
		getSetButtonPanel().add(save_button);
		getSettingPanel().add(getSetButtonPanel(),BorderLayout.SOUTH);
	}


	/*************************************************/
	//Initialize functions 
	
	private void fillCustomerInit() {

		fillorderPanelInit();
		initSetPanelFidPlan();
		initSetPanelNotif();
		finishOrderButton.addActionListener((ActionEvent e)-> {
			String orderedFood = currentOrder.getDishes().isEmpty() ? currentOrder.getMeals().toString() 
					: currentOrder.getDishes().toString();
			double price = currentOrder.getPrice();
			
		/**
		 * TODO pop up:
		 * {
			Order was registered
			show ordered Food and price
			yes / no
			core.placeNewOrder(currentOrder);
			setCurrentPanel(welcome_panel);
			}
		 * 
		 * 
		 * 
		 */
			
			
		});
		restaurants.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent evt) {
				JList<?> list = (JList<?>) evt.getSource();
				if (evt.getClickCount() == 2) {
					int index = list.locationToIndex(evt.getPoint());
					currentRestaurant = core.getRestaurantList().get(index);
					displayMeals(currentRestaurant);
				}
			}
		});
	}
	
	
	
	private void fillInfoMenuWithFunctionCustomer(Customer customer) {
		getInfoMenu().add(new CustomerActionInfoBasicCust("address", "show current address", customer));
		getInfoMenu().add(new CustomerActionInfoBasicCust("surname", "show current surname", customer));
		getInfoMenu().add(new CustomerActionInfoBasicCust("phoneNumb", "show current phone number", customer));
		getInfoMenu().add(new CustomerActionInfoBasicCust("emailAddress", "show current email address", customer));
		getInfoMenu().add(new CustomerActionInfoBasicCust("access history", "show old orders", customer));
	}
	
	
	private void fillSetMenuWithFunctionCustomer(Customer customer) {
		getSettingMenu().add(new CustomerActionSettingBasicCust("address", "change current address", customer));
		getSettingMenu().add(new CustomerActionSettingBasicCust("surname", "change current surname", customer));
		getSettingMenu().add(new CustomerActionSettingBasicCust("phoneNumb", "change current phone number", customer));
		getSettingMenu().add(new CustomerActionSettingBasicCust("emailAddress", "change current email address", customer));
		getSettingMenu().add(new CustomerActionSettingBasicCust("fidelity plan", "change current fidelity plan", customer));
		getSettingMenu().add(new CustomerActionSettingBasicCust("notification", "change current notification settings", customer));
	}
	
	private void fillcustomerActionOrder(Customer customer) {
		getMenuBar().add(orderMenu);
		orderMenu.add(new CustomerActionOrder("new order", "place a new order", customer));
	}
	
	public void fillAndSetMenuBarCustomer(Customer customer){
		fillInfoMenuWithFunctionCustomer(customer);
		fillSetMenuWithFunctionCustomer(customer);
		fillcustomerActionOrder(customer);
	}

	private void fillorderPanelInit() {
		orderPanel.setBorder(BorderFactory.createTitledBorder("Ordering"));
		orderPanel.setLayout(new BorderLayout());
		orderPanel.setBackground(Color.ORANGE);
	}
	
	private void initSetPanelFidPlan(){
		fidPlan.add(fidPlanBasic);
		fidPlan.add(fidPlanPoints);
		fidPlan.add(fidPlanLottery);
		fidPlanPanel.add(fidPlanBasic);
		fidPlanPanel.add(fidPlanPoints);
		fidPlanPanel.add(fidPlanLottery);
	}
	
	private void initSetPanelNotif(){
		notification.add(notificationOn);
		notification.add(notificationOff);
		notificationPanel.add(notificationOn);
		notificationPanel.add(notificationOff);
	}
	
	/*************************************************/
	//Action classes
	
	private class CustomerActionInfoBasicCust extends AbstractAction {

		String choice;
		Customer customer;

		public CustomerActionInfoBasicCust(String choice, String desc, Customer customer) {
			super(choice);
			this.choice = choice;
			this.customer = customer;
			putValue(Action.SHORT_DESCRIPTION, desc);
		}

		@Override
		public void actionPerformed(ActionEvent e){
			
			
			
			String descr = null;
			String value = null;
			
			switch (choice) {
            case "surname":
            	descr = "Your surname: ";
            	value = customer.getSurname();
            	fillInfoPanel(descr,value);
                break;
            case "address":
            	descr = "Your address: ";
            	value = customer.getAddress().toString();
            	fillInfoPanel(descr,value);
                break;
            case "phoneNumb":
            	descr = "Your phone number: ";
            	value = customer.getPhoneNumber();
            	fillInfoPanel(descr,value);
                break;
            case "emailAddress":
            	descr = "Your email address: ";
            	value = customer.getEmail();
            	fillInfoPanel(descr,value);
                break;
           case "access history":
        	   setScrollOrdersPanel();
        	   //TODO
        	   
               break; 
        }
			
			setCurrentPanel(getInfoPanel());
		}

		
	}
	
	private class CustomerActionSettingBasicCust extends AbstractAction {

		String choice;
		Customer customer;

		public CustomerActionSettingBasicCust(String choice, String desc, Customer customer) {
			super(choice);
			this.choice = choice;
			this.customer = customer;
			putValue(Action.SHORT_DESCRIPTION, desc);
		}

		@Override
		public void actionPerformed(ActionEvent e){
			
			String descr = null;
			String value = null;
			
			switch (choice) {
            case "surname":
            	descr = "Set your new surname: ";
            	value = customer.getSurname();
            	save_button = new JButton("SAVE");
				save_button.addActionListener((ActionEvent e2) -> {
					
					String value2 = getSetTextFieldValue().getText();
					customer.setSurname(value2);
				});
				fillSetPanel(descr,value);
				break;
			case "address":
				descr = "Set your new address: ";

				save_button = new JButton("SAVE");
				save_button.addActionListener((ActionEvent e4) -> {

					try{
						int xCoord = Integer.parseInt(getSetTextFieldXInt().getText());
						int yCoord = Integer.parseInt(getSetTextFieldYInt().getText());
						customer.getAddress().setxCoordinate(xCoord);
						customer.getAddress().setyCoordinate(yCoord);
						
					} catch (NumberFormatException fex) {
						String message = "Wrong Format! - Please write the address in the format \"xCoord,yCoord\"";
						// TODO pop up
					}
				});
				fillSetPanelAddress(descr, Integer.toString(customer.getAddress().getxCoordinate()),
						Integer.toString(customer.getAddress().getyCoordinate()));
				break;
			case "phoneNumb":
				descr = "Set your new phone number: ";
				value = customer.getPhoneNumber();
				save_button = new JButton("SAVE");
				save_button.addActionListener((ActionEvent e2) -> {

					String value2 = getSetTextFieldValue().getText();
					customer.setPhoneNumber(value2);
				});
				fillSetPanel(descr,value);
				break;
			case "emailAddress":
				descr = "Set your new email address: ";
				value = customer.getEmail();
				save_button = new JButton("SAVE");
				save_button.addActionListener((ActionEvent e2) -> {

					String value2 = getSetTextFieldValue().getText();
					customer.setEmail(value2);
				});
				fillSetPanel(descr,value);
				break;
			case "fidelity plan":
				
				save_button = new JButton("SAVE");
				save_button.addActionListener((ActionEvent e2) -> {
					if(fidPlanBasic.isSelected()){
						customer.setFidCardToBasic();
					} else if (fidPlanPoints.isSelected()) {
						customer.setFidCardToPoints();
					} else if (fidPlanLottery.isSelected()) {
						customer.setFidCardToLottery();
					}
				});
				fillSetPanelFidPlan(customer);
				break;
			case "notification":
				
				save_button = new JButton("SAVE");
				save_button.addActionListener((ActionEvent e2) -> {
					if(notificationOn.isSelected()){
						customer.setBeNotified(true);
					} else if (notificationOff.isSelected()) {
						customer.setBeNotified(false);
					}
				});
				fillSetPanelNotification(customer);
				break;
			}
			setCurrentPanel(getSettingPanel());
		}
	}
	
	private class CustomerActionOrder extends AbstractAction {

		String choice;
		Customer customer;

		public CustomerActionOrder(String choice, String desc, Customer customer) {
			super(choice);
			this.choice = choice;
			this.customer = customer;
			putValue(Action.SHORT_DESCRIPTION, desc);
		}

		@Override
		public void actionPerformed(ActionEvent e){
			
			switch (choice) {
            case "new order":
            	currentMeal = null;
            	currentOrder = null;
            	currentRestaurant = null;
            	isOrdering = false;
            	quantityTextField.setText("quantity");
            	displayRestaurants();
                break;
        }
			setCurrentPanel(orderPanel);
		}
	}
	
}