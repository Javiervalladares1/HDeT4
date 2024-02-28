import java.util.LinkedList;
import java.util.EmptyStackException;

public class LinkedListStack<E> extends StackAbstract<E> {
    private LinkedList<E> stack;

    public LinkedListStack() {
        stack = new LinkedList<>();
        size = 0;
    }

    public void push(E item) {
        stack.addLast(item);
        size++;
    }

    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        return stack.removeLast();
    }

    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.getLast();
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
