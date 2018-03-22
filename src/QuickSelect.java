import java.util.ArrayList;

public class QuickSelect extends Sorter{

	/*
	 * Used a combination of lecture notes on moodle and wikipedia.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		integerList = new ArrayList<>();
		integerList.add(10);
		integerList.add(4);
		integerList.add(5);
		integerList.add(8);
		integerList.add(6);
//		integerList.add(11);
//		integerList.add(26);
		
		int mid = (int) Math.floor((integerList.size()-1)/2);
		startTime = System.nanoTime();
		int k = quickselect(integerList, 0, integerList.size() - 1, mid);
		endTime = System.nanoTime();
		runtime = endTime - startTime;
		write(k);
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
			ArrayList<Integer> sorted = InsertionSort.nonRecursiveInsertionSort(list);
			int mid = (int) Math.floor((sorted.size() - 1)/2);
			return sorted.get(mid);
		}
	}

}
