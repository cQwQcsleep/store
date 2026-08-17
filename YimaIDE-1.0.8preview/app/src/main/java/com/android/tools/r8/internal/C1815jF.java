package com.android.tools.r8.internal;

import com.android.tools.r8.internal.AbstractC1560gF;
import com.android.tools.r8.internal.C1815jF;
import java.util.HashSet;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jF, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1815jF {
    public static final /* synthetic */ boolean c = true;
    public boolean a = false;
    public final HashSet b = new HashSet();

    public final C1815jF a(AbstractC2072mF abstractC2072mF) {
        if (abstractC2072mF instanceof C1986lF) {
            this.a = true;
            return this;
        }
        if (abstractC2072mF instanceof C1731iF) {
            this.a = true;
            this.b.addAll(((C1731iF) abstractC2072mF).b.b);
            return this;
        }
        if (c || (abstractC2072mF instanceof C1900kF)) {
            this.b.addAll(((C1900kF) abstractC2072mF).b);
            return this;
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ boolean a(AbstractC1560gF abstractC1560gF) {
        return abstractC1560gF instanceof TE;
    }

    public final void a() {
        if (c || this.b.stream().noneMatch(new Predicate() { // from class: rch
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C1815jF.a((AbstractC1560gF) obj);
            }
        })) {
            return;
        }
        x1f.a();
    }
}
