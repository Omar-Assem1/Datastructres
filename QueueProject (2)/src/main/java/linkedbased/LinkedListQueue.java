package linkedbased;

import interfaces.IQueue;
import interfaces.ILinkedBased;
import java.util.NoSuchElementException;

public class LinkedListQueue implements IQueue, ILinkedBased {
    private DoubleLinkedList list;

    public LinkedListQueue() {
        list = new DoubleLinkedList();
    }

    @Override
    public void enqueue(Object item) {
        list.add(0, item);
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Object item = list.get(list.size() - 1);
        list.remove(list.size() - 1);
        return item;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
