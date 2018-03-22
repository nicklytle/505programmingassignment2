import java.util.ArrayList;

/**
 * @author bpjanos, nalytle
 * 
 *         A class that implements insertion sort. Both the recursive and
 *         non-recursive form are represented here, but only the non-recursive
 *         form is used. The non-recursive insertion sort was based off of
 *         pseudocode found in Introduction to Algorithms 3rd e by Cormen. The
 *         recursive version was based off of pseudocode found in CSC 505 class
 *         notes.
 *
 */
public class InsertionSort extends Sorter {

	/**
	 * Reads in the input, tracks the runtime, and writes the results.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		read();
//		startTime = System.nanoTime();
//		ArrayList<Integer> sorted = nonRecursiveInsertionSort(integerList);
//		endTime = System.nanoTime();
//		runtime = endTime - startTime;
		//write(sorted);

	}

	/**
	 * A non-recursive implementation of insertion sort.
	 * 
	 * @param L
	 *            The ArrayList to sort in place
	 * @return the same ArrayList, L, sorted.
	 */
	public static ArrayList<Integer> nonRecursiveInsertionSort(ArrayList<Integer> L) {
		/*
		 * One element is removed and then compared to each element until its proper
		 * place is found. Its correct placement is when the element is less than the
		 * one it is being compared to. It will then be placed at that index.
		 */
		for (int j = 1; j < L.size(); j++) {
			int key = L.get(j);
			int i = j - 1;
			while (i >= 0 && comp.compare(L.get(i), key) > 0) {
				L.set(i + 1, L.get(i));
				i = i - 1;
			}
			L.set(i + 1, key);
		}
		return L;
	}

	/**
	 * Recursively takes one element of a list at a time and sorts it. This method
	 * is not run for grading purposes.
	 * 
	 * @param L
	 *            An unsorted list of Integers
	 * @return the list L in sorted order
	 */
	public static ArrayList<Integer> insertionSort(ArrayList<Integer> L) {
		/*
		 * If L is empty, our work is done. Otherwise, take the first element of the
		 * list and then insert it into a sorted list (the recursive call to
		 * insertionSort).
		 */
		if (L.size() == 0) {
			return L;
		} else {
			ArrayList<Integer> rest = new ArrayList<>(L.subList(1, L.size()));
			return insert(L.get(0), insertionSort(rest));
		}
	}

	/**
	 * Finds the proper position of an integer into a sorted list. This method is
	 * not run for grading purposes.
	 * 
	 * @param i
	 *            The Integer to place.
	 * @param l
	 *            The sorted list to place the Integer into
	 * @return The sorted list with the Integer.
	 */
	public static ArrayList<Integer> insert(Integer i, ArrayList<Integer> l) {
		ArrayList<Integer> sorted = new ArrayList<>();
		/*
		 * If the list is empty, then we can return a list with simply i. If the integer
		 * is smaller than the first element of the list, then it will be inserted in
		 * the first position in the list. If the first element of the list is smaller
		 * than the integer, then the first element of the list will take the first
		 * position of the new list and insert will be called again on the integer and
		 * the remaining list.
		 */
		if (l.size() == 0) {
			sorted.add(i);
		} else if (comp.compare(i, l.get(0)) <= 0) {
			sorted.add(i);
			sorted.addAll(l);
		} else {
			sorted.add(l.get(0));
			ArrayList<Integer> rest = new ArrayList<>(l.subList(1, l.size()));
			sorted.addAll(insert(i, rest));
		}
		return sorted;
	}

}