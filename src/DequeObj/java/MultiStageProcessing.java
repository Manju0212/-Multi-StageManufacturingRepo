package DequeObj.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiStageProcessing {
    private final SimpleStageQueue stage1;
    private final BlockingStageQueue stage2;
    private final ConcurrentStageQueue stage3;
    private final SimpleStageQueue stage4;

    public MultiStageProcessing() {
    	
        stage1 = new SimpleStageQueue();
        stage2 = new BlockingStageQueue();
        stage3 = new ConcurrentStageQueue();
        stage4 = new SimpleStageQueue();
    }

    public void processItem(Item item) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        // Stage 1
        executor.submit(() -> {
            System.out.println("Processing item " + item.getItemId() + " at stage 1");
            stage1.addItem(item);
        });

        // Stage 2
        executor.submit(() -> {
            try {
                while (stage1.isEmpty()) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                Item itemFromStage1 = stage1.getItem();
                if (itemFromStage1 != null) {
                    System.out.println("Processing  " + itemFromStage1.getItemId() + "  stage 2");
                    stage2.addItem(itemFromStage1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Stage 3
        executor.submit(() -> {
            try {
                while (stage2.isEmpty()) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
                Item itemFromStage2 = stage2.getItem();
                if (itemFromStage2 != null) {
                    System.out.println("Processing  " + itemFromStage2.getItemId() + " stage 3");
                    stage3.addItem(itemFromStage2);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Stage 4
        executor.submit(() -> {
            while (stage3.isEmpty()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Item itemFromStage3 = stage3.getItem();
            if (itemFromStage3 != null) {
                System.out.println("Processing  " + itemFromStage3.getItemId() + "stage 4");
                stage4.addItem(itemFromStage3);
            }
        });

        executor.shutdown();
    }

    public static void main(String[] args) {
        MultiStageProcessing processor = new MultiStageProcessing();
        Item item = new Item(1, 1, 1, "AAA");
        processor.processItem(item);
    }
}