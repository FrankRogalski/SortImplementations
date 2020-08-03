package sorting;

import java.util.LinkedList;
import java.util.List;
import java.util.function.IntPredicate;

public class CocktailSort implements SortingAlgorithm {
    final List<Change> changes = new LinkedList<>();

    @Override
    public List<Change> sort(final List<Integer> list) {
        int beginIndex = 0;
        int endIndex = list.size() - 1;
        changes.clear();
        while (beginIndex < endIndex) {
            endIndex = sort(beginIndex, endIndex, list);
            beginIndex = sort(endIndex, beginIndex, list);
        }
        return changes;
    }

    private int sort(final int begin, final int end, final List<Integer> list) {
        final int step = begin < end ? 1 : -1;
        int newIndex = end;
        final IntPredicate endTest = i -> (begin > end) == (i >= end); // logically equivalent to begin > end ? i >= end : i < end

        for (int i = begin; endTest.test(i); i += step) {
            changes.add(new Change(ChangeType.COMPARE, i, i + 1));
            if (list.get(i) > list.get(i + 1)) {
                final int temp = list.get(i);
                list.set(i, list.get(i + 1));
                list.set(i + 1, temp);
                changes.add(new Change(ChangeType.SWAP, i, i + 1));
                newIndex = i;
            }
        }
        return newIndex;
    }
}
