package com.android.tools.r8.internal;

import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class T40 extends AbstractC0706Nu {
    public static final T40 i = new T40(AbstractC0706Nu.e, null, 0);
    public final transient Map.Entry[] f;
    public final transient C0784Qu[] g;
    public final transient int h;

    public T40(Map.Entry[] entryArr, C0784Qu[] c0784QuArr, int i2) {
        this.f = entryArr;
        this.g = c0784QuArr;
        this.h = i2;
    }

    public static AbstractC0706Nu a(int i2, Map.Entry[] entryArr) {
        Map.Entry[] entryArr2 = i2 == entryArr.length ? entryArr : new C0784Qu[i2];
        int iA = AbstractC1189bt.a(i2, 1.2d);
        C0784Qu[] c0784QuArr = new C0784Qu[iA];
        int i3 = iA - 1;
        for (int i4 = i2 - 1; i4 >= 0; i4--) {
            Map.Entry entry = entryArr[i4];
            Objects.requireNonNull(entry);
            Map.Entry entry2 = entry;
            Object key = entry2.getKey();
            Object value = entry2.getValue();
            AbstractC0871Ud.a(key, value);
            int iA2 = AbstractC1189bt.a(key.hashCode()) & i3;
            C0784Qu c0784Qu = c0784QuArr[iA2];
            a(key, value, c0784Qu);
            C0784Qu c0784QuA = c0784Qu == null ? a(entry2, key, value) : new C0758Pu(key, value, c0784Qu);
            c0784QuArr[iA2] = c0784QuA;
            entryArr2[i4] = c0784QuA;
        }
        return new T40(entryArr2, c0784QuArr, i3);
    }

    @Override // java.util.Map
    public final void forEach(BiConsumer biConsumer) {
        biConsumer.getClass();
        for (Map.Entry entry : this.f) {
            biConsumer.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu, java.util.Map
    public final Object get(Object obj) {
        C0784Qu[] c0784QuArr = this.g;
        int i2 = this.h;
        if (obj == null || c0784QuArr == null) {
            return null;
        }
        for (C0784Qu c0784QuB = c0784QuArr[i2 & AbstractC1189bt.a(obj.hashCode())]; c0784QuB != null; c0784QuB = c0784QuB.b()) {
            if (obj.equals(c0784QuB.b)) {
                return c0784QuB.c;
            }
        }
        return null;
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv i() {
        return new C0810Ru(this, this.f);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC2554rv j() {
        return new R40(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final AbstractC3066xu k() {
        return new S40(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC0706Nu
    public final boolean m() {
        return false;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f.length;
    }

    public static C0784Qu a(Object obj, Object obj2, C0784Qu c0784Qu) throws Q40 {
        int i2 = 0;
        while (c0784Qu != null) {
            if (c0784Qu.b.equals(obj)) {
                throw AbstractC0706Nu.a("key", c0784Qu, obj + "=" + obj2);
            }
            i2++;
            if (i2 <= 8) {
                c0784Qu = c0784Qu.b();
            } else {
                throw new Q40();
            }
        }
        return null;
    }

    public static C0784Qu a(Map.Entry entry, Object obj, Object obj2) {
        if (entry instanceof C0784Qu) {
            C0784Qu c0784Qu = (C0784Qu) entry;
            if (c0784Qu.d()) {
                return c0784Qu;
            }
        }
        return new C0784Qu(obj, obj2);
    }
}
