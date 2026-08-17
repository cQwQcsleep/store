package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0230j0;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.InterfaceC0968Xw;
import com.android.tools.r8.ir.optimize.C3242a;
import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Xw, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface InterfaceC0968Xw extends InterfaceC0916Vw, ListIterator<AbstractC0890Uw>, InterfaceC1155bY {
    static {
        boolean z = AbstractC0942Ww.a;
    }

    H5 a(C0333y<?> c0333y, C0705Nt c0705Nt, C0705Nt c0705Nt2, ListIterator<H5> listIterator, Set<H5> set, com.android.tools.r8.graph.D2 d2);

    H5 a(C0705Nt c0705Nt, int i, ListIterator<H5> listIterator);

    H5 a(C0705Nt c0705Nt, L5 l5, C2752uB c2752uB, UnaryOperator<H5> unaryOperator);

    default H5 a(C0705Nt c0705Nt, ListIterator<H5> listIterator) {
        return a(c0705Nt, listIterator, hasPrevious() && i().g());
    }

    H5 a(C0705Nt c0705Nt, ListIterator<H5> listIterator, AbstractC0890Uw abstractC0890Uw, C2752uB c2752uB);

    H5 a(C0705Nt c0705Nt, ListIterator<H5> listIterator, boolean z);

    InterfaceC0968Xw a(C0705Nt c0705Nt, L5 l5, Collection<? extends AbstractC0890Uw> collection, C2752uB c2752uB);

    AbstractC1047aC a(C0333y<?> c0333y, C0705Nt c0705Nt, L5 l5, C2543rl0 c2543rl0, AbstractC2004lX abstractC2004lX);

    C2543rl0 a(C0333y<?> c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.H2 h2);

    C2543rl0 a(C0705Nt c0705Nt, C2752uB c2752uB, long j, AbstractC2624sj0 abstractC2624sj0);

    void a(C0333y<?> c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.H2 h2, C3242a c3242a);

    void a(C0333y<?> c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.I2 i2, C0230j0 c0230j0, C3242a c3242a);

    void a(C0333y<?> c0333y, C0705Nt c0705Nt, C0245l1 c0245l1, Set<C2543rl0> set);

    void a(C0333y<?> c0333y, C0705Nt c0705Nt, L5 l5, C2543rl0 c2543rl0, Set<H5> set, C3242a c3242a);

    void a(C0333y<?> c0333y, C0705Nt c0705Nt, ListIterator<H5> listIterator, Set<H5> set, C3242a c3242a);

    void a(C0333y<?> c0333y, C2543rl0 c2543rl0);

    void a(C0705Nt c0705Nt, int i);

    void a(AbstractC0890Uw abstractC0890Uw, Set<C2543rl0> set);

    void a(Collection<AbstractC0890Uw> collection);

    boolean a(C0333y<?> c0333y, com.android.tools.r8.graph.B5 b5);

    boolean a(C0333y<?> c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.I2 i2, Consumer<C0785Qv> consumer);

    default void b(AbstractC0890Uw abstractC0890Uw) {
        previous();
        add(abstractC0890Uw);
        AbstractC0890Uw abstractC0890UwPrevious = previous();
        if (AbstractC0942Ww.a || abstractC0890UwPrevious == abstractC0890Uw) {
            return;
        }
        x1f.a();
    }

    default void c(final AbstractC0890Uw abstractC0890Uw) {
        d(new Predicate() { // from class: u0g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InterfaceC0968Xw.b(abstractC0890Uw, (AbstractC0890Uw) obj);
            }
        });
    }

    default void d(AbstractC0890Uw abstractC0890Uw) {
        add(abstractC0890Uw);
        AbstractC0890Uw abstractC0890UwPrevious = previous();
        if (AbstractC0942Ww.a || abstractC0890UwPrevious == abstractC0890Uw) {
            return;
        }
        x1f.a();
    }

    default void e(AbstractC0890Uw abstractC0890Uw) {
        a(abstractC0890Uw, (Set<C2543rl0>) null);
    }

    void p();

    default void r() {
        throw new C1345dk0();
    }

    @Override // com.android.tools.r8.internal.InterfaceC0916Vw, java.util.Iterator, java.util.ListIterator
    void remove();

    default void c(C0705Nt c0705Nt) {
        a(c0705Nt, 1);
    }

    default AbstractC0890Uw d(Predicate predicate) {
        a(predicate);
        return previous();
    }

    static /* synthetic */ boolean b(AbstractC0890Uw abstractC0890Uw, AbstractC0890Uw abstractC0890Uw2) {
        return abstractC0890Uw2 == abstractC0890Uw;
    }

    static /* synthetic */ boolean a(AbstractC0890Uw abstractC0890Uw, AbstractC0890Uw abstractC0890Uw2) {
        return abstractC0890Uw2 == abstractC0890Uw;
    }

    default AbstractC0890Uw b(Predicate predicate) {
        c(predicate);
        return next();
    }

    default void a(AbstractC0890Uw[] abstractC0890UwArr) {
        for (AbstractC0890Uw abstractC0890Uw : abstractC0890UwArr) {
            add(abstractC0890Uw);
        }
    }

    default void b(C0705Nt c0705Nt) {
        a(c0705Nt, 0);
    }

    default InterfaceC0968Xw a(C0705Nt c0705Nt, L5 l5, AbstractC0890Uw[] abstractC0890UwArr, C2752uB c2752uB) {
        return a(c0705Nt, l5, Arrays.asList(abstractC0890UwArr), c2752uB);
    }

    default H5 b(C0705Nt c0705Nt, int i) {
        return a(c0705Nt, i, (ListIterator<H5>) null);
    }

    default void a(C2496rC c2496rC) {
        previous();
        add(c2496rC);
        next();
    }

    default C2543rl0 a(C0705Nt c0705Nt, C2752uB c2752uB) {
        return a(c0705Nt, c2752uB, 0L, AbstractC2624sj0.m());
    }

    default C2543rl0 a(C0705Nt c0705Nt, C2752uB c2752uB, int i) {
        return a(c0705Nt, c2752uB, i, AbstractC2624sj0.k());
    }

    default void a(final AbstractC0890Uw abstractC0890Uw) {
        b(new Predicate() { // from class: v0g
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InterfaceC0968Xw.a(abstractC0890Uw, (AbstractC0890Uw) obj);
            }
        });
    }

    default boolean a(C0333y c0333y, C0705Nt c0705Nt, com.android.tools.r8.graph.I2 i2) {
        return a((C0333y<?>) c0333y, c0705Nt, i2, C0822Sg.b());
    }

    default void a(C0705Nt c0705Nt, boolean z) {
        a(c0705Nt, Y6.a(z));
    }

    default H5 a(C0705Nt c0705Nt) {
        return a(c0705Nt, (ListIterator<H5>) null);
    }

    default H5 a(C0705Nt c0705Nt, L5 l5, C2752uB c2752uB) {
        return a(c0705Nt, l5, c2752uB, (UnaryOperator<H5>) null);
    }

    default H5 a(C0333y<?> c0333y, C0705Nt c0705Nt, C0705Nt c0705Nt2) {
        Set<H5> setC = AbstractC2780ub0.c();
        H5 h5A = a(c0333y, c0705Nt, c0705Nt2, (ListIterator<H5>) null, setC, (com.android.tools.r8.graph.D2) null);
        c0705Nt.b((Collection<H5>) setC);
        return h5A;
    }
}
