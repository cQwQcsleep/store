package com.android.tools.r8.naming;

import com.android.tools.r8.MapIdEnvironment;
import com.android.tools.r8.MapIdProvider;
import com.android.tools.r8.internal.AbstractC1016Zs;
import com.android.tools.r8.internal.AbstractC1103at;
import com.android.tools.r8.internal.InterfaceC0990Ys;
import com.android.tools.r8.internal.InterfaceC1326db;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L0 implements InterfaceC1326db {
    public final InterfaceC0990Ys a;

    public L0() {
        int i = AbstractC1103at.a;
        this.a = AbstractC1016Zs.a.a();
    }

    @Override // com.android.tools.r8.internal.InterfaceC1326db
    public final InterfaceC1326db a(String str) {
        InterfaceC0990Ys interfaceC0990Ys = this.a;
        Charset charset = StandardCharsets.UTF_8;
        com.android.tools.r8.internal.E e = (com.android.tools.r8.internal.E) interfaceC0990Ys;
        e.getClass();
        e.a(str.toString().getBytes(charset));
        return this;
    }

    public static MapIdProvider a(MapIdProvider mapIdProvider) {
        return mapIdProvider != null ? mapIdProvider : new MapIdProvider() { // from class: ok8
            @Override // com.android.tools.r8.MapIdProvider
            public final String get(MapIdEnvironment mapIdEnvironment) {
                return mapIdEnvironment.getMapHash().substring(0, 7);
            }
        };
    }
}
