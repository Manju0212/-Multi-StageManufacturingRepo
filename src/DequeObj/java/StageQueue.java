package DequeObj.java;

public interface StageQueue {
	
    void addItem(Item item) throws InterruptedException;
    
    Item getItem() throws InterruptedException;
    
    boolean isEmpty();
}
