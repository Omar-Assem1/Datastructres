package tester;

import interfaces.IQueue;
import arraybased.ArrayQueue;
import linkedbased.LinkedListQueue;
import java.util.*;

public class QueueTester {
    private static Object parseInput(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return input;
        }
    }

    private static void testQueue(IQueue queue, Scanner scanner) {
        try {
            String input = scanner.nextLine().trim();
            if (!input.equals("[]")) {
                String[] elements = input.substring(1, input.length() - 1).split(", ");
                for (int i = elements.length - 1; i >= 0; i--) {
                    try {
                        queue.enqueue(Integer.parseInt(elements[i]));
                    } catch (NumberFormatException e) {
                        queue.enqueue(elements[i]);
                    }
                }
            }
            String operation = scanner.nextLine().trim();

            switch (operation) {
                case "enqueue":
                    if (scanner.hasNextLine()) {
                        try {
                            Object element = parseInput(scanner.nextLine().trim());
                            queue.enqueue(element);
                            System.out.println(queue);
                        } catch (Exception e) {
                            System.out.println("Error");
                        }
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

                case "isEmpty":
                    System.out.println(queue.isEmpty() ? "True" : "False");
                    break;

                case "size":
                    System.out.println(queue.size());
                    break;

                default:
                    System.out.println("Error");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter capacity for ArrayQueue:");
            int capacity = Integer.parseInt(scanner.nextLine().trim());
            IQueue arrayQueue = new ArrayQueue(capacity);
            IQueue linkedQueue = new LinkedListQueue();

            System.out.println("Testing ArrayQueue:");
            System.out.println("Enter initial elements (e.g., [1, 2, 3]), operation, and optional element:");
            testQueue(arrayQueue, scanner);

            scanner = new Scanner(System.in);
            System.out.println("\nTesting LinkedListQueue:");
            System.out.println("Enter initial elements (e.g., [1, 2, 3]), operation, and optional element:");
            testQueue(linkedQueue, scanner);
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            scanner.close();
        }
    }
}
