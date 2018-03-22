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
		integerList = new ArrayList<>();
		integerList.add(10);
		integerList.add(4);
		integerList.add(5);
		integerList.add(8);
		integerList.add(6);
		integerList.add(11);
		integerList.add(26);
		integerList.add(32);
		integerList.add(2);
		integerList.add(9);

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
	 *            The last index
	 * @return The index partitioned about
	 */
	public static int partition(ArrayList<Integer> list, int left, int right, int pivot) {
		int pivotVal = list.get(pivot);
		list.set(pivot, list.get(right));
		list.set(right, pivotVal);
		int storeIndex = left;
		for(int i = left; i < right; i++) {
			if(comp.compare(list.get(i), pivotVal) < 0) {
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
//		int pivotVal = list.get(pivot);
//		int i = left - 1;
//		for (int j = left; j < right; j++) {
//			if (comp.compare(list.get(j), pivotVal) < 0) {
//				i++;
//				int temp = list.get(i);
//				list.set(i, list.get(j));
//				list.set(j, temp);
//			}
//		}
//		int temp = list.get(i + 1);
//		list.set(i + 1, pivotVal);
//		list.set(pivot, temp);
//
//		return i + 1;
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
			// int pivotIndex = left + (int) Math.floor(Math.random() * (right - left + 1));
			int pivotIndex;
			if(list.size() >= 9) {
				int mid = (int) Math.floor((list.size() + 1) / 2);
				pivotIndex = median(left, list.get(left), right, list.get(right), mid, list.get(mid));
			} else {
				pivotIndex = right;
			}
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
			int mid = (int) Math.floor((sorted.size() - 1) / 2);
			return sorted.get(mid);
		}
	}

	public static int median(int left, int leftVal, int right, int rightVal, int middle, int middleVal) {
		if(comp.compare(rightVal, leftVal) == comp.compare(middle, rightVal)) {
			return right;
		} else if (comp.compare(leftVal, rightVal) == comp.compare(middle, leftVal)) {
			return left;
		} else {
			return middle;
		}
	}

}
