package interfaces;

public interface IQueue {
    void enqueue(Object item);
    Object dequeue();
    boolean isEmpty();
    int size();
}
