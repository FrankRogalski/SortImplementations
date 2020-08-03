package sorting;

import java.util.LinkedList;
import java.util.List;

public class QuickSort implements SortingAlgorithm {
    private final List<Change> changes = new LinkedList<>();

    @Override
    public List<Change> sort(final List<Integer> list) {
        changes.clear();
        quickSort(list, 0, list.size() - 1);
        return changes;
    }

    private void quickSort(final List<Integer> list, final int start, final int end) {
        if (start < end) {
            final int part = partition(list, start, end);
            quickSort(list, start, part - 1);
            quickSort(list, part + 1, end);
        }
    }

    private int partition(final List<Integer> list, final int start, final int end) {
        final int pivot = list.get(end);
        changes.add(new Change(ChangeType.READ, end));
        int i = start;
        for (int j = start; j < end; j++) {
            changes.add(new Change(ChangeType.READ, j));
            if (list.get(j) < pivot) {
                final int temp = list.get(j);
                list.set(j, list.get(i));
                list.set(i, temp);
                changes.add(new Change(ChangeType.SWAP, i, j));
                i++;
            }
        }

        final int temp = list.get(end);
        list.set(end, list.get(i));
        list.set(i, temp);
        changes.add(new Change(ChangeType.SWAP, i, end));
        return i;
    }
}
