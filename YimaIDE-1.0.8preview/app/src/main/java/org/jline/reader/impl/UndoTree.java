package org.jline.reader.impl;

import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class UndoTree<T> {
    private UndoTree<T>.Node current;
    private final UndoTree<T>.Node parent;
    private final Consumer<T> state;

    public class Node {
        private UndoTree<T>.Node left = null;
        private UndoTree<T>.Node right = null;
        private final T state;

        public Node(T t) {
            this.state = t;
        }
    }

    public UndoTree(Consumer<T> consumer) {
        this.state = consumer;
        UndoTree<T>.Node node = new Node(null);
        this.parent = node;
        ((Node) node).left = node;
        clear();
    }

    public boolean canRedo() {
        return ((Node) this.current).right != null;
    }

    public boolean canUndo() {
        return ((Node) this.current).left != this.parent;
    }

    public void clear() {
        this.current = this.parent;
    }

    public void newState(T t) {
        UndoTree<T>.Node node = new Node(t);
        ((Node) this.current).right = node;
        ((Node) node).left = this.current;
        this.current = node;
    }

    public void redo() {
        if (!canRedo()) {
            k2d.a("Cannot redo.");
            return;
        }
        UndoTree<T>.Node node = ((Node) this.current).right;
        this.current = node;
        this.state.accept((T) ((Node) node).state);
    }

    public void undo() {
        if (!canUndo()) {
            k2d.a("Cannot undo.");
            return;
        }
        UndoTree<T>.Node node = ((Node) this.current).left;
        this.current = node;
        this.state.accept((T) ((Node) node).state);
    }
}
