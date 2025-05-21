package tests;

import interfaces.IQueue;
import arraybased.ArrayQueue;
import linkedbased.LinkedListQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    static Stream<IQueue> queueProvider() {
        return Stream.of(
            new LinkedListQueue(),
            new ArrayQueue(10)
        );
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testEnqueueAndDequeue(IQueue queue) {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(3, queue.size());
        assertEquals("[3, 2, 1]", queue.toString());
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.size());
        assertEquals("[3, 2]", queue.toString());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testIsEmpty(IQueue queue) {
        assertTrue(queue.isEmpty());
        queue.enqueue("test");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testSize(IQueue queue) {
        assertEquals(0, queue.size());
        queue.enqueue(1);
        assertEquals(1, queue.size());
        queue.enqueue(2);
        assertEquals(2, queue.size());
        queue.dequeue();
        assertEquals(1, queue.size());
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testDequeueEmptyThrowsException(IQueue queue) {
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }

    @Test
    void testArrayQueueFullThrowsException() {
        ArrayQueue queue = new ArrayQueue(2);
        queue.enqueue(1);
        queue.enqueue(2);
        assertThrows(IllegalStateException.class, () -> queue.enqueue(3));
    }

    @Test
    void testArrayQueueInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new ArrayQueue(0));
        assertThrows(IllegalArgumentException.class, () -> new ArrayQueue(-1));
    }

    @ParameterizedTest
    @MethodSource("queueProvider")
    void testMixedTypes(IQueue queue) {
        queue.enqueue(1);
        queue.enqueue("two");
        queue.enqueue(3.14);
        assertEquals(3, queue.size());
        assertEquals("[3.14, two, 1]", queue.toString());
        assertEquals(1, queue.dequeue());
        assertEquals("two", queue.dequeue());
        assertEquals(3.14, queue.dequeue());
    }
}
