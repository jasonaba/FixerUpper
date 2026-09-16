package FixerUpper;

import java.util.ArrayList;
import java.util.Stack;

public class Fixer {
	ArrayList<Character> postfix, prefix;

	// initialize this and then convert it to postfix
	public Fixer(String infix) {
		Stack<Character> post_stack, pre_stack;
		postfix = new ArrayList<Character>();
		prefix = new ArrayList<Character>();
		post_stack = new Stack<Character>();
		boolean closed_parenthesis_found = false;
		// pre_stack = new Stack<Character>();
		do {
			for (char c : infix.toCharArray()) {

				if (c == '(') {
					post_stack.push(c);
					System.out.println("Stack");
					System.out.println(post_stack);
					System.out.println("ArrayList");
					System.out.println(postfix);
				}

				if (isOperator(c)) {
						while (!post_stack.isEmpty()&&isOperator(post_stack.peek())) {
							if (getOperatorValue(c) < getOperatorValue(post_stack.peek())) {
								postfix.add(post_stack.pop());
							} else {
								break;
							}
						}
					
					post_stack.push(c);
					System.out.println("Stack");
					System.out.println(post_stack);
					System.out.println("ArrayList");
					System.out.println(postfix);
				}

				if (isOperand(c)) {
					postfix.add(c);
					System.out.println("Stack");
					System.out.println(post_stack);
					System.out.println("ArrayList");
					System.out.println(postfix);
				}
				if (c == ')') {
					closed_parenthesis_found = true;
				}
				if (!post_stack.isEmpty()&&closed_parenthesis_found) {
					if (isOperator(c))
						postfix.add(post_stack.pop());
					System.out.println("Stack");
					System.out.println(post_stack);
					System.out.println("ArrayList");
					System.out.println(postfix);
					// else return error message?
				}
			}
		} while (!postfix.isEmpty());

		if (!post_stack.isEmpty()) {
			System.out.println("error within expression");
		}

		// evaluatePost(true);
		// evaluatePre(true);
	}

	/**
	 * Evaluates the value using the postfix (evaluate first, and if there is an
	 * error, then don't allow the user to evaluate any longer)
	 * 
	 * @param showDemo (if the process will be output in the console)
	 * @return The solved value
	 */
	public int evaluatePost(boolean showDemo) {

	}

	/**
	 * 
	 * @param showDemo (if the process will be output in the console)
	 * @return The solved value
	 */
	public int evaluatePre(boolean showDemo) {

	}

	private boolean isOperand(char c) {
		String operands = "0123456789";
		if (operands.indexOf(c) > -1)
			return true;
		return false;
	}

	private boolean isOperator(char c) {
		String operators = "+-*/";
		if (operators.indexOf(c) > -1)
			return true;
		return false;
	}

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