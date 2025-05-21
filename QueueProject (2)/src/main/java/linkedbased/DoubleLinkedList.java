package linkedbased;

import interfaces.ILinkedList;

class DoubleLinkedList implements ILinkedList {
    private class Node {
        Object data;
        Node next;
        Node prev;

        Node(Object d) {
            this.data = d;
            this.next = null;
            this.prev = null;
        }
    }

    private Node header;
    private Node tailer;
    private int size;

    public DoubleLinkedList() {
        header = new Node(null);
        tailer = new Node(null);
        header.next = tailer;
        tailer.prev = header;
        size = 0;
    }

    @Override
    public void add(int index, Object element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node newNode = new Node(element);
        if (index == 0) {
            Node temp = header.next;
            newNode.next = temp;
            newNode.prev = header;
            temp.prev = newNode;
            header.next = newNode;
        } else if (index == size) {
            Node temp = tailer.prev;
            temp.next = newNode;
            newNode.prev = temp;
            newNode.next = tailer;
            tailer.prev = newNode;
        } else {
            Node iter = header.next;
            for (int i = 0; i < index - 1; i++) {
                iter = iter.next;
            }
            Node temp = iter.next;
            iter.next = newNode;
            temp.prev = newNode;
            newNode.next = temp;
            newNode.prev = iter;
        }
        size++;
    }

    @Override
    public void add(Object element) {
        add(size, element);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node iter = header.next;
        for (int i = 0; i < index; i++) {
            iter = iter.next;
        }
        return iter.data;
    }

    @Override
    public void set(int index, Object element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node iter = header.next;
        for (int i = 0; i < index; i++) {
            iter = iter.next;
        }
        iter.data = element;
    }

    @Override
    public void clear() {
        header.next = tailer;
        tailer.prev = header;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || fromIndex >= size || toIndex < 0 || toIndex >= size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException();
        DoubleLinkedList newList = new DoubleLinkedList();
        Node iter = header.next;
        for (int i = 0; i < fromIndex; i++) {
            iter = iter.next;
        }
        for (int i = fromIndex; i <= toIndex; i++) {
            newList.add(iter.data);
            iter = iter.next;
        }
        return newList;
    }

    @Override
    public boolean contains(Object o) {
        Node iter = header.next;
        while (iter != tailer) {
            if (iter.data != null && iter.data.equals(o)) {
                return true;
            }
            iter = iter.next;
        }
        return false;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) {
            Node temp1 = header.next;
            Node temp2 = temp1.next;
            header.next = temp2;
            temp2.prev = header;
            temp1.next = null;
            temp1.prev = null;
        } else if (index == size - 1) {
            Node temp1 = tailer.prev;
            Node temp2 = temp1.prev;
            temp2.next = tailer;
            tailer.prev = temp2;
            temp1.next = null;
            temp1.prev = null;
        } else {
            Node iter = header.next;
            for (int i = 0; i < index; i++) {
                iter = iter.next;
            }
            Node temp1 = iter.prev;
            Node temp2 = iter.next;
            temp1.next = temp2;
            temp2.prev = temp1;
            iter.next = null;
            iter.prev = null;
        }
        size--;
    }
}
