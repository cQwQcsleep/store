package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.Arrays;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2765uO extends AbstractC2205no {
    public static final C2765uO b = new C2765uO(false);
    public static final C2765uO c = new C2765uO(true);
    public final boolean a;

    public C2765uO(boolean z) {
        this.a = z;
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final boolean a(Object obj, Object obj2) {
        C0322w2 c0322w2 = (C0322w2) obj;
        C0322w2 c0322w3 = (C0322w2) obj2;
        if (this.a) {
            return c0322w2.i.f.equals(c0322w3.i.f);
        }
        return c0322w2.g.equals(c0322w3.g) && c0322w2.i.f.equals(c0322w3.i.f);
    }

    @Override // com.android.tools.r8.internal.AbstractC2205no
    public final int a(Object obj) {
        C0322w2 c0322w2 = (C0322w2) obj;
        if (this.a) {
            return Arrays.hashCode(c0322w2.i.f.b);
        }
        return (c0322w2.g.hashCode() * 31) + Arrays.hashCode(c0322w2.i.f.b);
    }
}
