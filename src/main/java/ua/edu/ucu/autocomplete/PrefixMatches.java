package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author andrii
 */
public class PrefixMatches {

    private Trie mainTrie;

    public PrefixMatches() {
        this.mainTrie = new RWayTrie();
    }

    public PrefixMatches(Trie trie) {
        this.mainTrie = trie;
    }

    public int load(String... strings) {
        int numberOfWords = strings.length;
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].contains(" ")) {
                String[] splited = strings[i].split(" ");
                numberOfWords += splited.length - 1;
                for (int j = 0; j < splited.length; j++) {
                    mainTrie.add(new Tuple(splited[j], splited[j].length()));
                }

            } else {
                mainTrie.add(new Tuple(strings[i], strings[i].length()));
            }
        }
        return numberOfWords;
    }

    public boolean contains(String word) {
        return mainTrie.contains(word);
    }

    public boolean delete(String word) {
        return mainTrie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return mainTrie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        ArrayList<Integer> lengthCache = new ArrayList<>();
        ArrayList<String> preResult = new ArrayList<>();
        int numberOfDiff = 0;
        for (String word :
                mainTrie.wordsWithPrefix(pref)) {
            if (lengthCache.contains(word.length())) {
                preResult.add(word);
            } else {
                preResult.add(word);
                lengthCache.add(word.length());
                numberOfDiff += 1;
            }
            if (numberOfDiff == k) {
                break;
            }
        }
        String[] result = Arrays.copyOf(preResult.toArray(), preResult.size(), String[].class);
        return Arrays.asList(result);
//        return () -> Arrays.stream(result).iterator();
    }

    public int size() {
        return mainTrie.size();
    }
}