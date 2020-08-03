package sorting;

import java.util.LinkedList;
import java.util.List;

public class HeapSort implements SortingAlgorithm {
    private final List<Change> changes = new LinkedList<>();

    @Override
    public List<Change> sort(final List<Integer> list) {
        changes.clear();
        for (int i = list.size() / 2 - 1; i >= 0; i--) {
            heapifiy(list, list.size(), i);
        }

        for (int i = list.size() - 1; i >= 0; i--) {
            final int temp = list.get(i);
            list.set(i, list.get(0));
            list.set(0, temp);
            changes.add(new Change(ChangeType.SWAP, i, 0));
            heapifiy(list, i, 0);
        }
        return changes;
    }

    private void heapifiy(final List<Integer> list, final int n, final int i) {
        int largest = i;
        final int l = 2 * i + 1;
        final int r = 2 * i + 2;

        changes.add(new Change(ChangeType.COMPARE, l, largest));
        if (l < n && list.get(l) > list.get(largest)) {
            largest = l;
        }

        changes.add(new Change(ChangeType.COMPARE, r, largest));
        if (r < n && list.get(r) > list.get(largest)) {
            largest = r;
        }

        if (largest != i) {
            final int temp = list.get(i);
            list.set(i, list.get(largest));
            list.set(largest, temp);
            changes.add(new Change(ChangeType.SWAP, i, largest));
            heapifiy(list, n, largest);
        }
    }
}
