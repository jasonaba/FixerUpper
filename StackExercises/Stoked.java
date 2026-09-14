package StackExercises;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

public class Stoked {
	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < 5; i++)
			stack.push((int) (Math.random() * 20));
		System.out.println(stack);
		System.out.println(stack.peek());

		String expression = "(x+[4+y]*{z-2}/(4+5))";
		String expression2 = ")(x+2)(";

		System.out.println(expChecker(expression2));
		
		int[] beatMe = {2,3,10,11,1,3,2,7};
		//should be 10, 10, 11, nothing, 7, 7, none
		
		System.out.println(Arrays.toString(getNexts(beatMe)));
		
		for(Integer i : stack) {
			System.out.println(i);
			//to go through the stack yourself (without having to iterate through them all, since there isn't indices)
			Iterator<Integer> it = stack.iterator();
			System.out.println(it.next());//it gives you the element then goes to the next one
		}
		
	}
	
	public static int[] getNexts(int[] arr) {
		int[] output = new int[arr.length];
		Stack<Integer> helper = new Stack<Integer>();
		
		for (int i = 0; i<arr.length; i++) {
			while(!helper.isEmpty()&&arr[i]>arr[helper.peek()]) {
				output[helper.pop()] = arr[i];
			}
			helper.push(i);
		}
		while(!helper.isEmpty()) {
			output[helper.pop()] = Integer.MIN_VALUE;
		}
		return output;
	}

	public static boolean expChecker(String arg) {
		String open = "([{";
		String close = ")]}";
		Stack<Character> s = new Stack<Character>();
		for (char c : arg.toCharArray()) {
			if(open.indexOf(c)>-1)
				s.push(c);
			if(close.indexOf(c)>-1) {
				if(!s.isEmpty()&&close.indexOf(c)==open.indexOf(s.peek()))
					s.pop();
				else
					return false;
			}
		}
		return s.isEmpty();
	}
}
