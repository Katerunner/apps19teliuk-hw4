package ua.edu.ucu.tries;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RWayTrieTest {
    private RWayTrie tree;
    private Tuple t1;
    private Tuple t2;
    private Tuple t3;


    @Before
    public void setUp() {
        Tuple t1 = new Tuple("qwe", 3);
        Tuple t2 = new Tuple("qwerty", 6);
        Tuple t3 = new Tuple("qwort", 5);
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.tree = new RWayTrie();
    }

    @Test
    public void testAdd() {
        tree.add(t1);
        tree.add(t2);
        String[] expected = new String[]{"qwe", "qwerty"};
        ArrayList<String> actual = new ArrayList<>();
        for (String word : tree.words()) {
            actual.add(word);
        }
        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    public void testContains() {
        tree.add(t1);
        tree.add(t2);
        tree.add(t3);
        assertTrue(tree.contains("qwe"));
        assertTrue(tree.contains("qwerty"));
        assertTrue(tree.contains("qwort"));
    }

    @Test
    public void testDeleteSize() {
        assertEquals(tree.size(), 0);
        tree.add(t1);
        assertEquals(tree.size(), 1);
        tree.delete(t1.term);
        assertEquals(tree.size(), 0);
    }
}