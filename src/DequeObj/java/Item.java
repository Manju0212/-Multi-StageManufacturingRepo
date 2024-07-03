package DequeObj.java;

public class Item {
    private int itemId;
    private int stage;
    private int priority;
    private String description;

    public Item(int itemId, int stage, int priority, String description) {
        this.itemId = itemId;
        this.stage = stage;
        this.priority = priority;
        this.description = description;
    }

    public int getItemId() {
        return itemId;
    }

    public int getStage() {
        return stage;
    }

    public int getPriority() {
        return priority;
    }

    public String getDescription() {
        return description;
    }
}