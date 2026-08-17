package com.android.tools.r8.internal;

import com.android.tools.r8.synthesis.S;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.Wk, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC0930Wk {
    public static final EnumC0930Wk b = new EnumC0930Wk(0, "WRAPPER");
    public static final EnumC0930Wk c = new EnumC0930Wk(1, "VIVIFIED_WRAPPER");

    public EnumC0930Wk(int i, String str) {
        super(str, i);
    }

    public final com.android.tools.r8.synthesis.I a() {
        return this == b ? new com.android.tools.r8.synthesis.I() { // from class: pof
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.n;
            }
        } : new com.android.tools.r8.synthesis.I() { // from class: qof
            @Override // com.android.tools.r8.synthesis.I
            public final S.b a(S s) {
                return s.o;
            }
        };
    }
}
