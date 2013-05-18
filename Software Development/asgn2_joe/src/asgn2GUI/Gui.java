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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

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

    // Primary Window
    private static final int WINDOW_X = 1200;
    private static final int WINDOW_Y = 650;
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
    private static final int LOGGER_ROWS = 10;
    private static final int LOGGER_COLUMNS = 10;

    public enum TrainTypes {
	LOCOMOTIVE, FREIGHTCAR, PASSENGERCAR
    };

    // Primary JPanels
    private JPanel infoDisplay;
    private JPanel informationPanel;
    private JPanel passengerInfo;

    // Departing Train
    private DepartingTrain Train;

    // Log Area
    private JTextArea logger;
    private int numberOfLogs;

    // Train Area
    private JPanel trainArea;
    private List<TrainCar> trainGraphicList;

    // Locomotive inputs
    private JButton addLocomotive;
    private JTextField locomotiveWeight;
    private SpinnerModel powerSpinnerVariables;
    private JComboBox<String> locomotiveTypes;

    // FreightCar inputs
    private JButton addFreightCar;
    private JTextField freightCarWeight;
    private JComboBox<String> freightCarTypes;

    // PassengerCar inputs
    private JButton addPassengerCar;
    private JTextField passengerCarWeight;
    private JTextField numberOfPassengers;

    // Adaptive Text Fields -- Change from input
    private JLabel canMoveLabel;
    private JLabel totalPassengers;

    // Quit Panel
    private JPanel quitPanel;
    private JButton quitButton;

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

	infoDisplay = new JPanel();
	informationPanel.setPreferredSize(defaultDimensions);
	infoDisplay.setLayout(new FlowLayout());
	internalConstraints.fill = GridBagConstraints.BOTH;
	internalConstraints.weightx = 1.0;
	internalConstraints.weighty = 1.0;
	internalConstraints.gridheight = 2;
	internalConstraints.gridwidth = 2;
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 0;
	informationPanel.add(infoDisplay, internalConstraints);

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

	// canMoveLabel = new JLabel("Train Can Move");
	// canMoveLabel.setForeground(Color.WHITE);
	// canMoveLabel.setOpaque(true);
	// canMoveLabel.setBackground(TRAFFIC_GREEN);
	// internalConstraints.fill = GridBagConstraints.NONE;
	// internalConstraints.anchor = GridBagConstraints.CENTER;
	// internalConstraints.weightx = 0.0;
	// internalConstraints.weighty = 0.0;
	// internalConstraints.gridheight = 1;
	// internalConstraints.gridwidth = 1;
	// internalConstraints.gridx = 1;
	// internalConstraints.gridy = 2;
	// informationPanel.add(canMoveLabel, internalConstraints);
	// TODO - Main Display End.

	constraints.fill = GridBagConstraints.BOTH;
	constraints.weightx = 1.0;
	constraints.weighty = 1.0;
	constraints.gridwidth = 2;
	constraints.gridheight = 2;
	constraints.gridx = 0;
	constraints.gridy = 0;
	getContentPane().add(informationPanel, constraints);

	trainArea = new JPanel();
	trainArea.setPreferredSize(defaultDimensions);

	trainArea.setLayout(new GridBagLayout());

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
	trainArea.add(addLocomotive, internalConstraints);

	// Passenger Car Panel
	JPanel addPassengerCar = new JPanel();
	addPassengerCar.setPreferredSize(new Dimension(300, 100));
	addPassengerCar.setBorder(BorderFactory.createTitledBorder("Add PassengerCar"));

	passengerCarSetup(addPassengerCar);
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 1;
	trainArea.add(addPassengerCar, internalConstraints);

	// Freight Car Panel
	JPanel addFreightCar = new JPanel();
	addFreightCar.setPreferredSize(new Dimension(300, 100));
	addFreightCar.setBorder(BorderFactory.createTitledBorder("Add FreightCar"));

	freightCarSetup(addFreightCar);
	internalConstraints.gridx = 0;
	internalConstraints.gridy = 2;
	trainArea.add(addFreightCar, internalConstraints);

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
	trainArea.add(removeCar, internalConstraints);

	constraints.fill = GridBagConstraints.BOTH;
	constraints.weightx = 0;
	constraints.weighty = 1;
	constraints.gridwidth = 1;
	constraints.gridheight = 2;
	constraints.gridx = 2;
	constraints.gridy = 0;
	getContentPane().add(trainArea, constraints); // Update Panel

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

	// Logger Panel
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
	constraints.anchor = GridBagConstraints.PAGE_END;
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.gridx = 1;
	constraints.gridy = 2;
	getContentPane().add(logger, constraints);

	// Quit Panel
	quitPanel = new JPanel();
	// quitButton = new JButton("Quit");
	// quitButton.addActionListener(new ActionListener() {
	// @Override
	// public void actionPerformed(ActionEvent event) {
	// System.exit(0);
	// }
	// });
	//
	// quitPanel.add(quitButton);
	// informationPanel.setPreferredSize(defaultDimensions);
	// constraints.fill = GridBagConstraints.NONE;
	// constraints.anchor = GridBagConstraints.CENTER;
	// constraints.insets = new Insets(0, 0, 10, 10);
	// constraints.gridwidth = 1;
	// constraints.gridheight = 1;
	// constraints.gridx = 2;
	// constraints.gridy = 2;

	canMoveLabel = new JLabel("Train Can Move");
	canMoveLabel.setForeground(Color.WHITE);
	canMoveLabel.setOpaque(true);
	canMoveLabel.setBackground(TRAFFIC_GREEN);
	constraints.fill = GridBagConstraints.NONE;
	constraints.anchor = GridBagConstraints.CENTER;
	constraints.insets = new Insets(0, 0, 10, 10);
	constraints.gridwidth = 1;
	constraints.gridheight = 1;
	constraints.gridx = 2;
	constraints.gridy = 2;
	quitPanel.add(canMoveLabel, constraints);

	getContentPane().add(quitPanel, constraints);

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

	JLabel powerLabel = new JLabel("Power");
	locomotiveConstraints.fill = GridBagConstraints.NONE;
	locomotiveConstraints.gridx = 0;
	locomotiveConstraints.gridy = 0;
	panel.add(powerLabel, locomotiveConstraints);

	// Power Spinner TODO - Better Variables
	powerSpinnerVariables = new SpinnerNumberModel(1, MIN_POWER, MAX_POWER, 1);
	JSpinner locomotivePower = new JSpinner(powerSpinnerVariables);
	locomotiveConstraints.gridx = 0;
	locomotiveConstraints.gridy = 1;
	panel.add(locomotivePower, locomotiveConstraints);

	// Type Combo Box Label
	JLabel locomtiveTypeLabel = new JLabel("Type");
	locomotiveConstraints.fill = GridBagConstraints.NONE;
	locomotiveConstraints.gridx = 1;
	locomotiveConstraints.gridy = 0;
	panel.add(locomtiveTypeLabel, locomotiveConstraints);

	// Type Combo Box TODO: Will this work
	locomotiveTypes = new JComboBox<String>(LOCOMOTIVE_TYPES);
	locomotiveConstraints.gridx = 1;
	locomotiveConstraints.gridy = 1;
	panel.add(locomotiveTypes, locomotiveConstraints);

	// Weight Label
	JLabel weightlabel = new JLabel("Weight");
	locomotiveConstraints.gridx = 2;
	locomotiveConstraints.gridy = 0;
	panel.add(weightlabel, locomotiveConstraints);

	// Weight Input
	locomotiveWeight = new JTextField(DEFAULT_COLUMNS);
	locomotiveConstraints.gridx = 2;
	locomotiveConstraints.gridy = 1;
	panel.add(locomotiveWeight, locomotiveConstraints);

	// Add Button
	addLocomotive = new JButton("Add");
	addLocomotive.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent event) {
		addLocomotiveCheck((Integer) (((SpinnerNumberModel) powerSpinnerVariables).getNumber()),
			(String) locomotiveTypes.getSelectedItem(), locomotiveWeight.getText());
	    }
	});

	locomotiveConstraints.gridx = 3;
	locomotiveConstraints.gridy = 1;
	panel.add(addLocomotive, locomotiveConstraints);

    }

    private boolean addLocomotiveCheck(int selectedPower, String item, String selectedWeight) {
	Integer tempWeight;
	try {
	    tempWeight = Integer.parseInt(selectedWeight);
	} catch (Exception expected) {
	    errorLogger("Invalid Weight \n");
	    return false;
	}
	String classification = "" + selectedPower + item.charAt(0);
	Locomotive locomotive;

	try {
	    locomotive = new Locomotive(tempWeight, classification);
	    Train.addCarriage(locomotive);
	} catch (TrainException expected) {
	    String errorOutput = expected.getMessage().replaceAll(
		    "^[asgn2Exceptions.TrainException:*Train Exception:]*", "");
	    errorLogger(errorOutput + "\n");

	    return false;
	}

	trainCanMove();
	TrainCar newGraphic = new TrainCar(TrainCar.TrainTypes.LOCOMOTIVE, locomotive.toString());
	infoDisplay.add(newGraphic);
	trainGraphicList.add(newGraphic);
	infoDisplay.revalidate();
	return true;
    }

    private void trainCanMove() {
	boolean canMove = Train.trainCanMove();

	if (canMove) {
	    canMoveLabel.setBackground(TRAFFIC_GREEN);
	} else {
	    canMoveLabel.setBackground(TRAFFIC_RED);
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
    	GridBagConstraints freightConstraints = new GridBagConstraints();

    	// Type Combo Box Label
    	JLabel freightTypeLabel = new JLabel("Type");
    	freightConstraints.fill = GridBagConstraints.NONE;
    	freightConstraints.gridx = 1;
    	freightConstraints.gridy = 0;
    	panel.add(freightTypeLabel, freightConstraints);

    	// Type Combo Box TODO: Will this work
    	freightCarTypes = new JComboBox<String>(FREIGHTCAR_TYPES);
    	freightConstraints.gridx = 1;
    	freightConstraints.gridy = 1;
    	panel.add(freightCarTypes, freightConstraints);
    	
    	// Weight Label
    	JLabel weightlabel = new JLabel("Weight");
    	freightConstraints.gridx = 2;
    	freightConstraints.gridy = 0;
    	panel.add(weightlabel, freightConstraints);

    	// Weight Input
    	freightCarWeight = new JTextField(DEFAULT_COLUMNS);
    	freightConstraints.gridx = 2;
    	freightConstraints.gridy = 1;
    	panel.add(freightCarWeight, freightConstraints);
    	
    	// Add Button
    	addFreightCar = new JButton("Add");
    	addFreightCar.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent event) {
    	    	addFreightCarCheck((String) freightCarTypes.getSelectedItem(),freightCarWeight.getText());
    	    }
    	});
    	
    	freightConstraints.gridx = 3;
    	freightConstraints.gridy = 1;
    	panel.add(addFreightCar, freightConstraints);
    	
    	
    }
    
    private boolean addFreightCarCheck(String item, String selectedWeight) {
    	Integer tempWeight;
    	try {
    	    tempWeight = Integer.parseInt(selectedWeight);
    	} catch (Exception expected) {
    	    errorLogger("Invalid Weight \n");
    	    return false;
    	}
    	FreightCar freightCar;
    	
    	String goodsType = "" + item.charAt(0);

    	try {
    		freightCar = new FreightCar(tempWeight, goodsType);
    	    Train.addCarriage(freightCar);
    	} catch (TrainException expected) {
    	    String errorOutput = expected.getMessage().replaceAll(
    		    "^[asgn2Exceptions.TrainException:*Train Exception:]*", "");
    	    errorLogger(errorOutput + "\n");

    	    return false;
    	}

    	trainCanMove();
    	TrainCar newGraphic = new TrainCar(TrainCar.TrainTypes.FREIGHTCAR, freightCar.toString());
    	infoDisplay.add(newGraphic);
    	trainGraphicList.add(newGraphic);
    	infoDisplay.revalidate();
    	return true;
    }

    private void passengerCarSetup(JPanel panel) {
    	panel.setLayout(new GridBagLayout());
    	GridBagConstraints passengerConstraints = new GridBagConstraints();
    	
    	// Capacity Label
    	JLabel capacitylabel = new JLabel("Capacity");
    	passengerConstraints.gridx = 1;
    	passengerConstraints.gridy = 0;
    	panel.add(capacitylabel, passengerConstraints);

    	// Capacity Input
    	numberOfPassengers = new JTextField(DEFAULT_COLUMNS);
    	passengerConstraints.gridx = 1;
    	passengerConstraints.gridy = 1;
    	panel.add(numberOfPassengers, passengerConstraints);  	
    	
    	// Weight Label
    	JLabel weightlabel = new JLabel("Weight");
    	passengerConstraints.gridx = 2;
    	passengerConstraints.gridy = 0;
    	panel.add(weightlabel, passengerConstraints);

    	// Weight Input
    	passengerCarWeight = new JTextField(DEFAULT_COLUMNS);
    	passengerConstraints.gridx = 2;
    	passengerConstraints.gridy = 1;
    	panel.add(passengerCarWeight, passengerConstraints);
    	
    	// Add Button
    	addPassengerCar = new JButton("Add");
    	addPassengerCar.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent event) {
    	    	addPassengerCarCheck(numberOfPassengers.getText(),passengerCarWeight.getText());
    	    }
    	});
    	
    	passengerConstraints.gridx = 3;
    	passengerConstraints.gridy = 1;
    	panel.add(addPassengerCar, passengerConstraints);
    }
    
    private boolean addPassengerCarCheck(String selectedCapacity, String selectedWeight) {
    	Integer tempWeight;
    	Integer tempCapacity;
    	PassengerCar passengerCar;
    	
    	try {
    	    tempWeight = Integer.parseInt(selectedWeight);
    	} catch (Exception expected) {
    	    errorLogger("Invalid Weight \n");
    	    return false;
    	}
    	
    	try {
    	    tempCapacity = Integer.parseInt(selectedCapacity);
    	} catch (Exception expected) {
    	    errorLogger("Invalid Capacity \n");
    	    return false;
    	}
    	

    	try {
    		passengerCar = new PassengerCar(tempWeight, tempCapacity);
    	    Train.addCarriage(passengerCar);
    	} catch (TrainException expected) {
    	    String errorOutput = expected.getMessage().replaceAll(
    		    "^[asgn2Exceptions.TrainException:*Train Exception:]*", "");
    	    errorLogger(errorOutput + "\n");

    	    return false;
    	}
    	
    	totalPassengers.setText("<html>Passengers<br />" + MIN_PASSENGERS + " | " + Train.numberOfSeats().toString() + "</html>");
    	trainCanMove();
    	TrainCar newGraphic = new TrainCar(TrainCar.TrainTypes.PASSENGERCAR, passengerCar.toString());
    	infoDisplay.add(newGraphic);
    	trainGraphicList.add(newGraphic);
    	infoDisplay.revalidate();
    	return true;
    }

    private void removeLastCar() {

    }

    private void addPassengers(String input) {
    	Integer didntBoard = 0;
    	
    	try {
    	    Integer.parseInt(input);
    	} catch (Exception expected) {
    	    errorLogger("Invalid passenger number \n");
    	}
    	
    	try {
			didntBoard = Train.board(Integer.parseInt(input));
		} catch (TrainException expected) {
			String errorOutput = expected.getMessage().replaceAll(
	    		    "^[asgn2Exceptions.TrainException:*Train Exception:]*", "");
	    	    errorLogger(errorOutput + "\n");
		}
    	errorLogger(didntBoard.toString() + " passengers could not board" + "\n");
    	totalPassengers.setText("<html>Passengers<br />" + Train.numberOnBoard() + " | " + Train.numberOfSeats().toString() + "</html>");
    	
    	RollingStock currentCar = Train.firstCarriage();
    	
    	for (int i = 0; i < trainGraphicList.size(); i ++){
    		if (currentCar instanceof PassengerCar){
    			trainGraphicList.remove(i);
    			infoDisplay.remove(i);
    			TrainCar newGraphic = new TrainCar(TrainCar.TrainTypes.PASSENGERCAR, currentCar.toString());
    			infoDisplay.add(newGraphic,i);
    			trainGraphicList.add(i,newGraphic);
    		}
    		currentCar = Train.nextCarriage();
    	}
    	infoDisplay.revalidate();
    	
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