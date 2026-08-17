package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Ah0;
import com.android.tools.r8.internal.C3048xh0;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Ah0 {
    public static final /* synthetic */ boolean e = true;
    public final C3048xh0 a;
    public final C3132yh0 b;
    public int c = 0;
    public C3048xh0 d = new C3048xh0("<zero>", false);

    public Ah0(String str, int i, Ch0 ch0) {
        this.a = (C3048xh0) ch0.b.peek();
        this.b = new C3132yh0(this, str, ch0.c, i);
    }

    public void a(Collection collection) {
        final boolean z = this.b.b;
        final ArrayDeque arrayDeque = new ArrayDeque();
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            Ch0 ch0 = (Ch0) it.next();
            if (ch0 != Ch0.a()) {
                if (!e && !ch0.b.isEmpty()) {
                    x01.a("Expected sub-timing to have completed prior to merge");
                    return;
                }
                this.c++;
                C3132yh0 c3132yh0 = this.b;
                long j = c3132yh0.d;
                C3048xh0 c3048xh0 = ch0.a;
                c3132yh0.d = j + c3048xh0.d;
                C3048xh0 c3048xh1 = this.d;
                if (c3048xh1 != null && c3048xh0.d > c3048xh1.d) {
                    this.d = c3048xh0;
                }
                arrayDeque.addLast(new C3218zh0(c3132yh0, c3048xh0));
            }
        }
        while (!arrayDeque.isEmpty()) {
            final C3218zh0 c3218zh0 = (C3218zh0) arrayDeque.pollFirst();
            c3218zh0.b.c.forEach(new BiConsumer() { // from class: jx
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    Ah0.a(c3218zh0, z, arrayDeque, (String) obj, (C3048xh0) obj2);
                }
            });
        }
    }

    public static /* synthetic */ void a(C3218zh0 c3218zh0, final boolean z, Deque deque, String str, C3048xh0 c3048xh0) {
        C3048xh0 c3048xh1 = (C3048xh0) c3218zh0.a.c.computeIfAbsent(str, new Function() { // from class: ix
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Ah0.a(z, (String) obj);
            }
        });
        c3048xh1.d += c3048xh0.d;
        c3048xh1.g = c3048xh0.g;
        if (c3048xh0.c.isEmpty()) {
            return;
        }
        deque.addLast(new C3218zh0(c3048xh1, c3048xh0));
    }

    public static /* synthetic */ C3048xh0 a(boolean z, String str) {
        return new C3048xh0(str, z);
    }

    public void a() {
        if (!e) {
            C3048xh0 c3048xh0 = this.a;
            String str = this.b.a;
            if (!Ch0.f && c3048xh0.c.containsKey(str)) {
                x01.a("Ambiguous timing chain. Insert a begin/end to fix");
                return;
            }
        }
        this.b.b();
        LinkedHashMap linkedHashMap = this.a.c;
        C3132yh0 c3132yh0 = this.b;
        linkedHashMap.put(c3132yh0.a, c3132yh0);
    }
}
