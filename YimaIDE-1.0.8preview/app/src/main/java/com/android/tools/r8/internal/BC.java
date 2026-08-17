package com.android.tools.r8.internal;

import defpackage.hkh;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class BC {
    public static final /* synthetic */ boolean a = true;

    public static Object a(ListIterator listIterator) {
        if (!listIterator.hasNext()) {
            return null;
        }
        Object next = listIterator.next();
        Object objPrevious = listIterator.previous();
        if (a || objPrevious == next) {
            return next;
        }
        x1f.a();
        return null;
    }

    public static Object b(ListIterator listIterator) {
        if (!listIterator.hasPrevious()) {
            return null;
        }
        Object objPrevious = listIterator.previous();
        Object next = listIterator.next();
        if (a || objPrevious == next) {
            return objPrevious;
        }
        x1f.a();
        return null;
    }

    public static Object a(L5 l5, Predicate predicate) {
        while (l5.c.hasPrevious()) {
            Object objPrevious = l5.previous();
            if (predicate.test(objPrevious)) {
                return objPrevious;
            }
        }
        return null;
    }

    public static Object a(Iterator it, Predicate predicate) {
        while (it.hasNext()) {
            Object next = it.next();
            if (predicate.test(next)) {
                return next;
            }
        }
        return null;
    }

    public static int a(Iterator it) {
        final C1131bA c1131bA = new C1131bA();
        it.forEachRemaining(new Consumer() { // from class: ml0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c1131bA.c();
            }
        });
        return c1131bA.a();
    }

    public static Object a(ListIterator listIterator, Predicate predicate) {
        while (listIterator.hasPrevious()) {
            Object objPrevious = listIterator.previous();
            if (predicate.test(objPrevious)) {
                return objPrevious;
            }
        }
        hkh.a();
        return null;
    }

    public static void a(InterfaceC0968Xw interfaceC0968Xw, int i) {
        int i2 = 0;
        if (i >= 0) {
            while (i2 < i) {
                interfaceC0968Xw.next();
                i2++;
            }
        } else {
            while (i2 > i) {
                interfaceC0968Xw.previous();
                i2--;
            }
        }
    }
}
