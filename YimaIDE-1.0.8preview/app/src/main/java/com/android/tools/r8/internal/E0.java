package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1254ch;
import com.android.tools.r8.internal.E0;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.ObjIntConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class E0 extends R0 implements Serializable {
    public final transient Map d;
    public transient long e;

    public E0(HashMap map) {
        if (map.isEmpty()) {
            this.d = map;
        } else {
            j2d.a();
            throw null;
        }
    }

    @Override // com.android.tools.r8.internal.R0, com.android.tools.r8.internal.InterfaceC1231cQ
    public final int a(Object obj, int i) {
        if (i == 0) {
            return b(obj);
        }
        int i2 = 0;
        if (!(i > 0)) {
            w01.a(Xf0.a("occurrences cannot be negative: %s", new Object[]{Integer.valueOf(i)}));
            return 0;
        }
        C1254ch c1254ch = (C1254ch) this.d.get(obj);
        if (c1254ch == null) {
            this.d.put(obj, new C1254ch(i));
        } else {
            int i3 = c1254ch.b;
            long j = ((long) i3) + ((long) i);
            if (!(j <= 2147483647L)) {
                w01.a(Xf0.a("too many occurrences: %s", new Object[]{Long.valueOf(j)}));
                return 0;
            }
            c1254ch.b = i3 + i;
            i2 = i3;
        }
        this.e += (long) i;
        return i2;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(int i, Object obj) {
        if (i == 0) {
            return b(obj);
        }
        if (!(i > 0)) {
            w01.a(Xf0.a("occurrences cannot be negative: %s", new Object[]{Integer.valueOf(i)}));
            return 0;
        }
        C1254ch c1254ch = (C1254ch) this.d.get(obj);
        if (c1254ch == null) {
            return 0;
        }
        int i2 = c1254ch.b;
        if (i2 <= i) {
            this.d.remove(obj);
            i = i2;
        }
        c1254ch.b += -i;
        this.e -= (long) i;
        return i2;
    }

    @Override // com.android.tools.r8.internal.R0
    public final int c() {
        return this.d.size();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final void clear() {
        Iterator it = this.d.values().iterator();
        while (it.hasNext()) {
            ((C1254ch) it.next()).b = 0;
        }
        this.d.clear();
        this.e = 0L;
    }

    @Override // com.android.tools.r8.internal.R0
    public final Iterator d() {
        return new A0(this, this.d.entrySet().iterator());
    }

    @Override // com.android.tools.r8.internal.R0
    public final Iterator e() {
        return new C0(this, this.d.entrySet().iterator());
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return new D0(this);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public final int size() {
        return MB.a(this.e);
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final int b(Object obj) {
        C1254ch c1254ch = (C1254ch) AbstractC1739iN.a(this.d, obj);
        if (c1254ch == null) {
            return 0;
        }
        return c1254ch.b;
    }

    public static void a(ObjIntConsumer objIntConsumer, Object obj, C1254ch c1254ch) {
        objIntConsumer.accept(obj, c1254ch.b);
    }

    @Override // com.android.tools.r8.internal.R0, com.android.tools.r8.internal.InterfaceC1231cQ
    public final int a(Object obj) {
        int i = 0;
        AbstractC0871Ud.a(0, "count");
        C1254ch c1254ch = (C1254ch) this.d.remove(obj);
        if (c1254ch != null) {
            int i2 = c1254ch.b;
            c1254ch.b = 0;
            i = i2;
        }
        this.e += (long) (0 - i);
        return i;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1231cQ
    public final void a(final ObjIntConsumer objIntConsumer) {
        objIntConsumer.getClass();
        this.d.forEach(new BiConsumer() { // from class: r14
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                E0.a(objIntConsumer, obj, (C1254ch) obj2);
            }
        });
    }
}
