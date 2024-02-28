public interface List<E> {
    void add(E item);
    E remove();
    E get(int index);
    boolean isEmpty();
    int size();
}
