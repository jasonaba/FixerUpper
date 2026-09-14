package Dishwasher;

import java.util.Iterator;

public class Plates implements Iterable<Integer> {
	private int[] plates;
	private int size;
	
	public Plates() {
		plates = new int[10];
		size = 0;
	}
	
	
	/**
	 * 
	 * @param plate
	 * @return if the stack capacity was changed
	 */
	public boolean push(int plate) {
		if(size<plates.length) {
			plates[size++] = plate;//increase size after you make that element "plate"
			return false;
		}
		int[] temp = new int[plates.length * 2];
		for(int i = 0; i<plates.length; i++) {
			temp[i] = plates[i];
		}
		//return push(plate);
		plates = temp;
		plates[size++] = plate;//increase size after you make that element "plate"
		return true;
	}
	
	public int pop() {
		/* int plate = plates[size-1];
		 * plates[size-1]=0; user won't be able to reach the plate being removed since peek looks at plate at size  
		 * return plate
		 */
		/*
		if(size>0)
			return plates[--size];
		return -1;
		*/
		return size>0 ? plates[--size] : -1; //choosing between returning plates[--size] if true and -1 if false
	}
	
	public int peek() {
		return size>0 ? plates[size-1] : -1;
	}


	//to be able to use the plate iterator
	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return new PlateIterator();
	}
	private class PlateIterator implements Iterator<Integer>{
		int index = size-1;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index>=0;
		}

		@Override
		public Integer next() {
			// TODO Auto-generated method stub
			return plates[index--];
		}
		
	}
}
