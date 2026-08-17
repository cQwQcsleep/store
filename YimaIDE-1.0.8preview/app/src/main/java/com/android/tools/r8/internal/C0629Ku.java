package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Ku, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C0629Ku {
    public Comparator a;
    public Map.Entry[] b;
    public int c;
    public boolean d;

    public C0629Ku(int i) {
        this.b = new Map.Entry[i];
        this.c = 0;
        this.d = false;
    }

    public C0629Ku a(Set set) {
        if (set instanceof Collection) {
            int size = set.size() + this.c;
            Map.Entry[] entryArr = this.b;
            if (size > entryArr.length) {
                this.b = (Map.Entry[]) Arrays.copyOf(entryArr, AbstractC2981wu.a(entryArr.length, size));
                this.d = false;
            }
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            a((Map.Entry) it.next());
        }
        return this;
    }

    public AbstractC0706Nu b() {
        Map.Entry[] entryArr;
        int i = this.c;
        if (i == 0) {
            return T40.i;
        }
        if (i == 1) {
            Map.Entry entry = this.b[0];
            Objects.requireNonNull(entry);
            Map.Entry entry2 = entry;
            return new Ac0(entry2.getKey(), entry2.getValue());
        }
        if (this.a == null) {
            entryArr = this.b;
        } else {
            if (this.d) {
                this.b = (Map.Entry[]) Arrays.copyOf(this.b, i);
            }
            Map.Entry[] entryArr2 = this.b;
            Comparator comparator = this.a;
            Arrays.sort(entryArr2, 0, i, new D7(EnumC1144bN.b, comparator instanceof AbstractC2943wV ? (AbstractC2943wV) comparator : new C2870ve(comparator)));
            entryArr = entryArr2;
        }
        this.d = true;
        T40 t40 = T40.i;
        DX.b(i, entryArr.length);
        if (i == 0) {
            return T40.i;
        }
        try {
            return T40.a(i, entryArr);
        } catch (Q40 unused) {
            return XC.a(i, entryArr);
        }
    }

    public C0629Ku() {
        this(4);
    }

    public void a(Map.Entry entry) {
        a(entry.getKey(), entry.getValue());
    }

    public C0629Ku a(Map map) {
        return a(map.entrySet());
    }

    public C0629Ku a(Object obj, Object obj2) {
        int i = this.c + 1;
        Map.Entry[] entryArr = this.b;
        if (i > entryArr.length) {
            this.b = (Map.Entry[]) Arrays.copyOf(entryArr, AbstractC2981wu.a(entryArr.length, i));
            this.d = false;
        }
        C0784Qu c0784Qu = new C0784Qu(obj, obj2);
        Map.Entry[] entryArr2 = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        entryArr2[i2] = c0784Qu;
        return this;
    }

    public AbstractC0706Nu a() {
        return b();
    }
}
