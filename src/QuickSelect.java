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
	public static int partition(ArrayList<Integer> list, int left, int right) {
		int x = list.get(right);
		int i = left - 1;
		for (int j = left; j < right; j++) {
			if (comp.compare(list.get(j), x) < 0) {
				i++;
				int temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		int temp = list.get(i + 1);
		list.set(i + 1, list.get(right));
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
			// int pivotIndex = left + (int) Math.floor(Math.random() * (right - left + 1));
			int pivotIndex;
			if(list.size() >= 9) {
				pivotIndex = median(list.get(left), list.get(right), list.get((int) Math.floor((list.size() + 1) / 2)));
			} else {
				pivotIndex = list.get(left);
			}
			pivotIndex = partition(list, left, right);
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

	public static int median(int left, int right, int middle) {
		if (right >= left && right <= middle) {
			return right;
		} else if (right >= middle && right <= left) {
			return right;
		} else if (left >= right && left <= middle) {
			return left;
		} else if (left >= middle && left <= right) {
			return left;
		} else {
			return middle;
		}
	}

}
