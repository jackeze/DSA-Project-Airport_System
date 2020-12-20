// TODO: Auto-generated Javadoc
/**
 * The Class QueueCRAB.
 *
 * @param <T> the generic type
 */
/*
 * Purpose: Data Structure and Algorithms project
 * Status: Complete and thoroughly tested
 * Last update: 12/5/19
 * Submitted:  12/5/19
 * Comment: test suite and sample run attached
 * @author: Jackie Zheng/Anthony
 * @version: 2019.10.10
 */
public class QueueCRAB<T> implements QueueInterface<T> {

    /** The number of items. */
    protected int numItems;

    /** The front. */
    protected int front;

    /** The back. */
    protected int back;

    /** The items array. */
    protected T []items;

    /**
     * Create a new queue CRAB.
     */
    public QueueCRAB() {
        items = (T[])new Object[3];
        numItems = 0;
        front = 0;
        back = 0;
    }

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
        return  numItems == 0;
    }

    /**
     * Enqueue or add item into the queue.
     *
     * @param newItem the new item
     */
    public void enqueue (T newItem) {
        if(numItems==items.length) {
            resize();
        }
        items[back] = newItem;
        back = (back+1)%items.length;
        numItems++;
    }

    /**
     * Dequeue or remove item from the queue.
     *
     * @return the t
     * @throws QueueException the queue exception
     */
    public T dequeue() throws QueueException {
        T curr;
        if(items[front] != null) {
            curr = items[front];
            items[front] = null;
            front = (front+1)%items.length;
            numItems--;
            return curr;
        }
        else {
            throw new QueueException("Exception! Queue is empty");
        }
    }

    /**
     * Dequeue all or remove all items in the queue.
     */
    public void dequeueAll() {
        items = (T[])new Object[3];
        front = 0;
        back = 0;
        numItems = 0;
    }

    /**
     * Peek or show the item in the top.
     *
     * @return the generic type
     * @throws QueueException the queue exception
     */
    public T peek() throws QueueException {
        if(numItems != 0) {
            return items[front];
        }
        else {
            throw new QueueException("Exception! Queue is empty");
        }
    }

    /**
     * adds all the items into the string and return that string.
     *
     * @return the string
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        int modFront = front;
        for(int i = 0; i<items.length; i++) {
            modFront = (modFront+1)%items.length;
            if(items[modFront] != null) {
                string.append(items[modFront] + " \n");
            }
        }
        return string.toString();
    }

    /**
     * Resize the array.
     */
    protected void resize() {
        T []newArray = (T[]) new Object[items.length*2];
        int modFront = front;
        for(int i = 0; i<items.length; i++) {
            newArray[i]=items[modFront];
            modFront = (modFront+1)%items.length;
        }
        items = newArray;
        front = 0;
        back = numItems;
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public int getSize() {

        return numItems;
    }


}
