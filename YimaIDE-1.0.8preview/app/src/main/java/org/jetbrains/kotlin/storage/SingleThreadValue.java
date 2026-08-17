package org.jetbrains.kotlin.storage;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
class SingleThreadValue<T> {
    private final Thread thread = Thread.currentThread();
    private final T value;

    public SingleThreadValue(T t) {
        this.value = t;
    }

    public T getValue() {
        if (hasValue()) {
            return this.value;
        }
        k2d.a("No value in this thread (hasValue should be checked before)");
        return null;
    }

    public boolean hasValue() {
        return this.thread == Thread.currentThread();
    }
}
