package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ts, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0860Ts extends AbstractC1396eN implements R5, Serializable {
    public static final /* synthetic */ int j = 0;
    public transient C0627Ks[] b;
    public transient C0627Ks[] c;
    public transient C0627Ks d;
    public transient C0627Ks e;
    public transient int f;
    public transient int g;
    public transient int h;
    public transient C0756Ps i;

    public C0860Ts(int i) {
        AbstractC0871Ud.a(i, "expectedSize");
        int iA = AbstractC1189bt.a(i, 1.0d);
        this.b = new C0627Ks[iA];
        this.c = new C0627Ks[iA];
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = iA - 1;
        this.h = 0;
    }

    public static C0860Ts j() {
        return new C0860Ts(16);
    }

    public final void a(C0627Ks c0627Ks) {
        C0627Ks c0627Ks2;
        int i = c0627Ks.d & this.g;
        C0627Ks c0627Ks3 = null;
        C0627Ks c0627Ks4 = null;
        for (C0627Ks c0627Ks5 = this.b[i]; c0627Ks5 != c0627Ks; c0627Ks5 = c0627Ks5.f) {
            c0627Ks4 = c0627Ks5;
        }
        if (c0627Ks4 == null) {
            this.b[i] = c0627Ks.f;
        } else {
            c0627Ks4.f = c0627Ks.f;
        }
        int i2 = c0627Ks.e & this.g;
        C0627Ks c0627Ks6 = this.c[i2];
        while (true) {
            c0627Ks2 = c0627Ks3;
            c0627Ks3 = c0627Ks6;
            if (c0627Ks3 == c0627Ks) {
                break;
            } else {
                c0627Ks6 = c0627Ks3.g;
            }
        }
        if (c0627Ks2 == null) {
            this.c[i2] = c0627Ks.g;
        } else {
            c0627Ks2.g = c0627Ks.g;
        }
        C0627Ks c0627Ks7 = c0627Ks.i;
        C0627Ks c0627Ks8 = c0627Ks.h;
        if (c0627Ks7 == null) {
            this.d = c0627Ks8;
        } else {
            c0627Ks7.h = c0627Ks8;
        }
        C0627Ks c0627Ks9 = c0627Ks.h;
        if (c0627Ks9 == null) {
            this.e = c0627Ks7;
        } else {
            c0627Ks9.i = c0627Ks7;
        }
        this.f--;
        this.h++;
    }

    public final C0627Ks b(int i, Object obj) {
        for (C0627Ks c0627Ks = this.c[this.g & i]; c0627Ks != null; c0627Ks = c0627Ks.g) {
            if (i == c0627Ks.e && WU.a(obj, c0627Ks.c)) {
                return c0627Ks;
            }
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        this.f = 0;
        Arrays.fill(this.b, (Object) null);
        Arrays.fill(this.c, (Object) null);
        this.d = null;
        this.e = null;
        this.h++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        return a(AbstractC1189bt.a(obj), obj) != null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        return b(AbstractC1189bt.a(obj), obj) != null;
    }

    @Override // com.android.tools.r8.internal.AbstractC1396eN
    public final Iterator e() {
        return new C0601Js(this);
    }

    @Override // com.android.tools.r8.internal.R5
    public final R5 f() {
        C0756Ps c0756Ps = this.i;
        if (c0756Ps != null) {
            return c0756Ps;
        }
        C0756Ps c0756Ps2 = new C0756Ps(this);
        this.i = c0756Ps2;
        return c0756Ps2;
    }

    @Override // java.util.Map
    public final void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        for (C0627Ks c0627Ks = this.d; c0627Ks != null; c0627Ks = c0627Ks.h) {
            biConsumer.accept(c0627Ks.b, c0627Ks.c);
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        C0627Ks c0627KsA = a(AbstractC1189bt.a(obj), obj);
        if (c0627KsA == null) {
            return null;
        }
        return c0627KsA.getValue();
    }

    public final void k() {
        C0627Ks[] c0627KsArr = this.b;
        int i = this.f;
        int length = c0627KsArr.length;
        if (i <= ((double) length) * 1.0d || length >= 1073741824) {
            return;
        }
        int length2 = c0627KsArr.length * 2;
        this.b = new C0627Ks[length2];
        this.c = new C0627Ks[length2];
        this.g = length2 - 1;
        this.f = 0;
        for (C0627Ks c0627Ks = this.d; c0627Ks != null; c0627Ks = c0627Ks.h) {
            a(c0627Ks, c0627Ks);
        }
        this.h++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        return new C0834Ss(this);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        return a(obj, obj2, false);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        C0627Ks c0627KsA = a(AbstractC1189bt.a(obj), obj);
        if (c0627KsA == null) {
            return null;
        }
        a(c0627KsA);
        c0627KsA.i = null;
        c0627KsA.h = null;
        return c0627KsA.c;
    }

    @Override // java.util.Map
    public final void replaceAll(BiFunction biFunction) {
        biFunction.getClass();
        clear();
        for (C0627Ks c0627Ks = this.d; c0627Ks != null; c0627Ks = c0627Ks.h) {
            Object obj = c0627Ks.b;
            a(obj, biFunction.apply(obj, c0627Ks.c), false);
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC1396eN, java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.f;
    }

    @Override // java.util.AbstractMap, java.util.Map, com.android.tools.r8.internal.R5
    public final Set values() {
        return new C0730Os((C0756Ps) f());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        return f().keySet();
    }

    public static Object a(C0860Ts c0860Ts, Object obj, Object obj2, boolean z) {
        c0860Ts.getClass();
        int iA = AbstractC1189bt.a(obj);
        int iA2 = AbstractC1189bt.a(obj2);
        C0627Ks c0627KsB = c0860Ts.b(iA, obj);
        C0627Ks c0627KsA = c0860Ts.a(iA2, obj2);
        if (c0627KsB != null && iA2 == c0627KsB.d && WU.a(obj2, c0627KsB.b)) {
            return obj2;
        }
        if (c0627KsA != null && !z) {
            aca.a("key already present: ", obj2);
            return null;
        }
        if (c0627KsB != null) {
            c0860Ts.a(c0627KsB);
        }
        if (c0627KsA != null) {
            c0860Ts.a(c0627KsA);
        }
        c0860Ts.a(new C0627Ks(obj2, iA2, obj, iA), c0627KsA);
        if (c0627KsA != null) {
            c0627KsA.i = null;
            c0627KsA.h = null;
        }
        if (c0627KsB != null) {
            c0627KsB.i = null;
            c0627KsB.h = null;
        }
        c0860Ts.k();
        return AbstractC1739iN.a(c0627KsB);
    }

    public final void a(C0627Ks c0627Ks, C0627Ks c0627Ks2) {
        int i = c0627Ks.d;
        int i2 = this.g;
        int i3 = i & i2;
        C0627Ks[] c0627KsArr = this.b;
        c0627Ks.f = c0627KsArr[i3];
        c0627KsArr[i3] = c0627Ks;
        int i4 = c0627Ks.e & i2;
        C0627Ks[] c0627KsArr2 = this.c;
        c0627Ks.g = c0627KsArr2[i4];
        c0627KsArr2[i4] = c0627Ks;
        if (c0627Ks2 == null) {
            C0627Ks c0627Ks3 = this.e;
            c0627Ks.i = c0627Ks3;
            c0627Ks.h = null;
            if (c0627Ks3 == null) {
                this.d = c0627Ks;
            } else {
                c0627Ks3.h = c0627Ks;
            }
            this.e = c0627Ks;
        } else {
            C0627Ks c0627Ks4 = c0627Ks2.i;
            c0627Ks.i = c0627Ks4;
            if (c0627Ks4 == null) {
                this.d = c0627Ks;
            } else {
                c0627Ks4.h = c0627Ks;
            }
            C0627Ks c0627Ks5 = c0627Ks2.h;
            c0627Ks.h = c0627Ks5;
            if (c0627Ks5 == null) {
                this.e = c0627Ks;
            } else {
                c0627Ks5.i = c0627Ks;
            }
        }
        this.f++;
        this.h++;
    }

    public final C0627Ks a(int i, Object obj) {
        for (C0627Ks c0627Ks = this.b[this.g & i]; c0627Ks != null; c0627Ks = c0627Ks.f) {
            if (i == c0627Ks.d && WU.a(obj, c0627Ks.b)) {
                return c0627Ks;
            }
        }
        return null;
    }

    public final Object a(Object obj, Object obj2, boolean z) {
        int iA = AbstractC1189bt.a(obj);
        int iA2 = AbstractC1189bt.a(obj2);
        C0627Ks c0627KsA = a(iA, obj);
        if (c0627KsA != null && iA2 == c0627KsA.e && WU.a(obj2, c0627KsA.c)) {
            return obj2;
        }
        C0627Ks c0627KsB = b(iA2, obj2);
        if (c0627KsB != null) {
            if (z) {
                a(c0627KsB);
            } else {
                aca.a("value already present: ", obj2);
                return null;
            }
        }
        C0627Ks c0627Ks = new C0627Ks(obj, iA, obj2, iA2);
        if (c0627KsA != null) {
            a(c0627KsA);
            a(c0627Ks, c0627KsA);
            c0627KsA.i = null;
            c0627KsA.h = null;
            return c0627KsA.c;
        }
        a(c0627Ks, (C0627Ks) null);
        k();
        return null;
    }

    @Override // com.android.tools.r8.internal.R5
    public final Object a(Object obj, Object obj2) {
        return a(obj, obj2, true);
    }
}
