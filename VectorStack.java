package src;
import java.util.Vector;
import java.util.EmptyStackException;

public class VectorStack<E> extends StackAbstract<E> {
    private Vector<E> stack;

    public VectorStack() {
        stack = new Vector<>();
        size = 0;
    }

    public void push(E item) {
        stack.add(item);
        size++;
    }

    public E pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        size--;
        return stack.remove(size);
    }

    public E peek() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.get(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
