package sorting;

import java.util.LinkedList;
import java.util.List;

public class ShellSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new LinkedList<>();
        final int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};
        for (final int gap : gaps) {
            for (int i = gap; i < list.size(); i++) {
                final int temp = list.get(i);
                changes.add(new Change(ChangeType.READ, i));

                int j;
                for (j = i; j >= gap && list.get(j - gap) > temp; j -= gap) {
                    list.set(j, list.get(j - gap));
                    changes.add(new Change(ChangeType.READ, j - gap));
                    changes.add(new Change(ChangeType.WRITE, j));
                    changes.get(changes.size() - 1).setValue(list.get(j - gap));
                }
                list.set(j, temp);
                changes.add(new Change(ChangeType.WRITE, j));
                changes.get(changes.size() - 1).setValue(temp);
            }
        }
        return changes;
    }
}
