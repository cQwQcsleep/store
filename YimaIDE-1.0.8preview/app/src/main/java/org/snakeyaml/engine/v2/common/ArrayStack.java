package org.snakeyaml.engine.v2.common;

import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class ArrayStack<T> {
    private final ArrayList<T> stack;

    public ArrayStack(int i) {
        this.stack = new ArrayList<>(i);
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public T pop() {
        ArrayList<T> arrayList = this.stack;
        return arrayList.remove(arrayList.size() - 1);
    }

    public void push(T t) {
        this.stack.add(t);
    }
}
