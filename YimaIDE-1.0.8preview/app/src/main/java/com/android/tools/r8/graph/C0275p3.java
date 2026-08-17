package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0288r3;
import com.android.tools.r8.graph.AbstractC0330x3;
import com.android.tools.r8.graph.C0275p3;
import com.android.tools.r8.graph.C0337y3;
import com.android.tools.r8.internal.AbstractC3179zC;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.EX;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.graph.p3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0275p3 {
    public static final /* synthetic */ boolean b = true;
    public AbstractC0330x3 a = null;

    public final void a(AbstractC0330x3 abstractC0330x3) {
        boolean z = b;
        if (this.a == null) {
            this.a = abstractC0330x3;
            return;
        }
        final C1975l7 c1975l7 = new C1975l7();
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        this.a.a(new Consumer() { // from class: xzh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                c1975l7.a((AbstractC0330x3.a) obj);
            }
        }, new Consumer() { // from class: yzh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((C0337y3) obj);
            }
        }, new Consumer() { // from class: zzh
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList2.add((AbstractC0288r3) obj);
            }
        });
        abstractC0330x3.a(new Consumer() { // from class: a0i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0275p3.a(c1975l7, (AbstractC0330x3.a) obj);
            }
        }, new Consumer() { // from class: b0i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0275p3.a(arrayList, (C0337y3) obj);
            }
        }, new Consumer() { // from class: c0i
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C0275p3.a(arrayList2, (AbstractC0288r3) obj);
            }
        });
        if (!c1975l7.b()) {
            if (arrayList.size() == 1 && arrayList2.isEmpty()) {
                this.a = (AbstractC0330x3) arrayList.get(0);
                return;
            } else if (arrayList.isEmpty() && arrayList2.size() == 1) {
                this.a = (AbstractC0330x3) arrayList2.get(0);
                return;
            } else {
                this.a = new C0309u3(arrayList, arrayList2);
                return;
            }
        }
        if (arrayList.isEmpty() && arrayList2.isEmpty()) {
            this.a = (AbstractC0330x3) c1975l7.a();
            return;
        }
        if (((AbstractC0330x3.a) c1975l7.a()).u()) {
            this.a = new C0316v3(((AbstractC0330x3.a) c1975l7.a()).m(), arrayList, arrayList2);
            return;
        }
        C0323w3 c0323w3K = ((AbstractC0330x3.a) c1975l7.a()).k();
        if (z || c0323w3K != null) {
            this.a = new C0295s3(c0323w3K, arrayList, arrayList2);
        } else {
            x1f.a();
        }
    }

    public static /* synthetic */ void a(C1975l7 c1975l7, AbstractC0330x3.a aVar) {
        if (c1975l7.b()) {
            if (b) {
                if (((AbstractC0330x3.a) c1975l7.a()).u()) {
                    return;
                }
            } else {
                x01.a("Unexpected multiple results between program and classpath");
                return;
            }
        }
        c1975l7.a(aVar);
    }

    public static /* synthetic */ void a(List list, final C0337y3 c0337y3) {
        if (AbstractC3179zC.b(list, new EX() { // from class: d0i
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return C0275p3.a(c0337y3, (C0337y3) obj);
            }
        })) {
            return;
        }
        list.add(c0337y3);
    }

    public static /* synthetic */ boolean a(C0337y3 c0337y3, C0337y3 c0337y4) {
        return c0337y4.d() == c0337y3.d();
    }

    public static /* synthetic */ void a(List list, final AbstractC0288r3 abstractC0288r3) {
        if (AbstractC3179zC.b(list, new EX() { // from class: e0i
            @Override // com.android.tools.r8.internal.EX
            public final boolean apply(Object obj) {
                return C0275p3.a(abstractC0288r3, (AbstractC0288r3) obj);
            }
        })) {
            return;
        }
        list.add(abstractC0288r3);
    }

    public static /* synthetic */ boolean a(AbstractC0288r3 abstractC0288r3, AbstractC0288r3 abstractC0288r4) {
        return abstractC0288r4.h() == abstractC0288r3.h();
    }
}
