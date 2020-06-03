
public interface PurePriorityQueue<E> {
    int size();
    boolean isEmpty();
    void add(E element);
    E getMin();
    E removeMin();
}
