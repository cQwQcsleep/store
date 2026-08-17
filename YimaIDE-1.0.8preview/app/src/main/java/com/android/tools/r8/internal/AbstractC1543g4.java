package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0215h;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.internal.AbstractC1543g4;
import com.android.tools.r8.internal.C2140n4;
import com.android.tools.r8.profile.art.ArtProfileProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.g4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1543g4 implements Iterable {
    public static final /* synthetic */ boolean b = true;

    public static AbstractC1543g4 a(C0215h c0215h, C2752uB c2752uB) {
        C2226o4 c2226o4W = c2752uB.w();
        List<ArtProfileProvider> listA = c2226o4W.a();
        ArrayList arrayList = new ArrayList(Y6.a(c2226o4W.b()) + listA.size());
        for (ArtProfileProvider artProfileProvider : listA) {
            X3.a aVarA = X3.a(artProfileProvider, c2752uB);
            artProfileProvider.getArtProfile(aVarA);
            arrayList.add(aVarA.build());
        }
        if (c2226o4W.b()) {
            arrayList.add(a(c0215h));
        }
        if (c2226o4W.c && !b) {
            if (c2226o4W.g != null) {
                String strA = C2226o4.a(c0215h);
                if (!C2226o4.h && !strA.equals(c2226o4W.g)) {
                    x1f.a();
                    return null;
                }
            } else {
                c2226o4W.g = C2226o4.a(c0215h);
            }
        }
        return arrayList.isEmpty() ? C0672Mm.c : new MR(arrayList);
    }

    public abstract MR a();

    public abstract AbstractC1543g4 a(com.android.tools.r8.graph.I5 i5, Ch0 ch0);

    public abstract AbstractC1543g4 a(C0333y c0333y, AbstractC3148ys abstractC3148ys, Ch0 ch0);

    public abstract void a(C0333y c0333y);

    public abstract AbstractC1543g4 b(C0333y c0333y);

    public abstract boolean b();

    public abstract boolean isEmpty();

    public static X3 a(C0215h c0215h) {
        boolean z = X3.b;
        final X3.a aVar = new X3.a();
        for (com.android.tools.r8.graph.D2 d2 : c0215h.e()) {
            C1287d4 c1287d4 = new C1287d4();
            com.android.tools.r8.graph.I2 type = d2.getType();
            c1287d4.b = type;
            aVar.d.put(type, new C1371e4(type));
            d2.h(new Consumer() { // from class: axg
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    AbstractC1543g4.a(aVar, (C0231j1) obj);
                }
            });
        }
        return aVar.build();
    }

    public static void a(X3.a aVar, C0231j1 c0231j1) {
        C2055m4.a aVarD = C2055m4.d();
        aVarD.b = c0231j1.getReference();
        aVar.a(aVarD.a(new Consumer() { // from class: cxg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C2140n4.a) obj).b().d().c();
            }
        }).build());
    }
}
