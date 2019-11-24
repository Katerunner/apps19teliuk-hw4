package ua.edu.ucu.queue;

public final class ImmutableLinkedList implements ImmutableList {

    private Node root;
    private int length;

    private static class Node {
        private Object data;
        private Node next;

        Node(Object d) {
            this.data = d;
            this.next = null;
        }

        Node(Object d, Node n) {
            this.data = d;
            this.next = n;
        }
    }

    public ImmutableLinkedList() {
        this.root = null;
        this.length = 0;
    }

    public ImmutableLinkedList(Object[] outside) {
        length = outside.length;
        root = new Node(outside[0]);
        Node cur = root;
        for (int i = 1; i < outside.length; i++) {
            cur.next = new Node(outside[i]);
            cur = cur.next;
        }
    }


    private void outOfBounds(int ind) {
        if (ind < 0 || ind >= length) {
            throw new IndexOutOfBoundsException();
        }
    }

    private ImmutableLinkedList copy() {
        if (isEmpty()) {
            return new ImmutableLinkedList();
        }
        ImmutableLinkedList newlist = new ImmutableLinkedList();
        newlist.root = new Node(root.data);
        newlist.length = length;
        Node cur = root.next;
        Node newcur = newlist.root;
        while (cur != null) {
            newcur.next = new Node(cur.data);
            newcur = newcur.next;
            cur = cur.next;
        }
        return newlist;
    }

    @Override
    public ImmutableLinkedList add(Object e) {
        return add(length, e);
    }

    @Override
    public ImmutableLinkedList add(int index, Object e) {

        ImmutableLinkedList newlist = copy();
        Node cur = newlist.root;
        if (index == 0) {
            newlist.root = new Node(e, cur);
        } else {
            outOfBounds(index - 1);
            int i;
            for (i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            Node before = cur;
            before.next = new Node(e, before.next);
        }
        newlist.length += 1;
        return newlist;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(length, c);
    }

    @Override
    public ImmutableLinkedList addAll(int index, Object[] c) {

        ImmutableLinkedList newlist = copy();
        Node cur = newlist.root;
        if (length == 0) {
            return new ImmutableLinkedList(c);
        }
        if (index == 0) {
            Node oldroot = cur;
            newlist.root = new Node(c[0], oldroot);
            Node newCur = newlist.root;
            for (int j = 1; j < c.length; j++) {
                newCur.next = new Node(c[j], oldroot);
                newCur = newCur.next;
            }
        } else {
            outOfBounds(index - 1);
            int i;
            for (i = 0; i < index - 1; i++) {
                cur = cur.next;
            }
            for (int j = 0; j < c.length; j++) {
                cur.next = new Node(c[j], cur.next);
                cur = cur.next;
            }
        }
        newlist.length += c.length;
        return newlist;
    }

    @Override
    public Object get(int index) {
        outOfBounds(index);
        Node cur = root;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.data;
    }

    @Override
    public ImmutableLinkedList remove(int index) {
        outOfBounds(index);
        ImmutableLinkedList newlist = copy();
        Node cur = newlist.root;
        if (index == 0) {
            newlist.root = cur.next;
            newlist.length--;
            return newlist;
        }
        for (int i = 1; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        newlist.length--;
        return newlist;
    }

    @Override
    public ImmutableLinkedList set(int index, Object e) {
        outOfBounds(index);
        ImmutableLinkedList newlist = copy();
        Node cur = newlist.root;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.data = e;
        return newlist;
    }

    @Override
    public int indexOf(Object e) {
        ImmutableLinkedList newlist = copy();
        Node cur = newlist.root;
        for (int i = 0; i < newlist.length; i++) {
            if (cur.data.equals(e)) {
                return i;
            } else {
                cur = cur.next;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ImmutableLinkedList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];
        Node cur = root;
        for (int i = 0; i < length; i++) {
            array[i] = cur.data;
            cur = cur.next;
        }
        return array;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        Node cur = root;
        for (int i = 0; i < length; i++) {
            result.append(cur.data.toString()).append(", ");
            cur = cur.next;
        }
        result.deleteCharAt(result.length()
                - 1).deleteCharAt(result.length() - 1);

        return result.toString();
    }

    public ImmutableLinkedList addFirst(Object i) {
        return add(0, i);
    }

    public ImmutableLinkedList addLast(Object i) {
        return add(i);
    }

    public Object getFirst() {
        return get(0);
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(length - 1);
    }

    public Object getLast() {
        return get(length - 1);
    }
}
