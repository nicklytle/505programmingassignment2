import java.util.ArrayList;
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
		//read();
		integerList = new ArrayList<>();
		integerList.add(10);
		integerList.add(4);
		integerList.add(5);
		integerList.add(8);
		integerList.add(6);
		integerList.add(11);
		integerList.add(26);
		startTime = System.nanoTime();
		Collections.sort(integerList, comp);
		endTime = System.nanoTime();
		runtime = endTime - startTime;
		int mid = (int) Math.floor((integerList.size())/2);
		write(integerList.get(mid - 1));

	}

}