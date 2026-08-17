package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC0890Uw;
import com.android.tools.r8.internal.C1041a8;
import com.android.tools.r8.internal.C1295d8;
import com.android.tools.r8.internal.Z7;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.internal.d8, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1295d8 {
    public static final C1295d8 b = new C1295d8(Collections.EMPTY_MAP);
    public final Map a;

    public C1295d8(Map map) {
        this.a = map;
    }

    public static a a() {
        return new a();
    }

    public static C1295d8 b() {
        return b;
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.d8$a */
    public static class a {
        public static final /* synthetic */ boolean b = true;
        public final IdentityHashMap a = new IdentityHashMap();

        public final C1295d8 a() {
            if (this.a.isEmpty()) {
                return C1295d8.b();
            }
            final IdentityHashMap identityHashMap = new IdentityHashMap(this.a.size());
            this.a.forEach(new BiConsumer() { // from class: ylg
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    C1295d8.a.a(identityHashMap, (AbstractC0890Uw) obj, (Z7) obj2);
                }
            });
            return new C1295d8(identityHashMap);
        }

        public final void a(AbstractC0890Uw abstractC0890Uw, Consumer consumer) {
            if (b || !this.a.containsKey(abstractC0890Uw)) {
                consumer.accept((Z7) this.a.computeIfAbsent(abstractC0890Uw, IM.a(new Supplier() { // from class: zlg
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return C1041a8.a();
                    }
                })));
            } else {
                x1f.a();
            }
        }

        public static void a(Map map, AbstractC0890Uw abstractC0890Uw, Z7 z7) {
            if (!Z7.c && z7.a.isEmpty() && !z7.b) {
                x1f.a();
            } else {
                map.put(abstractC0890Uw, new C1041a8(z7.a, z7.b));
            }
        }
    }
}
