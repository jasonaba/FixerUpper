package FixerUpper;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String expression1 = "(3*4+(5+6*7))/(8-9)";
		String expression2 = "3+(4*2)-(6/3)"; 
		System.out.println(expression2);
		Fixer Bob = new Fixer(expression2);
	}
}
