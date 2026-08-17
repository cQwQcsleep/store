package io.github.rosemoe.sora.util;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class MutableInt {
    public int value;

    public MutableInt(int i) {
        this.value = i;
    }

    public int decreaseAndGet() {
        int i = this.value - 1;
        this.value = i;
        return i;
    }

    public void increase() {
        this.value++;
    }
}
