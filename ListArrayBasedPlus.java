
// TODO: Auto-generated Javadoc
/*
 * Purpose: Data Structure and Algorithms Project
 * Status: Complete and thoroughly tested
 * Last update: 12/5/19
 * Submitted:  12/5/19
 * Comment: test suite and sample run attached
 * @author: Jackie Zheng/Anthony
 * @version: 2019.12.19
 */

/**
 * The Class ListArrayBasedPlus.
 *
 * @param <T> the generic type
 */
public class ListArrayBasedPlus<T> extends ListArrayBased<T> {

    /**
     * Resize the array list.
     */
    private void resize() {
        if(items.length == numItems) { //Checks if numItems and item length is equal
            T []newArray = (T[])new Object[(int) Math.round(items.length * 1.5)];
            for(int i = 0; i<items.length; i++) { //adds the old values to the new one
                newArray[i] = items[i];
            }
            items = newArray; //set the old array to the new one.
        }
    }

    /**
     * Adds the item into the array using index and if array is full, resize.
     *
     * @param index the index
     * @param item the item
     */
    @Override
    public void add(int index, T item) {
        if(numItems == items.length) {
            resize();
        }
        super.add(index, item);
    }

    /**
     * returns a string which items are all added into a single string.
     *
     * @return the string
     */
    public String toString() {
        StringBuilder string = new StringBuilder();
        for(int i = 0; i<numItems; i++) { //Runs through the array
            string.append(items[i] + " "); //Add the values to the stringbuilder.
        }
        return string.toString(); //return the string.
    }

    /**
     * Flips the items in the array.
     */
    public void reverse() {
        T[] reversed = (T[])new Object[items.length]; //new temporary array.
        for(int i = items.length-1; i>=0; i--) { //adds old to new array
            reversed[(items.length-1)-i] = items[i];
        }
        items = reversed; //set the reversed array.
    }
}
