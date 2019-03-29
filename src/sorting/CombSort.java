package sorting;

import java.util.ArrayList;
import java.util.List;

public class CombSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new ArrayList<>();
        int gap = list.size();
        final double shrink = 1.3;
        boolean sorted = false;
        while (!sorted) {
            gap = (int)Math.floor(gap / shrink);

            if (gap < 1) {
                gap = 1;
                sorted = true;
            }

            for (int i = 0; i + gap < list.size(); i++) {
                changes.add(new Change(ChangeType.COMPARE, i, i + gap));
                if (list.get(i) > list.get(i + gap)) {
                    final int temp = list.get(i);
                    list.set(i, list.get(i + gap));
                    list.set(i + gap, temp);
                    changes.add(new Change(ChangeType.SWAP, i, i + gap));
                    sorted = false;
                }
            }
        }
        return changes;
    }
}
