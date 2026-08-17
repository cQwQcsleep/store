package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wf, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2958wf extends R0 implements Serializable {
    public final transient ConcurrentMap d;

    public C2958wf(ConcurrentHashMap concurrentHashMap) {
        DX.a(concurrentHashMap.isEmpty(), "the backing map (%s) must be empty", concurrentHashMap);
        this.d = concurrentHashMap;
    }

    @Override // com.android.tools.r8.internal.R0, com.android.tools.r8.internal.InterfaceC1231cQ
    public final int a(Object obj, int i) {
        AtomicInteger atomicInteger;
        int i2;
        AtomicInteger atomicInteger2;
        int i3;
        obj.getClass();
        if (i == 0) {
            return b(obj);
        }
        if (i <= 0) {
            w01.a(CX.a(i, "occurrences must be positive but was: "));
            return 0;
        }
        do {
            atomicInteger = (AtomicInteger) AbstractC1739iN.a(this.d, obj);
            if (atomicInteger == null && (atomicInteger = (AtomicInteger) this.d.putIfAbsent(obj, new AtomicInteger(i))) == null) {
                return 0;
            }
            do {
                i2 = atomicInteger.get();
                if (i2 == 0) {
                    atomicInteger2 = new AtomicInteger(i);
                    if (this.d.putIfAbsent(obj, atomicInteger2) == null) {
                        break;
                    }
                } else {
                    long j = ((long) i2) + ((long) i);
                    i3 = (int) j;
                    if (j != i3) {
                        throw new ArithmeticException("overflow: checkedAdd(" + i2 + ", " + i + ")");
                    }
                    try {
                    } catch (ArithmeticException unused) {
                        dn0.a("Overflow adding ", i, " occurrences to a count of ", i2);
                        return 0;
                    }
                    dn0.a("Overflow adding ", i, " occurrences to a count of ", i2);
                    return 0;
                }
            } while (!atomicInteger.compareAndSet(i2, i3));
            return i2;
        } while (!this.d.replace(obj, atomicInteger, atomicInteger2));
        return 0;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(int i, Object obj) {
        int i2;
        int iMax;
        if (i == 0) {
            return b(obj);
        }
        if (i <= 0) {
            w01.a(CX.a(i, "occurrences must be positive but was: "));
            return 0;
        }
        AtomicInteger atomicInteger = (AtomicInteger) AbstractC1739iN.a(this.d, obj);
        if (atomicInteger == null) {
            return 0;
        }
        do {
            i2 = atomicInteger.get();
            if (i2 == 0) {
                return 0;
            }
            iMax = Math.max(0, i2 - i);
        } while (!atomicInteger.compareAndSet(i2, iMax));
        if (iMax == 0) {
            this.d.remove(obj, atomicInteger);
        }
        return i2;
    }

    @Override // com.android.tools.r8.internal.R0
    public final int c() {
        return this.d.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        this.d.clear();
    }

    @Override // com.android.tools.r8.internal.R0
    public final Iterator d() {
        throw new AssertionError("should never be called");
    }

    @Override // com.android.tools.r8.internal.R0
    public final Iterator e() {
        return new C2787uf(this, new C2701tf(this));
    }

    @Override // com.android.tools.r8.internal.R0, java.util.AbstractCollection, java.util.Collection
    public final boolean isEmpty() {
        return this.d.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new C1570gQ(this, entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        Iterator it = this.d.values().iterator();
        long j = 0;
        while (it.hasNext()) {
            j += (long) ((AtomicInteger) it.next()).get();
        }
        return MB.a(j);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray() {
        ArrayList arrayList = new ArrayList(AL.a(size()));
        for (AbstractC1314dQ abstractC1314dQ : entrySet()) {
            Object objB = abstractC1314dQ.b();
            for (int iA = abstractC1314dQ.a(); iA > 0; iA--) {
                arrayList.add(objB);
            }
        }
        return arrayList.toArray();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        ArrayList arrayList = new ArrayList(AL.a(size()));
        for (AbstractC1314dQ abstractC1314dQ : entrySet()) {
            Object objB = abstractC1314dQ.b();
            for (int iA = abstractC1314dQ.a(); iA > 0; iA--) {
                arrayList.add(objB);
            }
        }
        return arrayList.toArray(objArr);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(Object obj) {
        AtomicInteger atomicInteger = (AtomicInteger) AbstractC1739iN.a(this.d, obj);
        if (atomicInteger == null) {
            return 0;
        }
        return atomicInteger.get();
    }

    @Override // com.android.tools.r8.internal.R0
    public final Set b() {
        return new C2872vf(this);
    }

    @Override // com.android.tools.r8.internal.R0
    public final Set a() {
        return new C2615sf(this.d.keySet());
    }

    @Override // com.android.tools.r8.internal.R0, com.android.tools.r8.internal.InterfaceC1231cQ
    public final int a(Object obj) {
        int i;
        obj.getClass();
        AbstractC0871Ud.a(0, "count");
        AtomicInteger atomicInteger = (AtomicInteger) AbstractC1739iN.a(this.d, obj);
        if (atomicInteger == null) {
            return 0;
        }
        do {
            i = atomicInteger.get();
            if (i == 0) {
                return 0;
            }
        } while (!atomicInteger.compareAndSet(i, 0));
        this.d.remove(obj, atomicInteger);
        return i;
    }

    @Override // com.android.tools.r8.internal.R0, com.android.tools.r8.internal.InterfaceC1231cQ
    public final boolean a(int i, Object obj) {
        obj.getClass();
        AbstractC0871Ud.a(i, "oldCount");
        AbstractC0871Ud.a(0, "newCount");
        AtomicInteger atomicInteger = (AtomicInteger) AbstractC1739iN.a(this.d, obj);
        if (atomicInteger == null) {
            return i == 0;
        }
        int i2 = atomicInteger.get();
        if (i2 == i) {
            if (i2 == 0) {
                this.d.remove(obj, atomicInteger);
                return true;
            }
            if (atomicInteger.compareAndSet(i2, 0)) {
                this.d.remove(obj, atomicInteger);
                return true;
            }
        }
        return false;
    }
}
