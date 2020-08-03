package sorting;

import java.util.LinkedList;
import java.util.List;

public class CycleSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new LinkedList<>();
        for (int i = 0; i < list.size() - 1; i++) {
            boolean first = true;
            int item = list.get(i);
            changes.add(new Change(ChangeType.READ, i));
            int pos;
            do {
                pos = getPos(list, changes, i, item);

                if (pos == i && first) {
                    continue;
                }
                first = false;

                changes.add(new Change(ChangeType.READ, pos));
                while (item == list.get(pos)) {
                    pos++;
                    changes.add(new Change(ChangeType.READ, pos));
                }

                final int temp = list.get(pos);
                list.set(pos, item);
                changes.add(new Change(ChangeType.WRITE, pos));
                changes.get(changes.size() - 1).setValue(item);
                item = temp;
            } while (pos != i);
        }
        return changes;
    }

    private int getPos(List<Integer> list, List<Change> changes, int i, int item) {
        int pos;
        pos = i;
        for (int j = i + 1; j < list.size(); j++) {
            changes.add(new Change(ChangeType.READ, j));
            if (list.get(j) < item) {
                pos++;
            }
        }
        return pos;
    }
}
