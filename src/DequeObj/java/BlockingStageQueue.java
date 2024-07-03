package DequeObj.java;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockingStageQueue implements StageQueue {
    private final BlockingDeque<Item> queue = new LinkedBlockingDeque<>();

    @Override
    public void addItem(Item item) throws InterruptedException {
        queue.put(item);
    }

    @Override
    public Item getItem() throws InterruptedException {
        return queue.take();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
