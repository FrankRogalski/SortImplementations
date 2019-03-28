package sorting;

import java.util.ArrayList;
import java.util.List;

public class InsertionSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new ArrayList<>();
        for (int i = 1; i < list.size(); i++) {
            int j = i;
            changes.add(new Change(ChangeType.COMPARE, j, j - 1));
            while ( j > 0 && list.get(j - 1) > list.get(j)) {
                final int temp = list.get(j);
                list.set(j, list.get(j - 1));
                list.set(j - 1, temp);
                changes.add(new Change(ChangeType.SWAP, j, j - 1));
                j--;
                changes.add(new Change(ChangeType.COMPARE, j, j - 1));
            }
        }
        return changes;
    }
}
