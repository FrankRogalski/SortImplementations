package sorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MergeSort implements SortingAlgorithm {
    private final List<Change> changes = new LinkedList<>();
    private List<Integer> list;

    @Override
    public List<Change> sort(final List<Integer> list) {
        changes.clear();
        this.list = list;
        final List<Integer> copy = new ArrayList<>(list);
        splitMerge(copy, 0, list.size(), list);
        return changes;
    }

    private void splitMerge(final List<Integer> b, final int begin, final int end, final List<Integer> a) {
        if (end - begin < 2) {
            return;
        }

        final int middle = (begin + end) / 2;

        splitMerge(a, begin, middle, b);
        splitMerge(a, middle, end, b);

        merge(b, begin, middle, end, a);
    }

    private void merge(final List<Integer> a, final int begin, final int middle, final int end, final List<Integer> b) {
        int i = begin;
        int j = middle;

        for (int k = begin; k < end; k++) {
            changes.add(new Change(ChangeType.COMPARE, i, j));
            if (i < middle && (j >= end || a.get(i) <= a.get(j))) {
                b.set(k, a.get(i));
                if (b == list) {
                    changes.add(new Change(ChangeType.WRITE, k));
                    changes.get(changes.size() - 1).setValue(a.get(i));
                } else {
                    changes.add(new Change(ChangeType.READ, i));
                }
                i++;
            } else {
                b.set(k, a.get(j));
                if (b == list) {
                    changes.add(new Change(ChangeType.WRITE, k));
                    changes.get(changes.size() - 1).setValue(a.get(j));
                } else {
                    changes.add(new Change(ChangeType.READ, j));
                }
                j++;
            }
        }
    }
}
