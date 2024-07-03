package DequeObj.java;

import java.util.concurrent.ConcurrentLinkedDeque;

public class ConcurrentStageQueue implements StageQueue {
    private final ConcurrentLinkedDeque<Item> queue = new ConcurrentLinkedDeque<>();

    @Override
    public void addItem(Item item) {
        queue.add(item);
    }

    @Override
    public Item getItem() {
        return queue.poll();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
