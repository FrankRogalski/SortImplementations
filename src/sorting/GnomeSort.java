package sorting;

import java.util.LinkedList;
import java.util.List;

public class GnomeSort implements SortingAlgorithm {
    @Override
    public List<Change> sort(final List<Integer> list) {
        final List<Change> changes = new LinkedList<>();
        int pos = 0;
        while (pos < list.size()) {
            changes.add(new Change(ChangeType.COMPARE, pos, pos - 1));
            if (pos == 0 || list.get(pos) >= list.get(pos - 1)) {
                pos++;
            } else {
                final int temp = list.get(pos);
                list.set(pos, list.get(pos - 1));
                list.set(pos - 1, temp);
                changes.add(new Change(ChangeType.SWAP, pos, pos - 1));
                pos--;
            }
        }
        return changes;
    }
}
