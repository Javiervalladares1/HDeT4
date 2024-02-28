package src;

public class StackFactory {
    public static <E> Stack<E> createStack(String type) {
        if (type.equalsIgnoreCase("ArrayList")) {
            return new ArrayListStack<>();
        } else if (type.equalsIgnoreCase("Vector")) {
            return new VectorStack<>();
        } else if (type.equalsIgnoreCase("LinkedList")) {
            return new LinkedListStack<>();
        } else {
            throw new IllegalArgumentException("Invalid stack type");
        }
    }
}
