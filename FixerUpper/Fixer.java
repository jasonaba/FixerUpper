package FixerUpper;

import java.util.ArrayList;
import java.util.Stack;

public class Fixer {
	ArrayList<Character> postfix, prefix;
	Stack<Character> post_stack, pre_stack;
	
	public Fixer(String infix) {
		post_stack = new Stack<Character>();
		pre_stack = new Stack<Character>();
		for(int i = 0; i<infix.length(); i++) {
			postfix.set(i, infix.charAt(i));
			prefix.set(i, infix.charAt(i));
		}
		evaluatePost(true);
		evaluatePre(true);
	}
	
	/**
	 * 
	 * @param showDemo (if the process will be output in the console)
	 * @return The solved value
	 */
	public int evaluatePost(boolean showDemo) {
		Stack<Character> temp_op = new Stack<Character>();
		String operators = "(*/+-";
		for(int i = 0; i<postfix.size(); i++) {
			if(operators.indexOf(postfix.get(i))>-1) {
				if(postfix.get(i)=='(') {//Add this to the stack
					post_stack.push(postfix.get(i));
				}
					
			}
		}
	}
	/**
	 * 
	 * @param showDemo (if the process will be output in the console)
	 * @return The solved value
	 */
	public int evaluatePre(boolean showDemo) {
		
	}
}
