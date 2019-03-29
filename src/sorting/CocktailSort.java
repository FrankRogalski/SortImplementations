package sorting;

import java.util.ArrayList;
import java.util.List;

public class CocktailSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new ArrayList<>();
        int beginIndex = 0;
        int endIndex = list.size() - 1;
        while (beginIndex < endIndex) {
            int newBeginIndex = endIndex;
            int newEndIndex = beginIndex;

            for (int i = beginIndex; i < endIndex; i++) {
                changes.add(new Change(ChangeType.COMPARE, i, i + 1));
                if (list.get(i) > list.get(i + 1)) {
                    final int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    changes.add(new Change(ChangeType.SWAP, i, i + 1));
                    newEndIndex = i;
                }
            }

            endIndex = newEndIndex;

            for (int i = endIndex; i >= beginIndex; i--) {
                changes.add(new Change(ChangeType.COMPARE, i, i + 1));
                if (list.get(i) > list.get(i + 1)) {
                    final int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    changes.add(new Change(ChangeType.SWAP, i, i + 1));
                    newBeginIndex = i;
                }
            }
            beginIndex = newBeginIndex;
        }
        return changes;
    }
}
