import java.util.Collections;

/**
 * @author bpjanos, nalytle
 * 
 *         This class utilizes the Java Collections sort utility for comparison
 *         purposes.
 *
 */
public class SortUtility extends Sorter {

	/**
	 * Reads in the input, tracks the runtime, and writes the results.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		read();
		startTime = System.nanoTime();
		Collections.sort(integerList, comp);
		endTime = System.nanoTime();
		runtime = endTime - startTime;
		write(integerList);

	}

}