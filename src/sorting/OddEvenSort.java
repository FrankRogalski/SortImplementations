package sorting;

import java.util.LinkedList;
import java.util.List;

public class OddEvenSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new LinkedList<>();
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int j = 0; j < 2; j++) {
                for (int i = j; i < list.size() - 1; i += 2) {
                    changes.add(new Change(ChangeType.COMPARE, i, i + 1));
                    if (list.get(i) > list.get(i + 1)) {
                        final int temp = list.get(i);
                        list.set(i, list.get(i + 1));
                        list.set(i + 1, temp);
                        changes.add(new Change(ChangeType.SWAP, i, i + 1));
                        sorted = false;
                    }
                }
            }
        }
        return changes;
    }
}
