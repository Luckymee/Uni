package myAnswers;

public class CardBuilder {

    /**
     * @param args
     */
    public static void main(String[] args) {
	// TODO Auto-generated method stub
	int two = 0;
	int three = 1;
	int four = 2;
	int five = 3;
	int six = 4;
	int seven = 5;
	int eight = 6;
	int nine = 7;
	int ten = 8;
	int jack = 9;
	int queen = 10;
	int king = 11;
	int ace = 12;

	int clubs = 0;
	int diamonds = 1;
	int hearts = 2;
	int spades = 3;

	int[] hand1 = { three, clubs, four, clubs, four, clubs, five, clubs,
		six, spades };
	int[] hand2 = { two, clubs, four, clubs, four, clubs, five, clubs,
		seven, spades };
	int[] hand3 = { ten, hearts, ten, hearts, eight, spades, eight, spades,
		three, clubs };
	int[] hand4 = { ten, hearts, ten, hearts, six, spades, six, spades,
		ace, clubs };
	int[] hand5 = { queen, diamonds, queen, diamonds, nine, spades, nine,
		spades, two, spades };
	int[] hand6 = { jack, diamonds, jack, diamonds, nine, spades, nine,
		spades, two, spades };
	int[] hand7 = { ten, hearts, ten, hearts, eight, spades, eight, spades,
		ace, clubs };
	int[] hand8 = { ten, hearts, ten, hearts, six, spades, six, spades,
		ace, clubs };
	int[] hand9 = { five, spades, five, spades, four, clubs, four, clubs,
		ace, diamonds };
	int[] hand10 = { seven, spades, seven, spades, four, clubs, four,
		clubs, two, diamonds };
	int[][] hands = { hand1, hand2, hand3, hand4, hand5, hand6, hand7,
		hand8, hand9, hand10 };

	String output = "";

	for (int i = 0; i < 10; i += 2) {
	    for (int j = 0; j < 10; j++) {
		output += hands[j][i] + " ";
		output += hands[j][i + 1] + " ";
	    }
	}

	System.out.println(output);
    }

}
