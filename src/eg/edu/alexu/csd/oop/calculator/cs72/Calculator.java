package eg.edu.alexu.csd.oop.calculator.cs72;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**

 * @author HP
 *
 */
public class Calculator implements eg.edu.alexu.csd.oop.calculator.Calculator {
	/**
	 * number of saved operations.
	 */
    private static final int Z4 = 4;
    /**
     * the two operators of the mathematical expression.
     */
    private double operator1, operator2;
    /**
     * the operand of the mathematical expression.
     */
    private String operand;
    /**
     * the history of the expressions.
     */
	private List<String> savedOperations = new ArrayList<>();
	/**
	 * the index of the current expression.
	 */
    private int pointer;

	/* Take input from user */
    @Override
	public final void input(final String s) {
		if (savedOperations.size() > Z4) {
			savedOperations.remove(0);
		}
		savedOperations.add(s);
		pointer = savedOperations.size() - 1;

	}

	/*
	 * Return the result of the current operations.
	 *  or throws a runtime exception
	 */
	@Override
	public final String getResult() {
		String[] strings;
		String[] operands = {"\\+", "/", "-", "\\*"};

		for (int i = 0; i < operands.length; i++) {

			strings = savedOperations.get(pointer)
					.split(operands[i]);
			if (strings.length == 2) {
				operator1 = Double.valueOf(strings[0].trim());
				operator2 = Double.valueOf(strings[1].trim());
				operand = operands[i];
				break;
			}

		}

		String answer;
		switch (operand) {
		case "\\+":
			answer = String.valueOf(operator1 + operator2);
			break;
		case "-":
			answer = String.valueOf(operator1 - operator2);

			break;
		case "\\*":
			answer = String.valueOf(operator1 * operator2);

			break;
		case "/":
			answer = String.valueOf(operator1 / operator2);

			break;
		default:
			return null;

		}

		return answer;
	}

	/* return the current formula */
	@Override
	public final String current() {
		if (pointer < 0 || pointer > savedOperations.size() - 1) {
			return null;
		}
		return savedOperations.get(pointer);
	}

	/*
	 * return the last operation in String format.
	 * or Null if no more history
	 * available
	 */
	@Override
	public final String prev() {
		String prev;
		pointer--;
		prev = current();
		if (prev == null) {
			pointer++;
		}
		return prev;

	}

	/*
	 * return the next operation in String format.
	 *  or Null if no more history
	 * available
	 */
	@Override
	public final String next() {
		String prev;
		pointer++;
		prev = current();
		if (prev == null) {
			pointer--;
		}
		return prev;
	}

	/* Save in file the last 5 done Operations */
	@Override
	public final void save() {

		try {
			PrintWriter writer = new PrintWriter(new
					File("saved.txt"));
			writer.println(pointer);
			for (int i = 0; i < savedOperations.size(); i++) {
				writer.println(savedOperations.get(i));
			}
			System.out.println("saved");
			writer.close();
		} catch (FileNotFoundException e) {
		System.out.println("no saving ");
		}
	}

	/* Load from file the saved operations */
	@Override
	public final void load() {
		Scanner scan;
		List<String> operations = new ArrayList<>();
		try {
			scan = new Scanner(new File("saved.txt"));
			pointer = Integer.valueOf(scan.nextLine());
			while (scan.hasNextLine()) {
				operations.add(scan.nextLine());
			}
			savedOperations = operations;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
