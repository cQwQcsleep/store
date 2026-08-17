package com.android.tools.r8.internal;

import defpackage.x0g;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3150yu extends ArrayDeque {
    public boolean b;

    public C3150yu(List list) {
        super(list);
        this.b = false;
    }

    public static C3150yu a(Object... objArr) {
        C3150yu c3150yu = new C3150yu(Arrays.asList(objArr));
        c3150yu.b = true;
        return c3150yu;
    }

    @Override // java.util.ArrayDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque, java.util.Queue
    public final boolean add(Object obj) {
        if (!this.b) {
            return super.add(obj);
        }
        x0g.a("Modification not allowed on immutable structure");
        return false;
    }

    @Override // java.util.ArrayDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final boolean addAll(Collection collection) {
        if (!this.b) {
            return super.addAll(collection);
        }
        x0g.a("Modification not allowed on immutable structure");
        return false;
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final void addFirst(Object obj) {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final void addLast(Object obj) {
        if (this.b) {
            x0g.a("Modification not allowed on immutable structure");
        } else {
            super.addLast(obj);
        }
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final Object pop() {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final void push(Object obj) {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.AbstractCollection, java.util.Collection, java.util.Deque
    public final boolean remove(Object obj) {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.AbstractCollection, java.util.Collection
    public final boolean removeAll(Collection collection) {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final Object removeFirst() {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final boolean removeFirstOccurrence(Object obj) {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Collection
    public final boolean removeIf(Predicate predicate) {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final Object removeLast() {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Deque
    public final boolean removeLastOccurrence(Object obj) {
        throw new Kk0("Modification not allowed on immutable structure");
    }

    @Override // java.util.ArrayDeque, java.util.Deque, java.util.Queue
    public final Object remove() {
        throw new Kk0("Modification not allowed on immutable structure");
    }
}
