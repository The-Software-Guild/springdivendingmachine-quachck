package com.wileyedge.view;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class UserIOConsoleImpl implements UserIO {
	private Scanner scanner = new Scanner(System.in);

	public void print(String message) {
		System.out.println(message);

	}

	public String readString(String prompt) {
		print(prompt);
		return scanner.nextLine();
	}

	public int readInt(String prompt) {
		print(prompt);
		try {
			return Integer.parseInt(scanner.nextLine());
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	public int readInt(String prompt, int min, int max) {
		int value;
		do {
			value = readInt(prompt);
		} while (value < min || value > max);
		return value;
	}

}
