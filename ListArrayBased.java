
// TODO: Auto-generated Javadoc
// ********************************************************
// Array-based implementation of the ADT list.
/**
 * The Class ListArrayBased.
 *
 * @param <T> the generic type
 */
// *********************************************************
public class ListArrayBased<T> implements ListInterface<T>
{

    /** The Constant MAX_LIST. */
    private static final int MAX_LIST = 3;

    /** The array of list items. */
    protected T []items;  // an array of list items

    /** The number of items in list. */
    protected int numItems;  // number of items in list

    /**
     * Creates a new list array based.
     */
    public ListArrayBased()
    {
        items = (T[])new Object[MAX_LIST];
        numItems = 0;
    }  // end default constructor

    /**
     * Checks if is empty.
     *
     * @return true, if is empty
     */
    public boolean isEmpty()
    {
        return (numItems == 0);
    } // end isEmpty

    /**
     * return the size.
     *
     * @return the int
     */
    public int size()
    {
        return numItems;
    }  // end size

    /**
     * Removes all items and create a new empty array.
     */
    public void removeAll()
    {
        // Creates a new array; marks old array for
        // garbage collection.
        items = (T[])new Object[MAX_LIST];
        numItems = 0;
    } // end removeAll

    /**
     * Adds the new item into the array with the index.
     *
     * @param index the index
     * @param item the item
     * @throws ListIndexOutOfBoundsException the list index out of bounds exception
     */
    public void add(int index, T item)
    throws  ListIndexOutOfBoundsException
    {
        if (numItems == items.length)
        {
            throw new ListException("ListException on add");
        }  // end if
        if (index >= 0 && index <= numItems)
        {
            // make room for new element by shifting all items at
            // positions >= index toward the end of the
            // list (no shift if index == numItems+1)
            for (int pos = numItems-1; pos >= index; pos--)  //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException
            {
                items[pos+1] = items[pos];
            } // end for
            // insert new item
            items[index] = item;
            numItems++;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on add");
        }  // end if
    } //end add

    /**
     * Gets the item in the list based off of the index.
     *
     * @param index the index
     * @return the generic type
     * @throws ListIndexOutOfBoundsException the list index out of bounds exception
     */
    public T get(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            return items[index];
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on get");
        }  // end if
    } // end get

    /**
     * Removes the item in the index.
     *
     * @param index the index
     * @throws ListIndexOutOfBoundsException the list index out of bounds exception
     */
    public void remove(int index)
    throws ListIndexOutOfBoundsException
    {
        if (index >= 0 && index < numItems)
        {
            // delete item by shifting all items at
            // positions > index toward the beginning of the list
            // (no shift if index == size)
            for (int pos = index+1; pos < numItems; pos++) //textbook code modified to eliminate logic error causing ArrayIndexOutOfBoundsException

            {
                items[pos-1] = items[pos];
            }  // end for
            numItems--;
            items[numItems] = null;
        }
        else
        {
            // index out of range
            throw new ListIndexOutOfBoundsException(
                "ListIndexOutOfBoundsException on remove");
        }  // end if
    } //end remove
}