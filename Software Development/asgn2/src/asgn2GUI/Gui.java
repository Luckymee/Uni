package asgn2GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.BevelBorder;

import asgn2Exceptions.TrainException;
import asgn2RollingStock.FreightCar;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.RollingStock;
import asgn2Train.DepartingTrain;

/**
 * Constructs the GUI
 * 
 * @author Joe Maher - n8571520
 * @author Connor Livsey - n8510873
 * 
 */
public class Gui extends JFrame {

	// Black Magic UUID?
	private static final long serialVersionUID = -8738556975914216497L;

	private static final Color TRAFFIC_GREEN = new Color(0, 128, 0);
	private static final Color TRAFFIC_RED = new Color(191, 0, 0);

	// Primary Window
	private static final int WINDOW_X = 1200;
	private static final int WINDOW_Y = 800;
	private static final int START_X = 200;
	private static final int START_Y = 200;

	private static final int MIN_POWER = 1;
	private static final int MAX_POWER = 9;

	private static final int MIN_PASSENGERS = 0;
	private static final int INITAL_SEATS = 0;
	private static final int INVALID_INPUT = -1;

	private static final String[] LOCOMOTIVE_TYPES = { "Electric", "Diesel",
			"Steam" };
	private static final String[] FREIGHTCAR_TYPES = { "General Goods",
			"Refrigerated", "Dangerous" };

	// Text Box Rows | Columns
	private static final int DEFAULT_COLUMNS = 3;
	private static final int LOGGER_ROWS = 5;
	private static final int LOGGER_COLUMNS = 5;
	private static final int MAX_LOGS = 10;

	private static final TrainCar.FreightTypes DEFAULT_TYPE = TrainCar.FreightTypes.NONE;

	// Boolean Check
	private boolean addingPassengers = false;
	private boolean hasPassengerCar = false;
	
	// Primary JPanels
	private JPanel trainDrawArea;
	private JPanel informationPanel;
	private JPanel passengerInfo;

	// Departing Train
	private DepartingTrain Train;

	// Log Area
	private JTextArea logger;
	private int numberOfLogs;

	// Train Area
	private JPanel trainBuildArea;
	private JPanel locomotiveArea;
	private JPanel passengerCarArea;
	private JPanel freightCarArea;
	private List<TrainCar> trainGraphicList;

	// Locomotive inputs
	private JButton addLocomotive;
	private JTextField locomotiveWeight;
	private SpinnerModel powerSpinnerVariables;
	private JSpinner locomotivePower;
	private JComboBox<String> FreightTypes;

	// FreightCar inputs
	private JButton addFreightCar;
	private JTextField freightCarWeight;
	private JComboBox<String> freightCarTypes;

	// PassengerCar inputs
	private JButton addPassenger;
	private JTextField passengerCarWeight;
	private JTextField numberOfPassengers;

	// removeCar inputs
	private JButton removeCar;

	// Adaptive Text Fields -- Change from input
	private JLabel canMoveLabel;
	private JLabel totalPassengers;
	private JLabel seatsAvalailableLabel;

	// Train status
	private JPanel trainCanMovePanel;
	private JPanel seatsAvailablePanel;

