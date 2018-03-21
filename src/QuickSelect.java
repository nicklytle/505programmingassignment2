import java.util.ArrayList;

public class QuickSelect extends Sorter{

	/*
	 * Used a combination of lecture notes on moodle and wikipedia.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> test = new ArrayList<>();
		test.add(10);
		test.add(4);
		test.add(5);
		test.add(8);
		test.add(6);
		test.add(11);
		test.add(26);
		
		int k = (int) Math.floor((test.size()+1)/2);
		
		System.out.println(quickselect(test, 0, test.size() - 1, k));
	}
	
	public static int partition(ArrayList<Integer> list, int left, int right, int pivotIndex) {
		int pivotVal = list.get(pivotIndex);
		list.set(pivotIndex, list.get(right));
		list.set(right, pivotVal);
		int storeIndex = left;
		for(int i = left; i < right; i++) {
			if(list.get(i) < pivotVal) {
				int temp = list.get(storeIndex);
				list.set(storeIndex, list.get(i));
				list.set(i, temp);
				storeIndex++;
			}
		}
		int temp = list.get(right);
		list.set(right, list.get(storeIndex));
		list.set(storeIndex, temp);
		return storeIndex;		
	}
	
	public static int quickselect(ArrayList<Integer> list, int left, int right, int k) {
		if(right - left > 1) {
			int pivotIndex = left + (int) Math.floor(Math.random()*(right - left + 1));
			pivotIndex = partition(list, left, right, pivotIndex);
			if (k == pivotIndex) {
				return list.get(k);
			} else if (k < pivotIndex) {
				return quickselect(list, left, right - 1, k);
			} else {
				return quickselect(list, left + 1, right, k - pivotIndex);
			}
			
		} else {
			return list.get(left);
		}
//		if(left == right) {
//			return list.get(left); 
//		}
//		
//		int pivotIndex = (int)Math.floor((list.size()-1)/2);
//		pivotIndex = partition(list, left, right, pivotIndex);
//		
//		if(k == pivotIndex) {
//			return list.get(k);
//		} else if (k < pivotIndex) {
//			return quickselect(list, left, pivotIndex - 1, k);
//		} else {
//			return quickselect(list, pivotIndex + 1, right, k);
//		}
	}

}
