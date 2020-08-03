package sorting;

import java.util.LinkedList;
import java.util.List;

public class BubbleSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new LinkedList<>();
        int n = list.size();
        do {
            int newN = 0;
            for (int i = 0; i < n - 1; i++) {
                changes.add(new Change(ChangeType.COMPARE, i, i + 1));
                if (list.get(i) > list.get(i + 1)) {
                    final int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    newN = i + 1;
                    changes.add(new Change(ChangeType.SWAP, i, i + 1));
                }
            }
            n = newN;
        } while (n > 1);
        return changes;
    }
}
