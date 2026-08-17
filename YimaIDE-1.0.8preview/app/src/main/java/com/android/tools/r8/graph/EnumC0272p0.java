package com.android.tools.r8.graph;

import com.android.tools.r8.graph.EnumC0272p0;
import java.util.function.Function;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.graph.p0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC0272p0 {
    public static final EnumC0272p0 b = new EnumC0272p0(0, "FIELD");
    public static final EnumC0272p0 c = new EnumC0272p0(1, "METHOD");
    public static final EnumC0272p0 d = new EnumC0272p0(2, "TYPE");
    public static final EnumC0272p0 e = new EnumC0272p0(3, "PARAMETER");

    public EnumC0272p0(int i, String str) {
        super(str, i);
    }

    public static EnumC0272p0 a(F2 f2) {
        return (EnumC0272p0) f2.a(new Function() { // from class: qzh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return EnumC0272p0.d;
            }
        }, new Function() { // from class: tzh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return EnumC0272p0.b;
            }
        }, new Function() { // from class: vzh
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return EnumC0272p0.c;
            }
        });
    }

    public static EnumC0272p0 a(InterfaceC0332x5 interfaceC0332x5) {
        return a(interfaceC0332x5.getReference());
    }
}
