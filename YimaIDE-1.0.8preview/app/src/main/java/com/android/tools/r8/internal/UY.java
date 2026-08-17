package com.android.tools.r8.internal;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.InterfaceC0189d1;
import defpackage.fag;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class UY extends AbstractC0490Fl<com.android.tools.r8.graph.B5> {
    public static final SY d = new SY();
    public static final /* synthetic */ boolean e = true;

    public UY() {
    }

    public static UY c() {
        return new TY();
    }

    public static UY d() {
        return new RY();
    }

    public static UY k(int i) {
        return new TY(i);
    }

    public UY a(InterfaceC0189d1 interfaceC0189d1, AbstractC3148ys abstractC3148ys) {
        AbstractC3148ys abstractC3148ysG = AbstractC3148ys.g();
        TY ty = null;
        ArrayList arrayList = null;
        for (final com.android.tools.r8.graph.B5 b5 : this.b.values()) {
            com.android.tools.r8.graph.B5 b5A = b5.a(interfaceC0189d1, abstractC3148ys, abstractC3148ysG);
            if (b5A == null) {
                if (!e && !(abstractC3148ys instanceof C0907Vn)) {
                    x1f.a();
                    return null;
                }
                if (ty == null) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(b5);
                }
            } else if (b5A != b5) {
                if (ty == null) {
                    TY ty2 = new TY(this.b.size());
                    C1674he.a(this, (Consumer) new fag(ty2), new Predicate() { // from class: tye
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return b5.b((B5) obj);
                        }
                    });
                    if (arrayList != null) {
                        ty2.removeAll(arrayList);
                        arrayList = null;
                    }
                    ty = ty2;
                }
                ty.add(b5A);
            } else if (ty != null) {
                ty.add(b5A);
            }
        }
        if (ty == null) {
            if (arrayList != null) {
                removeAll(arrayList);
            }
            return this;
        }
        if (ty.b.size() < this.b.size()) {
            IdentityHashMap identityHashMap = new IdentityHashMap(ty.b.size());
            identityHashMap.putAll(ty.b);
            ty.b = identityHashMap;
        }
        return ty;
    }

    @Override // com.android.tools.r8.internal.AbstractC0490Fl
    public Map j(int i) {
        return a();
    }

    public UY(int i) {
        super(i);
    }

    public static UY a(InterfaceC0806Rq interfaceC0806Rq) {
        UY uyC = c();
        Objects.requireNonNull(uyC);
        interfaceC0806Rq.forEach(new fag(uyC));
        return uyC;
    }

    public final void a(com.android.tools.r8.graph.D2 d2, C0231j1 c0231j1) {
        add(new com.android.tools.r8.graph.B5(d2, c0231j1));
    }

    public static UY a(com.android.tools.r8.graph.B5 b5) {
        TY ty = new TY(1);
        ty.add(b5);
        return ty;
    }

    public final UY a(final com.android.tools.r8.graph.I5 i5) {
        removeIf(new Predicate() { // from class: uye
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return i5.a(((B5) obj).getReference());
            }
        });
        return this;
    }
}
