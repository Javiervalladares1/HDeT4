import java.util.ArrayList;
import java.util.EmptyStackException;

public class ArrayListStack<E> extends StackAbstract<E> {
    private ArrayList<E> stack;

    public ArrayListStack() {
        stack = new ArrayList<>();
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