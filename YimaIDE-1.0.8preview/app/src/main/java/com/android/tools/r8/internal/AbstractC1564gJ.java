package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.gJ, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC1564gJ {
    public static final /* synthetic */ boolean e = true;
    public final C0322w2 a;
    public final EnumC2326pC b;
    public final boolean c;
    public boolean d;

    public AbstractC1564gJ(C0322w2 c0322w2, EnumC2326pC enumC2326pC, boolean z) {
        if (!e && c0322w2 == null) {
            x1f.a();
            throw null;
        }
        this.a = c0322w2;
        this.b = enumC2326pC;
        this.c = z;
    }

    public abstract com.android.tools.r8.graph.B5 a(InterfaceC0832Sq interfaceC0832Sq, Consumer consumer);

    public C0322w2 a() {
        return this.a;
    }
}
