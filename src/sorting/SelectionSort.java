package sorting;

import java.util.LinkedList;
import java.util.List;

public class SelectionSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new LinkedList<>();
        int n = list.size();
        for (int j = 0; j < n - 1; j++) {
            int iMin = j;
            for (int i = j + 1; i < n; i++) {
                changes.add(new Change(ChangeType.COMPARE, i, iMin));
                if (list.get(i) < list.get(iMin)) {
                    iMin = i;
                }
            }

            if (iMin != j) {
                final int temp = list.get(j);
                list.set(j, list.get(iMin));
                list.set(iMin, temp);
                changes.add(new Change(ChangeType.SWAP, j, iMin));
            }
        }
        return changes;
    }
}
