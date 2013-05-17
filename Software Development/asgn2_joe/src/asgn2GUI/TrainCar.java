package asgn2GUI;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TrainCar extends JPanel {

    // Black Magic UUID
    private static final long serialVersionUID = -7265743984197911846L;

    protected static enum TrainTypes {
	LOCOMOTIVE, PASSENGERCAR, FREIGHTCAR
    };

    // Custom Colours
    private static final Color TUNGSTEN = new Color(51, 51, 51);
    private static final Color CANDYAPPLERED = new Color(153, 10, 1);
    private static final Color SAFETY_ORANGE = new Color(232, 118, 0);

    // Dimensions
    private static final int TRAIN_LENGTH = 100;
    private static final int TRAIN_WIDTH = 27;

    // Swing Objects
    private static Dimension trainSize;
    private static JLabel infoLabel;

    public TrainCar(TrainTypes type, String label) {
	super();
	trainSize = new Dimension(TRAIN_LENGTH, TRAIN_WIDTH);
	infoLabel = new JLabel(label);

	switch (type) {
	case LOCOMOTIVE:
	    setBackground(TUNGSTEN);
	    infoLabel.setForeground(Color.WHITE);
	    break;
	case FREIGHTCAR:
	    setBackground(SAFETY_ORANGE);
	    infoLabel.setForeground(Color.WHITE);
	    break;
	case PASSENGERCAR:
	    setBackground(CANDYAPPLERED);
	    infoLabel.setForeground(Color.WHITE);
	    break;

	}

	setPreferredSize(trainSize);
	add(infoLabel);

    }
}
