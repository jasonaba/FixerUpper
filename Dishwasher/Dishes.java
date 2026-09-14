package Dishwasher;

public class Dishes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Plates Ramsay = new Plates();
		Plates Remy = new Plates();
		
		for(int i = 0; i<15; i++) {
			Ramsay.push((int) (Math.random()*20)+1);
			Remy.push(15-i);
			System.out.println(Ramsay.peek());
		}
		System.out.println("Commence washing");
		
		while(Ramsay.peek()>0) {
			System.out.println(Ramsay.pop());
		}
		for(Integer i : Remy) {//to see how the plate iterator works, go to Plates (where we made the private class PlateIterator)
			System.out.println(i);//this automatically checks if the iterator hasNext, and goes next as it iterates through
			
		}
	}

}
 