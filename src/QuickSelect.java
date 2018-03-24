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
 *         modifications were made to accommodate the program directions, such
 *         as choosing the pivot index as the median of the right, left, and
 *         middle indices and applying insertion sort at a certain cutoff.
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
		read();
		int mid = (int) Math.floor((integerList.size() - 1) / 2);
		startTime = System.nanoTime();
		int k = quickselect(integerList, 0, integerList.size() - 1, mid);
		endTime = System.nanoTime();
		runtime = endTime - startTime;
		write(k);
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
		/*
		 * The cutoff determines when the algorithm will start using insertion sort
		 */
		if (right - left > cutoff) {
			/*
			 * The pivot will be moved to the right, so initially setting it to the right
			 * makes it convenient.
			 */
			int pivotIndex = right;
			/*
			 * While there are more than 9 elements to sort through, the pivot will be the
			 * median of the first, middle, and last elements of the sublist.
			 */
			if (right - left >= 9) {
				pivotIndex = median(list, left, right);
				int temp = list.get(pivotIndex);
				list.set(pivotIndex, list.get(right));
				list.set(right, temp);
			}
			/*
			 * Partition about the pivot (now in the right position)
			 */
			pivotIndex = partition(list, left, right);
			/*
			 * If the middle value is at the pivot index, we've found our median. Otherwise,
			 * recurse and narrow down the sublist.
			 */
			if (k == pivotIndex) {
				return list.get(k);
			} else if (k <= pivotIndex) {
				return quickselect(list, left, pivotIndex - 1, k);
			} else {
				return quickselect(list, pivotIndex + 1, right, k);
			}

		} else {
			return insertionSortMedian(list, left, right);
		}
	}

	/**
	 * Partitions a sublist of integers, list[left...right] in place.
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
		/*
		 * The pivot value will always be at the right index.
		 */
		int pivotVal = list.get(right);
		int i = left - 1;
		/*
		 * Partitions the array so that 
		 * A[j] <= pivotVal, from left to pivotIndex - 1
		 * A[pivotIndex] = pivotVal 
		 * A[j] > pivotVal from pivotIndex + 1 to right
		 */
		for (int j = left; j < right; j++) {
			if (comp.compare(list.get(j), pivotVal) < 0) {
				i++;
				int temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
			}
		}
		/*
		 * Move the pivot value to its correct place.
		 */
		int temp = list.get(i + 1);
		list.set(i + 1, pivotVal);
		list.set(right, temp);

		return i + 1;
	}

	/**
	 * A helper method to find the median of the left, right, and middle indices in
	 * a list L.
	 * 
	 * @param L
	 *            the list the indices refer to.
	 * @param left
	 *            the left (or first) index.
	 * @param right
	 *            the right (or last) index.
	 * @return the index where the median occurs.
	 */
	public static int median(ArrayList<Integer> L, int left, int right) {
		int middle = (int) Math.floor((left + right) / 2);
		if (comp.compare(L.get(left), L.get(middle)) > 0) {
			if (comp.compare(L.get(middle), L.get(right)) > 0) {
				return middle;
			} else if (comp.compare(L.get(left), L.get(right)) > 0) {
				return right;
			} else {
				return left;
			}
		} else {
			if (comp.compare(L.get(left), L.get(right)) > 0) {
				return left;
			} else if (comp.compare(L.get(middle), L.get(right)) > 0) {
				return right;
			} else {
				return middle;
			}
		}
	}

	/**
	 * A modified version of insertion sort that sorts the sublist L[left...right]
	 * and returns the middle (median) value of that sublist.
	 * 
	 * @param L
	 *            the list the indices refer to.
	 * @param left
	 *            the left (or first) index.
	 * @param right
	 *            the right (or last) index.
	 * @return The middle element of the sorted sublist.
	 */
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
		return L.get((int) Math.floor((left + right) / 2)); // Return the median value.
	}

}
