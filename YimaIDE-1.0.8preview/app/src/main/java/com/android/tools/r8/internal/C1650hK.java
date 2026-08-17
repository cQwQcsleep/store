package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.ir.optimize.C3242a;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.hK, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1650hK implements InterfaceC0968Xw {
    public static final /* synthetic */ boolean f = true;
    public final C0705Nt b;
    public H5 c;
    public K5 d;
    public final Set e;

    public C1650hK(C0705Nt c0705Nt, H5 h5, int i) {
        Set setC = AbstractC2780ub0.c();
        this.e = setC;
        this.b = c0705Nt;
        this.c = h5;
        this.d = h5.a(c0705Nt, i);
        setC.add(h5);
        if (i > 0) {
            previous();
            next();
        }
    }

    public static H5 a(H5 h5) {
        H5 h6;
        if (h5.s().size() != 1 || !a(h5.s().get(0), h5)) {
            return null;
        }
        H5 h7 = h5.s().get(0);
        while (true) {
            h6 = h7;
            if (h6.s().size() != 1 || !a(h6.s().get(0), h6) || !h6.G()) {
                break;
            }
            h7 = h6.s().get(0);
        }
        if (h6.G()) {
            return null;
        }
        return h6;
    }

    @Override // java.util.ListIterator
    public final void add(AbstractC0890Uw abstractC0890Uw) {
        this.d.add(abstractC0890Uw);
    }

    public final boolean b(H5 h5) {
        return this.e.contains(h5);
    }

    @Override // java.util.Iterator, java.util.ListIterator
    public final boolean hasNext() {
        return this.d.hasNext();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0916Vw
    public final boolean hasPrevious() {
        return this.d.hasPrevious() || a(this.c) != null;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0916Vw
    public final AbstractC0890Uw i() {
        AbstractC0890Uw abstractC0890UwI = this.d.i();
        if (abstractC0890UwI != null) {
            return abstractC0890UwI;
        }
        H5 h5A = a(this.c);
        if (h5A == null || h5A.f.size() < 2) {
            return null;
        }
        return h5A.k().get(h5A.f.size() - 2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0916Vw
    public final AbstractC0890Uw m() {
        AbstractC0890Uw abstractC0890UwM = this.d.m();
        if (abstractC0890UwM.K1()) {
            H5 h5L2 = abstractC0890UwM.Q().L2();
            if (a(this.c, h5L2)) {
                while (h5L2.G()) {
                    H5 h5A = C5.a(h5L2);
                    if (!a(h5L2, h5A)) {
                        break;
                    }
                    h5L2 = h5A;
                }
                return (AbstractC0890Uw) h5L2.f.get(0);
            }
        }
        return abstractC0890UwM;
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void p() {
        this.d.p();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.ListIterator
    public final AbstractC0890Uw previous() {
        H5 h5A;
        if (!this.d.hasPrevious() && (h5A = a(this.c)) != null) {
            this.c = h5A;
            this.e.add(h5A);
            H5 h5 = this.c;
            K5 k5A = h5.a(this.b, h5.k().size());
            this.d = k5A;
            k5A.previous();
            return this.d.previous();
        }
        return this.d.previous();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        throw new UnsupportedOperationException();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw, com.android.tools.r8.internal.InterfaceC0916Vw, java.util.Iterator, java.util.ListIterator
    public final void remove() {
        this.d.remove();
    }

    @Override // java.util.ListIterator
    public final void set(AbstractC0890Uw abstractC0890Uw) {
        this.d.set(abstractC0890Uw);
    }

    public C1650hK(C0705Nt c0705Nt, H5 h5) {
        this(c0705Nt, h5, 0);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final C2543rl0 a(C0705Nt c0705Nt, C2752uB c2752uB, long j, AbstractC2624sj0 abstractC2624sj0) {
        return this.d.a(c0705Nt, c2752uB, j, abstractC2624sj0);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final C2543rl0 a(C0333y c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.H2 h2) {
        return this.d.a((C0333y<?>) c0333y, c0705Nt, h2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final AbstractC1047aC a(C0333y c0333y, C0705Nt c0705Nt, L5 l5, C2543rl0 c2543rl0, AbstractC2004lX abstractC2004lX) {
        return this.d.a((C0333y<?>) c0333y, c0705Nt, l5, c2543rl0, abstractC2004lX);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final boolean a(C0333y c0333y, com.android.tools.r8.graph.B5 b5) {
        return this.d.a((C0333y<?>) c0333y, b5);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final boolean a(C0333y c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.I2 i2, Consumer consumer) {
        return this.d.a((C0333y<?>) c0333y, c0705Nt, i2, (Consumer<C0785Qv>) consumer);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(C0333y c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.I2 i2, C0230j0 c0230j0, C3242a c3242a) {
        this.d.a((C0333y<?>) c0333y, c0705Nt, i2, c0230j0, c3242a);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(C0705Nt c0705Nt, int i) {
        this.d.a(c0705Nt, i);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(C0333y c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.H2 h2, C3242a c3242a) {
        this.d.a((C0333y<?>) c0333y, c0705Nt, h2, c3242a);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(C0333y c0333y, C2543rl0 c2543rl0) {
        this.d.a((C0333y<?>) c0333y, c2543rl0);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(C0333y c0333y, C0705Nt c0705Nt, C0245l1 c0245l1, Set set) {
        this.d.a((C0333y<?>) c0333y, c0705Nt, c0245l1, (Set<C2543rl0>) set);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(C0333y c0333y, C0705Nt c0705Nt, L5 l5, C2543rl0 c2543rl0, Set set, C3242a c3242a) {
        this.d.a((C0333y<?>) c0333y, c0705Nt, l5, c2543rl0, (Set<H5>) set, c3242a);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(C0333y c0333y, C0705Nt c0705Nt, ListIterator listIterator, Set set, C3242a c3242a) {
        this.d.a((C0333y<?>) c0333y, c0705Nt, (ListIterator<H5>) listIterator, (Set<H5>) set, c3242a);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final H5 a(C0705Nt c0705Nt, ListIterator listIterator, boolean z) {
        return this.d.a(c0705Nt, (ListIterator<H5>) listIterator, z);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final H5 a(C0705Nt c0705Nt, int i, ListIterator listIterator) {
        return this.d.a(c0705Nt, i, (ListIterator<H5>) listIterator);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final H5 a(C0705Nt c0705Nt, L5 l5, C2752uB c2752uB, UnaryOperator unaryOperator) {
        return this.d.a(c0705Nt, l5, c2752uB, (UnaryOperator<H5>) unaryOperator);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final H5 a(C0333y c0333y, C0705Nt c0705Nt, C0705Nt c0705Nt2, ListIterator listIterator, Set set, com.android.tools.r8.graph.D2 d2) {
        return this.d.a((C0333y<?>) c0333y, c0705Nt, c0705Nt2, (ListIterator<H5>) listIterator, (Set<H5>) set, d2);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final InterfaceC0968Xw a(C0705Nt c0705Nt, L5 l5, Collection collection, C2752uB c2752uB) {
        return this.d.a(c0705Nt, l5, (Collection<? extends AbstractC0890Uw>) collection, c2752uB);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final H5 a(C0705Nt c0705Nt, ListIterator listIterator, AbstractC0890Uw abstractC0890Uw, C2752uB c2752uB) {
        return this.d.a(c0705Nt, (ListIterator<H5>) listIterator, abstractC0890Uw, c2752uB);
    }

    public static boolean a(H5 h5, H5 h6) {
        boolean z = f;
        if (!z && !h5.t().contains(h6)) {
            x1f.a();
            return false;
        }
        if (z || h6.s().contains(h5)) {
            C2636ss c2636ssQ = h5.h().Q();
            return c2636ssQ != null && c2636ssQ.L2() == h6 && h6.s().size() == 1;
        }
        x1f.a();
        return false;
    }

    @Override // java.util.Iterator, java.util.ListIterator
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final AbstractC0890Uw next() {
        AbstractC0890Uw next = this.d.next();
        if (next.K1()) {
            H5 h5L2 = next.Q().L2();
            if (a(this.c, h5L2)) {
                while (h5L2.G()) {
                    H5 h5A = C5.a(h5L2);
                    if (!a(h5L2, h5A)) {
                        break;
                    }
                    this.e.add(h5L2);
                    h5L2 = h5A;
                }
                this.c = h5L2;
                this.e.add(h5L2);
                K5 k5A = this.c.a(this.b);
                this.d = k5A;
                return k5A.next();
            }
        }
        return next;
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(AbstractC0890Uw abstractC0890Uw, Set set) {
        this.d.a(abstractC0890Uw, (Set<C2543rl0>) set);
    }

    @Override // com.android.tools.r8.internal.InterfaceC0968Xw
    public final void a(Collection collection) {
        this.d.a((Collection<AbstractC0890Uw>) collection);
    }
}
