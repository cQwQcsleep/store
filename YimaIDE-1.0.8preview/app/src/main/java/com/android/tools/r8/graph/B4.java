package com.android.tools.r8.graph;

import com.android.tools.r8.graph.InterfaceC0331x4;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class B4 {
    public static a a(Map map, List list, List list2, int i) {
        return new a(map, list, list2, i);
    }

    public abstract void a(Consumer consumer);

    public abstract void a(Consumer<? super InterfaceC0331x4> consumer, Consumer<? super C0324w4> consumer2);

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public a a() {
        return null;
    }

    public static class a extends B4 {
        public static final a e;
        public final Map a;
        public final List b;
        public final List c;
        public int d;

        static {
            IdentityHashMap identityHashMap = new IdentityHashMap();
            List list = Collections.EMPTY_LIST;
            e = new a(identityHashMap, list, list, 2);
        }

        public a(Map map, List list, List list2, int i) {
            this.a = map;
            this.b = list;
            this.c = list2;
            this.d = i;
        }

        public static A4 d() {
            return new A4();
        }

        @Override // com.android.tools.r8.graph.B4
        public void a(final Consumer<? super InterfaceC0331x4> consumer, Consumer<? super C0324w4> consumer2) {
            this.a.forEach(new BiConsumer() { // from class: jl0
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    consumer.accept((InterfaceC0331x4) obj2);
                }
            });
            this.b.forEach(consumer2);
        }

        @Override // com.android.tools.r8.graph.B4
        public final boolean c() {
            return true;
        }

        public boolean e() {
            return !this.b.isEmpty();
        }

        public boolean f() {
            return this.d == 1;
        }

        public boolean g() {
            return this.d == 2;
        }

        @Override // com.android.tools.r8.graph.B4
        public final a a() {
            return this;
        }

        @Override // com.android.tools.r8.graph.B4
        public final void a(Consumer consumer) {
            this.c.forEach(consumer);
        }

        public boolean a(C0231j1 c0231j1) {
            return this.a.containsKey(c0231j1.getReference());
        }
    }
}
