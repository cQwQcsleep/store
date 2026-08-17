package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.cd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1246cd extends AbstractC1501fd {
    public final List b;

    public C1246cd(com.android.tools.r8.graph.V v, ArrayList arrayList) {
        super(v);
        this.b = arrayList;
    }

    @Override // com.android.tools.r8.internal.AbstractC1501fd
    public final Collection a() {
        Set setC = AbstractC2780ub0.c();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            setC.addAll(((AbstractC1501fd) it.next()).a());
        }
        return setC;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        String str = "combined(";
        for (AbstractC1501fd abstractC1501fd : this.b) {
            sb.append(str);
            sb.append(abstractC1501fd);
            str = ", ";
        }
        sb.append(")");
        return sb.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC1501fd
    public final void a(com.android.tools.r8.graph.I2 i2, Consumer consumer) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((AbstractC1501fd) it.next()).a(i2, consumer);
        }
    }
}
