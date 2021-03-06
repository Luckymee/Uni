package asgn2GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Graphical representation of Train cars.
 * 
 * @author Connor Livsey - n8510873
 */
public class TrainCar extends JPanel {

	// Black Magic UUID
	private static final long serialVersionUID = -7265743984197911846L;

	/**
	 * Rolling stock type.
	 */
	protected static enum TrainTypes {
		LOCOMOTIVE, PASSENGERCAR, FREIGHTCAR
	};

	/**
	 * Freight Cargo type.
	 */
	protected static enum FreightTypes {
		NONE, GENERAL_GOODS, DANGEROUS_GOODS, REFRIGERATED_GOODS
	}

	// Custom Colours
	private static final Color TUNGSTEN = new Color(51, 51, 51);
	private static final Color CANDYAPPLERED = new Color(153, 10, 1);
	private static final Color SAFETY_ORANGE = new Color(232, 118, 0);
	private static final Color FORREST_GREEN = new Color(38, 131, 64);
	private static final Color ICE_BLUE = new Color(0, 204, 211);

	// Dimensions
	private static final int TRAIN_LENGTH = 120;
	private static final int TRAIN_WIDTH = 27;

	// Swing Objects
	private Dimension trainSize;
	private JLabel infoLabel;

	/**
	 * Constructor for TrainCar.
	 * 
	 * @param type
	 *            TrainTypes: Enum of potential Rolling Stock.
	 * @param label
	 *            String: Textual representation of Rolling Stock.
	 * @param freightType
	 *            FreightTypes: Enum of potential freight cargo.
	 */
	public TrainCar(TrainTypes type, String label, FreightTypes freightType) {

		super();
		trainSize = new Dimension(TRAIN_LENGTH, TRAIN_WIDTH);
		infoLabel = new JLabel(label);

		switch (type) { // Select Rolling stock.
			case LOCOMOTIVE:
				setBackground(TUNGSTEN);
				infoLabel.setForeground(Color.WHITE);
				break;
			case FREIGHTCAR:
				switch (freightType) { // Select Freight type.
					case GENERAL_GOODS:
						setBackground(FORREST_GREEN);
						infoLabel.setForeground(Color.WHITE);
						break;
					case REFRIGERATED_GOODS:
						setBackground(ICE_BLUE);
						infoLabel.setForeground(Color.WHITE);
						break;
					case DANGEROUS_GOODS:
						setBackground(SAFETY_ORANGE);
						infoLabel.setForeground(Color.WHITE);
						break;
					default:
						break;
				}
				break;
			case PASSENGERCAR:
				setBackground(CANDYAPPLERED);
				infoLabel.setForeground(Color.WHITE);
				break;

		}

		infoLabel.setVerticalAlignment(SwingConstants.CENTER);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		setPreferredSize(trainSize);
		add(infoLabel);
	}
}
