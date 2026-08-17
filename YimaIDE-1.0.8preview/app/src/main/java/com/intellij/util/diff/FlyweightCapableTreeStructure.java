package com.intellij.util.diff;

import com.intellij.openapi.util.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface FlyweightCapableTreeStructure<T> {
    void disposeChildren(T[] tArr, int i);

    int getChildren(T t, Ref<T[]> ref);

    int getEndOffset(T t);

    T getParent(T t);

    T getRoot();

    int getStartOffset(T t);

    CharSequence toString(T t);
}
