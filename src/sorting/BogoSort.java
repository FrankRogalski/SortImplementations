package sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BogoSort implements SortingAlgorithm {
    private final List<Change> changes =new ArrayList<>();
    private final Random random = new Random();

    @Override
    public List<Change> sort(final List<Integer> list) {
        changes.clear();
        for (int i = 0; i < 1000 && !sorted(list); i++) {
            shuffle(list);
        }
        return changes;
    }

    private void shuffle(final List<Integer> list) {
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            final int randomNumber = random.nextInt(size);
            final int temp = list.get(i);
            list.set(i, list.get(randomNumber));
            list.set(randomNumber, temp);
            changes.add(new Change(ChangeType.SWAP, i, randomNumber));
        }
    }

    private boolean sorted(final List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            changes.add(new Change(ChangeType.COMPARE, i, i - 1));
            if (list.get(i - 1) > list.get(i)) {
                return false;
            }
        }
        return true;
    }
}
