/**
 * 
 */
package myAnswers;

import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author Connor
 *
 */
public class Decoder {
	
	static Scanner reader = new Scanner(System.in);
	
	final static int[] message = {82, 96, 103, 103, 27, 95, 106, 105, 96, 28};
	final static int KEY = 5;
	
	static List<Integer> myMessage;
	static int myKey;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		decode(message, KEY);
		encode();
		decode(myMessage, myKey);

	}
	
	static void decode(int[] myMessage,int myKey)
	{
		for(int i : myMessage)
		{
			int decoded = (i + myKey);
			char convertInt = (char) decoded;
			System.out.print(convertInt);
		}
		System.out.println();
	}
	
	static void decode(List<Integer> myMessage,int myKey)
	{
		for(int i : myMessage)
		{
			int decoded = (i + myKey);
			char convertInt = (char) decoded;
			System.out.print(convertInt);
		}
		System.out.println();
	}
	
	static void encode()
	{
		System.out.print("Enter message to be encode: ");
		String inputMessage = reader.nextLine();
		System.out.println("Enter encoder key:");
		myKey = reader.nextInt();
		List<Integer> encodeArray = new ArrayList<Integer>();

		for (int i = 0; i < inputMessage.length(); i++)
		{
			char convertString = inputMessage.charAt(i);
			int encode = (int) (convertString - myKey);
			
			encodeArray.add(encode);
		}
		myMessage = encodeArray;
	}
}
