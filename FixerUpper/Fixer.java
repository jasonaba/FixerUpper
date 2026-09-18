package FixerUpper;

import java.util.ArrayList;
import java.util.Stack;

public class Fixer {
	ArrayList<Character> postfix, prefix;

	// initialize this Fixer object and then convert it to postfix
	public Fixer(String infix) {
		Stack<Character> post_stack;
		Stack<String> pre_stack;
		postfix = new ArrayList<Character>();
		prefix = new ArrayList<Character>();
		post_stack = new Stack<Character>();
		pre_stack = new Stack<String>();
		for (char c : infix.toCharArray()) {

			if (isOperand(c)) {//add the numbers to the list
				postfix.add(c);
			}
			
			else if (c == '(') {//add the open-parenthesis to the stack 
				post_stack.push(c);

			}

			else if (isOperator(c)) {
				while (!post_stack.isEmpty() && isOperator(post_stack.peek())
						&& getOperatorValue(c) <= getOperatorValue(post_stack.peek())) {//if there is an operator of higher precedence on the stack already
					postfix.add(post_stack.pop());//add that higher precedence operator to the expression
				}

				post_stack.push(c);//add the operator to the stack
			}

			
			else if (c == ')') {
				while (!post_stack.isEmpty() && post_stack.peek() != '(') {//while we haven't found an open-parenthesis

					postfix.add(post_stack.pop());//add the operators meant to be inside that parenthesis combo
				}
				post_stack.pop();
			}
		}

		while (!post_stack.isEmpty()) {//add the rest of the operators on the stack to the expression
			postfix.add(post_stack.pop());
		}
		System.out.println("Post Stack");
		System.out.println(post_stack);
		System.out.println("ArrayList");
		System.out.println(postfix);

		prefix = postfix;
		
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
	//public int evaluatePost(boolean showDemo) {

	//}

	/**
	 * 
	 * @param showDemo (if the process will be output in the console)
	 * @return The solved value
	 */
	//public int evaluatePre(boolean showDemo) {

	//}

	private boolean isOperand(char c) {
		String operands = "0123456789";
		if (operands.indexOf(c) > -1)
			return true;
		return false;
	}

	private boolean isOperator(char c) {
		String operators = "+-*/^";
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