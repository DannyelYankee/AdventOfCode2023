package day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Part1 {

	public static void main(String[] args) {
		String inputPath = "resources/inputs/day1.txt";

		try {
			getSum(inputPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void getSum(String ruta) throws IOException {
		int sum = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
			String linea;

			while ((linea = br.readLine()) != null) {
				sum += getCalibrationValue(linea);
			}
		}
		System.out.println("Result: " + sum);
	}

	private static int getCalibrationValue(String linea) {
		LinkedList<Integer> numbers = new LinkedList<>();
		for (int i = 0; i < linea.length(); i++) {
			char character = linea.charAt(i);
			if (isNumber(character)) {
				numbers.add(Integer.parseInt(character + ""));
			}
		}
		if (numbers.isEmpty()) {
			return 0;
		}
		int rdo = Integer.parseInt(numbers.getFirst() + "" + numbers.getLast());
		return rdo;
	}

	private static boolean isNumber(char character) {
		try {
			Integer.parseInt(character + "");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
