package com.intellij.util.containers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;
import java.util.RandomAccess;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Stack<T> extends ArrayList<T> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "init";
        } else {
            objArr[0] = "items";
        }
        objArr[1] = "com/intellij/util/containers/Stack";
        objArr[2] = CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME;
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @SafeVarargs
    public Stack(T... tArr) {
        super(tArr.length);
        if (tArr == null) {
            $$$reportNull$$$0(1);
        }
        for (T t : tArr) {
            push(t);
        }
    }

    public boolean empty() {
        return isEmpty();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        if (!(obj instanceof RandomAccess) || !(obj instanceof List)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        if (size() != list.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Objects.equals(list.get(i), get(i))) {
                return false;
            }
        }
        return true;
    }

    public T peek() {
        int size = size();
        if (size != 0) {
            return get(size - 1);
        }
        throw new EmptyStackException();
    }

    public T pop() {
        int size = size();
        if (size != 0) {
            return remove(size - 1);
        }
        throw new EmptyStackException();
    }

    public void push(T t) {
        add(t);
    }

    public Stack(int i) {
        super(i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Stack(Collection<? extends T> collection) {
        super(collection);
        if (collection == null) {
            $$$reportNull$$$0(0);
        }
    }

    public Stack() {
    }
}
