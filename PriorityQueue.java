
public class PriorityQueue {

    PriorityCustomer[] heap;
    int size,sizeOfLine,total,maxLength;

    public PriorityQueue () {
        heap = new PriorityCustomer [60];
        int size = 0 ,sizeOfLine = 0, total = 0, maxLength =0;

    }

    public void add (PriorityCustomer p) { 
        //Make sure there is room for the new object
        if (size + 1 >= heap.length) {
            System.out.println ("Heap is full");
            return;
        }

        //Add p to the next open spot in the heap (@ index size + 1)
        size ++;
        heap [size] = p;
        total++;
        //Store index where new object was placed
        int index = size;

        //Loop while p has a parent (not at the root)
        while (index > 1) {

            //Get access to parent
            int parentIndex = index / 2;

            //Determine if swap is needed
            if (p.getPriority () > heap[parentIndex].getPriority() ) {
                heap [index] = heap [parentIndex];
                heap [parentIndex] = p;

                //update p's index since we swapped it with its parent
                index = parentIndex;
            } else {
                //p's parent value is larger -> p is in the correct position
                break;
            }
        }
    }

    public PriorityCustomer remove () {
        if (maxLength < size){
            maxLength = size;
        }
        //Make sure heap isn't empty
        if (size == 0) 
        {

            System.out.println ("The heap is already empty");
            return null;
        }

        //Store root node in temp variable so we can return it at the end
        PriorityCustomer root = heap [1];   

        //Take the node at the last position and move it to the root
        heap [1] = heap [size];
        heap [size] = null;

        //dec size
        size --;

        //if heap is empty after removing, STOP!
        if (size == 0) {
            return root;
        }

        //Store index and value where new object was placed (we moved it to the root)
        int index = 1;
        int value = heap [1].getPriority ();

        /*Loop while our node has at least one child
         *(size / 2) gives us the index to the last parent. Any index after that
         *will not have children.
         */
        while (index <= size / 2) {

            //get index to left and right children
            int leftIndex = index * 2;
            int rightIndex = (index * 2) + 1;

            //store values of child nodes
            int leftValue = heap [leftIndex].getPriority ();

            //initially start the right child with a realllllly small value
            //in case there's not a right child.
            int rightValue = Integer.MIN_VALUE; 

            //check to see if there's a right child and update its value if so
            if (heap [rightIndex] != null) {
                rightValue = heap [rightIndex].getPriority ();
            }

            //determine which of the children is larger
            if (leftValue > rightValue) {
                //compare leftValue with parent value
                if (leftValue > value) {
                    //swap with left child
                    swap (leftIndex, index);

                    //update index since we swapped it with a child
                    index = leftIndex;

                } else {  break;  }
            } else {
                //compare rightValue with parent value
                if (rightValue > value) {
                    //swap with right child
                    swap (rightIndex, index);

                    //update index since we swapped it with a child
                    index = rightIndex;

                } else {  break;  }
            }
        }

        //return removed node
        return root;

    }

    void swap (int index1, int index2) {
        PriorityCustomer p = heap [index1];
        heap [index1] = heap [index2];
        heap [index2] = p;
    }

    public int getSize() {
        return size;
    }

    public int getTotal(){
        return total;
    }

    public int getMaxLength(){
        return maxLength;
    }

    public PriorityCustomer peek(){
        if (heap[1] == null){
            System.out.println ("The line is empty ");
            return null;
        }

        else{
            return heap[1];
        }
    }
}