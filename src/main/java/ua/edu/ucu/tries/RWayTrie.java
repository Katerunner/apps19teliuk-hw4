package ua.edu.ucu.tries;

import ua.edu.ucu.queue.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;


public class RWayTrie implements Trie {

    private char val;
    private ArrayList<RWayTrie> children;
    private int numOfLettersAbove;

    public RWayTrie() {
        children = new ArrayList<>();
        val = '☼';
        numOfLettersAbove = 0;
    }

    private RWayTrie(char value) {
        children = new ArrayList<>();
        val = value;
        numOfLettersAbove = 0;
    }

    @Override
    public void add(Tuple t) {
        if (t.weight > 2) {
            RWayTrie curTree = this;
            for (int i = 0; i < t.term.length(); i++) {
                boolean noBreak = true;
                char curChar = t.term.charAt(i);
                int j = 0;
                while (j < curTree.children.size()) {
                    RWayTrie curChild = curTree.children.get(j);
                    if (curChild.val == curChar) {
                        noBreak = false;
                        curTree = curChild;
                        break;
                    }
                    j++;
                }
                if (noBreak) {
                    RWayTrie newTree = new RWayTrie(curChar);
                    curTree.children.add(newTree);
                    curTree = newTree;
                }
            }
            curTree.numOfLettersAbove = t.weight;
        }
    }

    @Override
    public boolean contains(String word) {
        RWayTrie curTree = this;
        for (int i = 0; i < word.length(); i++) {
            boolean kill = true;
            char curChar = word.charAt(i);
            for (int j = 0; j < curTree.children.size(); j++) {
                RWayTrie curChild = curTree.children.get(j);
                if (curChild.val == curChar) {
                    kill = false;
                    curTree = curChild;
                    break;
                }
            }
            if (kill) {
                return false;
            }
        }
        return word.length() == curTree.numOfLettersAbove;
    }


    @Override
    public boolean delete(String word) {
        if (!contains(word)) {
            return false;
        }
        Stack<RWayTrie[]> miniCache = new Stack<>();
        RWayTrie curTree = this;
        for (int i = 0; i < word.length(); i++) {
            char curChar = word.charAt(i);
            for (int j = 0; j < curTree.children.size(); j++) {
                RWayTrie curChild = curTree.children.get(j);
                if (curChild.val == curChar) {
                    miniCache.push(new RWayTrie[]{curTree, curChild});
                    curTree = curChild;
                    break;
                }
            }
        }
        if (miniCache.peek()[1].children.size() != 0) {
            miniCache.peek()[1].numOfLettersAbove = 0;
            return true;
        }
        if (miniCache.peek()[1].numOfLettersAbove == word.length()) {
            RWayTrie[] curRes = miniCache.pop();
            curRes[1].numOfLettersAbove = 0;
            while (curRes[1].numOfLettersAbove == 0) {
                curRes[0].children.remove(curRes[1]);
                if (miniCache.isEmpty()) {
                    break;
                }
                curRes = miniCache.pop();
            }
        }
        return true;
    }


    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        RWayTrie curTree = this;
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);
            for (int j = 0; j < curTree.children.size(); j++) {
                RWayTrie curChild = curTree.children.get(j);
                if (curChild.val == curChar) {
                    curTree = curChild;
                    break;
                }
            }
        }
        Queue mainQueue = new Queue();
        Queue preResQueue = new Queue();
        fillQueue(curTree, s, mainQueue);
        while (!mainQueue.isEmpty()) {
            String candidate = (String) mainQueue.dequeue();
            if (contains(candidate)) {
                preResQueue.enqueue(candidate);
            }
        }
        Object[] arrQueue = preResQueue.toArray();
        String[] result = Arrays.copyOf(arrQueue,
                arrQueue.length, String[].class);
        return () -> Arrays.stream(result).iterator();
    }

    private void fillQueue(RWayTrie curTree, String pre, Queue queue) {
        if (curTree == null) {
            return;
        }
        if (curTree.val != '☼') {
            queue.enqueue(pre);
        }
        for (int i = 0; i < curTree.children.size(); i++) {
            RWayTrie curChild = curTree.children.get(i);
            fillQueue(curChild, pre + curChild.val, queue);
        }
    }

    @Override
    public int size() {
        int res = 0;
        for (int i = 0; i < children.size(); i++) {
            res += children.get(i).size();
        }
        if (numOfLettersAbove != 0) {
            res += 1;
        }
        return res;
    }
}