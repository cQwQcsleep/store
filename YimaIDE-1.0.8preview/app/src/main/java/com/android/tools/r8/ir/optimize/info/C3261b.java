package com.android.tools.r8.ir.optimize.info;

import com.android.tools.r8.internal.AbstractC0439Dm;
import com.android.tools.r8.internal.AbstractC2216nz;
import com.android.tools.r8.internal.AbstractC2238oA;
import com.android.tools.r8.internal.Ak0;
import com.android.tools.r8.internal.B1;
import com.android.tools.r8.internal.C0919Vz;
import com.android.tools.r8.internal.C1022Zy;
import com.android.tools.r8.internal.InterfaceC0943Wx;
import com.android.tools.r8.internal.InterfaceC2045lz;
import com.android.tools.r8.internal.InterfaceC2942wU;
import defpackage.a0c;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.ir.optimize.info.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3261b extends AbstractC3260a {
    public static final /* synthetic */ boolean d = true;
    public final int a;
    public final InterfaceC2045lz b;
    public final InterfaceC2045lz c;

    public C3261b(int i, InterfaceC2045lz interfaceC2045lz, InterfaceC2045lz interfaceC2045lz2) {
        boolean z = d;
        if (!z && i <= 0) {
            x1f.a();
            throw null;
        }
        if (!z && !interfaceC2045lz2.values().stream().noneMatch(new a0c())) {
            x1f.a();
            throw null;
        }
        if (!z && !interfaceC2045lz.values().stream().noneMatch(new Predicate() { // from class: qeg
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC0439Dm) obj).l();
            }
        })) {
            x1f.a();
            throw null;
        }
        this.a = i;
        Objects.requireNonNull(interfaceC2045lz);
        this.b = interfaceC2045lz;
        Objects.requireNonNull(interfaceC2045lz2);
        this.c = interfaceC2045lz2;
    }

    public final AbstractC3260a a(com.android.tools.r8.graph.proto.j jVar) {
        Collection collectionKeySet;
        if (!jVar.g()) {
            com.android.tools.r8.graph.proto.c cVar = jVar.b;
            if (!cVar.c()) {
                if (!d) {
                    int iA = com.android.tools.r8.graph.proto.c.a(Integer.MAX_VALUE, cVar.a);
                    if (iA == 0) {
                        collectionKeySet = AbstractC2238oA.a;
                    } else if (iA == cVar.a.size()) {
                        collectionKeySet = cVar.a.keySet();
                    } else {
                        C0919Vz c0919Vz = new C0919Vz(iA);
                        InterfaceC2942wU it = cVar.a.b().iterator();
                        while (it.hasNext()) {
                            InterfaceC0943Wx interfaceC0943Wx = (InterfaceC0943Wx) it.next();
                            if (((com.android.tools.r8.graph.proto.b) interfaceC0943Wx.getValue()).c()) {
                                c0919Vz.add(interfaceC0943Wx.a());
                            }
                        }
                        collectionKeySet = c0919Vz;
                    }
                    if (!collectionKeySet.stream().allMatch(new Predicate() { // from class: yeg
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return this.b.a((Integer) obj);
                        }
                    })) {
                        x1f.a();
                        return null;
                    }
                }
                int iA2 = this.a - com.android.tools.r8.graph.proto.c.a(Integer.MAX_VALUE, cVar.a);
                if (iA2 == 0) {
                    return E.a;
                }
                InterfaceC2045lz c1022Zy = new C1022Zy(iA2);
                InterfaceC2045lz c1022Zy2 = new C1022Zy(iA2);
                int i = 0;
                for (int i2 = 0; i2 < this.a; i2++) {
                    if (!cVar.a(i2).c()) {
                        com.android.tools.r8.graph.proto.k kVarB = cVar.a(i2).b();
                        if (kVarB == null || !kVarB.g().U0() || !kVarB.f().T0()) {
                            B1 b1 = (B1) this.c.getOrDefault(Integer.valueOf(i2), Ak0.a);
                            if (!b1.isUnknown()) {
                                c1022Zy.a(i, b1);
                            }
                            AbstractC0439Dm abstractC0439Dm = (AbstractC0439Dm) this.b.get(i2);
                            if (abstractC0439Dm != null) {
                                c1022Zy2.a(i, abstractC0439Dm);
                            }
                        }
                        i++;
                    }
                }
                int size = jVar.a.size() + iA2;
                if (c1022Zy.isEmpty() && c1022Zy2.isEmpty()) {
                    return E.a;
                }
                if (c1022Zy2.isEmpty()) {
                    c1022Zy2 = AbstractC2216nz.a;
                }
                if (c1022Zy.isEmpty()) {
                    c1022Zy = AbstractC2216nz.a;
                }
                return new C3261b(size, c1022Zy2, c1022Zy);
            }
            if (jVar.e()) {
                return new C3261b(jVar.a.size() + this.a, this.b, this.c);
            }
        }
        return this;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3260a
    public final AbstractC0439Dm b(int i) {
        if (d || (i >= 0 && i < this.a)) {
            return (AbstractC0439Dm) this.b.getOrDefault(Integer.valueOf(i), AbstractC0439Dm.m());
        }
        x1f.a();
        return null;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C3261b)) {
            return false;
        }
        C3261b c3261b = (C3261b) obj;
        return this.b.equals(c3261b.b) && this.c.equals(c3261b.c);
    }

    public final int hashCode() {
        return System.identityHashCode(this.c) + (System.identityHashCode(this.b) * 7);
    }

    public final String toString() {
        String str;
        String string = this.b.toString();
        if (this.c == null) {
            str = XmlPullParser.NO_NAMESPACE;
        } else {
            str = System.lineSeparator() + this.c;
        }
        return string + str;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3260a
    public final C3261b a() {
        return this;
    }

    public final /* synthetic */ boolean a(Integer num) {
        return num.intValue() < this.a;
    }

    @Override // com.android.tools.r8.ir.optimize.info.AbstractC3260a
    public final B1 a(int i) {
        if (d || (i >= 0 && i < this.a)) {
            return (B1) this.c.getOrDefault(Integer.valueOf(i), Ak0.a);
        }
        x1f.a();
        return null;
    }
}
