package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.AbstractC0287r2;
import com.android.tools.r8.internal.B4;
import com.android.tools.r8.internal.C4;
import com.android.tools.r8.internal.IM;
import com.android.tools.r8.internal.Q5;
import com.android.tools.r8.shaking.C3413k;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3413k {
    public final ConcurrentHashMap a = new ConcurrentHashMap();

    public final C3418l a() {
        return new C3418l(IM.a(new Q5() { // from class: afh
            @Override // com.android.tools.r8.internal.Q5
            public final void forEach(BiConsumer biConsumer) {
                this.b.a(biConsumer);
            }
        }, this.a.size()));
    }

    public final B4 a(AbstractC0287r2 abstractC0287r2) {
        return (B4) this.a.computeIfAbsent(abstractC0287r2, IM.a(new Supplier() { // from class: qeh
            @Override // java.util.function.Supplier
            public final Object get() {
                return C4.a();
            }
        }));
    }

    public final C3413k a(boolean z, Consumer consumer) {
        if (z) {
            consumer.accept(this);
        }
        return this;
    }

    public final /* synthetic */ void a(final BiConsumer biConsumer) {
        this.a.forEach(new BiConsumer() { // from class: ofh
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                C3413k.a(biConsumer, (AbstractC0287r2) obj, (B4) obj2);
            }
        });
    }

    public static void a(BiConsumer biConsumer, AbstractC0287r2 abstractC0287r2, B4 b4) {
        C4 c4A = C4.a(b4.a, b4.b, b4.c);
        if (c4A.b()) {
            return;
        }
        biConsumer.accept(abstractC0287r2, c4A);
    }
}
