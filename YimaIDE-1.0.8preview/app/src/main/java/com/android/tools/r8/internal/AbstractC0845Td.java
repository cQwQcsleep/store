package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0845Td;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0819Sd;
import com.android.tools.r8.internal.C1614gv;
import com.android.tools.r8.internal.C1700hv;
import com.android.tools.r8.internal.C1870jv;
import com.android.tools.r8.internal.K10;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Td, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0845Td {
    public static final Collector a;

    static {
        Collector.of(new Supplier() { // from class: e3e
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractC0551Hu.g();
            }
        }, new BiConsumer() { // from class: h3e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((C0473Eu) obj).b(obj2);
            }
        }, new BinaryOperator() { // from class: i3e
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((C0473Eu) obj).a((C0473Eu) obj2);
            }
        }, new Function() { // from class: j3e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0473Eu) obj).a();
            }
        }, new Collector.Characteristics[0]);
        a = Collector.of(new Supplier() { // from class: v2e
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractC2554rv.g();
            }
        }, new BiConsumer() { // from class: w2e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((C1870jv) obj).a(obj2);
            }
        }, new BinaryOperator() { // from class: x2e
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((C1870jv) obj).a((C1870jv) obj2);
            }
        }, new Function() { // from class: y2e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C1870jv) obj).a();
            }
        }, new Collector.Characteristics[0]);
        Collector.of(new Supplier() { // from class: z2e
            @Override // java.util.function.Supplier
            public final Object get() {
                return C1700hv.b();
            }
        }, new BiConsumer() { // from class: a3e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((C1614gv) obj).a((K10) obj2);
            }
        }, new BinaryOperator() { // from class: f3e
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((C1614gv) obj).a((C1614gv) obj2);
            }
        }, new Function() { // from class: g3e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C1614gv) obj).a();
            }
        }, new Collector.Characteristics[0]);
    }

    public static /* synthetic */ C0819Sd a() {
        return new C0819Sd();
    }

    public static Collector b() {
        return Collector.of(new Supplier() { // from class: u2e
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractC0845Td.a();
            }
        }, new BiConsumer() { // from class: b3e
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                ((C0819Sd) obj).a((Enum) obj2);
            }
        }, new BinaryOperator() { // from class: c3e
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return ((C0819Sd) obj).a((C0819Sd) obj2);
            }
        }, new Function() { // from class: d3e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0819Sd) obj).a();
            }
        }, Collector.Characteristics.UNORDERED);
    }
}
