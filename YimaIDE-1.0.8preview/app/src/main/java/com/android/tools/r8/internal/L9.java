package com.android.tools.r8.internal;

import com.android.tools.r8.internal.L9;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class L9 extends AbstractC2526rd {
    public C2986wz c;
    public final /* synthetic */ N9 d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L9(N9 n9) {
        super(null);
        this.d = n9;
        this.c = null;
    }

    public static C2986wz d(String str) {
        return new C2986wz(16);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final XO a(int i, String str, String str2, String str3, String[] strArr) {
        return new M9(str + ";;" + str2, this.c);
    }

    @Override // com.android.tools.r8.internal.AbstractC2526rd
    public final void a(int i, int i2, String str, String str2, String str3, String[] strArr) {
        super.a(i, i2, str, str2, str3, strArr);
        this.c = (C2986wz) this.d.a.computeIfAbsent(str, new Function() { // from class: sl8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return L9.d((String) obj);
            }
        });
    }
}
