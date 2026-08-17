package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.QO;
import java.util.AbstractMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class SO {
    public static final /* synthetic */ boolean b = true;
    public final Map a;

    public SO(AbstractMap abstractMap) {
        if (b || abstractMap.values().stream().noneMatch(new Predicate() { // from class: coc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((QO) obj).d();
            }
        })) {
            this.a = abstractMap;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final /* synthetic */ QO a(Function function, Ch0 ch0, C0333y c0333y, Object obj, Object obj2, QO qo) {
        if (qo == null) {
            QO qo2 = (QO) function.apply(C1209c7.a);
            if (qo2.d()) {
                return null;
            }
            return qo2;
        }
        boolean z = b;
        if (!z && qo.d()) {
            x1f.a();
            return null;
        }
        ch0.a("Join temporary method state");
        QO qoA = qo.a(c0333y, b(obj), function, AbstractC1589ge0.b);
        if (z || !qoA.d()) {
            ch0.b();
            return qoA;
        }
        x1f.a();
        return null;
    }

    public abstract Object a(com.android.tools.r8.graph.B5 b5);

    public abstract com.android.tools.r8.graph.B2 b(Object obj);

    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final void b(final C0333y c0333y, final Object obj, final QO qo) {
        boolean zIsUnknown = qo.isUnknown();
        Map map = this.a;
        if (zIsUnknown) {
            map.put(obj, qo);
        } else {
            map.compute(obj, new BiFunction() { // from class: aoc
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj2, Object obj3) {
                    return this.b.a(qo, c0333y, obj, obj2, (QO) obj3);
                }
            });
        }
    }

    public final /* synthetic */ QO a(QO qo, C0333y c0333y, Object obj, Object obj2, QO qo2) {
        QO qoA;
        if (qo2 == null) {
            qoA = qo.a();
        } else {
            qoA = qo2.a(c0333y, b(obj), qo, AbstractC1589ge0.a);
        }
        if (b || !qoA.d()) {
            return qoA;
        }
        x1f.a();
        return null;
    }

    public final void a(final C0333y c0333y, final C0322w2 c0322w2, final Function function, final Ch0 ch0) {
        this.a.compute(c0322w2, new BiFunction() { // from class: znc
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return this.b.a(function, ch0, c0333y, c0322w2, obj, (QO) obj2);
            }
        });
    }

    public final void a(final C0333y c0333y, SO so) {
        so.a.forEach(new BiConsumer() { // from class: boc
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b(c0333y, obj, (QO) obj2);
            }
        });
    }

    public final void a(BiConsumer biConsumer) {
        this.a.forEach(biConsumer);
    }

    public QO a(Object obj) {
        return (QO) this.a.getOrDefault(obj, C1209c7.a);
    }

    public final void a(com.android.tools.r8.graph.B5 b5, QO qo) {
        Object objA = a(b5);
        boolean zD = qo.d();
        Map map = this.a;
        if (zD) {
            map.remove(objA);
        } else {
            map.put(objA, qo);
        }
    }
}
