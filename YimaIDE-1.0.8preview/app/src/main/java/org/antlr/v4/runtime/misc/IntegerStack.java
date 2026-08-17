package org.antlr.v4.runtime.misc;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class IntegerStack extends IntegerList {
    public IntegerStack() {
    }

    public final int peek() {
        return get(size() - 1);
    }

    public final int pop() {
        return removeAt(size() - 1);
    }

    public final void push(int i) {
        add(i);
    }

    public IntegerStack(int i) {
        super(i);
    }

    public IntegerStack(IntegerStack integerStack) {
        super(integerStack);
    }
}
