package ua.edu.ucu.queue;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableLinkedList small;
    private Object[] addon;
    private ImmutableLinkedList big;
    private ImmutableLinkedList noElements;

    @Before
    public void setUp() throws Exception {
        small = new ImmutableLinkedList(new Object[]{7, 2, 9, 5});
        big = new ImmutableLinkedList(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6});
        noElements = new ImmutableLinkedList();
        addon = new Object[]{666, 777, 888};
    }


    @Test
    public void testAdd() {
        Object[] expected1 = new Object[]{7, 2, 9, 5, 666};
        Object[] expected2 = new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6, 666};
        Object[] expected3 = new Object[]{666};
        ImmutableList actual1 = small.add(666);
        ImmutableList actual2 = big.add(666);
        ImmutableList actual3 = noElements.add(666);
        assertArrayEquals(expected1, actual1.toArray());
        assertArrayEquals(expected2, actual2.toArray());
        assertArrayEquals(expected3, actual3.toArray());
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testAddMany() {
        Object[] expected1 = new Object[]{7, 2, 666, 9, 5};
        Object[] expected2 = new Object[]{12, 3, 666, 5, 7, 65, 4, 645, 7, 56, 33, 6};
        Object[] expected3 = new Object[]{666};
        ImmutableList actual1 = small.add(2, 666);
        ImmutableList actual2 = big.add(2, 666);
        ImmutableList actual3 = noElements.add(0, 666);
        assertArrayEquals(expected1, actual1.toArray());
        assertArrayEquals(expected2, actual2.toArray());
        assertArrayEquals(expected3, actual3.toArray());
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testAddAll() {
        Object[] expected1 = new Object[]{7, 2, 9, 5, 666, 777, 888};
        Object[] expected2 = new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6, 666, 777, 888};
        Object[] expected3 = new Object[]{666, 777, 888};
        ImmutableList actual1 = small.addAll(addon);
        ImmutableList actual2 = big.addAll(addon);
        ImmutableList actual3 = noElements.addAll(addon);
        assertArrayEquals(expected1, actual1.toArray());
        assertArrayEquals(expected2, actual2.toArray());
        assertArrayEquals(expected3, actual3.toArray());
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testAddAllMany() {
        Object[] expected1 = new Object[]{666, 777, 888, 7, 2, 9, 5};
        Object[] expected2 = new Object[]{12, 3, 666, 777, 888, 5, 7, 65, 4, 645, 7, 56, 33, 6};
        Object[] expected3 = new Object[]{666, 777, 888};
        ImmutableList actual1 = small.addAll(0, addon);
        ImmutableList actual2 = big.addAll(2, addon);
        ImmutableList actual3 = noElements.addAll(0, addon);
        assertArrayEquals(expected1, actual1.toArray());
        assertArrayEquals(expected2, actual2.toArray());
        assertArrayEquals(expected3, actual3.toArray());
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());

    }

    @Test
    public void testGet() {
        Object expected1 = 2;
        Object expected2 = 12;
        Object actual1 = small.get(1);
        Object actual2 = big.get(0);
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOOBGet() {
        noElements.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOOBGetAddNegative() {
        small.addAll(-12, addon);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOOBGetAddPositive() {
        small.addAll(212, addon);
    }

    @Test
    public void testRemove() {
        Object[] actual = big.remove(1).toArray();
        Object[] expected = new Object[]{12, 5, 7, 65, 4, 645, 7, 56, 33, 6};
        assertArrayEquals(expected, actual);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOOBRemove() {
        noElements.remove(134);
    }

    @Test
    public void testSet() {
        Object[] actual = big.set(1, 666).toArray();
        Object[] expected = new Object[]{12, 666, 5, 7, 65, 4, 645, 7, 56, 33, 6};
        assertArrayEquals(expected, actual);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testOOBSet() {
        noElements.set(134, 2);
    }


    @Test
    public void testIndexOf() {
        int actual1 = big.indexOf(12);
        int expected1 = 0;
        int actual2 = big.indexOf(12312312);
        int expected2 = -1;
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testSize() {
        int actual1 = big.size();
        int expected1 = 11;
        int actual2 = noElements.size();
        int expected2 = 0;
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testClear() {
        Object[] actual = big.clear().toArray();
        Object[] expected = new Object[]{};
        assertArrayEquals(expected, actual);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(noElements.isEmpty());
        assertFalse(big.isEmpty());
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testToArray() {
        Object[] expected = new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6};
        Object[] actual = big.toArray();
        assertArrayEquals(expected, actual);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testToString() {
        String expected = "12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6";
        String actual = big.toString();
        assertEquals(expected, actual);
        assertEquals(noElements.toString(), "");
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testAddFirst() {
        Object[] expected1 = new Object[]{666, 7, 2, 9, 5};
        Object[] expected2 = new Object[]{666, 12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6};
        Object[] expected3 = new Object[]{666};
        ImmutableList actual1 = small.addFirst(666);
        ImmutableList actual2 = big.addFirst(666);
        ImmutableList actual3 = noElements.addFirst(666);
        assertArrayEquals(expected1, actual1.toArray());
        assertArrayEquals(expected2, actual2.toArray());
        assertArrayEquals(expected3, actual3.toArray());
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testAddLast() {
        Object[] expected1 = new Object[]{7, 2, 9, 5, 666};
        Object[] expected2 = new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6, 666};
        Object[] expected3 = new Object[]{666};
        ImmutableList actual1 = small.addLast(666);
        ImmutableList actual2 = big.addLast(666);
        ImmutableList actual3 = noElements.addLast(666);
        assertArrayEquals(expected1, actual1.toArray());
        assertArrayEquals(expected2, actual2.toArray());
        assertArrayEquals(expected3, actual3.toArray());
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testGetFirst() {
        Object expected1 = 7;
        Object expected2 = 12;
        Object actual1 = small.getFirst();
        Object actual2 = big.getFirst();
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void testRemoveFirst() {
        Object[] actual = big.removeFirst().toArray();
        Object[] expected = new Object[]{3, 5, 7, 65, 4, 645, 7, 56, 33, 6};
        assertArrayEquals(expected, actual);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void removeLast() {
        Object[] actual = big.removeLast().toArray();
        Object[] expected = new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33};
        assertArrayEquals(expected, actual);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }

    @Test
    public void getLast() {
        Object expected1 = 5;
        Object expected2 = 6;
        Object actual1 = small.getLast();
        Object actual2 = big.getLast();
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
        assertArrayEquals(new Object[]{12, 3, 5, 7, 65, 4, 645, 7, 56, 33, 6}, big.toArray());
    }
}
