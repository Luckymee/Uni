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

public class Gui extends JFrame {

    // Black Magic UUID?
    private static final long serialVersionUID = -8738556975914216497L;

    private static final Color TRAFFIC_GREEN = new Color(0, 128, 0);
    private static final Color TRAFFIC_RED = new Color(191, 0, 0);

    private boolean addingPassengers = false;

    // Primary Window
    private static final int WINDOW_X = 1200;
    private static final int WINDOW_Y = 800;
    private static final int START_X = 200;
    private static final int START_Y = 200;

    private static final int MIN_POWER = 1;
    private static final int MAX_POWER = 9;

    private static final int MIN_PASSENGERS = 0;
    private static final int INITAL_SEATS = 0;

    private static final String[] LOCOMOTIVE_TYPES = { "Electric", "Diesel", "Steam" };
    private static final String[] FREIGHTCAR_TYPES = { "General Goods", "Refrigerated", "Dangerous" };

    // Text Box Rows | Columns
    private static final int DEFAULT_COLUMNS = 3;
    private static final int LOGGER_ROWS = 5;
    private static final int LOGGER_COLUMNS = 5;

    private static final TrainCar.LocomotiveTypes DEFAULT_TYPE = TrainCar.LocomotiveTypes.NONE;

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
    private List<TrainCar> trainGraphicList;

    // Locomotive inputs
    private JButton addLocomotive;
    private JTextField locomotiveWeight;
    private SpinnerModel powerSpinnerVariables;
    private JSpinner locomotivePower;
    private JComboBox<String> locomotiveTypes;

    // FreightCar inputs
    private JButton addFreightCar;
    private JTextField freightCarWeight;
    private JComboBox<String> freightCarTypes;

    // PassengerCar inputs
    private JButton addPassenger;
    private JTextField passengerCarWeight;
    private JTextField numberOfPassengers;

    // Adaptive Text Fields -- Change from input
    private JLabel canMoveLabel;
    private JLabel totalPassengers;

    // Train can move
    private JPanel trainCanMovePanel;

