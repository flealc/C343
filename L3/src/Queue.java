class EmptyQueueE extends Exception {}

abstract class Queue<E> {
    abstract void enqueue (E elem);
    abstract void dequeue () throws EmptyQueueE;
    abstract E getFront () throws EmptyQueueE;
}

// ----------------------------------------------------------------------

class SlowQueue<E> extends Queue<E> {
    // Our queue is represented by an object of our Stack class in Stack.java
    // So, for clarification, whenever the method docs ask to "modify our queue",
    // we are actually modifying this object.
    // Read Stack.java please:)
    private Stack<E> stack;

    /**
     * This initializes our private var "stack" to be a new EmptyS (see -Stack.java-)
     */
    SlowQueue () { stack = new EmptyS<>(); }

    /**
     * Although the method itself does not return anything, enqueue modifies our queue by adding given element to the
     * end. Hint: look in -Stack.java-
     *
     * @param elem a generic element to be added to the queue
     */
    void enqueue(E elem) {
            stack = stack.addLast(elem);                    // DONE
        }




    /**
     * This method should remove the first element of our queue. Hint: look in -Stack.java-.
     *
     * @throws ????? (throws what and why)
     */
    void dequeue() throws EmptyQueueE {
        try {  stack = stack.pop();                     // DONE
        } catch (EmptyStackE e) {
            throw new EmptyQueueE();
        }
    }

    /**
     * getFront returns an element of **generic type**.
     * This method returns the first element of our queue by using -Stack.java-'s getFront()
     *
     * @return the removed first element of out queue, E
     * @throws ????? (throws what and why)
     */
    E getFront() throws EmptyQueueE {
        try { return stack.getTop(); }
        catch (EmptyStackE e) {
            throw new EmptyQueueE();
        }
    }
}

// ----------------------------------------------------------------------

class AmortizedQueue<E> extends Queue<E> {
    private Stack<E> front, back;
    // enqueue in front; dequeue from back

    /**
     * This method initializes our private vars "front" and "back" to be
     * a new EmptyS (see -Stack.java-)
     */
    AmortizedQueue () {
        front = new EmptyS<>();
        back = new EmptyS<>();
    }

    /**
     * Although the method itself does not return anything, enqueue modifies our queue by calling on
     * -Stack.java-'s push() on our front stack.
     *
     * @param elem a generic element to be added to the queue. we do this on front because we are enqueueing
     */
    void enqueue(E elem) {
        front = front.push(elem);
    }

    /**
     * This method should remove the first element of our queue. What we mean by "first" here is based off of the
     * queue's FIFO (first-in-first-out) structure.
     * That is: we cannot simply just remove from our enqueued "front". Why?
     * In order to complete this method, transfer everything from our front to our back whenever back DOES NOT
     * contain any elements. Then, dequeue (remove).
     *
     * Think about what would happen if we moved everything from front to back on every dequeue call. Would this process
     * still be considered amortized? Would this process still be correct?
     *
     * Use methods in -Stack.java- to help you deal with front and back.
     *
     * @throws ????? (throws what and why)
     */
    void dequeue() throws EmptyQueueE {
        if (back.isEmpty()) {
            try {
                while (!front.isEmpty()) {
                    back = back.push(front.getTop());
                    front =front.pop();
                }
            } catch (EmptyStackE e) {
                throw new EmptyQueueE();
            }
        }
        try {
            back = back.pop();
        } catch (EmptyStackE e) {
            throw new EmptyQueueE();
        }
// TODO
    }

    /**
     * Returns the first element in our queue. Similar to dequeue with maintaining the
     * 2 stacks; the only thing that changes here is that we are not removing the top, but instead returning the element
     *
     * @return the removed first element of out queue, E
     * @throws ????? (throws what and why)
     */
    E getFront() throws EmptyQueueE {
        if (back.isEmpty()) {
            try {
                while (!front.isEmpty()) {
                    back = back.push(front.getTop());
                    front = front.pop();
                }
            } catch (EmptyStackE e) {
                throw new EmptyQueueE();
            }
        }
            try { return back.getTop();
            } catch (EmptyStackE e) {
                throw new EmptyQueueE();
            }
    }
}