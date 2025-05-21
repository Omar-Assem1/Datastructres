package arraybased;

import interfaces.IQueue;
import interfaces.IArrayBased;
import java.util.*;

public class ArrayQueue implements IQueue, IArrayBased {
    private Object[] data;
    private int size;
    private final int capacity;

    public ArrayQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        this.capacity = capacity;
        this.data = new Object[capacity];
        this.size = 0;
    }

    @Override
    public void enqueue(Object item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        for (int i = size; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = item;
        size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        Object item = data[size - 1];
        data[size - 1] = null;
        size--;
        return item;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public void addToRear(Object item) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        data[size++] = item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private static Object parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return input;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter capacity for ArrayQueue:");
            int capacity = Integer.parseInt(scanner.nextLine().trim());
            ArrayQueue queue = new ArrayQueue(capacity);

            String inputLine = scanner.nextLine().trim();
            inputLine = inputLine.replaceAll("[\\[\\]]", "");
            String[] elements = inputLine.split(",");

            for (String e : elements) {
                e = e.trim();
                if (!e.isEmpty()) {
                    queue.addToRear(parseInput(e));
                }
            }

            String command = scanner.nextLine().trim();

            switch (command) {
                case "enqueue":
                    if (scanner.hasNextLine()) {
                        Object value = parseInput(scanner.nextLine().trim());
                        queue.enqueue(value);
                        System.out.println(queue);
                    } else {
                        System.out.println("Error");
                    }
                    break;

                case "dequeue":
                    try {
                        queue.dequeue();
                        System.out.println(queue);
                    } catch (NoSuchElementException e) {
                        System.out.println("Error");
                    }
                    break;

                case "size":
                    System.out.println(queue.size());
                    break;

                case "isEmpty":
                    System.out.println(queue.isEmpty() ? "True" : "False");
                    break;

                default:
                    System.out.println("Error: Unknown command");
            }
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            scanner.close();
        }
    }
}
