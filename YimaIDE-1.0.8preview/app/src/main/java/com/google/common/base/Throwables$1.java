package com.google.common.base;

import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class Throwables$1 extends AbstractList<StackTraceElement> {
    final /* synthetic */ Throwable val$t;

    public Throwables$1(Throwable th) {
        this.val$t = th;
    }

    @Override // java.util.AbstractList, java.util.List
    public StackTraceElement get(int i) {
        Method methodAccess$000 = Throwables.access$000();
        Objects.requireNonNull(methodAccess$000);
        Object objAccess$100 = Throwables.access$100();
        Objects.requireNonNull(objAccess$100);
        return (StackTraceElement) Throwables.access$200(methodAccess$000, objAccess$100, new Object[]{this.val$t, Integer.valueOf(i)});
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        Method methodAccess$300 = Throwables.access$300();
        Objects.requireNonNull(methodAccess$300);
        Object objAccess$100 = Throwables.access$100();
        Objects.requireNonNull(objAccess$100);
        return ((Integer) Throwables.access$200(methodAccess$300, objAccess$100, new Object[]{this.val$t})).intValue();
    }
}
