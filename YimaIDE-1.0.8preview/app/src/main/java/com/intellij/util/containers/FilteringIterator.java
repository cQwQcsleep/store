package com.intellij.util.containers;

import com.intellij.openapi.util.Condition;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FilteringIterator implements PeekableIterator {
    private final java.util.function.Predicate<Object> condition;
    private Object current;
    private Boolean currentPassedFilter;
    private final Iterator<Object> delegate;
    private boolean isCurrentIsValid;
    private boolean isNextObtained;

    /* JADX WARN: Code duplicated, block: B:10:0x001d  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i == 1 || i == 2) {
            objArr[0] = "condition";
        } else if (i == 4 || i == 5) {
            objArr[0] = "iterator";
        } else if (i != 6) {
            objArr[0] = "delegate";
        } else {
            objArr[0] = "condition";
        }
        objArr[1] = "com/intellij/util/containers/FilteringIterator";
        if (i == 4) {
            objArr[2] = "skipNulls";
        } else if (i == 5 || i == 6) {
            objArr[2] = "create";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    @Deprecated
    public FilteringIterator(Iterator<Object> it, Condition<Object> condition) {
        if (it == null) {
            $$$reportNull$$$0(0);
        }
        if (condition == null) {
            $$$reportNull$$$0(1);
        }
        this.delegate = it;
        this.condition = condition;
    }

    private boolean isCurrentPassesFilter() {
        Boolean bool = this.currentPassedFilter;
        if (bool != null) {
            return bool.booleanValue();
        }
        boolean zTest = this.condition.test(this.current);
        this.currentPassedFilter = Boolean.valueOf(zTest);
        return zTest;
    }

    private void obtainNext() {
        if (this.isNextObtained) {
            return;
        }
        boolean zHasNext = this.delegate.hasNext();
        setCurrent(zHasNext ? this.delegate.next() : null);
        this.isCurrentIsValid = zHasNext;
        this.isNextObtained = true;
    }

    private void setCurrent(Object obj) {
        this.current = obj;
        this.currentPassedFilter = null;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        obtainNext();
        if (!this.isCurrentIsValid) {
            return false;
        }
        boolean zIsCurrentPassesFilter = isCurrentPassesFilter();
        while (!zIsCurrentPassesFilter && this.delegate.hasNext()) {
            setCurrent(this.delegate.next());
            zIsCurrentPassesFilter = isCurrentPassesFilter();
        }
        return zIsCurrentPassesFilter;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (!hasNext()) {
            z0e.a();
            return null;
        }
        Object obj = this.current;
        this.isNextObtained = false;
        return obj;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.isNextObtained) {
            g33.a();
        } else {
            this.delegate.remove();
        }
    }
}
