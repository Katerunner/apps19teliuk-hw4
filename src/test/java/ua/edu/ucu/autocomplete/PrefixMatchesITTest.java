
package ua.edu.ucu.autocomplete;

import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import ua.edu.ucu.tries.RWayTrie;

/**
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";

        Iterable<String> result = pm.wordsWithPrefix(pref);

        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;

        Iterable<String> result = pm.wordsWithPrefix(pref, k);

        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_Operations() {
        PrefixMatches pm2 = new PrefixMatches();
        pm2.load("abc abce", "abcd", "abcde", "abcdef");
        String pref = "abc";
        int k = 2;
        String[] expResult = {"abce", "abcd", "abcde"};
        String[] expResult2 = {"abc", "abce", "abcd", "abcde", "abcdef"};
        assertThat(pm2.wordsWithPrefix(""), containsInAnyOrder(expResult2));
        assertTrue(pm2.contains("abc"));
        assertTrue(pm2.delete("abc"));
        assertEquals(4, pm2.size());
        Iterable<String> result = pm2.wordsWithPrefix(pref, k);
        assertThat(result, containsInAnyOrder(expResult));
    }

}