	/**
	 * Names Default JFrame.
	 * Sets default close behavior.
	 */
	public Gui() {

		super("Train Simulation");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Constructs the default appearance of the GUI
	 */
	private void defaultGUI() {

		// Clear Logger
		numberOfLogs = 0;

		// Default Layout - Fluid Grid Type
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		GridBagConstraints internalConstraints = new GridBagConstraints();
		Dimension defaultDimensions = new Dimension(300, 200);
		Dimension buildAreaDimensions = new Dimension(300, 100);

		// Initializing panels
		informationPanel = new JPanel();
		trainDrawArea = new JPanel();
		trainBuildArea = new JPanel();
		locomotiveArea = new JPanel();
		passengerCarArea = new JPanel();
		freightCarArea = new JPanel();
		passengerInfo = new JPanel();
		trainCanMovePanel = new JPanel();
		seatsAvailablePanel = new JPanel();

		// setting sizes
		informationPanel.setPreferredSize(defaultDimensions);
		locomotiveArea.setPreferredSize(buildAreaDimensions);
		passengerCarArea.setPreferredSize(buildAreaDimensions);
		freightCarArea.setPreferredSize(buildAreaDimensions);
		passengerInfo.setPreferredSize(defaultDimensions);

		// setting layouts
		informationPanel.setLayout(new GridBagLayout());
		trainDrawArea.setLayout(new FlowLayout());
		trainBuildArea.setLayout(new GridBagLayout());
		passengerInfo.setLayout(new GridBagLayout());

		// setting borders
		locomotiveArea.setBorder(BorderFactory
				.createTitledBorder("Add Locomotive"));
		passengerCarArea.setBorder(BorderFactory
				.createTitledBorder("Add PassengerCar"));
		freightCarArea.setBorder(BorderFactory
				.createTitledBorder("Add FreightCar"));
		passengerInfo.setBorder(BorderFactory
				.createTitledBorder("Add Passengers"));

		// create panels
		createTrainAssemblyArea(internalConstraints);
		createInformationArea(constraints);
		createTrainBuildArea(internalConstraints, constraints);
		createPassengerInfoArea(internalConstraints);
		createLoggerArea(constraints);
		createCanMoveArea(constraints);

		// End layout

		// Draw to Screen
		setPreferredSize(new Dimension(WINDOW_X, WINDOW_Y));
		setMinimumSize(new Dimension(WINDOW_X, WINDOW_Y));
		setLocation(new Point(START_X, START_Y));

		pack();
		setVisible(true);

		// Set variables
		Train = new DepartingTrain();
		trainGraphicList = new ArrayList<TrainCar>();
		updateButtons();

	}

	/**
	 * Creates the locomotive build area panel
	 * 
	 * @param panel
	 *            JPanel: Panel to be setup
	 */
	private void locomotivePanelSetup(JPanel panel) {

		panel.setLayout(new GridBagLayout());
		GridBagConstraints locomotiveConstraints = new GridBagConstraints();
		powerSpinnerVariables = new SpinnerNumberModel(1, MIN_POWER, MAX_POWER,
				1);

		// Initialize variables
		JLabel powerLabel = null;
		JLabel locomtiveTypeLabel = null;
		JLabel weightlabel = null;

		FreightTypes = new JComboBox<String>(LOCOMOTIVE_TYPES);
		locomotiveWeight = new JTextField(DEFAULT_COLUMNS);
		locomotivePower = new JSpinner(powerSpinnerVariables);

		// create panel
		createLabel(powerLabel, "Power", locomotiveConstraints, panel, 0, 0,
				false);
		createPowerSpinner(locomotivePower, locomotiveConstraints, panel, 0, 1);
		createLabel(locomtiveTypeLabel, "Type", locomotiveConstraints, panel,
				1, 0, false);
		createComboBox(FreightTypes, locomotiveConstraints, panel, 1, 1);
		createLabel(weightlabel, "Gross Weight", locomotiveConstraints, panel,
				2, 0, false);
		createInput(locomotiveWeight, locomotiveConstraints, panel, 2, 1, false);

		// Add Button
		addLocomotive = new JButton("Add");
		addLocomotive.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				addLocomotive(
						(Integer) (((SpinnerNumberModel) powerSpinnerVariables)
								.getNumber()),
						(String) FreightTypes.getSelectedItem(),
						locomotiveWeight.getText());
			}
		});
		locomotiveConstraints.fill = GridBagConstraints.BOTH;
		locomotiveConstraints.gridx = 2;
		locomotiveConstraints.gridy = 2;
		panel.add(addLocomotive, locomotiveConstraints);

	}

	/**
	 * Adds a locomotive to the train and draws it on the trainDrawArea
	 * 
	 * @param selectedPower
	 *            int: User selected locomotive pulling power
	 * @param selectedEngine
	 *            String: User selected locomotive engine type
	 * @param inputedWeight
	 *            String: user selected locomotive weight
	 * @return boolean: Whether or not the users inputs allowed for a valid
	 *         locomotive and train configuration
	 */
	public boolean addLocomotive(int selectedPower, String selectedEngine,
			String inputedWeight) {

		Integer tempWeight;
		String classification = "" + selectedPower + selectedEngine.charAt(0);
		Locomotive locomotive;

		try { // Check if is NaN
			tempWeight = Integer.parseInt(inputedWeight);
		} catch (Exception expected) {
			errorLogger("Invalid Weight. Positive integers only.\n");
			return false;
		}

		try { // Determine if valid classification
			locomotive = new Locomotive(tempWeight, classification);
			Train.addCarriage(locomotive);
		} catch (TrainException expected) {
			String errorOutput = expected.getMessage().replaceAll(
					"^[+:Train Exception:]+", "");
			errorLogger(errorOutput + "\n");
			return false;
		}

		// update all affected sections of the GUI
		updateButtons();
		trainCanMove();
		TrainCar newTrainGraphics = new TrainCar(
				TrainCar.TrainTypes.LOCOMOTIVE, locomotive.toString(),
				TrainCar.FreightTypes.NONE);
		trainDrawArea.add(newTrainGraphics);
		trainGraphicList.add(newTrainGraphics);
		trainDrawArea.revalidate();
		return true;
	}

	/**
	 * Creates the freight car build area panel
	 * 
	 * @param panel
	 *            JPanel: Panel to be setup
	 */
	private void freightCarSetup(JPanel panel) {

		panel.setLayout(new GridBagLayout());
		GridBagConstraints freightCarConstraints = new GridBagConstraints();

		// Initialize variables
		JLabel freightCarTypeLabel = null;
		JLabel weightLabel = null;

		freightCarTypes = new JComboBox<String>(FREIGHTCAR_TYPES);
		freightCarWeight = new JTextField(DEFAULT_COLUMNS);

		// Create panel
		createLabel(freightCarTypeLabel, "Type", freightCarConstraints, panel,
				0, 0, false);
		createComboBox(freightCarTypes, freightCarConstraints, panel, 0, 1);
		createLabel(weightLabel, "Gross Weight", freightCarConstraints, panel,
				1, 0, false);
		createInput(freightCarWeight, freightCarConstraints, panel, 1, 1, false);

		// Create button
		addFreightCar = new JButton("Add");
		addFreightCar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				addFreightCar((String) freightCarTypes.getSelectedItem(),
						freightCarWeight.getText());
			}
		});
		freightCarConstraints.gridx = 1;
		freightCarConstraints.gridy = 2;
		panel.add(addFreightCar, freightCarConstraints);
	}

	/**
	 * Adds a freight car to the train and draws it on the trainDrawArea
	 * 
	 * @param selectedType
	 *            String: user selected freight car type
	 * @param inputedWeight
	 *            String: user selected freight car weight
	 * @return boolean: Whether or not the users inputs allowed for a valid
	 *         freight car and train configuration
	 */
	private boolean addFreightCar(String selectedType, String inputedWeight) {

		int tempWeight;
		FreightCar freightCar;

		try {// Check NaN
			tempWeight = Integer.parseInt(inputedWeight);
		} catch (Exception expected) {
			errorLogger("Invalid Weight. Positive integers only.\n");
			return false;
		}

		try {// Determine if valid shunt
			freightCar = new FreightCar(tempWeight, selectedType);
			Train.addCarriage(freightCar);
		} catch (TrainException expected) {
			String errorOutput = expected.getMessage().replaceAll(
					"^[+:Train Exception:]+", "");
			errorLogger(errorOutput + "\n");
			return false;
		}

		TrainCar.FreightTypes goodsType = setFreightType(freightCar);

		// update all affected sections of the GUI
		updateButtons();
		trainCanMove();
		TrainCar newTrainGraphics = new TrainCar(
				TrainCar.TrainTypes.FREIGHTCAR, freightCar.toString(),
				goodsType);
		trainDrawArea.add(newTrainGraphics);
		trainGraphicList.add(newTrainGraphics);
		trainDrawArea.revalidate();
		return true;
	}

	/**
	 * Returns the goods type within a freight car
	 * 
	 * @param freightCar
	 *            FreightCar: Freight car in question
	 * @return goodsType: The type of goods a freight car contains
	 */
	private TrainCar.FreightTypes setFreightType(FreightCar freightCar) {

		TrainCar.FreightTypes goodsType = DEFAULT_TYPE;

		switch (freightCar.goodsType()) {
			case "G":
				return goodsType = TrainCar.FreightTypes.GENERAL_GOODS;
			case "D":
				return goodsType = TrainCar.FreightTypes.DANGEROUS_GOODS;
			case "R":
				return goodsType = TrainCar.FreightTypes.REFRIGERATED_GOODS;
		}

		return goodsType;
	}

	/**
	 * Creates the passenger car build area panel
	 * 
	 * @param panel
	 *            JPanel: Panel to be setup
	 */
	private void passengerCarSetup(JPanel panel) {

		panel.setLayout(new GridBagLayout());
		GridBagConstraints passengerConstraints = new GridBagConstraints();

		// Initialize variable
		JLabel capacityLabel = null;
		JLabel weightLabel = null;

		numberOfPassengers = new JTextField(DEFAULT_COLUMNS);
		passengerCarWeight = new JTextField(DEFAULT_COLUMNS);

		// Create panel
		createLabel(capacityLabel, "Capacity", passengerConstraints, panel, 0,
				0, false);
		createInput(numberOfPassengers, passengerConstraints, panel, 0, 1,
				false);
		createLabel(weightLabel, "Gross Weight", passengerConstraints, panel,
				2, 0, true);
		createInput(passengerCarWeight, passengerConstraints, panel, 2, 1, true);

		// Create button
		addPassenger = new JButton("Add");
		addPassenger.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				addPassengerCar(numberOfPassengers.getText(),
						passengerCarWeight.getText());
			}
		});
		passengerConstraints.gridx = 2;
		passengerConstraints.gridy = 2;
		panel.add(addPassenger, passengerConstraints);
	}

	/**
	 * Adds a passengers car to the train and draws it on the trainDrawArea
	 * 
	 * @param numberOfPassengers
	 *            String: User selected passenger car capacity
	 * @param inputedWeight
	 *            String: user selected passenger car weight
	 * @return boolean: Whether or not the users inputs allowed for a valid
	 *         passenger car and train configuration
	 */
	private boolean addPassengerCar(String numberOfPassengers,
			String inputedWeight) {

		int tempCapacity, tempWeight;
		PassengerCar passengerCar;

		try {// Check Nan
			tempCapacity = Integer.parseInt(numberOfPassengers);
			tempWeight = Integer.parseInt(inputedWeight);
		} catch (Exception expected) {
			errorLogger("Invalid Input. Positive integers only.\n");
			return false;
		}

		try {// Determine if valid shunt
			passengerCar = new PassengerCar(tempWeight, tempCapacity);
			Train.addCarriage(passengerCar);
		} catch (TrainException expected) {
			String errorOutput = expected.getMessage().replaceAll(
					"^[+:Train Exception:]+", "");
			errorLogger(errorOutput + "\n");
			return false;
		}

		// update all affected sections of the GUI
		updateButtons();
		updatePassengerInfo();
		trainCanMove();
		TrainCar newTrainGraphics = new TrainCar(
				TrainCar.TrainTypes.PASSENGERCAR, passengerCar.toString(),
				DEFAULT_TYPE);
		trainDrawArea.add(newTrainGraphics);
		trainGraphicList.add(newTrainGraphics);
		trainDrawArea.revalidate();
		return true;
	}

	/**
	 * Removes the last car from the train
	 * 
	 * @return boolean: Whether or not the last car was successfully removed
	 */
	private boolean removeLastCar() {

		try { // Remove from Train
			Train.removeCarriage();
		} catch (TrainException expected) {
			String errorOutput = expected.getMessage().replaceAll(
					"^[+:Train Exception:]+", "");
			errorLogger(errorOutput + "\n");
			return false;
		}

		// update all affected sections of the GUI
		updateButtons();
		int lastCarriage = trainGraphicList.size() - 1;
		TrainCar removeCarriage = trainGraphicList.get(lastCarriage);
		trainDrawArea.remove(removeCarriage);
		trainDrawArea.validate();
		trainDrawArea.repaint();
		trainGraphicList.remove(lastCarriage);
		updatePassengerInfo();
		trainCanMove();
		return true;
	}

	/**
	 * Attempts to add the selected amount of passengers to the train
	 * 
	 * @param inputedAmount
	 *            String: User selected numbers of passengers to attempt to
	 *            board the train
	 * @return int: The amount of passengers who were unable to board the train
	 */
	private Integer addPassengers(String inputedAmount) {

		int tempAmount;
		addingPassengers = false;
		hasPassengerCar = false;
		
		try {// Check NaN
		    	tempAmount = Integer.parseInt(inputedAmount);
		} catch (Exception e) {
		    	errorLogger("Invalid Input. Positive integers only.\n");
		    	return INVALID_INPUT;
		}
		
		try {// Check if valid board
		    	tempAmount = Train.board(tempAmount);
		} catch (TrainException expected) {
		    	String errorOutput = expected.getMessage().replaceAll("^[+:Train Exception:]+", "");
		    	errorLogger(errorOutput + "\n");
		    	return INVALID_INPUT;
		}
		
		// Check for passenger car in train.
		addingPassengers = true;
		updatePassengerInfo();
		
		if (hasPassengerCar) { // If has passengerCar, no free seats.
		    if (tempAmount > INITAL_SEATS) { // Number of passengers unable to board
			errorLogger("Passengers unable to board: " + tempAmount 
				+ ". No Seats Available Seats.\n");
		    }
		} else { // No passenger car on train.
		    	errorLogger("Passengers unable to board: " + tempAmount 
			    + ". No Passenger Car.\n");
		}
		// update all affected sections of the GUI
		updateButtons();
		updatePassengerInfo();
		return tempAmount;
	}

	/**
	 * Updates both the Passenger cars displayed in the trainDrawArea and the
	 * totalPassengers label
	 */
	private void updatePassengerInfo() {

		totalPassengers.setText("<html><b>Passengers<br/>"
				+ Train.numberOnBoard() + " | " + Train.numberOfSeats()
				+ "</b></html>");
		totalPassengers.revalidate();
		totalPassengers.repaint();
		trainCapacity();

		if (addingPassengers) {// Attempting to add passengers to train
			RollingStock currentCar = Train.firstCarriage();

			// each train car graphic
			for (int i = 0; i < trainGraphicList.size(); ++i) {
				if (currentCar instanceof PassengerCar) {// is passenger car
				    	hasPassengerCar = true;
					trainGraphicList.remove(i);// remove graphic
					trainDrawArea.remove(i);// remove car from train
					TrainCar newTrainGraphics = new TrainCar(
							TrainCar.TrainTypes.PASSENGERCAR,
							currentCar.toString(),
							DEFAULT_TYPE);
					trainDrawArea.add(newTrainGraphics, i);// replace removed graphic
					trainGraphicList.add(i, newTrainGraphics);// replace removed car
				}
				currentCar = Train.nextCarriage();
			}
			trainDrawArea.revalidate();
		}

	}

	/**
	 * Creates a label
	 * 
	 * @param labelName
	 *            JLabel: Name of the label
	 * @param labelText
	 *            String: Text displayed on label
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 * @param panel
	 *            JPanel: Panel label is to be displayed on
	 * @param xCo
	 *            int: x-coordinate of label's position
	 * @param yCo
	 *            int: y-coordinate of label's position
	 * @param insets
	 *            boolean: Whether of not the label has insets
	 */
	private void createLabel(JLabel labelName, String labelText,
			GridBagConstraints constraints, JPanel panel, int xCo,
			int yCo, boolean insets) {

		labelName = new JLabel(labelText);
		constraints.fill = GridBagConstraints.NONE;
		if (insets) {
			constraints.insets = new Insets(0, 60, 0, 0);
		}
		constraints.gridx = xCo;
		constraints.gridy = yCo;
		panel.add(labelName, constraints);
	}

	/**
	 * Creates a text input field
	 * 
	 * @param inputName
	 *            JTextField: Name of the input field
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 * @param panel
	 *            JPanel: Panel the input field is to be displayed on
	 * @param xCo
	 *            int: x-coordinate of input fields's position
	 * @param yCo
	 *            int: y-coordinate of input fields's position
	 * @param insets
	 *            boolean: Whether of not the input field has insets
	 */
	private void createInput(JTextField inputName,
			GridBagConstraints constraints, JPanel panel, int xCo, int yCo,
			boolean insets) {

		inputName.setPreferredSize(new Dimension(DEFAULT_COLUMNS, 100));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		if (insets) {
			constraints.insets = new Insets(0, 65, 0, 0);
		}
		constraints.gridx = xCo;
		constraints.gridy = yCo;
		panel.add(inputName, constraints);
	}

	/**
	 * Creates a power spinner
	 * 
	 * @param spinnerName
	 *            JSpinner: Name of the power spinner
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 * @param panel
	 *            JPanel: Panel the power spinner is to be displayed on
	 * @param xCo
	 *            int: x-coordinate of power spinner's position
	 * @param yCo
	 *            int: y-coordinate of power spinner's position
	 */
	private void createPowerSpinner(JSpinner spinnerName,
			GridBagConstraints constraints, JPanel panel, int xCo, int yCo) {

		constraints.gridx = xCo;
		constraints.gridy = yCo;
		panel.add(spinnerName, constraints);
	}

	private void createComboBox(JComboBox<String> boxName,
			GridBagConstraints constraints, JPanel panel, int xCo, int yCo) {

		constraints.gridx = xCo;
		constraints.gridy = yCo;
		panel.add(boxName, constraints);
	}

	/**
	 * Creates the train assembly area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createTrainAssemblyArea(GridBagConstraints constraints) {

		// Train display area
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridheight = 2;
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = 0;
		informationPanel.add(trainDrawArea, constraints);

		// Passenger info
		totalPassengers = new JLabel("<html><b>Passengers<br/>" + MIN_PASSENGERS
				+ " | " + INITAL_SEATS + "</b></html>");
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new Insets(0, 6, 0, 0);
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		informationPanel.add(totalPassengers, constraints);
	}

	/**
	 * Creates the train build area
	 * 
	 * @param internalConstraints
	 *            GridBagConstraints: Type of constraints to be applied
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createTrainBuildArea(GridBagConstraints internalConstraints,
			GridBagConstraints constraints) {

		// Add carriage type panels
		createLocomotiveArea(internalConstraints);
		createPassengerCarArea(internalConstraints);
		createFreightCarArea(internalConstraints);

		// Create button
		removeCar = new JButton("Remove Last Car");
		removeCar.addActionListener(new ActionListener() {
			@Override
			// Must be public
			public void actionPerformed(ActionEvent event) {

				removeLastCar();
			}
		});
		internalConstraints.gridx = 0;
		internalConstraints.gridy = 3;
		trainBuildArea.add(removeCar, internalConstraints);

		// Update Panel
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0;
		constraints.weighty = 1;
		constraints.gridwidth = 1;
		constraints.gridheight = 2;
		constraints.gridx = 2;
		constraints.gridy = 0;
		getContentPane().add(trainBuildArea, constraints);

	}

	/**
	 * Creates the locomotive setup area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createLocomotiveArea(GridBagConstraints constraints) {

		// Locomotive panel
		locomotivePanelSetup(locomotiveArea);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 0;
		constraints.gridy = 0;
		trainBuildArea.add(locomotiveArea, constraints);
	}

	/**
	 * Creates the passenger car setup area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createPassengerCarArea(GridBagConstraints constraints) {

		// Passenger car panel
		passengerCarSetup(passengerCarArea);
		constraints.gridx = 0;
		constraints.gridy = 1;
		trainBuildArea.add(passengerCarArea, constraints);
	}

	/**
	 * Creates the freight car setup area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createFreightCarArea(GridBagConstraints constraints) {

		// Freight car panel
		freightCarSetup(freightCarArea);
		constraints.gridx = 0;
		constraints.gridy = 2;
		trainBuildArea.add(freightCarArea, constraints);
	}

	/**
	 * Creates the train information area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createInformationArea(GridBagConstraints constraints) {

		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridwidth = 2;
		constraints.gridheight = 2;
		constraints.gridx = 0;
		constraints.gridy = 0;
		getContentPane().add(informationPanel, constraints);
	}

	/**
	 * Creates the passenger information area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createPassengerInfoArea(GridBagConstraints constraints) {

		// Passenger label
		JLabel passengerAddLabel = new JLabel("No. Passengers to add:");
		constraints.fill = GridBagConstraints.NONE;
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.ipady = 10;
		constraints.weightx = 0.3;
		constraints.weighty = 0.3;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		passengerInfo.add(passengerAddLabel, constraints);

		// Passenger input
		// This must be final for addActionListener
		final JTextField numberOfPassengerInput = new JTextField(
				DEFAULT_COLUMNS);
		numberOfPassengerInput.setPreferredSize(new Dimension(DEFAULT_COLUMNS,
				20));
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.weightx = 0.3;
		constraints.weighty = 0.3;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 1;
		constraints.gridy = 0;
		passengerInfo.add(numberOfPassengerInput, constraints);

		// Add Passengers Button
		JButton addPassengers = new JButton("Add Passengers");
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 0.5;
		constraints.weighty = 0.1;
		constraints.gridwidth = 2;
		constraints.gridx = 0;
		constraints.gridy = 1;
		addPassengers.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				addPassengers(numberOfPassengerInput.getText());
			}
		});
		passengerInfo.add(addPassengers, constraints);
	}

	/**
	 * Creates the logger area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createLoggerArea(GridBagConstraints constraints) {

		// Passenger area
		constraints.fill = GridBagConstraints.BOTH;
		constraints.anchor = GridBagConstraints.LAST_LINE_START;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 0;
		constraints.gridy = 2;
		getContentPane().add(passengerInfo, constraints);

		// Logger box
		logger = new JTextArea(LOGGER_ROWS, LOGGER_COLUMNS);
		logger.setBorder(BorderFactory.createTitledBorder("Error Log:"));
		logger.setEditable(false);
		logger.setPreferredSize(new Dimension(100, 40));
		logger.setLineWrap(true);
		logger.setWrapStyleWord(true);
		constraints.anchor = GridBagConstraints.PAGE_END;
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 1;
		constraints.gridy = 2;
		getContentPane().add(logger, constraints);
	}

	/**
	 * Creates the train status area
	 * 
	 * @param constraints
	 *            GridBagConstraints: Type of constraints to be applied
	 */
	private void createCanMoveArea(GridBagConstraints constraints) {

		Dimension statusDimensions = new Dimension(200, 100);

		// Can move label
		canMoveLabel = new JLabel("Train Can Move");
		canMoveLabel.setForeground(Color.WHITE);
		canMoveLabel.setOpaque(true);
		canMoveLabel.setBackground(TRAFFIC_GREEN);
		canMoveLabel.setPreferredSize(statusDimensions);
		canMoveLabel.setHorizontalAlignment(SwingConstants.CENTER);
		canMoveLabel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED));
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(-105, 0, 0, 0);
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 2;
		constraints.gridy = 2;
		trainCanMovePanel.add(canMoveLabel, constraints);

		getContentPane().add(trainCanMovePanel, constraints);

		// Seats available label
		seatsAvalailableLabel = new JLabel("No Seats Available");
		seatsAvalailableLabel.setForeground(Color.WHITE);
		seatsAvalailableLabel.setOpaque(true);
		seatsAvalailableLabel.setBackground(TRAFFIC_RED);
		seatsAvalailableLabel.setPreferredSize(statusDimensions);
		seatsAvalailableLabel.setHorizontalAlignment(SwingConstants.CENTER);
		seatsAvalailableLabel.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED));
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.insets = new Insets(105, 0, 0, 0);
		constraints.gridwidth = 1;
		constraints.gridheight = 1;
		constraints.gridx = 2;
		constraints.gridy = 2;
		seatsAvailablePanel.add(seatsAvalailableLabel, constraints);

		getContentPane().add(seatsAvailablePanel, constraints);

	}

	/**
	 * Updates the train can move label
	 */
	private void trainCanMove() {

		boolean canMove = Train.trainCanMove();

		if (canMove) {
			canMoveLabel.setBackground(TRAFFIC_GREEN);
			canMoveLabel.setText("Train Can Move");
		} else {
			canMoveLabel.setBackground(TRAFFIC_RED);
			canMoveLabel.setText("Train Can't Move");
		}
	}

	/**
	 * Updates the seats available label
	 */
	private void trainCapacity() {

		boolean seatsAvailable = Train.numberOfSeats() > Train.numberOnBoard();

		if (seatsAvailable) {
			seatsAvalailableLabel.setBackground(TRAFFIC_GREEN);
			seatsAvalailableLabel.setText("Seats Available");
		} else {
			seatsAvalailableLabel.setBackground(TRAFFIC_RED);
			seatsAvalailableLabel.setText("No Seats Available");
		}
	}

	/**
	 * Adds messages to the error logger
	 * 
	 * @param newLog
	 *            String: Message to be added to the logger
	 */
	private void errorLogger(String newLog) {

		if (numberOfLogs >= MAX_LOGS) {
			logger.setText("");
			numberOfLogs = 0;
		}

		logger.append(newLog);
		numberOfLogs++;
	}

	/**
	 * Updates the various buttons throughout the GUI and whether or not they
	 * are enabled
	 */
	private void updateButtons() {

		if (Train.numberOnBoard() > MIN_PASSENGERS) {
			addLocomotive.setEnabled(false);
			addPassenger.setEnabled(false);
			addFreightCar.setEnabled(false);
			removeCar.setEnabled(false);
		} else {

			removeCar.setEnabled(false);// assume no cars for logic purposes

			// is first carriage locomotive
			if (Train.firstCarriage() instanceof Locomotive) {
				addLocomotive.setEnabled(false);
				addFreightCar.setEnabled(true);
				addPassenger.setEnabled(true);
			} else {
				addLocomotive.setEnabled(true);
				addFreightCar.setEnabled(false);
				addPassenger.setEnabled(false);
			}

			RollingStock currentCar = null;
			boolean hasFreightCar = false;

			// see if train contains freight cars
			for (int i = 0; i < trainGraphicList.size(); i++) {

				currentCar = Train.nextCarriage();

				if (currentCar instanceof FreightCar) {
					hasFreightCar = true;
				}
			}

			if (hasFreightCar) {// is last car freight car
				addPassenger.setEnabled(false);
			}

			if (Train.firstCarriage() != null) {// does train have carriages
				removeCar.setEnabled(true);
			}

		}
	}

	/**
	 * Creates the GUI
	 * 
	 * @param args
	 *            String[]: Command line arguments
	 */
	public static void main(String[] args) {

		try { // Apply theme to GUI
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
		}

		Gui trainGUI = new Gui();
		trainGUI.defaultGUI();
	}
}