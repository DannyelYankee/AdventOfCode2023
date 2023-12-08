package day1.part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Part2 {
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
		LinkedList<NumberInfo> numbers = new LinkedList<>();
		LinkedList<NumberInfo> digitsNumbers = getDigitsValues(linea);
		for (int i = 0; i < linea.length(); i++) {
			char character = linea.charAt(i);
			if (isNumber(character)) {
				NumberInfo info = new NumberInfo();
				info.setValue(Integer.parseInt(character + ""));
				info.setFirstIndex(i);
				numbers.add(info);
			}

		}

		if (numbers.isEmpty() && digitsNumbers.isEmpty()) {
			return 0;
		}
		NumberInfo numbersFirst = null;
		NumberInfo numbersLast = null;
		if (!numbers.isEmpty()) {
			numbersFirst = numbers.getFirst();
			numbersLast = numbers.getLast();
		}
		NumberInfo digitsFirst = null;
		NumberInfo digitsLast = null;
		if (!digitsNumbers.isEmpty()) {
			digitsFirst = digitsNumbers.getFirst();
			digitsLast = digitsNumbers.getLast();
		}

		int firstNum = -1, lastNum = -1;

		if (numbersFirst == null) {
			firstNum = digitsFirst.getValue();
		}
		if (digitsFirst == null) {
			firstNum = numbersFirst.getValue();
		}
		if (numbersFirst != null && digitsFirst != null) {
			if (numbersFirst.getFirstIndex() < digitsFirst.getFirstIndex()) {
				firstNum = numbersFirst.getValue();
			} else {
				firstNum = digitsFirst.getValue();
			}
		}

		if (numbersLast == null) {
			lastNum = digitsLast.getValue();
		}
		if (digitsLast == null) {
			lastNum = numbersLast.getValue();
		}
		if (numbersLast != null && digitsLast != null) {
			if (numbersLast.getFirstIndex() > digitsLast.getFirstIndex()) {
				lastNum = numbersLast.getValue();
			} else {
				lastNum = digitsLast.getValue();
			}
		}

		int rdo = Integer.parseInt(firstNum + "" + lastNum);
		return rdo;
	}

	private static LinkedList<NumberInfo> getDigitsValues(String linea) {
		HashMap<String, Integer> digits = new HashMap<>();
		digits.put("one", 1);
		digits.put("two", 2);
		digits.put("three", 3);
		digits.put("four", 4);
		digits.put("five", 5);
		digits.put("six", 6);
		digits.put("seven", 7);
		digits.put("eight", 8);
		digits.put("nine", 9);

		LinkedList<NumberInfo> rdo = new LinkedList<>();

		for (String key : digits.keySet()) {
			int firstIndex = linea.indexOf(key);
			int lastIndex = linea.lastIndexOf(key);
			if (firstIndex > -1 && lastIndex > -1) {
				NumberInfo newNumber = new NumberInfo();
				newNumber.setFirstIndex(firstIndex);
				newNumber.setLastIndex(lastIndex);
				newNumber.setValue(digits.get(key));
				rdo.add(newNumber);
			}
		}
		sortByFirstIndex(rdo);
		return rdo;
	}

	private static void sortByFirstIndex(LinkedList<NumberInfo> list) {
		int n = list.size();
		boolean swapped;

		for (int i = 0; i < n - 1; i++) {
			swapped = false;

			for (int j = 0; j < n - i - 1; j++) {
				if (list.get(j).getFirstIndex() > list.get(j + 1).getFirstIndex()) {

					NumberInfo temp = list.get(j);
					list.set(j, list.get(j + 1));
					list.set(j + 1, temp);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}
		}

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
