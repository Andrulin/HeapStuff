/*****************************************************
 * class RunMed
 * Implements an online algorithm to track the median of a growing dataset
 * Maintains 2 heaps: minheap for upper half of dataset, maxheap for lower half
 * Median will always be one of these:
 *  - max of left heap  (lower range)
 *  - min of right heap (upper range)
 *  - mean of heap roots
 *****************************************************/

public class RunMed {

    //instance vars
    private ALMinHeap leftHeap;  //for lower range of dataset
    private ALMaxHeap rightHeap; //for upper range of dataset


    /*****************************************************
     * default constructor  ---  inits empty heap
     *****************************************************/
    public RunMed() 
    { 
        leftHeap = new ALMinHeap();
        rightHeap = new ALMaxHeap();
    }//O(1)



    /*****************************************************
     * double getMedian()  ---  returns median of dataset
     *****************************************************/
    public double getMedian() 
    {   
        if (leftHeap.size() < rightHeap.size()) {
            return rightHeap.peekMax();
        }
        else if (leftHeap.size() > rightHeap.size()) {
            return leftHeap.peekMin();
        }
        else {
            return (leftHeap.peekMin() + rightHeap.peekMax()) / 2;
        }
    }//O(1)



    /*****************************************************
     * insert(int)  ---  adds a new element to the dataset
     * postcondition: dataset is maintained such that 
     *                getMedian() can run in constant time
     *****************************************************/
    public void insert( int addVal )
    {   
        if (isEmpty()) {
            rightHeap.add(addVal);
            return;
        }
        
        if (addVal < rightHeap.peekMax()) {
            rightHeap.add(addVal);
        }
        else if (addVal >= rightHeap.peekMax()) {
            leftHeap.add(addVal);
        }
        
        ///BALANCE HEAPS
        if (rightHeap.size() - leftHeap.size() > 1) {
            leftHeap.add(rightHeap.removeMax());
        }
        else if (leftHeap.size() - rightHeap.size() >1) {
            rightHeap.add(leftHeap.removeMin());
        }

        
     }//O(logn)



    /*****************************************************
     * boolean isEmpty()  ---  tells whether a median may be calculated
     * postcondition: dataset structure unchanged
     *****************************************************/
    public boolean isEmpty() 
    {
        return (leftHeap.size() == 0 && rightHeap.size() == 0); 
    }//O(1)



    //main method for testing
    public static void main( String[] args ) {


        RunMed med = new RunMed();
        med.insert(1);
	System.out.println( med.getMedian() ); //1
        med.insert(3);
	System.out.println( med.getMedian() ); //2
        med.insert(5);
	System.out.println( med.getMedian() ); //3
        med.insert(7);
	System.out.println( med.getMedian() ); //4
        med.insert(9);
	System.out.println( med.getMedian() ); //5
		/*~~~V~~~~~~~~~~~~move~me~down~~~~~~~~~~~~~V~~~
	~~~~~|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|~~~*/

    }//end main()

}//end class RunMed
