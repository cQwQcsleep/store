package com.intellij.util.pico;

import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class CyclicDependencyException extends PicoIntrospectionException {
    private final List stack;

    public CyclicDependencyException(Class cls) {
        super((Throwable) null);
        this.stack = new LinkedList();
        push(cls);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return "Cyclic dependency: " + this.stack.toString();
    }

    public void push(Class cls) {
        this.stack.add(cls);
    }
}
