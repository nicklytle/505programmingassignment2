import java.util.ArrayList;

/**
 * @author bjanos
 * 
 *         A class that implements the recursive version of the quickselect.
 *         This algorithm draws from quicksort and uses partitioning to find the
 *         kth largest element, where k is the median value of the array. The
 *         partitioning occurs in place and is drawn from pseudocode found in
 *         Introduction to Algorithms 3rd e by Cormen. The quickselect algorithm
 *         is largely based off of notes from CSC 505 Moodle site, however
 *         modifications were made to accommodate the program directions.
 * 
 *
 */
public class QuickSelect extends Sorter {

	/**
	 * Reads in the input, tracks the runtime, and writes the results.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		integerList = new ArrayList<>();
//		integerList.add(10);
//		integerList.add(4);
//		integerList.add(5);
//		integerList.add(8);
//		integerList.add(6);
//		integerList.add(11);
//		integerList.add(26);
//		integerList.add(32);
//		integerList.add(2);
//		integerList.add(9);
//		integerList.add(22);

		read();
		int mid = (int) Math.floor((integerList.size() - 1) / 2);
		startTime = System.nanoTime();
		int k = quickselect(integerList, 0, integerList.size() - 1, mid);
		endTime = System.nanoTime();
		runtime = endTime - startTime;
		write(k);
	}

	/**
	 * Partitions an array of integers.
	 * 
	 * @param list
	 *            A list of integers to be partitioned
	 * @param left
	 *            The first index
	 * @param right
	 *            The pivot index, moved to the last index
	 * @return The index partitioned about
	 */
	public static int partition(ArrayList<Integer> list, int left, int right) {
		int pivotVal = list.get(right);
		int i = left - 1;
		for (int j = left; j < right; j++) {
			if (comp.compare(list.get(j), pivotVal) < 0) {
				i++;
				int temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		int temp = list.get(i + 1);
		list.set(i + 1, pivotVal);
		list.set(right, temp);

		return i + 1;
	}

	/**
	 * A method to recursively find the median of a list of integers.
	 * 
	 * @param list
	 *            The list to find the median within
	 * @param left
	 *            The first index
	 * @param right
	 *            The last index
	 * @param k
	 *            The position of the median
	 * @return The value of the median
	 */
	public static int quickselect(ArrayList<Integer> list, int left, int right, int k) {
		if (right - left > 1) {
			int pivotIndex = k;
			if(right - left >= 9) {
//				int mid = (int) Math.floor((left + right) / 2);
				int mid = (left + right)/2;
				pivotIndex = median(left, list.get(left), right, list.get(right), mid, list.get(mid));
				int temp = list.get(pivotIndex);
				list.set(pivotIndex, list.get(right));
				list.set(right, temp);
			} else {
				pivotIndex = right;
			}
			pivotIndex = partition(list, left, right);
			if (k == pivotIndex) {
				return list.get(k);
			} else if (k < pivotIndex) {
				return quickselect(list, left, right - 1, k);
			} else {
				return quickselect(list, left + 1, right, k);
			}

		} else {
			
			return insertionSortMedian(list, left, right);
//			int mid = (int) Math.floor((sorted.size() - 1) / 2);
//			return sorted.get(mid);
		}
	}

	public static int median(int left, int leftVal, int right, int rightVal, int middle, int middleVal) {
		int rtol = comp.compare(rightVal, leftVal);
		if(rtol == comp.compare(middle, rightVal)) {
			return right;
		} else if (rtol*(-1) == comp.compare(middle, leftVal)) {
			return left;
		} else {
			return middle;
		}
	}
	
	public static int insertionSortMedian(ArrayList<Integer> L, int left, int right) {
		/*
		 * One element is removed and then compared to each element until its proper
		 * place is found. Its correct placement is when the element is less than the
		 * one it is being compared to. It will then be placed at that index.
		 */
		for (int j = left; j <= right; j++) {
			int key = L.get(j);
			int i = j - 1;
			while (i >= left && comp.compare(L.get(i), key) > 0) {
				L.set(i + 1, L.get(i));
				i = i - 1;
			}
			L.set(i + 1, key);
		}
		return L.get((int) Math.floor((left + right)/2));
	}

}
