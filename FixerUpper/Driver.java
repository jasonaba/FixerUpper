package FixerUpper;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner = new Scanner(System.in);
		System.out.println("Print your expression here:");
		String exp = scanner.nextLine();
		Fixer user = new Fixer(exp);
		if (user.isValid()) {
			System.out.println("Would you like to see the evaluations (Y/N)?");

			String response = scanner.nextLine();
			if (response.equals("Y")) {
				System.out.println("Answer: ");
				user.evaluatePost(true);
				System.out.println(user.evaluatePre(true));
			} else {
				System.out.println("Answer: ");
				user.evaluatePost(false);
				System.out.println(user.evaluatePre(false));
			}
		}

		scanner.close();
	}
}
