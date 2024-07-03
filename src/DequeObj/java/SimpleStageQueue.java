package DequeObj.java;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleStageQueue implements StageQueue {
    private final Deque<Item> queue = new ArrayDeque<>();

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

    public Deque<Item> getQueue() {
        return queue;
    }
}