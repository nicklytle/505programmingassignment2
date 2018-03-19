import java.util.Comparator;

/**
 * @author bpjanos, nalytle
 *
 *         The comparator function that ensures comparisons happen in a uniform
 *         manner. It also tracks the amount of comparisons that occur.
 */
public class Counter implements Comparator<Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 * 
	 * Takes in two integers and returns a positive value if the first number is
	 * greater than the second, a negative value if the first number is less than
	 * the second number, and zero if the numbers are equal. No matter the outcome,
	 * the counter for the number of comparisons is incremented.
	 */
	@Override
	public int compare(Integer num1, Integer num2) {
		Sorter.numComparisons++;
		
		if (num1 < num2) {
			return -1;
		}
		if (num1 > num2) {
			return 1;
		}
		return 0;
	}
}
