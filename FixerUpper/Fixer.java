package FixerUpper;

import java.util.ArrayList;
import java.util.Stack;

public class Fixer {
	private ArrayList<Character> postfix, prefix;
	private boolean isValid;

	// initialize this Fixer object and then convert it
	public Fixer(String infix) {
		postfix = new ArrayList<Character>();
		prefix = new ArrayList<Character>();
		isValid = true;// will stay true unless proven otherwise

		// Before conversion, check for errors in expression
		if (infix == null || infix.trim().isEmpty()) {
			System.out.println("Error! Empty Expression!");
			isValid = false;
		}
		if (isValid) {// if the expression is valid
			convert(infix);

		}
	}

	public ArrayList<Character> getPostfix() {
		return postfix;
	}

	public void setPostfix(ArrayList<Character> postfix) {
		this.postfix = postfix;
	}

	public ArrayList<Character> getPrefix() {
		return prefix;
	}

	public void setPrefix(ArrayList<Character> prefix) {
		this.prefix = prefix;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	@Override
	public String toString() {

		String output = "Postfix:\n";
		output += postfix + "\n";
		output += "Prefix:\n";
		output += prefix + "\n";
		output += "The Expression is Valid: " + isValid;

		return output;
	}

	/**
	 * Converts the infix expression to postfix & prefix
	 * 
	 * @param infix - the expression given
	 */
	private void convert(String infix) {
		// Infix to Postfix
		Stack<Character> post_stack = new Stack<Character>();

		// Helper variables to check expression validity
		boolean expectingOperand = true;
		int parentheses = 0;

		for (char c : infix.toCharArray()) {

			if (isOperand(c)) {// add the numbers to the list

				if (!expectingOperand) {
					System.out.println("Error! Too many operands in a row!");
					isValid = false;
					return;
				}

				postfix.add(c);
				expectingOperand = false;
			}

			else if (c == '(') {// add the open-parenthesis to the stack

				if (!expectingOperand) {// if an operand came right before this open parenthesis
					System.out.println("Error! Operator expected before '('!");
					isValid = false;
					return;
				}

				post_stack.push(c);
				parentheses++;
				expectingOperand = true;
			}

			else if (isOperator(c)) {
				if (expectingOperand) {
					System.out.println("Error! Operand expected!");
					isValid = false;
					return;
				}

				while (!post_stack.isEmpty() && isOperator(post_stack.peek())
						&& (getOperatorValue(c) < getOperatorValue(post_stack.peek())
						|| (getOperatorValue(c) == getOperatorValue(post_stack.peek()) && c != '^'))) {
					// if there is an operator of equal/higher precedence on the stack already
					//if there is two '^', this rule does not apply, so we can have exponents with exponents
					//ChatGPT said it is because it is right associative and should stay on the stack
					postfix.add(post_stack.pop());// add that equal/higher precedence operator to the expression
				}

				post_stack.push(c);// add the operator to the stack
				expectingOperand = true;
			}

			else if (c == ')') {// numbers come before ')', and there must be a '(' somewhere

				if (expectingOperand) {
					System.out.println("Error! Expecting operand before ')'!");
					isValid = false;
					return;
				}

				if (parentheses == 0) {
					System.out.println("Error! Unbalanced closed parenthesis!");
					isValid = false;
					return;
				}

				while (!post_stack.isEmpty() && post_stack.peek() != '(') {// while we haven't found an open-parenthesis

					postfix.add(post_stack.pop());// add the operators meant to be inside that parenthesis combo
				}

				post_stack.pop();
				parentheses--;
				expectingOperand = false;
			}
		}

		if (expectingOperand) {// if an expression ends while still expecting an operand
			System.out.println("Error! Expression cannot end with operator!");
			isValid = false;
			return;
		}

		if (parentheses != 0) {// if there is an extra open parentheses
			System.out.println("Error! Unbalanced open parenthesis!");
			isValid = false;
			return;
		}

		while (!post_stack.isEmpty()) {// add the rest of the operators on the stack to the expression
			postfix.add(post_stack.pop());
		}

		// Will continue converting if the expression is valid

		// Postfix to Prefix
		Stack<String> pre_stack = new Stack<String>();// Stack allocated for Prefix use

		for (int i = 0; i < postfix.size(); i++) {
			char c = postfix.get(i);// for each character in postfix

			if (isOperand(c)) {// add numbers to the list
				pre_stack.push("" + c);
			}

			// if it is an operator, add the mini prefix expression to the stack
			else if (isOperator(c)) {
				String right = pre_stack.pop();
				String left = pre_stack.pop();
				pre_stack.push(c + left + right);
			}
		}

		String result = pre_stack.pop();// the finished result

		for (int i = 0; i < result.length(); i++) {// add the finished result to the prefix ArrayList
			prefix.add(result.charAt(i));
		}
		System.out.println(this);

	}

	/**
	 * Evaluates the value using the Postfix representation
	 * 
	 * @param showDemo (if the process will be output in the console)
	 * @return The solved value
	 */
	public int evaluatePost(boolean showDemo) {
		Stack<Integer> s = new Stack<Integer>();// temp stack
		if (showDemo)
			System.out.println("Postfix Evaluation:");
		for (char c : postfix) {
			if (isOperand(c)) {// if it's a number, add it to the stack
				s.push(c - '0');
				if (showDemo)
					System.out.println(s);
			} else if (isOperator(c)) {// if it is an operator, then operate on the 2 most recent numbers in the stack
				int right = s.pop();// for POSTfix, the first number on the stack is the right number
				if (showDemo)
					System.out.println(s);
				int left = s.pop();// for POSTfix, the second number on the stack is the left number
				if (showDemo)
					System.out.println(s);
				int result = 0;

				// apply the operation to the extracted numbers
				if (c == '+')
					result = left + right;
				if (c == '-')
					result = left - right;
				if (c == '*')
					result = left * right;
				if (c == '/') {
					if (right == 0) {
						System.out.println("Error! Cannot divide by zero!");
						isValid = false;
						return -9999999;
					}
					result = left / right;
				}
				if (c == '^')
					result = (int) Math.pow(left, right);
				s.push(result);// add the number to the stack
				if (showDemo)
					System.out.println(s);
			}
		}
		return s.pop();// assuming no errors, the final number on the stack will be the final answer
	}

	/**
	 * Evaluates the Prefix
	 * 
	 * @param showDemo (if the process will be output in the console)
	 * @return The solved value
	 */
	public int evaluatePre(boolean showDemo) {
		Stack<Integer> s = new Stack<Integer>();
		if (showDemo)
			System.out.println("Prefix Evaluation:");
		for (int i = prefix.size() - 1; i >= 0; i--) {// look through prefix from right to left
			if (isOperand(prefix.get(i))) {// if it's a number, add it to the stack
				s.push(prefix.get(i) - '0');
				if (showDemo)
					System.out.println(s);
			} else if (isOperator(prefix.get(i))) {// if it is an operator, then operate on the 2 most recent numbers in
													// the stack
				//a bit different due to opposite scanning
				int left = s.pop();// for PREfix, the first number on the stack is the left number
				if (showDemo)
					System.out.println(s);
				int right = s.pop();// for PREfix, the second number on the stack is the right number
				if (showDemo)
					System.out.println(s);
				int result = 0;

				// apply the operations to the extracted numbers
				if (prefix.get(i) == '+')
					result = left + right;
				if (prefix.get(i) == '-')
					result = left - right;
				if (prefix.get(i) == '*')
					result = left * right;
				if (prefix.get(i) == '/') {
					if (right == 0) {
						System.out.println("Error! Cannot divide by zero!");
						isValid = false;
						return -9999999;
					}
					result = left / right;
				}
				if (prefix.get(i) == '^')
					result = (int) Math.pow(left, right);
				s.push(result);// add the number to the stack
				if (showDemo)
					System.out.println(s);
			}
		}
		return s.pop();// assuming no errors, the final number on the stack will be the final answer
	}

	/**
	 * Checks if character is a number
	 * 
	 * @param c
	 * @return if character is a number or not
	 */
	private boolean isOperand(char c) {
		String operands = "0123456789";
		if (operands.indexOf(c) > -1)
			return true;
		return false;
	}

	/**
	 * Checks if a character is an operator
	 * 
	 * @param c
	 * @return if character is an operator or not
	 */
	private boolean isOperator(char c) {
		String operators = "+-*/^";
		if (operators.indexOf(c) > -1)
			return true;
		return false;
	}

	/**
	 * Gives a way to compare operator precedence
	 * 
	 * @param c - the operator in question
	 * @return precedence value of the operator
	 */
	private int getOperatorValue(char c) {
		if (c == '-' || c == '+')
			return 1;
		if (c == '/' || c == '*')
			return 2;
		if (c == '^')
			return 3;
		return 0;
	}

}
