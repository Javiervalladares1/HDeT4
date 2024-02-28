public class DoublyLinkedListStack<E> extends StackAbstract<E> {
    private DoublyLinkedList<E> stack;

    public DoublyLinkedListStack() {
        stack = new DoublyLinkedList<>();
        size = 0;
    }

    public void push(E item) {
        stack.add(item);
        size++;
    }

    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        size--;
        return stack.remove();
    }

    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.get(size - 1);
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
