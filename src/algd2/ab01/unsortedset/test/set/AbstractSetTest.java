package algd2.ab01.unsortedset.test.set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import algd2.ab01.unsortedset.test.AbstractCollectionTest;

public abstract class AbstractSetTest extends AbstractCollectionTest {

	@Override
	protected void addNumbersToBag(Integer[] numbers) {
		Set<Integer> alreadyAdded = new HashSet<Integer>();
		for (Integer number : numbers) {
			if (alreadyAdded.contains(number)) {
				assertFalse(bag.add(number));
			} else {
				assertTrue(bag.add(number));
				alreadyAdded.add(number);
			}
		}
	}
	
}
