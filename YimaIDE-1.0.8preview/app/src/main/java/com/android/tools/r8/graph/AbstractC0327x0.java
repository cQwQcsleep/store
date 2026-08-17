package com.android.tools.r8.graph;

import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.naming.C3313b;
import defpackage.iqi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.x0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0327x0 implements InterfaceC0189d1 {
    public static final /* synthetic */ boolean g = true;
    public final AbstractC0551Hu a;
    public final C3313b b;
    public final Ch0 c;
    public final C2752uB d;
    public final B1 e;
    public final C0341z0 f;

    public AbstractC0327x0(C3313b c3313b, C0341z0 c0341z0, AbstractC0551Hu abstractC0551Hu, C2752uB c2752uB, Ch0 ch0) {
        this.b = c3313b;
        this.f = c0341z0;
        this.a = abstractC0551Hu;
        this.d = c2752uB;
        this.e = c2752uB.a;
        this.c = ch0;
    }

    public static List a(ArrayList arrayList) {
        arrayList.sort(Comparator.comparing(new iqi()));
        return arrayList;
    }

    public abstract void a(Consumer consumer);

    public C0177b3 b() {
        throw new Kk0("Cannot use a LazyDexApplication where a DirectDexApplication is expected.");
    }

    public abstract void b(Consumer consumer);

    public abstract D2 c(I2 i2);

    public abstract AbstractC0320w0 c();

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public abstract E0 d(I2 i2);

    public Collection<D2> d() {
        Collection<D2> collectionH = h();
        if (g || C2752uB.V1) {
            return collectionH;
        }
        ArrayList arrayList = new ArrayList(collectionH);
        Collections.shuffle(arrayList);
        return AbstractC0551Hu.a(arrayList);
    }

    public Collection<D2> e() {
        Comparator comparatorComparing = Comparator.comparing(new iqi());
        if (this.d.u1.c) {
            comparatorComparing = comparatorComparing.reversed();
        }
        ArrayList arrayList = new ArrayList(h());
        arrayList.sort(comparatorComparing);
        return arrayList;
    }

    public final C0341z0 f() {
        return this.f;
    }

    public C3313b g() {
        return this.b;
    }

    public abstract Collection h();

    public abstract C0177b3 i();

    @Override // com.android.tools.r8.graph.InterfaceC0189d1
    public final B1 a() {
        return this.e;
    }

    public static C0283q4.a a(C2752uB c2752uB, Ch0 ch0) {
        return new C0283q4.a(c2752uB, ch0);
    }
}
