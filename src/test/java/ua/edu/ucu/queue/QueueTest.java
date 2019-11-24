package ua.edu.ucu.queue;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueueTest {
    private Queue queue;

    @Before
    public void setUp() {
        queue = new Queue();
    }


    @Test
    public void testEnqueuePeek() {
        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
            assertEquals(0, queue.peek());
        }
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testDequeue() {
        for (int i = 0; i < 15; i++) {
            queue.enqueue(i);
        }
        for (int j = 0; j < 15; j++) {
            assertFalse(queue.isEmpty());
            assertEquals(j, queue.dequeue());
        }
        assertTrue(queue.isEmpty());
    }
}