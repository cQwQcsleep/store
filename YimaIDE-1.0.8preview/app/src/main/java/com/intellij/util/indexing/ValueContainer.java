package com.intellij.util.indexing;

import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class ValueContainer<Value> {

    @FunctionalInterface
    public interface ContainerAction<T> {
        boolean perform(int i, T t);
    }

    public interface IntIterator {
        boolean hasNext();

        int next();
    }

    public interface ValueIterator<Value> extends Iterator<Value> {
        IntIterator getInputIdsIterator();
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        objArr[0] = "action";
        objArr[1] = "com/intellij/util/indexing/ValueContainer";
        if (i != 1) {
            objArr[2] = "forEach";
        } else {
            objArr[2] = "process";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public final synchronized boolean forEach(ContainerAction<? super Value> containerAction) {
        if (containerAction == null) {
            try {
                $$$reportNull$$$0(0);
            } catch (Throwable th) {
                throw th;
            }
        }
        ValueIterator<Value> valueIterator = getValueIterator();
        while (valueIterator.hasNext()) {
            Value next = valueIterator.next();
            IntIterator inputIdsIterator = valueIterator.getInputIdsIterator();
            while (inputIdsIterator.hasNext()) {
                if (!containerAction.perform(inputIdsIterator.next(), next)) {
                    return false;
                }
            }
        }
        return true;
    }

    public abstract ValueIterator<Value> getValueIterator();
}
