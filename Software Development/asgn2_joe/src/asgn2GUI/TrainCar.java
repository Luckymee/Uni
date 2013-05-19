package asgn2GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TrainCar extends JPanel {

    // Black Magic UUID
    private static final long serialVersionUID = -7265743984197911846L;

    protected static enum TrainTypes {
	LOCOMOTIVE, PASSENGERCAR, FREIGHTCAR
    };

    protected static enum LocomotiveTypes {
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
    private static Dimension trainSize;
    private static JLabel infoLabel;

    public TrainCar(TrainTypes type, String label, LocomotiveTypes locoType) {
	super();
	trainSize = new Dimension(TRAIN_LENGTH, TRAIN_WIDTH);
	infoLabel = new JLabel(label);

	switch (type) {
	case LOCOMOTIVE:
	    setBackground(TUNGSTEN);
	    infoLabel.setForeground(Color.WHITE);
	    break;
	case FREIGHTCAR:
	    if (locoType == LocomotiveTypes.DANGEROUS_GOODS) {
		setBackground(SAFETY_ORANGE);
		infoLabel.setForeground(Color.WHITE);
	    } else if (locoType == LocomotiveTypes.GENERAL_GOODS) {
		setBackground(FORREST_GREEN);
		infoLabel.setForeground(Color.WHITE);
	    } else {
		setBackground(ICE_BLUE);
		infoLabel.setForeground(Color.WHITE);
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
