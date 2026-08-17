package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uu, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2809uu extends C0629Ku {
    public C2809uu() {
        super(4);
    }

    @Override // com.android.tools.r8.internal.C0629Ku
    public final void a(Map.Entry entry) {
        super.a(entry.getKey(), entry.getValue());
    }

    @Override // com.android.tools.r8.internal.C0629Ku
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final AbstractC2895vu b() {
        int i = this.c;
        if (i == 0) {
            return O40.l;
        }
        int i2 = 0;
        boolean z = true;
        if (i == 1) {
            Map.Entry entry = this.b[0];
            Objects.requireNonNull(entry);
            Map.Entry entry2 = entry;
            return new Ac0(entry2.getKey(), entry2.getValue());
        }
        if (this.a != null) {
            if (this.d) {
                this.b = (Map.Entry[]) Arrays.copyOf(this.b, i);
            }
            Map.Entry[] entryArr = this.b;
            int i3 = this.c;
            Comparator comparator = this.a;
            Arrays.sort(entryArr, 0, i3, new D7(EnumC1144bN.b, comparator instanceof AbstractC2943wV ? (AbstractC2943wV) comparator : new C2870ve(comparator)));
        }
        this.d = true;
        int i4 = this.c;
        Map.Entry[] entryArr2 = this.b;
        O40 o40 = O40.l;
        DX.b(i4, entryArr2.length);
        int iA = AbstractC1189bt.a(i4, 1.2d);
        int i5 = iA - 1;
        C0784Qu[] c0784QuArr = new C0784Qu[iA];
        C0784Qu[] c0784QuArr2 = new C0784Qu[iA];
        Map.Entry[] entryArr3 = i4 == entryArr2.length ? entryArr2 : new C0784Qu[i4];
        int i6 = 0;
        int i7 = 0;
        while (i6 < i4) {
            Map.Entry entry3 = entryArr2[i6];
            Objects.requireNonNull(entry3);
            Map.Entry entry4 = entry3;
            Object key = entry4.getKey();
            Object value = entry4.getValue();
            AbstractC0871Ud.a(key, value);
            int iHashCode = key.hashCode();
            int iHashCode2 = value.hashCode();
            int iA2 = AbstractC1189bt.a(iHashCode) & i5;
            int iA3 = AbstractC1189bt.a(iHashCode2) & i5;
            int i8 = i2;
            C0784Qu c0784Qu = c0784QuArr[iA2];
            boolean z2 = z;
            C0784Qu c0784Qu2 = c0784QuArr2[iA3];
            try {
                T40.a(key, value, c0784Qu);
                int i9 = i6;
                int i10 = i8;
                C0784Qu c0784QuC = c0784Qu2;
                while (c0784QuC != null) {
                    C0784Qu[] c0784QuArr3 = c0784QuArr;
                    if (value.equals(c0784QuC.c)) {
                        throw AbstractC0706Nu.a("value", entry4, c0784QuC);
                    }
                    int i11 = i10 + 1;
                    C0784Qu[] c0784QuArr4 = c0784QuArr2;
                    if (i11 > 8) {
                        throw new Q40();
                    }
                    c0784QuC = c0784QuC.c();
                    c0784QuArr2 = c0784QuArr4;
                    i10 = i11;
                    c0784QuArr = c0784QuArr3;
                }
                C0784Qu[] c0784QuArr5 = c0784QuArr;
                C0784Qu[] c0784QuArr6 = c0784QuArr2;
                C0784Qu c0784QuA = (c0784Qu2 == null && c0784Qu == null) ? T40.a(entry4, key, value) : new C0732Ou(key, value, c0784Qu, c0784Qu2);
                c0784QuArr5[iA2] = c0784QuA;
                c0784QuArr6[iA3] = c0784QuA;
                entryArr3[i9] = c0784QuA;
                i7 += iHashCode ^ iHashCode2;
                i6 = i9 + 1;
                i2 = i8;
                z = z2;
                c0784QuArr2 = c0784QuArr6;
                c0784QuArr = c0784QuArr5;
            } catch (Q40 unused) {
                return WC.a(i4, entryArr2);
            }
        }
        return new O40(c0784QuArr, c0784QuArr2, entryArr3, i5, i7);
    }

    public final void a(Object obj, String str) {
        super.a(str, obj);
    }

    @Override // com.android.tools.r8.internal.C0629Ku
    public final C0629Ku a(Set set) {
        super.a(set);
        return this;
    }

    @Override // com.android.tools.r8.internal.C0629Ku
    public final AbstractC0706Nu a() {
        return b();
    }

    @Override // com.android.tools.r8.internal.C0629Ku
    public final C0629Ku a(Object obj, Object obj2) {
        super.a(obj, obj2);
        return this;
    }

    @Override // com.android.tools.r8.internal.C0629Ku
    public final C0629Ku a(Map map) {
        super.a(map.entrySet());
        return this;
    }
}
