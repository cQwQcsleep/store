package com.android.tools.r8.internal;

import java.util.EnumSet;
import java.util.stream.Collector;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Sd, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0819Sd {
    public static final Collector b = AbstractC0845Td.b();
    public EnumSet a;

    public final AbstractC2554rv a() {
        AbstractC2554rv c0395Bu;
        EnumSet enumSet = this.a;
        if (enumSet == null) {
            int i = AbstractC2554rv.c;
            return W40.j;
        }
        int i2 = C0395Bu.f;
        int size = enumSet.size();
        if (size != 0) {
            c0395Bu = size != 1 ? new C0395Bu(enumSet) : new Cc0((Enum) AbstractC3179zC.a(enumSet));
        } else {
            c0395Bu = W40.j;
        }
        this.a = null;
        return c0395Bu;
    }

    public final C0819Sd a(C0819Sd c0819Sd) {
        EnumSet enumSet = this.a;
        if (enumSet == null) {
            return c0819Sd;
        }
        EnumSet enumSet2 = c0819Sd.a;
        if (enumSet2 == null) {
            return this;
        }
        enumSet.addAll(enumSet2);
        return this;
    }

    public final void a(Enum r2) {
        EnumSet enumSet = this.a;
        if (enumSet == null) {
            this.a = EnumSet.of(r2);
        } else {
            enumSet.add(r2);
        }
    }
}
