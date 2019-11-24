package ua.edu.ucu.tries;

import org.junit.Test;

import static org.junit.Assert.*;

public class RWayTrieTest {

    @Test
    public void testAdd() {
        Tuple t1 = new Tuple("qwe", 3);
        Tuple t2 = new Tuple("qwerty", 6);
        Tuple t3 = new Tuple("qwort", 5);

        RWayTrie tree = new RWayTrie();
        tree.add(t1);
        tree.add(t2);
        System.out.println(tree.toString());

    }

    @Test
    public void testContains() {
    }

    @Test
    public void testDelete() {
    }
}