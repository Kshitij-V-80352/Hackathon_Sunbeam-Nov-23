package com.sunbeam.main;

import java.util.Scanner;

public class MainMenu {

	public static EMainMenu mainMenu(Scanner sc) {
		int input;
		EMainMenu choice1 = EMainMenu.LOG_OUT;

		while (true) {
			System.out.println("\nWhat do you wish to do?");
			System.out.println("0.Log Out  1.Edit Profile  2.Change Password  3.Write a Review  4.Edit Review");
			System.out.println("5.Display all Movies  6.Display all Reviews  7.Display My Reviews");
			System.out.println("8.Display Reviews Shared with me 9.Share a Review 10.Delete a review");
			System.out.println("=========================================");
			System.out.print("Your Choice :: ");
			input = sc.nextInt();
			if (input >= 0 && input < 11) {
				choice1 = EMainMenu.values()[input];
				System.out.println("=========================================");
				break;
			}
			choice1 = EMainMenu.values()[11];
		}

		return choice1;
	}
}
