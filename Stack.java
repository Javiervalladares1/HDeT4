package src;
import java.util.EmptyStackException;

public interface Stack<E> {
    void push(E item);
    E pop() throws EmptyStackException;
    E peek() throws EmptyStackException;
    boolean isEmpty();
    int size();
}