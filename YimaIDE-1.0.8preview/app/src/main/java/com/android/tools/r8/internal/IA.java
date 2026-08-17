package com.android.tools.r8.internal;

import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.internal.IA;
import java.util.ArrayList;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class IA {
    public static final IA b = new IA(M20.a);
    public static final /* synthetic */ boolean c = true;
    public final J20 a;

    public IA(J20 j20) {
        if (c || j20 != null) {
            this.a = j20;
        } else {
            x1f.a();
            throw null;
        }
    }

    public boolean a(BiPredicate<com.android.tools.r8.graph.I2, Boolean> biPredicate) {
        BU it = this.a.g().iterator();
        while (it.hasNext()) {
            I20 i20 = (I20) it.next();
            if (biPredicate.test((com.android.tools.r8.graph.I2) i20.getKey(), Boolean.valueOf(i20.getBooleanValue()))) {
                return true;
            }
        }
        return false;
    }

    public final ArrayList b() {
        final ArrayList arrayList = new ArrayList(this.a.size());
        this.a.forEach(new BiConsumer() { // from class: xe6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                arrayList.add(new C1405eW((I2) obj, (Boolean) obj2));
            }
        });
        return arrayList;
    }

    public com.android.tools.r8.graph.I2 c() {
        if (this.a.size() != 1) {
            return null;
        }
        com.android.tools.r8.graph.I2 i2 = (com.android.tools.r8.graph.I2) this.a.keySet().iterator().next();
        if (this.a.a(i2)) {
            return i2;
        }
        return null;
    }

    public final boolean d() {
        return c() != null;
    }

    public int e() {
        return this.a.size();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof IA) {
            return this.a.equals(((IA) obj).a);
        }
        return false;
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public static class a {
        public final V20 a = new V20();

        public IA a() {
            return this.a.isEmpty() ? IA.b : new IA(this.a);
        }

        public a a(com.android.tools.r8.graph.I2 i2) {
            return a(i2, true);
        }

        public a a(com.android.tools.r8.graph.I2 i2, final boolean z) {
            this.a.compute(i2, new BiFunction() { // from class: ze6
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    Boolean bool = (Boolean) obj2;
                    return Boolean.valueOf((bool == null || bool.booleanValue()) && z);
                }
            });
            return this;
        }
    }

    public boolean b(com.android.tools.r8.graph.I2 i2) {
        return a(i2).d();
    }

    public static IA c(com.android.tools.r8.graph.I2 i2) {
        return new IA(new L20(i2));
    }

    public void a(BiConsumer<com.android.tools.r8.graph.I2, Boolean> biConsumer) {
        this.a.forEach(biConsumer);
    }

    public final void a(final Consumer consumer) {
        a(new BiConsumer() { // from class: ye6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                IA.a(consumer, (I2) obj, (Boolean) obj2);
            }
        });
    }

    public static /* synthetic */ void a(Consumer consumer, com.android.tools.r8.graph.I2 i2, Boolean bool) {
        if (bool.booleanValue()) {
            consumer.accept(i2);
        }
    }

    public static a a() {
        return new a();
    }

    public AbstractC2173nV a(com.android.tools.r8.graph.I2 i2) {
        AbstractC2392q1 abstractC2392q1 = (AbstractC2392q1) this.a;
        Boolean boolValueOf = abstractC2392q1.containsKey(i2) ? Boolean.valueOf(abstractC2392q1.a(i2)) : null;
        if (boolValueOf == null) {
            return AbstractC2173nV.b;
        }
        return boolValueOf.booleanValue() ? AbstractC2173nV.a : AbstractC2173nV.c;
    }
}