    public Gui() {
	super("Train Simulation");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void defaultGUI() {

	// Clear Logger
	numberOfLogs = 0;

	// Default Layout - Fluid Grid Type
	getContentPane().setLayout(new GridBagLayout());
	GridBagConstraints constraints = new GridBagConstraints();
	Dimension defaultDimensions = new Dimension(300, 200);

	informationPanel = new JPanel();
	informationPanel.setPreferredSize(defaultDimensions);
	informationPanel.setLayout(new GridBagLayout());
	GridBagConstraints internalConstraints = new GridBagConstraints();

	trainDrawArea = new JPanel();
	informationPanel.setPreferredSize(defaultDimensions);
	trainDrawArea.setLayout(new FlowLayout());
	internalConstraints.fill = GridBagConstraints.BOTH;
	internalConstraints.weightx = 1.0;
	internalConstraints.weighty = 1.0;
	internalConstraints.gridheight = 2;
	internalConstraints.gridwidth = 2;
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 0;
	informationPanel.add(trainDrawArea, internalConstraints);

	totalPassengers = new JLabel("<html>Passengers<br />" + MIN_PASSENGERS + " | " + INITAL_SEATS + "</html>");
	internalConstraints.fill = GridBagConstraints.NONE;
	internalConstraints.insets = new Insets(0, 6, 0, 0);
	internalConstraints.anchor = GridBagConstraints.LAST_LINE_START;
	internalConstraints.weightx = 0.0;
	internalConstraints.weighty = 0.0;
	internalConstraints.gridheight = 1;
	internalConstraints.gridwidth = 1;
	internalConstraints.gridx = 1;
	internalConstraints.gridy = 2;
	informationPanel.add(totalPassengers, internalConstraints);

	// TODO - Main Display End.

	constraints.fill = GridBagConstraints.BOTH;
	constraints.weightx = 1.0;
	constraints.weighty = 1.0;
	constraints.gridwidth = 2;
	constraints.gridheight = 2;
	constraints.gridx = 0;
	constraints.gridy = 0;
	getContentPane().add(informationPanel, constraints);

	trainBuildArea = new JPanel();
	trainBuildArea.setPreferredSize(defaultDimensions);

	trainBuildArea.setLayout(new GridBagLayout());

	// Locomotive Panel
	JPanel addLocomotive = new JPanel();
	addLocomotive.setPreferredSize(new Dimension(300, 100));
	addLocomotive.setBorder(BorderFactory.createTitledBorder("Add Locomotive"));

	locomotivePanelSetup(addLocomotive);

	internalConstraints.fill = GridBagConstraints.BOTH;
	internalConstraints.weightx = 0.5;
	internalConstraints.weighty = 0.5;
	internalConstraints.gridwidth = 1;
	internalConstraints.gridheight = 1;
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 0;
	trainBuildArea.add(addLocomotive, internalConstraints);

	// Passenger Car Panel
	JPanel addPassengerCar = new JPanel();
	addPassengerCar.setPreferredSize(new Dimension(300, 100));
	addPassengerCar.setBorder(BorderFactory.createTitledBorder("Add PassengerCar"));

	passengerCarSetup(addPassengerCar);
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 1;
	trainBuildArea.add(addPassengerCar, internalConstraints);

	// Freight Car Panel
	JPanel addFreightCar = new JPanel();
	addFreightCar.setPreferredSize(new Dimension(300, 100));
	addFreightCar.setBorder(BorderFactory.createTitledBorder("Add FreightCar"));

	freightCarSetup(addFreightCar);
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 2;
	trainBuildArea.add(addFreightCar, internalConstraints);

	JButton removeCar = new JButton("Remove Last Car");
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

	constraints.fill = GridBagConstraints.BOTH;
	constraints.weightx = 0;
	constraints.weighty = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 2;
	constraints.gridx = 2;
	constraints.gridy = 0;
	getContentPane().add(trainBuildArea, constraints); // Update Panel

	// Passenger info
	passengerInfo = new JPanel();
	passengerInfo.setPreferredSize(defaultDimensions);
	passengerInfo.setBorder(BorderFactory.createTitledBorder("Add Passengers"));
	passengerInfo.setLayout(new GridBagLayout());

	JLabel passengerAddLabel = new JLabel("No. Passengers to add:");

	internalConstraints.fill = GridBagConstraints.NONE;
	internalConstraints.insets = new Insets(5, 5, 5, 5);
	internalConstraints.ipady = 10;
	internalConstraints.weightx = 0.3;
	internalConstraints.weighty = 0.3;
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 0;
	internalConstraints.gridwidth = 1;
	internalConstraints.gridheight = 1;
	passengerInfo.add(passengerAddLabel, internalConstraints);

	// This must be final for addActionListener
	final JTextField numberOfPassengerInput = new JTextField(DEFAULT_COLUMNS);
	numberOfPassengerInput.setPreferredSize(new Dimension(DEFAULT_COLUMNS, 20));
	internalConstraints.fill = GridBagConstraints.HORIZONTAL;
	internalConstraints.weightx = 0.3;
	internalConstraints.weighty = 0.3;
	internalConstraints.gridwidth = 1;
	internalConstraints.gridheight = 1;
	internalConstraints.gridx = 1;
	internalConstraints.gridy = 0;

	passengerInfo.add(numberOfPassengerInput, internalConstraints);

	// Add Passengers Button
	JButton addPassengers = new JButton("Add Passengers");
	internalConstraints.fill = GridBagConstraints.BOTH;
	internalConstraints.weightx = 0.5;
	internalConstraints.weighty = 0.1;
	internalConstraints.gridwidth = 2;
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 1;
	addPassengers.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent event) {
		addPassengers(numberOfPassengerInput.getText());
	    }
	});
	passengerInfo.add(addPassengers, internalConstraints);

	// Logger Panel TODO - This isnt right
	constraints.fill = GridBagConstraints.BOTH;
	constraints.anchor = GridBagConstraints.LAST_LINE_START;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.gridx = 0;
	constraints.gridy = 2;
	getContentPane().add(passengerInfo, constraints);

	logger = new JTextArea(LOGGER_ROWS, LOGGER_COLUMNS);
	logger.setBorder(BorderFactory.createTitledBorder("Error Log:"));
	logger.setEditable(false);
	logger.setPreferredSize(new Dimension(100, 40));
	constraints.anchor = GridBagConstraints.PAGE_END;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.gridx = 1;
	constraints.gridy = 2;
	getContentPane().add(logger, constraints);

	// Quit Panel
	trainCanMovePanel = new JPanel();

	canMoveLabel = new JLabel("Train Can Move");
	canMoveLabel.setForeground(Color.WHITE);
	canMoveLabel.setOpaque(true);
	canMoveLabel.setBackground(TRAFFIC_GREEN);
	// TODO Magic Numbers
	canMoveLabel.setPreferredSize(new Dimension(200, 100));
	canMoveLabel.setHorizontalAlignment(SwingConstants.CENTER);
	canMoveLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
	constraints.fill = GridBagConstraints.NONE;
	constraints.anchor = GridBagConstraints.CENTER;
	constraints.insets = new Insets(0, 0, 0, 0);
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.gridx = 2;
	constraints.gridy = 2;
	trainCanMovePanel.add(canMoveLabel, constraints);

	getContentPane().add(trainCanMovePanel, constraints);

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
    }

    private void locomotivePanelSetup(JPanel panel) {
    	
		panel.setLayout(new GridBagLayout());
		GridBagConstraints locomotiveConstraints = new GridBagConstraints();
		powerSpinnerVariables = new SpinnerNumberModel(1, MIN_POWER, MAX_POWER, 1);
		
		//Initialize variables
		JLabel powerLabel = null;
		JLabel locomtiveTypeLabel = null;
		JLabel weightlabel = null;
		
		locomotiveTypes = new JComboBox<String>(LOCOMOTIVE_TYPES);
		locomotiveWeight = new JTextField(DEFAULT_COLUMNS);
		locomotivePower = new JSpinner(powerSpinnerVariables);
				
		//create panel
		createLabel(powerLabel, "Power", locomotiveConstraints, panel, 0, 0, false);
		createPowerSpinner(locomotivePower, locomotiveConstraints, panel, 0, 1);
		createLabel(locomtiveTypeLabel, "Type", locomotiveConstraints, panel, 1, 0, false);
		createComboBox(locomotiveTypes, locomotiveConstraints, panel, 1, 1);
		createLabel(weightlabel, "Gross Weight", locomotiveConstraints, panel, 2, 0, false);
		createInput(locomotiveWeight, locomotiveConstraints, panel, 2, 1, false);
	
	
		// Add Button
		addLocomotive = new JButton("Add");
		addLocomotive.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
			addLocomotiveCheck((Integer) (((SpinnerNumberModel) powerSpinnerVariables).getNumber()),
				(String) locomotiveTypes.getSelectedItem(), locomotiveWeight.getText());
		    }
		});
		locomotiveConstraints.fill = GridBagConstraints.BOTH;
		locomotiveConstraints.gridx = 2;
		locomotiveConstraints.gridy = 2;
		panel.add(addLocomotive, locomotiveConstraints);

    }

    public boolean addLocomotiveCheck(int selectedPower, String selectedEngine, String inputedWeight) {
	Integer tempWeight;
	try { // Check if is NaN
	    tempWeight = Integer.parseInt(inputedWeight);
	} catch (Exception expected) {
	    errorLogger("Invalid Weight \n");
	    return false;
	}

	// Set classification
	String classification = "" + selectedPower + selectedEngine.charAt(0);
	Locomotive locomotive;

	try { // Determine if valid classification
	    locomotive = new Locomotive(tempWeight, classification);
	    Train.addCarriage(locomotive);
	} catch (TrainException expected) {
	    String errorOutput = expected.getMessage().replaceAll("^[+:Train Exception:]+", "");
	    errorLogger(errorOutput + "\n");
	    return false;
	}

	trainCanMove();
	TrainCar newTrainGraphics = new TrainCar(TrainCar.TrainTypes.LOCOMOTIVE, locomotive.toString(),
		TrainCar.LocomotiveTypes.NONE);
	trainDrawArea.add(newTrainGraphics);
	trainGraphicList.add(newTrainGraphics);
	trainDrawArea.revalidate();
	return true;
    }

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

    private void errorLogger(String newLog) {
	// TODO Magic numbers
	if (numberOfLogs >= 10) {
	    logger.setText("");
	    numberOfLogs = 0;
	}

	logger.append(newLog);
	numberOfLogs++;
    }

    private void freightCarSetup(JPanel panel) {
    	
		panel.setLayout(new GridBagLayout());
		GridBagConstraints freightCarConstraints = new GridBagConstraints();
		
		//Initialize variables
		JLabel freightCarTypeLabel = null;
		JLabel weightLabel = null;
		
		freightCarTypes = new JComboBox<String>(FREIGHTCAR_TYPES);
		freightCarWeight = new JTextField(DEFAULT_COLUMNS);
		
		//Create panel
		createLabel(freightCarTypeLabel, "Type", freightCarConstraints, panel, 0, 0, false);
		createComboBox(freightCarTypes, freightCarConstraints, panel, 0, 1);
		createLabel(weightLabel, "Gross Weight", freightCarConstraints, panel, 1, 0, false);
		createInput(freightCarWeight, freightCarConstraints, panel, 1, 1, false);
	
		addFreightCar = new JButton("Add");
		addFreightCar.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
			addFreightCarCheck((String) freightCarTypes.getSelectedItem(), freightCarWeight.getText());
		    }
		});
		freightCarConstraints.gridx = 1;
		freightCarConstraints.gridy = 2;
		panel.add(addFreightCar, freightCarConstraints);
    }

    private boolean addFreightCarCheck(String selectedType, String inputedWeight) {
	int tempWeight;
	try {
	    tempWeight = Integer.parseInt(inputedWeight);
	} catch (Exception expected) {
	    errorLogger("Invalid Weight. \n");
	    return false;
	}

	FreightCar freightCar;

	try {
	    freightCar = new FreightCar(tempWeight, selectedType);
	    Train.addCarriage(freightCar);
	} catch (TrainException expected) {
	    String errorOutput = expected.getMessage().replaceAll("^[+:Train Exception:]+", "");
	    errorLogger(errorOutput + "\n");
	    return false;
	}

	TrainCar.LocomotiveTypes goodsType = setFreightType(freightCar);

	trainCanMove();
	TrainCar newTrainGraphics = new TrainCar(TrainCar.TrainTypes.FREIGHTCAR, freightCar.toString(), goodsType);
	trainDrawArea.add(newTrainGraphics);
	trainGraphicList.add(newTrainGraphics);
	trainDrawArea.revalidate();
	
	

	return true;
    }

    private TrainCar.LocomotiveTypes setFreightType(FreightCar freightCar) {
	TrainCar.LocomotiveTypes goodsType = DEFAULT_TYPE;

	switch (freightCar.goodsType()) {
	case "G":
	    return goodsType = TrainCar.LocomotiveTypes.GENERAL_GOODS;
	case "D":
	    return goodsType = TrainCar.LocomotiveTypes.DANGEROUS_GOODS;
	case "R":
	    return goodsType = TrainCar.LocomotiveTypes.REFRIGERATED_GOODS;
	}

	return goodsType;
    }

    private void passengerCarSetup(JPanel panel) {

		panel.setLayout(new GridBagLayout());
		GridBagConstraints passengerConstraints = new GridBagConstraints();
		
		//Initialize variable
		JLabel capacityLabel = null;
		JLabel weightLabel = null;
		
		numberOfPassengers = new JTextField(DEFAULT_COLUMNS);
		passengerCarWeight = new JTextField(DEFAULT_COLUMNS);
		
		//Create panel
		createLabel(capacityLabel, "Capacity", passengerConstraints, panel, 0, 0, false);
		createInput(numberOfPassengers, passengerConstraints, panel, 0, 1, false);
		createLabel(weightLabel, "Gross Weight", passengerConstraints, panel, 2, 0, true);
		createInput(passengerCarWeight, passengerConstraints, panel, 2, 1, true);
		
	
		// Add Gross Weight
		addPassenger = new JButton("Add");
		addPassenger.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
			addPassengerCar(numberOfPassengers.getText(), passengerCarWeight.getText());
		    }
		});
		passengerConstraints.gridx = 2;
		passengerConstraints.gridy = 2;
		panel.add(addPassenger, passengerConstraints);
    }

    private boolean addPassengerCar(String numberOfPassengers, String inputedWeight) {
	int tempCapacity, tempWeight;
	try {
	    tempCapacity = Integer.parseInt(numberOfPassengers);
	    tempWeight = Integer.parseInt(inputedWeight);
	} catch (Exception expected) {
	    errorLogger("Invalid Input" + "\n");
	    // TODO test for type
	    return false;
	}

	PassengerCar passengerCar;

	try {
	    passengerCar = new PassengerCar(tempWeight, tempCapacity);
	    Train.addCarriage(passengerCar);
	} catch (TrainException expected) {
	    String errorOutput = expected.getMessage().replaceAll("^[+:Train Exception:]+", "");
	    errorLogger(errorOutput + "\n");
	    return false;
	}
	
	updatePassengerInfo();
	trainCanMove();
	TrainCar newTrainGraphics = new TrainCar(TrainCar.TrainTypes.PASSENGERCAR, passengerCar.toString(),
		DEFAULT_TYPE);
	trainDrawArea.add(newTrainGraphics);
	trainGraphicList.add(newTrainGraphics);
	trainDrawArea.revalidate();
	return true;
    }

    private boolean removeLastCar() {
	try { // Remove from Train
	    Train.removeCarriage();
	} catch (TrainException expected) {
	    String errorOutput = expected.getMessage().replaceAll("^[+:Train Exception:]+", "");
	    errorLogger(errorOutput + "\n");
	    return false;
	}

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

    private Integer addPassengers(String inputedAmount) {
	int tempAmount;
	addingPassengers = false;

	try {
	    tempAmount = Integer.parseInt(inputedAmount);
	} catch (Exception e) {
	    errorLogger("Invalid Amount. \n");
	    return -1;
	}

	try {
	    tempAmount = Train.board(tempAmount);
	} catch (TrainException expected) {
	    String errorOutput = expected.getMessage().replaceAll("^[+:Train Exception:]+", "");
	    errorLogger(errorOutput + "\n");
	    return -1;
	}

	if (tempAmount > 0) { // Number of passengers unable to board
	    errorLogger("Passengers unable to board: " + tempAmount + "\n");
	}

	addingPassengers = true;
	updatePassengerInfo();

	return tempAmount;
    }

    private void updatePassengerInfo() {
	totalPassengers.setText("<html>Passengers<br />" + Train.numberOnBoard() + " | " + Train.numberOfSeats()
		+ "</html>");
	totalPassengers.revalidate();
	totalPassengers.repaint();

	if (addingPassengers) {
	    RollingStock currentCar = Train.firstCarriage();

	    for (int i = 0; i < trainGraphicList.size(); ++i) {
		if (currentCar instanceof PassengerCar) {
		    trainGraphicList.remove(i);
		    trainDrawArea.remove(i);
		    TrainCar newTrainGraphics = new TrainCar(TrainCar.TrainTypes.PASSENGERCAR, currentCar.toString(),
			    DEFAULT_TYPE);
		    trainDrawArea.add(newTrainGraphics, i);
		    trainGraphicList.add(i, newTrainGraphics);
		}
		currentCar = Train.nextCarriage();
	    }
	    trainDrawArea.revalidate();
	}

    }
    
    private void createLabel(JLabel labelName, String labelText, GridBagConstraints constraints, JPanel panel, int xCo, int yCo, boolean insets){
    	labelName = new JLabel(labelText);
    	constraints.fill = GridBagConstraints.NONE;
    	if (insets){
    		constraints.insets = new Insets(0, 60, 0, 0);
    	}
    	constraints.gridx = xCo;
    	constraints.gridy = yCo;
    	panel.add(labelName, constraints);
    }
    
    private void createInput(JTextField inputName, GridBagConstraints constraints, JPanel panel, int xCo, int yCo, boolean insets){
    	inputName.setPreferredSize(new Dimension(DEFAULT_COLUMNS, 100));
    	constraints.fill = GridBagConstraints.HORIZONTAL;
    	if (insets){
    		constraints.insets = new Insets(0, 65, 0, 0);
    	}
    	constraints.gridx = xCo;
    	constraints.gridy = yCo;
    	panel.add(inputName, constraints);
    }
    
    private void createPowerSpinner(JSpinner spinnerName, GridBagConstraints constraints, JPanel panel, int xCo, int yCo){
    	constraints.gridx = xCo;
    	constraints.gridy = yCo;
    	panel.add(spinnerName, constraints);
    }
    
    private void createComboBox(JComboBox<String> boxName, GridBagConstraints constraints, JPanel panel, int xCo, int yCo){
    	constraints.gridx = xCo;
    	constraints.gridy = yCo;
    	panel.add(boxName, constraints);
    }

    public static void main(String[] args) {
	try {
	    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		if ("Nimbus".equals(info.getName())) {
		    UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (Exception e) {
	    // If Nimbus is not available, you can set the GUI to another look and feel.
	}
	Gui trainGUI = new Gui();
	trainGUI.defaultGUI();
    }
}