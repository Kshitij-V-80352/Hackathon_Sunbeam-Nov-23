package com.sunbeam.main;

import java.util.Scanner;

public class SignInMenu {
		public static ESignInMenu signInMenu(Scanner sc) {
			int input;
			ESignInMenu choice = ESignInMenu.EXIT;

			while (true) {
				System.out.println("\nWhat do you wish to do?");
				System.out.println("0.EXIT  1.Sign Up  2.Sign in");
				System.out.println("=========================================");
				System.out.print("Your Choice :: ");
				input = sc.nextInt();
				if (input >= 0 && input < 3) {
					choice = ESignInMenu.values()[input];
					System.out.println("=========================================");
					break;
				}
				choice = ESignInMenu.values()[3];
			}

			return choice;
		}
}
