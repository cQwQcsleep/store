package io.github.rosemoe.sora.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class TrieTree<T> {
    public final Node<T> root = new Node<>();
    private int maxLen = 0;

    public static class LinkedPair<V> {
        public char first;
        public LinkedPair<V> next;
        public V second;
    }

    public static class Node<T> {
        public final HashCharMap<Node<T>> map = new HashCharMap<>();
        public T token;
    }

    private void addInternal(Node<T> node, CharSequence charSequence, int i, int i2, T t) {
        char cCharAt = charSequence.charAt(i);
        Node<T> node2 = node.map.get(cCharAt);
        if (node2 == null) {
            node2 = new Node<>();
            node.map.put(cCharAt, node2);
        }
        Node<T> node3 = node2;
        if (i2 == 1) {
            node3.token = t;
        } else {
            addInternal(node3, charSequence, i + 1, i2 - 1, t);
        }
    }

    private T getInternal(Node<T> node, CharSequence charSequence, int i, int i2) {
        if (i2 == 0) {
            return node.token;
        }
        Node<T> node2 = node.map.get(charSequence.charAt(i));
        if (node2 == null) {
            return null;
        }
        return getInternal(node2, charSequence, i + 1, i2 - 1);
    }

    public T get(CharSequence charSequence, int i, int i2) {
        if (i2 > this.maxLen) {
            return null;
        }
        return getInternal(this.root, charSequence, i, i2);
    }

    public void put(String str, T t) {
        this.maxLen = Math.max(str.length(), this.maxLen);
        addInternal(this.root, str, 0, str.length(), t);
    }

    public static class HashCharMap<V> {
        private static final int CAPACITY = 64;
        private final LinkedPair<V>[] columns = new LinkedPair[64];
        private final LinkedPair<V>[] ends = new LinkedPair[64];

        private static int position(int i) {
            return Math.abs(i ^ ((i << 6) * ((i & 1) != 0 ? 3 : 1))) % 64;
        }

        public V get(char c) {
            for (LinkedPair<V> linkedPair = this.columns[position(c)]; linkedPair != null; linkedPair = linkedPair.next) {
                if (linkedPair.first == c) {
                    return linkedPair.second;
                }
            }
            return null;
        }

        public void put(char c, V v) {
            int iPosition = position(c);
            LinkedPair<V>[] linkedPairArr = this.ends;
            if (linkedPairArr[iPosition] == null) {
                LinkedPair<V>[] linkedPairArr2 = this.columns;
                LinkedPair<V> linkedPair = new LinkedPair<>();
                linkedPairArr[iPosition] = linkedPair;
                linkedPairArr2[iPosition] = linkedPair;
                LinkedPair<V> linkedPair2 = this.ends[iPosition];
                linkedPair2.first = c;
                linkedPair2.second = v;
                return;
            }
            LinkedPair<V> linkedPair3 = get(c, iPosition);
            if (linkedPair3 == null) {
                LinkedPair<V> linkedPair4 = this.ends[iPosition];
                LinkedPair<V> linkedPair5 = new LinkedPair<>();
                linkedPair4.next = linkedPair5;
                this.ends[iPosition] = linkedPair5;
                linkedPair3 = linkedPair5;
            }
            linkedPair3.first = c;
            linkedPair3.second = v;
        }

        private LinkedPair<V> get(char c, int i) {
            for (LinkedPair<V> linkedPair = this.columns[i]; linkedPair != null; linkedPair = linkedPair.next) {
                if (linkedPair.first == c) {
                    return linkedPair;
                }
            }
            return null;
        }
    }

    public void put(CharSequence charSequence, int i, int i2, T t) {
        this.maxLen = Math.max(this.maxLen, i2);
        addInternal(this.root, charSequence, i, i2, t);
    }
}
